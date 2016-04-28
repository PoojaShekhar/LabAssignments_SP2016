describe('myController', function () {
  beforeEach(module('ArrayExample'));

  var $controller;

  beforeEach(inject(function(_$controller_){
    // The injector unwraps the underscores (_) from around the parameter names when matching
    $controller = _$controller_;
  }));

  describe('contactName', function() {
    it('Tests the Full name feature of the application', function() {
      var $scope = {};
      var controller = $controller('myController', { $scope: $scope });
      var contactName = 'Sushma';
      
	  //expect($scope.CName(cname)).toEqual('Sushma');   // succeeds
	  expect($scope.contactName(contactName)).toEqual('Anusha');   // fails
      
      
    });
  });
  
});