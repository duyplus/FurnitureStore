app.controller("product-ctrl", function ($scope, $http) {
    $http.get("/api/product").then(resp=>{
        $scope.listProduct = resp.data;
        $scope.listProduct.forEach(x => {
            x.modelYear = new Date(x.modelYear);
        })
        console.log($scope.listProduct);
    }).catch(error =>{
        console.log(error);
    })
    $scope.doilink = function (x){
        location.href = "/admin/index.html#!/product-form?id="+x.id;
        console.log(location.href.substring(location.href.lastIndexOf("id=")+3,location.href.length));
    }

    // $http.get("/api/product").then(resp=>{
    //     $scope.listProduct = resp.data;
    // }).catch(error =>{
    //     console.log(error);
    // })
})

app.controller("product-detail-ctrl", function ($scope,$http){
    var id = location.href.substring(location.href.lastIndexOf("id=")+3,location.href.length);

    $http.get("/api/product").then(resp=>{
        $scope.listProduct = resp.data;
        $scope.listProduct.forEach(x => {
            x.modelYear = new Date(x.modelYear);
        })
    }).catch(error =>{
        console.log(error);
    })

    $http.get("/api/product/"+id).then(resp =>{
        $scope.product = resp.data;
        $scope.product.modelYear = new Date($scope.product.modelYear);
        console.log($scope.product);
    }).catch(error =>{
        console.log(error);
    })

    $http.get("/api/brand").then(resp =>{
        $scope.brand = resp.data;
    }).catch(error =>{
        console.log(error);
    })

    $http.get("/api/category").then(resp =>{
        $scope.category = resp.data;
    }).catch(error =>{
        console.log(error);
    })

    $scope.checkForm = function (){
        var pd = angular.copy($scope.product);
        console.log($scope.product)
        if(pd === undefined){
            alert("Vui lòng nhập thông tin đầy đủ!");
            return false;
        }
        if($scope.product.name == "" || $scope.product.modelYear == null
            || $scope.product.category.id == null || $scope.product.brand.id ==null
            || $scope.product.listPrice == null ){
            alert("Vui lòng nhập thông tin đầy đủ!");
            return false;
        }
        return true;
    }

    $scope.update = function (){
        var pd = angular.copy($scope.product);
        var flag = $scope.checkForm();
        if(!flag){
            return;
        }
        console.log($scope.pd)
        if (pd.id == undefined){
            $scope.checkId = "Mã sản phẩm không tồn tại!";
            return;
        }else{
            $scope.checkId = "";
        }
        $http.put("/api/product",pd).then(resp => {
            resp.data.modelYear = new Date(resp.data.modelYear);
            var index = $scope.listProduct.findIndex( x => x.id == resp.data.id);
            $scope.listProduct[index] = resp.data;
            console.log($scope.listProduct);
            $scope.checkId = "";
            alert("cập nhật thành công!");
            $scope.reset();
        })
    }

    $scope.create = function (){
        $scope.pd1 = angular.copy($scope.listProduct[0]);
        $scope.pd1 = $scope.product;
        var flag = $scope.checkForm();
        if(!flag){
            return;
        }
        if($scope.pd1.id === undefined){
            $scope.pd1.id = $scope.listProduct.length+1;
            $scope.checkId = "";
        }else{
            $scope.checkId = "Mã sản phẩm đã tồn tại!";
            return;
        }
        console.log($scope.pd1);
        if($scope.pd1.images === undefined){
            $scope.pd1.images = null;
        }
        $scope.pd1.modelYear = new Date($scope.pd1.modelYear);
        $http.post("/api/product",$scope.pd1).then(resp => {
            resp.data.modelYear = new Date(resp.data.modelYear);
            $scope.listProduct.push(resp.data);
            console.log($scope.listProduct);
            $scope.checkId = "";
            alert("Thêm thành công!");
            $scope.reset();
        })
    }

    $scope.reset = function (){
        $scope.product = undefined;
        console.log($scope.product);
    }

    $scope.detele =function (){
        var pd1 = angular.copy($scope.listProduct[0]);
        pd1 = $scope.product;
        if(pd1 == undefined){
            $scope.checkId = "Mã sản phẩm không tồn tại!";
            return;
        }
        console.log(pd1)
        if (pd1.id == undefined){
            $scope.checkId = "Mã sản phẩm không tồn tại!";
            return;
        }else{
            $scope.checkId = "";
        }
        $http.delete("/api/product/"+pd1.id).then(resp =>{
            var index = $scope.listProduct.findIndex( x => x.id == resp.data.id);
            $scope.listProduct.splice(index,1);
            alert("Xóa thành công !")
            $scope.checkId = "";
            $scope.reset();
        }).catch(error =>{
            console.log(error);
        })
    }

// đổi ảnh
	$scope.imageChanged = function (files) {
		var data = new FormData();
		data.append('file', files[0]);
		if (files[0] == null) {
			return;
		}
		$http.post('/rest/upload/produdct', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.product.images =resp.data.name;
		}).catch(error => {
			alert("Lỗi upload hình ảnh");
			console.log("Error", error);
		})
	}
})