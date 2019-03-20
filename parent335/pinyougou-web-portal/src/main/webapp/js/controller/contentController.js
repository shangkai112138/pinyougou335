app.controller("contentController",function($scope,contentService){

	$scope.contentList = [];
	// 根据分类ID查询广告的方法:
	$scope.findByCategoryId = function(categoryId){
		contentService.findByCategoryId(categoryId).success(function(response){
			$scope.contentList[categoryId] = response;
		});
	}

    $scope.contentMap = {};
    $scope.findContent = function(){
        contentService.findContent().success(function(response){
            $scope.contentMap = response;
        });
    }

    $scope.itemCatList = [];
    // 根据分类ID查询广告的方法:
    $scope.findByParentId = function(parentId){
        contentService.findByParentId(parentId).success(function(response){
            $scope.itemCatList = response;
        });
    }

	
	//搜索  （传递参数）
	$scope.search=function(){
		location.href="http://localhost:9103/search.html#?keywords="+$scope.keywords;
	}
	
});