app.controller('statistic-ctrl', function($scope, $http) {
    const urlDate = "http://localhost:8080/api/order/date";
    const urlTopPro = "http://localhost:8080/api/product/getTopProduct";
    const urlTopOrder = "http://localhost:8080/api/order/getTopOrder";


    $scope.monthOrder = [];
    $scope.products = [];
    $scope.orders = [];
    $scope.cate = "products";

    //Load tháng order
    $http.get(urlDate).then(function(response) {
        $scope.monthOrder = response.data;
        $scope.month = $scope.monthOrder[0].orderDate;
    })

    //Load Sản phẩm
    $http.get(`${urlTopPro}/${$scope.month}`).then(resp => {
        $scope.products = resp.data;
    });

    //Load order
    $http.get(`${urlTopOrder}/${$scope.month}`).then(resp => {
        $scope.orders = resp.data;
    });

    //Load lại sản phẩm
    $scope.loadTopProduct = function () {
        $http.get(`${urlTopPro}/${$scope.month}`).then(resp => {
            $scope.products = resp.data;
        });
    }

    //Load lại order
    $scope.loadTopOrder = function () {
        $http.get(`${urlTopOrder}/${$scope.month}`).then(resp => {
            $scope.orders = resp.data;
        });
    }

    $scope.reloadTable = function () {
        if($scope.cate == 'products'){
            $scope.loadTopProduct();
        }else{
            $scope.loadTopOrder();
        }
    }
})