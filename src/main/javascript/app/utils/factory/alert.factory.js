AlertFactory.$inject = ['$rootScope'];

function AlertFactory($rootScope) {
  return {
    messageList: [],
    isDuplicate: function (msgObj) {
      var i = this.messageList.length;
      while (i--) {
        if (this.messageList[i].code == msgObj.code) {
          return true;
        }
      }
      return false;
    },
    addMessage: function (message, type, code, clear) {
      if (clear === true) {
        this.messageList = [];
      }

      var msgObj = {
        text: message,
        type: type,
        code: code
      };

      if (this.isDuplicate(msgObj)) return;

      $rootScope.$broadcast('add-message', {
        message: msgObj
      });

      this.messageList.push(msgObj);
    },
    warn: function (text, code) {
      this.addMessage(text, 'warning', code);
    },
    info: function (text, code) {
      this.addMessage(text, 'info', code);
    },
    error: function (text, code) {
      this.addMessage(text, 'danger', code);
    },
    success: function (text, code) {
      this.addMessage(text, 'success', code);
    },
    clear: function (code) {
      if (code) {
        this.messageList = this.messageList.filter(function (message) {
          if (message.code && message.code == code) {
            return false;
          } else {
            return true;
          }
        });
      } else {
        this.messageList = [];
      }
      $rootScope.$broadcast('clear-message', {
        message: this.messageList
      });
    }
  };
}

AlertPanelController.$inject = ['$scope', 'AlertFactory'];

function AlertPanelController($scope, alertFactory) {

  this.messageList = [];
  alertFactory.clear();
  var self = this;

  function initMessages() {
    alertFactory.messageList.forEach(function (msg) {
      self.messageList.push(msg);
    });
  }

  $scope.$on('add-message', function (event, data) {
    var msg = data.message;

    self.messageList.push(msg);
  });

  $scope.$on('clear-message', function (event, data) {
    self.messageList = [];
    for (var i = 0; i < data.message.length; i++) {
      self.messageList.push(data.message[i]);
    }
  });

  self.closeAlert = function (alert) {
    alertFactory.clear(alert.code);
  };

  initMessages();
}

angular.module('instaton.app.utils')
  .factory('AlertFactory', AlertFactory)
  .component('alertPanel', {
    controller: AlertPanelController,
    controllerAs: 'self',
    templateUrl: 'assets/tpl/utils/factory/alert-panel.template.html'
  });
