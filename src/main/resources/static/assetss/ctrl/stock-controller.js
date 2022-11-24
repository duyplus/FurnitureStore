app.controller('stock-ctrl', function($scope, $http, $location, stockService) {
    const url = "http://localhost:8080/api/stock";
    const urlStore = "http://localhost:8080/api/store";
    const urlProduct = "http://localhost:8080/api/product";

    $scope.items = [];
    $scope.stores = [];
    $scope.products = [];
    $scope.item = stockService.get();
    console.log($scope.item)

    $http.get(url).then(function(response) {
        $scope.items = response.data;
    })
    $http.get(urlStore).then(function(response) {
        $scope.stores = response.data;
    })
    $http.get(urlProduct).then(function(response) {
        $scope.products = response.data;
    })

    $scope.reset = () =>{
        $scope.item = {};
        stockService.set($scope.item)
    }

    $scope.edit = (item) => {
        stockService.set(item)
    }

    $scope.create = () => {
        const item = angular.copy($scope.item);
        $http.post(`${url}`, item).then(function(response) {
            $scope.items.push(response.data);
            $scope.reset();
            alert("Created success!");
        }).catch(error => {
            console.log("Error:" + error)
        })
    }

    $scope.update = () => {
        const item = angular.copy($scope.item);
        $http.put(`${url}/${item.id}`, item).then((response) => {
            const index = $scope.items.findIndex(p => p.id = item.id);
            $scope.items[index] = item;
            $scope.reset();
            alert("Updated success!");
        }).catch(error => {
            console.log("Error:" + error)
        })
    }

    $scope.delete = () => {
        $http.delete(`${url}/${$scope.item.id}`).then(response => {
            const index = $scope.items.findIndex(p => p.id = $scope.item.id);
            $scope.items.splice(index, 1);
            $scope.reset();
            alert("Deleted success!");
        }).catch(error => {
            console.log("Error:" + error)
        })
    }

    $scope.returnStock = (val) =>{
        return (item) => {
            return item.store.id == val;
        }
    }
})