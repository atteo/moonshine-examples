angular.module('NoteModule', [ 'ngResource' ]);

function NoteController($scope, $resource) {
	var Note = $resource('note', {}, { });

	var note = Note.get(function() {
		//success
		$scope.note = note;
	}, function() {
		//error
		alert("Error contacting REST service");
	});


    $scope.save = function() {
		Note.save($scope.note, function() {
		});
	};
}
