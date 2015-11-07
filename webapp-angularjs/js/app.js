var app = angular.module('diceArenaApp', ['ngResource']);

app.controller('statsCtrl', function ($scope, dataRetriever, $resource) {
  $scope.stats = dataRetriever();

 var client = $resource('http://localhost:9200/arena-stats/game/_search');

console.log(client.get([],function() {}));
});

app.directive('erPlayer', function() {
return {
templateUrl: 'tpl/erplayer.html',
scope: { itemstat:'='}
};
});

app.directive('erGame', function() {
return {
templateUrl: 'tpl/ergame.html',
scope: { itemstat:'='}
};
});

app.directive('erMatch', function() {
return {
templateUrl: 'tpl/ermatch.html',
scope: { itemstat:'='}
};
});

app.value('dataRetriever', function() {
//var client = $resource('http://localhost:9201/arena-stats/game/_search');

//console.log(client.get([],function() {}).hits.hits);

return 


[
    {'name': 'Yathzee',
     'type': 'game',
     'description': 'On each turn, a player gets up to three rolls of the dice. He or she can save any dice that are wanted to complete a combination and then re-roll the other dice. After the third roll, the player must find a place to put the score (though he or she can choose to end the turn and ...'},
    {'name': 'Yams',
     'type': 'game',
     'class': 'glyphicon-tower',
     'description': 'On every turn, a player gets up to three rolls of the dice. He or she can save any dice that are wanted to complete a combination and then re-roll the other dice. After the third roll, the player must find a place to put the score (though he or she can choose to end the turn and ...'},
    {'name': 'John Doe',
     'type': 'player',
     'class': 'glyphicon-user',
     'account': 'john.doe@dojo.serli.com',
     'game': 'Pig'},
    {'startTime': '20/11/2014',
     'type': 'match',
     'game': 'Pig',
     'class': 'glyphicon-calendar',
     'winner': 'Bill Smith',
     'players': ['John Doe', 'Bill Smith', 'Ben Jackson']}
  ];
});




