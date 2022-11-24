app.controller("customer-ctrl", function ($scope, $http) {
    $http.get("/api/customer").then(resp => {
        $scope.listCustomer = resp.data;
		$scope.listCustomer.forEach(x => x.birthday = new Date(x.birthday));
        console.log($scope.listCustomer);
    })

    $scope.doilink = function (x){
        location.href = "/admin/index.html#!/customer-form?id="+x.id;
    }
})

app.controller("customer-form-ctrl", function ($scope,$http){
    var id = location.href.substring(location.href.lastIndexOf("id=")+3,location.href.length);
    $http.get("/api/customer").then(resp => {
        $scope.listCustomer = resp.data;

        console.log($scope.listCustomer);
    })
    $http.get("/api/customer/"+id).then(resp => {
        $scope.userdata = resp.data;
		$scope.userdata.image = "assets/images/" + $scope.userdata.image;
		$scope.userdata.birthday = new Date($scope.userdata.birthday)
        console.log($scope.userdata);
    })


    $scope.create = function (){
        var form = angular.copy($scope.userdata);
        var flag = true;
        if(form == undefined){
            alert("vui lòng nhập thông tin đầy đủ!");
			return;
        }
		if($scope.checkForm()==false){
			return;
		}
		console.log("không ăn")
        $scope.listCustomer.forEach(x =>{
            if(form.username == x.username){
                alert("username đã tồn tại!");
                flag = false;
                return;
            }
			if(form.email == x.email){
                alert("email đã tồn tại!");
                flag = false;
                return;
            }
        })
        if(flag==true){
            form.id = $scope.listCustomer.length+1;
        }else{
            return;
        }
        console.log(form);
        $http.post("/api/customer",form).then(resp =>{
            $scope.listCustomer.push(resp.data);
            alert("thêm tài khoản thành công!");
			$scope.reset();
        }).catch(error =>{
            console.log(error);
        })
    }

    $scope.update = function (){
        var form = angular.copy($scope.userdata);
        var flag = true;
        if(form == undefined){
            alert("vui lòng nhập thông tin đầy đủ!");
        }
		if($scope.checkForm()==false){
			return;
		}
        var index = $scope.listCustomer.findIndex(x => x.username == form.username);
        for(let i=0;i<$scope.listCustomer.length;i++){
            if(form.username == $scope.listCustomer[i].username){
                flag = false;
            }
            if(form.email == $scope.listCustomer[i].email && index !=i){
                alert("Email đã tồn tại!");
                return;
            }
        }
        if(flag==true){
            alert("username không tồn tại!");
            return;
        }
        $http.put("/api/customer/"+form.id,form).then(resp =>{
            $scope.listCustomer[index]= resp.data;
            alert("cập nhật tài khoản thành công!");
			$scope.reset();
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
        var item = $scope.listCustomer.find(x => x.username == form.username);
        for(let i=0;i<$scope.listCustomer.length;i++){
            if(form.username == $scope.listCustomer[i].username){
                flag = false;
            }
        }
        if(flag==true){
            alert("username không tồn tại!");
            return;
        }
        console.log(item);
		$http.delete("/api/customer/"+item.id).then(resp =>{
			alert("Xóa thành công!");
			var index = $scope.listCustomer.findIndex(x=> x.id == item.id);
			$scope.listCustomer.splice(index,1);
			$scope.reset();
		})
		
    }

	$scope.reset = function(){
		$scope.userdata = undefined;
		location.href= "/admin/index.html#!/customer-form";
	}
	
	// đổi ảnh
	$scope.imageChanged = function (files) {
		var data = new FormData();
		data.append('file', files[0]);
		if (files[0] == null) {
			return;
		}
		$http.post('/rest/upload/avatars', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.userdata.image ="assets/images/"+resp.data.name;
		}).catch(error => {
			alert("Lỗi upload hình ảnh");
			console.log("Error", error);
		})
	}
	
	$scope.checkForm = function(){
		if($scope.userdata.username == undefined 
			|| $scope.userdata.username == ""
			|| $scope.userdata.password == undefined || $scope.userdata.password ==""
			|| $scope.userdata.fullname == undefined || $scope.userdata.fullname ==""
			|| $scope.userdata.phone == undefined || $scope.userdata.phone == "" 
			|| $scope.userdata.birthday == undefined || $scope.userdata.birthday == null
			|| $scope.userdata.street == undefined || $scope.userdata.street == ""
			|| $scope.userdata.city == undefined || $scope.userdata.city == ""
			|| $scope.userdata.email == undefined || $scope.userdata.email == ""){
				alert("vui lòng nhập nhập đầy đủ thông tin!");
				return false;
		}
		return true;
	}
})