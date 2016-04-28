describe('myController', function () {
  beforeEach(module('ArrayExample'));

  var $controller;

  beforeEach(inject(function(_$controller_){
    // The injector unwraps the underscores (_) from around the parameter names when matching
    $controller = _$controller_;
  }));

  describe('nameCheck', function() {
    it('Tests the Full name feature of the application', function() {
      var $scope = {};
      var controller = $controller('myController', { $scope: $scope });
      var exists = 'YES';
      
	  expect($scope.nameCheck(exists)).toEqual('YES');   // succeeds        
      
    });
  });
  
});