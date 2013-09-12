function BrowseRecipesCtrl($scope, $http) {
  
  $http.get('js/recipes.json').success(function(data) {
    //$scope.recipes = data;
     $http({method: 'GET', url: 'http://localhost:9000/recipes'}).
            success(function(data, status, headers, config) {
                $scope.recipes = data;
            }).
            error(function(data, status, headers, config) {
                $scope.apps = data || "Request failed";
            });
  });

  $scope.orderProp = 'name';
}