// 定义服务层:
app.service("ProCitAreaService",function($http){

    this.selectProvincesList = function(){
        return $http.get("../province/findAll.do");
    }
    this.findByProvinceId = function(){
        return $http.get("../city/findByProvinceId.do?provinceId="+provinceId);
    }
    this.findByCityId = function(){
        return $http.get("../area/findByCityId.do?cityId="+cityId);
    }
}