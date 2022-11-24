const app = angular.module("myApp", []);
app.constant("HOST", "http://localhost:8080");
app.config(function ($httpProvider, $qProvider) {
    // $httpProvider.interceptors.push("authInterceptor");
});
app.run(function ($http, $rootScope, HOST) {
    $http.get(`${HOST}/auth/authentication`).then(resp => {
        if (resp.data) {
            $auth = $rootScope.$auth = resp.data;
            $http.defaults.headers.common["Authorization"] = $auth.token;
            localStorage.setItem("customerID", JSON.stringify($auth.customer.id));
        }
    });
});
app.controller("myCtrl", function ($scope, $http, HOST) {
    let sweetalert = function (text) {
        Swal.fire({
            icon: "success",
            title: text,
            showConfirmButton: false,
            timer: 2000,
        });
    }

    // Quan ly gio hang
    $scope.cart = {
        items: [],
        // Them sp vao gio hang
        add(id) {
            let item = this.items.find(item => item.id == id);
            if (item) {
                item.qty++;
                this.saveToLocalStorage();
            }
            else {
                $http.get(`${HOST}/api/product/${id}`).then(resp => {
                    resp.data.qty = 1;
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                })
            }
        },
        // Xoa sp khoi gio hang
        remove(id) {
            let index = this.items.findIndex(item => item.id == id);
            this.items.splice(index, 1);// xoa phan tu
            this.saveToLocalStorage();
        },
        // Xoa sach sp trong gio hang
        clear() {
            this.items = [];
            this.saveToLocalStorage();
        },
        // Tinh thanh tien cua 1 sp
        amt_of(item) { },
        // Tinh tong so luong cac mat hang trong gio
        get count() {
            return this.items
                .map(item => item.qty)
                .reduce((total, qty) => total += qty, 0);
        },
        // Tong thanh tien cac mat hang trong gio
        get amount() {
            return this.items
                .map(item => item.qty * item.listPrice)
                .reduce((total, qty) => total += qty, 0);
        },
        // Luu gio hang vao localstorage
        saveToLocalStorage() {
            let json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart", json);
        },
        // Doc gio hang vao local storage
        loadFromLocalStorage() {
            let json = localStorage.getItem("cart");
            this.items = json ? JSON.parse(json) : [];
        },
    }
    // Luu gio hang
    $scope.cart.loadFromLocalStorage();
    $scope.order = {
        status: 1,
        staff: { id: 1},
        store: { id: 1},
        orderDate: moment(new Date()).format("yyyy-MM-DD HH:mm:ss"),
        customer: { id: localStorage.getItem("customerID") },
        get orderDetails() {
            return $scope.cart.items.map(item => {
                return {
                    product: { id: item.id },
                    listPrice: item.listPrice,
                    quantity: item.qty,
                    discount: item.discount,
                    description: item.description,
                }
            });
        },
        purchase() {
            let order = angular.copy(this);
            // Thuc hien dat hang
            $http.post(HOST + "/api/order", order).then(resp => {
                sweetalert("Đặt hàng thành công!");
                $scope.cart.clear();// xaa trong gio hang
                location.href = "/order/detail/" + resp.data.id; // chuyen den trang chi tiet don hang
            }).catch(error => {
                sweetalert("Đặt hàng lỗi!");
                console.log("Error", error);
            })
        }
    }

})