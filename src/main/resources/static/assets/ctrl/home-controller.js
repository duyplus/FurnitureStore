const app = angular.module("myApp", []);
app.controller("myCtrl", function ($scope, $http) {
    var url = "http://localhost:8080/api/product";
    var url1 = "http://localhost:8080/api/order";

    var sweetalert = function (text) {
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
            var item = this.items.find(item => item.id == id);
            if (item) {
                item.qty++;
                this.saveToLocalStorage();
            }
            else {
                $http.get(`${url}/${id}`).then(resp => {
                    resp.data.qty = 1;
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                })
            }
        },
        // Xoa sp khoi gio hang
        remove(id) {
            var index = this.items.findIndex(item => item.id == id);
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
            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart", json);
        },
        // Doc gio hang vao local storage
        loadFromLocalStorage() {
            var json = localStorage.getItem("cart");
            this.items = json ? JSON.parse(json) : [];
        },
    }
    // Lưu giỏ hàng
    $scope.cart.loadFromLocalStorage();
    $scope.order = {
        createDate: new Date(),
        address: "",
        account: { username: $("#username").text() },
        get orderDetails() {
            return $scope.cart.items.map(item => {
                return {
                    product: { id: item.id },
                    price: item.listPrice,
                    quantity: item.qty
                }
            });
        },
        purchase() {
            var order = angular.copy(this);
            // Thực hiện đặt hàng
            $http.post(url1, order).then(resp => {
                sweetalert("Đặt hàng thành công!");
                $scope.cart.clear();// xóa
                location.href = "/order/detail/" + resp.data.id; // chuyển đến tảng chi tiết dơn hàng
            }).catch(error => {
                sweetalert("Đặt hàng lỗi!");
                console.log("Error", error);
            })
        }
    }
})