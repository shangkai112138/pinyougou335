// 定义控制器:
app.controller("AddressController",function($scope,$http,AddressService,loginService){
    $scope.showName=function(){
        loginService.showName().success(
            function(response){
                $scope.img=response.img;
                $scope.loginName=response.loginName;
                $scope.findAll();
            }
        );
    }
    $scope.findAll = function(){
        // 向后台发送请求:
        AddressService.findAll().success(function(response){
            $scope.list = response;
        });
    }

    $scope.save = function () {
        // 区分是保存还是修改
        var object;
        if($scope.entity.id != null){
            // 更新
            object = AddressService.update($scope.entity);
        }else{
            // 保存
            object = AddressService.insert($scope.entity);
        }
        object.success(function(response) {
            // {flag:true,message:xxx}
            // 判断保存是否成功:
            if (response.flag) {
                // 保存成功
                $scope.findAll();
            } else {
                // 保存失败
                alert(response.message);
            }
        });
    }





    $scope.insert = function(){
        // 向后台发送请求:
        AddressService.insert($scope.entity).success(function(response){
            // 判断保存是否成功:
            if(response.flag){
                // 保存成功
                $scope.findAll();
                 alert(response.message);

            }else{
                // 保存失败
                alert(response.message);
            }
        });
    }

    $scope.delete = function(id){
        // 向后台发送请求:
        if (confirm("确认要删除吗","取消")){
        AddressService.delete(id).success(function(response){
            // 判断删除成功:
            if(response.flag){
                $scope.findAll();
            }else{
                // 保存失败
                alert(response.message);
            }
        });

        }
    }

    $scope.findOne = function(id){
        AddressService.findOne(id).success(function(response){
            $scope.entity = response;
        });
    }

    $scope.clear  =  function () {
        $scope.entity = {};
    }

    $scope.setDefaultAddress = function (id) {
        AddressService.setDefaultAddress(id).success(function(response){
            // 判断设置默认是否成功:
            if(response.flag){
                // 设置成功
                $scope.findAll();
            }else{
                // 设置失败
                alert(response.message);
            }
        });
    }
});
