"use strict";

const loginEventHandler = () => {
    try {
        const getLoginInputValue = {
            "email": $("#login-email").val(),
            "password": $("#login-password").val()
        }

        $.ajax({
            "headers": {
                "accept": "application/json",
                "content-type": "application/json"
            },

            "url": "http://localhost:8080/api/authentication/login",
            "data": JSON.stringify(getLoginInputValue),
            "type": "post"
        })
        .done((data) => {
            alert("Bạn đã đăng nhập vào hệ thống thành công!");
        })
        .fail(() => alert("Địa chỉ email hoặc mật khẩu không đúng, vui lòng kiểm tra lại thông tin!"));

    } catch (error) {
        throw new Error("An error occurred: " + error);
    }
}

$("#login-submit").on("click", () => loginEventHandler());

