app.controller("user-ctrl", function ($scope, $http) {
    $http.get("/api/staff").then(resp => {
        $scope.listStaff = resp.data;
        console.log($scope.listStaff);
    })

    $scope.doilink = function (x){
        location.href = "/admin/index.html#!/user-form?id="+x.id;
    }
})

app.controller("user-form-ctrl", function ($scope,$http){
    var id = location.href.substring(location.href.lastIndexOf("id=")+3,location.href.length);
    $http.get("/api/staff").then(resp => {
		var id1 = location.href.substring(location.href.lastIndexOf("id=")+3,location.href.length);
        $scope.listStaff = resp.data;
		if(Number(id1) == undefined || id1.length >=10){
		}else{
			$http.get("/api/staff/"+id1).then(resp => {
        	$scope.userdata = resp.data;
    	})
		}
    })
    
    $http.get("/api/store").then(resp => {
        $scope.listStore = resp.data;
        $scope.listRole = [0,1];
        $scope.listActive = [1,2,3];
    })

    $scope.create = function (){
        var form = angular.copy($scope.userdata);
		if(form == undefined){
            alert("vui lòng nhập thông tin đầy đủ!");
			return;
        }
        var flag = true;
		form.password = "1";
        
		if($scope.checkForm() == false){
			return;
		}
        $scope.listStaff.forEach(x =>{
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
            form.id = $scope.listStaff.length+1;
        }else{
            return;
        }
        console.log(form);
        $http.post("/api/staff",form).then(resp =>{
            $scope.listStaff.push(resp.data);
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
			return;
        }
		if($scope.checkForm() == false){
			return;
		}
        var index = $scope.listStaff.findIndex(x => x.username == form.username);
        for(let i=0;i<$scope.listStaff.length;i++){
            if(form.username == $scope.listStaff[i].username){
                flag = false;
            }
            if(form.email == $scope.listStaff[i].email && index !=i){
                alert("Email đã tồn tại!");
                return;
            }
        }
        if(flag==true){
            alert("username không tồn tại!");
            return;
        }
        $http.put("/api/staff/"+form.id,form).then(resp =>{
            $scope.listStaff[index]= resp.data;
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
			return;
        }
		if(form.username == null){
			alear("vui lòng username");
			return;
		}
        var item = $scope.listStaff.find(x => x.username == form.username);
        for(let i=0;i<$scope.listStaff.length;i++){
            if(form.username == $scope.listStaff[i].username){
                flag = false;
            }
        }
        if(flag==true){
            alert("username không tồn tại!");
            return;
        }
        console.log(item);
		$http.delete("/api/staff/"+item.id).then(resp =>{
			alert("Xóa thành công!");
			var index = $scope.listStaff.findIndex(x=> x.id == item.id);
			$scope.listStaff.splice(index,1);
		})
		
    }

	$scope.reset = function(){
		$scope.userdata = undefined;
		location.href= "/admin/index.html#!/user-form";
	}
	
	$scope.checkForm = function(){
		if($scope.userdata.username == undefined || $scope.userdata.username == ""
			|| $scope.userdata.fullname == undefined || $scope.userdata.fullname == ""
			|| $scope.userdata.phone == undefined || $scope.userdata.phone == ""
			|| $scope.userdata.store == undefined || $scope.userdata.store == null
			|| $scope.userdata.managerId == undefined || $scope.userdata.managerId == null
			|| $scope.userdata.email == undefined || $scope.userdata.email == ""
			|| $scope.userdata.active == undefined || $scope.userdata.active ==""){
				alert("Vui lòng nhập đầy đủ thông tin");
				return false;
			}
			return true;
	}
})