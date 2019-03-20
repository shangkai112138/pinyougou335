// 定义服务层:
app.service("userService",function($http){
    this.findAll = function(){
        return $http.get("../user/findAll.do");
    }

    this.findByPage = function(page,rows){
        return $http.get("../user/findByPage.do?page="+page+"&rows="+rows);
    }

    this.update=function(id,status){
        return $http.get("../user/update.do?id="+id+"&status="+status);
    }

    this.findOne=function(id){
        return $http.get("../user/findOne.do?id="+id);
    }
    //分页
    this.findPage=function(page,rows){
        return $http.get('../user/findPage.do?page='+page+'&rows='+rows);
    }

    this.dele = function(ids){
        return $http.get("../user/delete.do?ids="+ids);
    }

    this.search = function(page,rows,searchEntity){
        return $http.post("../user/search.do?page="+page+"&rows="+rows,searchEntity);
    }

    this.selectOptionList = function(){
        return $http.get("../user/selectOptionList.do");
    }
    this.updateStatus = function(id,status){
        return $http.get('../user/updateStatus.do?id='+id+"&status="+status);
    }
});