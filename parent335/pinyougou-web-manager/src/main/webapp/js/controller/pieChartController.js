//控制层
app.controller('pieChartController' ,function($scope,$controller   ,pieChartService){

    $controller('baseController',{$scope:$scope});//继承

    //读取列表数据绑定到表单中
    $scope.findAll=function(){
        pieChartService.findAll().success(
            function(response){
                $scope.list=response;
            }
        );
    }

    //分页
    $scope.findPage=function(page,rows){
        pieChartService.findPage(page,rows).success(
            function(response){
                $scope.list=response.rows;
                $scope.paginationConf.totalItems=response.total;//更新总记录数
            }
        );
    }

    // 查询一个:
    $scope.findById = function(orderId){
        pieChartService.findById(orderId).success(function(response){
            // {id:xx,name:yy,firstChar:zz}
            $scope.entity = response;
        });
    }


    $scope.searchEntity={};//定义搜索对象

    //搜索
    $scope.search=function(page,rows){
        pieChartService.search(page,rows,$scope.searchEntity).success(
            function(response){
                $scope.list=response.rows;
                $scope.paginationConf.totalItems=response.total;//更新总记录数
            }
        );
    }


    //返回json串
    $scope.PieChartData=function(){
        pieChartService.PieChartData().success(
            function(response){
                $scope.pieChart=response;
            }
        );
    }

});
