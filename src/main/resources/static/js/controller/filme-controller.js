/**
 * http://usejsdoc.org/
 */
appFilme.controller("filmeController", function($scope, $http) {
	
	$scope.filmes = [];
	$scope.createFilme = {}
	carregarFilmes= function() {
		$http({method:'GET', url:'http://localhost:8080/filmes'})
		.then(function(response){
			
			$scope.filmes = response.data;
			console.log(response.data);
			console.log(response.status);
		}, function(response){
			console.log(response.data);
			console.log(response.status);
			
			
		});
		
	};
	
	carregarFilmes();
	
	$scope.salvarFilme = function() {
		$http({method:'POST', url:'http://localhost:8080/filmes', data:$scope.createFilme})
		.then(function(response){
			carregarFilmes()
			$scope.limparCamposFilme();
			
			console.log(response.status);
		}, function(response){

			console.log(response.data);
			console.log(response.status);
			
		});
	};
	
	
	$scope.excluirFilme = function(filme) {
		$http({method:'DELETE', url:'http://localhost:8080/filmes/'+filme.id})
		.then(function(response){
			
			//buscar um index de um objeto dentro do array
			pos = $scope.filmes.indexOf(filme);
			$scope.filmes.splice(pos,1);
			console.log(response.status);
		}, function(response){

			console.log(response.data);
			console.log(response.status);
			
		});

	
	};
	
	$scope.alterarFilme = function(filme) {
		$scope.createFilme = angular.copy(filme);
	
	};
	
	$scope.limparCamposFilme= function() {
		$scope.createFilme = {};
		
	};
	
	
	
	
});