import md5 from "./md5.min.js";

function onClickLogin() {
    var userId = $("#input_username").val();
    var password = $("#input_password").val();
    var captcha = $("#input_checkcode").val();

    if(userId != "" && password != "") {
        $("#input_password").val(md5(password));
    }
    
// j_captcha: cf78
// _spring_security_remember_me: on

}