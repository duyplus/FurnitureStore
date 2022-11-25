app.controller("company-ctrl", function ($scope, $rootScope, $location, $http, $filter, companyService) {
    var url = "http://localhost:8080/api/brand";
    $scope.items = [];
    $scope.item = companyService.get();

    $http.get(url).then(function(response) {
        $scope.items = response.data;
    })

    $scope.reset = () =>{
        $scope.item = {};
        companyService.set($scope.item);
    }

    $scope.edit = (item) => {
        companyService.set(item);
        console.log(companyService.get())
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

    })