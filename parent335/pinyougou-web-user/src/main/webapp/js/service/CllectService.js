// 定义服务层:
app.service("CllectService",function($http){
    this.findCllect = function(){
        return $http.get("user/findCllect.do");
    }
});