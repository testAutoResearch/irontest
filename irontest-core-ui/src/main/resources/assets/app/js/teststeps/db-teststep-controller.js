'use strict';

//  NOTICE:
//    The $scope here prototypically inherits from the $scope of teststeps-controller.js.
//    ng-include also creates a scope.
angular.module('irontest').controller('DBTeststepController', ['$scope', 'Teststeps', 'IronTestUtils',
  function($scope, Teststeps, IronTestUtils) {
    $scope.responseOptions = {
      enableFiltering: true,
      columnDefs: [ ]
    };

    $scope.createDSFieldContainAssertion = function(fieldName) {
      $scope.$broadcast('createDSFieldContainAssertion', fieldName);
    };

    $scope.evaluateDataSet = function() {
      $scope.$broadcast('evaluateDataSet', $scope.responseOptions.data);
    };

    $scope.invoke = function() {
      $scope.invocationResponse = null;
      //  exclude the result property from the assertion, as the property does not exist in server side Assertion class
      $scope.teststep.assertions.forEach(function(assertion) {
        delete assertion.result;
      });

      var teststep = new Teststeps($scope.teststep);
      teststep.$run(function(response) {
        $scope.invocationResponse = response;
        if (!response.resultSet) {    //  non select statements
          var results = response.statementExecutionResults;
          $scope.nonSelectStatementsExecutionResult = "";
          for (var i = 0; i < results.length; i += 1) {
            var statementType = results[i].statementType;
            var log = results[i].returnValue + ' rows ' + statementType.toLowerCase();
            log += statementType.endsWith('E') ? 'd' : 'ed';
            $scope.nonSelectStatementsExecutionResult += log + '\n' ;
          }
        } else {                      //  select statement
          $scope.responseOptions.data = response.resultSet;
          $scope.responseOptions.columnDefs = [ ];
          if (response.resultSet.length > 0) {
            var row = response.resultSet[0];
            for (var key in row) {
              $scope.responseOptions.columnDefs.push({
                field: key,
                menuItems: [
                  {
                    title: 'Create An Assertion', icon: 'ui-grid-icon-info-circled',
                    context: $scope,
                    action: function() {
                      this.context.createDSFieldContainAssertion(this.context.col.colDef.field);
                    }
                  }
                ]
              });
            }
          }
        }
      }, function(response) {
        IronTestUtils.openErrorHTTPResponseModal(response);
      });
    };
  }
]);
