//控制层
app.controller('orderManagerController' ,function($scope,$controller   ,orderService){

    $controller('baseController',{$scope:$scope});//继承

    //读取列表数据绑定到表单中
    $scope.findAll=function(){
        orderService.findAll().success(
            function(response){
                $scope.list=response;
            }
        );
    }

    //分页
    $scope.findPage=function(page,rows){
        orderService.findPage(page,rows).success(
            function(response){
                $scope.list=response.rows;
                $scope.paginationConf.totalItems=response.total;//更新总记录数
            }
        );
    }

    // 查询一个:
    $scope.findById = function(orderId){
        orderService.findById(orderId).success(function(response){
            // {id:xx,name:yy,firstChar:zz}
            $scope.entity = response;
        });
    }

    // 查询总金额:
    $scope.findTotalMoney = function(){
        orderService.findTotalMoney().success(function(response){
            // {id:xx,name:yy,firstChar:zz}
            $scope.entity = response;
        });
    }

    // //保存
    // $scope.save=function(){
    //     var serviceObject;//服务层对象
    //     if($scope.entity.specification.id!=null){//如果有ID
    //         serviceObject=orderService.update( $scope.entity ); //修改
    //     }else{
    //         serviceObject=orderService.add( $scope.entity  );//增加
    //     }
    //     serviceObject.success(
    //         function(response){
    //             if(response.success){
    //                 //重新查询
    //                 $scope.reloadList();//重新加载
    //             }else{
    //                 alert(response.message);
    //             }
    //         }
    //     );
    // }


    // //批量删除
    // $scope.dele=function(){
    //     //获取选中的复选框
    //     orderService.dele( $scope.selectIds ).success(
    //         function(response){
    //             if(response.success){
    //                 $scope.reloadList();//刷新列表
    //                 $scope.selectIds = [];
    //             }
    //         }
    //     );
    // }

    $scope.searchEntity={};//定义搜索对象

    //搜索
    $scope.search=function(page,rows){
        orderService.search(page,rows,$scope.searchEntity).success(
            function(response){
                $scope.list=response.rows;
                $scope.paginationConf.totalItems=response.total;//更新总记录数
            }
        );
    }

    // 支付类型显示状态
    $scope.PaymentType = ["","在线支付","货到付款"];

    // 支付类型显示状态
    $scope.Status = ["","未付款","已付款","未发货","已发货","交易成功","交易关闭","待评价"];

    // 支付类型显示状态
    $scope.PaymentType = ["","在线支付","货到付款"];

    // 支付类型显示状态
    $scope.PaymentType = ["","在线支付","货到付款"];


    // $scope.addTableRow = function(){
    //     $scope.entity.specificationOptionList.push({});
    // }
    //
    // $scope.deleteTableRow = function(index){
    //     $scope.entity.specificationOptionList.splice(index,1);
    // }

});
