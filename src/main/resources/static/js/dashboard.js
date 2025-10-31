document.addEventListener('DOMContentLoaded', () => {

    // --- LẤY ELEMENT TỪ DOM ---
    const statsCardsContainer = document.getElementById('stats-cards');
    const recentOrdersList = document.getElementById('recent-orders-list');
    const salesChartCanvas = document.getElementById('salesChart');
    const filterButton = document.getElementById('filterButton');
    const topProductsBody = document.getElementById('top-products-body');

    let mySalesChart = null;

    // --- 1. DỮ LIỆU CỨNG (HARDCODED DATA) ---
    // (Chúng ta sẽ dùng dữ liệu này)

    // Dữ liệu cho các Card
    const staticStatsData = {
        totalProducts: 150,
        totalUsers: 58,
        totalOrders: 42
    };

    // Dữ liệu cho Đơn hàng gần đây
    const staticRecentOrders = [
        { orderId: 1001, customerName: 'Nguyễn Văn A', totalAmount: 25000000, createdAt: new Date() },
        { orderId: 1002, customerName: 'Trần Thị B', totalAmount: 32500000, createdAt: new Date() },
        { orderId: 1003, customerName: 'Lê Văn C', totalAmount: 18990000, createdAt: new Date() }
    ];

    // Dữ liệu cho Biểu đồ
    const staticRevenueChartData = [
        { month: 6, revenue: 120000000 },
        { month: 7, revenue: 180000000 },
        { month: 8, revenue: 150000000 },
        { month: 9, revenue: 210000000 },
        { month: 10, revenue: 190000000 }
    ];

    // Dữ liệu cứng cho Top Sản Phẩm
    const staticTopLaptops = [
        { name: 'MacBook Pro 14 M3', image: 'https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/g/r/group_560_1_.png', sold: 135 },
        { name: 'Dell XPS 13 Plus 9320', image: 'https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/t/e/text_ng_n_17__5.png', sold: 120 },
        { name: 'Asus TUF Gaming F15', image: 'https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/g/r/group_509_4__1.png', sold: 98 },
        { name: 'Lenovo Legion 5', image: 'https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/l/a/laptop-lenovo-scs_6__2.png', sold: 95 },
        { name: 'Acer Nitro 5 Gaming', image: 'https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/t/e/text_d_i_7_4.png', sold: 80 }
    ];


    // Tạm thời vô hiệu hóa nút lọc
    if (filterButton) {
        filterButton.disabled = true;
        filterButton.style.opacity = 0.5;
        filterButton.style.cursor = 'not-allowed';
    }

    // --- CÁC HÀM RENDER ---

    // 1. Render các Card thống kê
    function renderStatsCards(data) {
        if (!statsCardsContainer) return;
        const statsData = [
            { title: "Tổng Sản Phẩm", value: data.totalProducts, icon: '<svg class="w-6 h-6 text-yellow-500" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 11V7a4 4 0 00-8 0v4M5 9h14l1 12H4L5 9z"></path></svg>' },
            { title: "Tổng Người Dùng", value: data.totalUsers, icon: '<svg class="w-6 h-6 text-blue-500" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 4.354a4 4 0 110 5.292M15 21H3v-1a6 6 0 0112 0v1zm0 0h6v-1a6 6 0 00-9-5.197M15 21a6 6 0 00-9-5.197m0 0A5.975 5.975 0 0112 13a5.975 5.975 0 013-1.197"></path></svg>' },
            { title: "Tổng Đơn Hàng", value: data.totalOrders, icon: '<svg class="w-6 h-6 text-green-500" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2m-3 7h3m-3 4h3m-6-4h.01M9 16h.01"></path></svg>' },
        ];
        let cardsHTML = '';
        statsData.forEach(stat => {
            cardsHTML += `
                <div class="bg-white p-6 rounded-xl border border-gray-200 shadow-sm">
                    <div class="flex items-center justify-between">
                        <div>
                            <p class="text-sm font-medium text-gray-500">${stat.title}</p>
                            <p class="text-2xl font-bold text-gray-800">${stat.value}</p>
                        </div>
                        <div class="p-3 rounded-full bg-blue-100">
                           ${stat.icon}
                        </div>
                    </div>
                </div>
            `;
        });
        statsCardsContainer.innerHTML = cardsHTML;
    }

    // 2. Render Đơn hàng gần đây
    function renderRecentOrders(orders) {
        if (!recentOrdersList) return;
        const formatter = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' });
        let ordersHTML = '';
        if (!orders || orders.length === 0) {
            ordersHTML = '<li class="text-gray-500">Không có đơn hàng mới.</li>';
            recentOrdersList.innerHTML = ordersHTML;
            return;
        }
        orders.forEach(order => {
            ordersHTML += `
                <li class="flex items-center justify-between py-2">
                    <div class="flex items-center">
                        <img class="h-10 w-10 rounded-full object-cover" src="https://i.pravatar.cc/150?u=${order.customerName}" alt="${order.customerName}" />
                        <div class="ml-3">
                            <p class="text-sm font-semibold text-gray-800">${order.customerName || 'N/A'}</p>
                            <p class="text-xs text-gray-500">#${order.orderId}</p>
                        </div>
                    </div>
                    <div class='text-right'>
                        <p class="text-sm font-medium text-gray-800">${formatter.format(order.totalAmount || 0)}</p>
                        <span class="text-xs text-gray-500">${new Date(order.createdAt).toLocaleDateString('vi-VN')}</span>
                    </div>
                </li>
            `;
        });
        recentOrdersList.innerHTML = ordersHTML;
    }

    // 3. Render Biểu đồ doanh số
    function renderSalesChart(chartData) {
        if (!salesChartCanvas || !chartData) {
            console.error("Không tìm thấy canvas 'salesChart' hoặc không có dữ liệu");
            return;
        }
        const ctx = salesChartCanvas.getContext('2d');
        if (!ctx) {
            console.error("Không thể lấy context 2d từ canvas");
            return;
        }
        const labels = chartData.map(item => `Tháng ${item.month}`);
        const revenues = chartData.map(item => item.revenue);
        if (mySalesChart) {
            mySalesChart.destroy(); // Xóa biểu đồ cũ
        }
        mySalesChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: labels,
                datasets: [{
                    label: 'Doanh Thu (VND)',
                    data: revenues,
                    borderColor: 'rgb(79, 70, 229)',
                    backgroundColor: 'rgba(79, 70, 229, 0.1)',
                    fill: true,
                    tension: 0.4,
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                scales: { y: { beginAtZero: true, ticks: { callback: function(value) { return (Number(value) / 1000000).toLocaleString('vi-VN') + ' tr'; } } } },
                plugins: { legend: { display: true } }
            }
        });
    }

    // 4. Render Top Sản Phẩm
    function renderTopProducts(products) {
        if (!topProductsBody) return;
        topProductsBody.innerHTML = ''; // Xóa nội dung cũ
        if (!products || products.length === 0) {
            topProductsBody.innerHTML = '<tr><td colspan="2" class="text-gray-500">Không có dữ liệu.</td></tr>';
            return;
        }
        products.forEach(product => {
            const rowHTML = `
                <tr class="border-b border-gray-200">
                    <td class="py-3">
                        <div class="flex items-center">
                            <img src="${product.image}" alt="${product.name}" class="product-thumbnail">
                            <span class="text-sm font-medium text-gray-800">${product.name}</span>
                        </div>
                    </td>
                    <td class="py-3 text-sm text-gray-700 text-right">${product.sold}</td>
                </tr>
            `;
            topProductsBody.innerHTML += rowHTML;
        });
    }

    // --- 5. GỌI TẤT CẢ CÁC HÀM RENDER ---
    renderStatsCards(staticStatsData);
    renderRecentOrders(staticRecentOrders);
    renderTopProducts(staticTopLaptops);
    renderSalesChart(staticRevenueChartData);

});