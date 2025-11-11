package com.hsf.laptopshop.service;

import com.hsf.laptopshop.config.PaymentConfig;
import com.hsf.laptopshop.config.VNpayConfig;
import com.hsf.laptopshop.dto.PaymentResponse;
import com.hsf.laptopshop.entity.InvoiceEntity;
import com.hsf.laptopshop.repository.InvoiceRepository;
import com.hsf.laptopshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService{

    private String vnp_SecretKey = PaymentConfig.secretKey;
    private String payUrl = PaymentConfig.vnp_payurl;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private VNpayConfig vnpayConfig;
    @Autowired
    private InvoiceService invoiceService;

    @Override
    public PaymentResponse createPaymentUrl(Long orderId, BigDecimal totalAmount) {
        var orderInfo = orderRepository.findById(orderId).
                orElseThrow(() -> new RuntimeException("Invoice not found"));
        Long amount = totalAmount.multiply(BigDecimal.valueOf(100)).longValue();

        InvoiceEntity invoice = invoiceRepository.findByOrder(orderInfo).orElse(null);

        if (invoice == null) {
            invoice = InvoiceEntity.builder()
                    .order(orderInfo)
                    .totalAmount(totalAmount)
                    .status("PENDING")
                    .build();
        } else {
            invoice.setTotalAmount(totalAmount);
            invoice.setStatus("PENDING");
        }
        invoiceRepository.save(invoice);

        Map<String, String> vnp_ParamsMap = vnpayConfig.getVnPayConfig();
        vnp_ParamsMap.put("vnp_Amount", String.valueOf(amount));
        vnp_ParamsMap.put("vnp_IpAddr", "127.0.0.1");
        vnp_ParamsMap.put("vnp_TxnRef", invoice.getInvoiceId().toString());
        vnp_ParamsMap.put("vnp_OrderInfo", "Pay order " + orderInfo.getOrderId());
        vnp_ParamsMap.put("vnp_OrderType", "billpayment");
        vnp_ParamsMap.put("vnp_Locale", "vn");
        vnp_ParamsMap.put("vnp_ReturnUrl", PaymentConfig.vnp_returnurl);

        String queryUrl = PaymentConfig.getPaymentUrl(vnp_ParamsMap, true);
        String hashdata = PaymentConfig.getPaymentUrl(vnp_ParamsMap, false);
        String vnp_SecureHash = PaymentConfig.hmacSHA512(vnp_SecretKey, hashdata);
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = payUrl + "?" + queryUrl;

        return PaymentResponse.builder()
                .orderId(orderId)
                .invoiceId(invoice.getInvoiceId())
                .amount(totalAmount)
                .method("online")
                .message("create VnpayUrl successfully")
                .paymentUrl(paymentUrl)
                .paymentType("VNPAY")
                .build();
    }

    @Override
    public PaymentResponse handlePaymentCallback(Map<String, String> allParams) {
        String responseCode = allParams.get("vnp_ResponseCode");
        String orderId = allParams.get("vnp_OrderInfo").replaceAll("\\D+", "");
        BigDecimal amount = BigDecimal.valueOf(Long.parseLong(allParams.get("vnp_Amount"))).divide(BigDecimal.valueOf(100));
        String vnp_TxnRef = allParams.get("vnp_TxnRef");
        Long invoiceId = Long.parseLong(vnp_TxnRef);
        InvoiceEntity invoice = invoiceRepository.findById(invoiceId).
                orElseThrow(() -> new RuntimeException("Payment not found with id: " + vnp_TxnRef));
        if(responseCode.equals("00")) {
            invoiceService.MarkInvoiceAsPaid(invoiceId, Long.parseLong(orderId), amount);
        }else {
            System.out.println("Payment failed with response code: " + responseCode);
        }
        return PaymentResponse.builder()
                .orderId(Long.parseLong(orderId))
                .invoiceId(invoiceId)
                .amount(amount)
                .method("online")
                .message("00".equals(responseCode) ? "Payment successfully" : "Payment failed with response: " + responseCode)
                .paymentType("VNPAY")
                .paymentDate(LocalDateTime.now())
                .build();
    }

}
