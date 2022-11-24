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
        $scope.listStaff = resp.data;
        console.log($scope.listStaff);
    })
    $http.get("/api/staff/"+id).then(resp => {
        $scope.userdata = resp.data;
        console.log($scope.userdata);
    })
    $http.get("/api/store").then(resp => {
        $scope.listStore = resp.data;
        $scope.listRole = [0,1];
        $scope.listActive = [1,2,3];
    })

    $scope.create = function (){
        var form = angular.copy($scope.userdata);
        var flag = true;
        if(form == undefined){
            alert("vui lòng nhập thông tin đầy đủ!");
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
})