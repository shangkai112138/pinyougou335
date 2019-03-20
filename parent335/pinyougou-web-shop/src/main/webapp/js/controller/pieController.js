// 定义控制器:
app.controller("pieController",function($scope,$controller,pieService){
    $controller('baseController',{$scope:$scope});

    // 查询所有的品牌列表的方法:
    $scope.findTotalMoneyGroupByGoodsIdForSellId = function(){
        // 向后台发送请求:
        pieService.findTotalMoneyGroupByGoodsIdForSellId().success(function(response){
            $scope.entityVo = response;
        });
    }
}