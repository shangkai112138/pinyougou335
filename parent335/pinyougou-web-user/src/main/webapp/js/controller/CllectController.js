// 定义控制器:
app.controller("CllectController",function($scope,$http,CllectService){

    $scope.cllectList = [];
    $scope.findCllect = function(){
        // 向后台发送请求:
        CllectService.findCllect().success(function(response){
            $scope.cllectList = response;
        });
    }

});
