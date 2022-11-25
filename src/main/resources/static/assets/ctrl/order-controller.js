app.controller("order-ctrl", function ($scope, $http) {
    $http.get("/api/order").then(resp => {
        $scope.listOrder = resp.data;
        $scope.listStatus = [1,2,3,4];
        console.log($scope.listOrder);
    })

    $scope.doilink = function (x){
        location.href = "/admin/index.html#!/order-approval?id="+x.id;
    }
})

app.controller("order-form-ctrl", function ($scope,$http){
    var id = location.href.substring(location.href.lastIndexOf("id=")+3,location.href.length);
    $http.get("/api/order").then(resp => {
        $scope.listOrder = resp.data;

        $scope.listStatus = [
            {
                "id": 1,
                "name": "Chưa xử lý"
            },
            {
                "id": 2,
                "name": "Đã xác nhận"
            },
            {
                "id": 3,
                "name": "Đang giao"
            },
            {
                "id": 4,
                "name": "Đã giao"
            }
        ];
    })
    $http.get("/api/order/"+id).then(resp => {
        $scope.form = resp.data;
    })

    $scope.update = function (){
        var form = angular.copy($scope.form);
        console.log($scope.form);
        form.orderDate =  moment(form.orderDate).format("YYYY-MM-DD HH:mm");
        form.shippedDate = moment(form.shippedDate).format("YYYY-MM-DD HH:mm");
        var flag = true;
        if(form == undefined){
            alert("Vui lòng chọn đơn hàng!");
            return;
        }
        if(form.id == undefined){
            alert("Vui lòng chọn đơn hàng!");
            return;
        }
        console.log(form);
        var index = $scope.listOrder.findIndex(x => x.id == form.id);
        $http.put("/api/order/"+form.id,form).then(resp =>{
            // $scope.listOrder[index]= resp.data;
            alert("cập nhật đơn hàng thành công!");
			// $scope.reset();
        }).catch(error =>{
            console.log(error);
        })
    }

    $scope.delete = function (X){
        var form = angular.copy($scope.userdata);
        var flag = true;
        if(form == undefined){
            alert("vui lòng nhập thông tin đầy đủ!");
        }
        var item = $scope.listOrder.find(x => x.username == form.username);
        for(let i=0;i<$scope.listOrder.length;i++){
            if(form.username == $scope.listOrder[i].username){
                flag = false;
            }
        }
        if(flag==true){
            alert("username không tồn tại!");
            return;
        }
        console.log(item);
		$http.delete("/api/order/"+item.id).then(resp =>{
			alert("Xóa thành công!");
			var index = $scope.listOrder.findIndex(x=> x.id == item.id);
			$scope.listOrder.splice(index,1);
		})
		
    }

	$scope.reset = function(){
		$scope.form = undefined;
		location.href= "/admin/index.html#!/user-form";
	}
})