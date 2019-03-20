app.service("contentService",function($http){
	this.findByCategoryId = function(categoryId){
		return $http.get("content/findByCategoryId.do?categoryIds="+categoryId);
	}
    this.findContent = function(){
        return $http.get("content/findContent.do");
    }
});