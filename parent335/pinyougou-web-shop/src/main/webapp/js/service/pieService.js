// 定义服务层:
app.service("pieService",function($http){
    this.findTotalMoneyGroupByGoodsIdForSellId = function(){
        return $http.post("../pie/findTotalMoneyGroupByGoodsIdForSellId.do");
    }
});