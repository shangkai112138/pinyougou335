//服务层
app.service('pieChartService',function($http){

    //读取列表数据绑定到表单中
    this.findAll=function(){
        return $http.get('../pieChart/findAll.do');
    }
    //分页
    this.findPage=function(page,rows){
        return $http.get('../pieChart/findPage.do?page='+page+'&rows='+rows);
    }
    this.findById=function(orderId){
        return $http.get("../pieChart/findOne.do?orderId="+orderId);
    }

    this.search=function(page,rows,searchEntity){
        return $http.post('../pieChart/search.do?page='+page+"&rows="+rows, searchEntity);
    }

    //返回json串
    this.PieChartData=function(){
        return $http.get('../pieChart/PieChartData.do');
    }

});
