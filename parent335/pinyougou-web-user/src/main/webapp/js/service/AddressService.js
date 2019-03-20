// 定义服务层:
app.service("AddressService",function($http){
    this.findAll = function(){
        return $http.get("../address/findAll.do");
    }
    this.findOne = function(id){
        return $http.get("../address/findOne.do?id="+id);
    }
    this.insert = function(entity){
        return $http.post("../address/insert.do",entity);
    }
    this.update = function(entity){
        return $http.post("../address/update.do",entity);
    }
    this.delete = function(id){
        return $http.get("../address/delete.do?id="+id);
    }
    this.setDefaultAddress = function(id){
        return $http.get("../address/setDefaultAddress.do?id="+id);
    }
});