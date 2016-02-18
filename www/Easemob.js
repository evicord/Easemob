var exec = require('cordova/exec');

exports.chat = function(arg0, success, error) {
    exec(success, error, "Easemob", "chat", [arg0]);
};
