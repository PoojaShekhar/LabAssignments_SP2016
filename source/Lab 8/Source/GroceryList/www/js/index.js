var app = angular.module('grocery',[])

app.controller("GroceryController", function($scope, $http) {
   
    document.getElementById('b1').onclick = function(){
         //   $scope.onSearch = function () {
                var itemName = document.getElementById('itemName').value;
                //var destination = document.getElementById('destination-input').value; 
                $http.get('http://www.supermarketapi.com/api.asmx/SearchByProductName?APIKEY=62043b0f15&ItemName='+itemName).success(function(data) {
                    $scope.items = data;
                });
                
           // };
    };
});