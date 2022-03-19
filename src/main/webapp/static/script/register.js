$(function () {
    // 用户名输入框失焦事件
    $(".register-username").blur(function () {
        // 验证用户名格式
        let username = $(".register-username").val();
        let usernameExp = new RegExp("^[a-zA-Z][a-zA-Z0-9_-]{0,16}$");
        let $nameTips = $(".tips-username");
        if (!usernameExp.test(username) || username.length > 16 || username.length <= 0) {
            $nameTips.css("color", "red");
            return false;
        }
        // 发起 AJAX 请求服务器验证用户名存在性
        $.ajax({
            url: "userServlet",
            data: "action=ajaxVerifyUsername&username=" + username,
            type: "POST",
            // function 函数中一定要有参数以接收来自服务器的数据
            success: function (data) {
                if (data === "true") {
                    $nameTips.css("color", "red");
                    $nameTips.text("* 用户名已被占用，请重新输入")
                } else {
                    $nameTips.css("color", "darkolivegreen");
                    $nameTips.text("* 用户名可用")
                }
            },
            dataType: "text"
        });
    });

    // 密码输入框的失焦事件
    $(".register-password").blur(function () {
        // 验证密码
        let password = $(".register-password").val();
        let $pwdTips = $(".tips-password");
        // let pwdReg = new RegExp("^(?=.*[A-Za-z])(?=.*\d)(?=.*[@#$%&]){6,16}$")
        if (password.length < 6 || password.length > 16) {
            $pwdTips.css("color", "red");
            return false;
        } else {
            $pwdTips.css("color", "darkolivegreen");
        }
    });

    // 邮箱输入框的失焦事件
    let emailExists = false;
    $(".register-email").blur(function () {
        // 验证邮箱格式
        let email = $(".register-email").val();
        let emailReg = new RegExp("^([a-z0-9_-]+)@([\\da-z-]+)\\.([a-z]{2,6})$");
        let $emailTips = $(".tips-email");
        if (!emailReg.test(email) || email.length <= 0) {
            $emailTips.css("color", "red");
            $emailTips.text("* 邮箱格式不正确")
            return false;
        } else {
            $emailTips.css("color", "darkolivegreen");
            $emailTips.text("* e.g bookhouse@bookhouse.com")
        }

        // 发起 AJAX 请求服务器验证用户名存在性
        $.ajax({
            url: "userServlet",
            data: "action=ajaxVerifyEmail&email=" + email,
            type: "POST",
            // function 函数中一定要有参数以接收来自服务器的数据
            success: function (data) {
                if (data === "true") {
                    $emailTips.css("color", "red");
                    $emailTips.text("* 邮箱已被占用，请更换邮箱地址")
                    emailExists = true;
                } else {
                    $emailTips.css("color", "darkolivegreen");
                    $emailTips.text("* 邮箱地址可用")
                    emailExists = false;
                }
            },
            dataType: "text"
        });
    })

    // 邮箱验证码输入框的失焦事件
    $(".emailVerifyCode").blur(function () {
        // 验证验证码格式
        let $emailCode = $(".emailVerifyCode").val()
        let $emailCodeTips = $(".tips-emailCode");
        if ($emailCode.length !== 6) {
            $emailCodeTips.css("color", "red");
            $emailCodeTips.text("* 邮箱验证码长度为 6 位字符")
        } else {
            $emailCodeTips.css("color", "darkolivegreen");
            $emailCodeTips.text("* 点击获取邮件会发送至您的邮箱")
        }
    });

    // 图片验证码输入框的失焦事件
    $(".imgVerifyCode").blur(function () {
        // 验证验证码格式
        let $imgCode = $(".imgVerifyCode").val()
        let $imgCodeTips = $(".tips-imgCode");
        if ($imgCode.length !== 5) {
            $imgCodeTips.css("color", "red");
            $imgCodeTips.text("* 图片验证码长度为 5 位字符")
        } else {
            $imgCodeTips.css("color", "darkolivegreen");
            $imgCodeTips.text("* 点击图片可刷新验证码")
        }
    });

    // 图片验证码单击事件
    $("#imgCodeBtn").click(function () {
        // 加上时间戳作为参数以解决 Firefox、IE 等浏览器缓存导致不能刷新问题
        this.src = "imgVerifyCode.jpg?date=" + new Date();
    });

    // 密码框眼睛图片单击事件
    let eyeClosed = false;
    $(".pas-eye").click(function () {
        if (eyeClosed) {
            // 如果闭眼 则睁眼，由可见变为不可见
            this.src = "static/img/eye.png"
            $(".register-password").prop('type', 'password');
            eyeClosed = false;
        } else {
            // 如果睁眼 则闭眼，由不可见变为可见
            this.src = "static/img/eye_closed.png"
            $(".register-password").prop('type', 'text');
            eyeClosed = true;
        }
    })

    // 获取邮箱验证码按钮单击事件
    let emailLocked = false;
    $("#emailCodeBtn").click(function () {
        // 邮箱地址无效则不允许获取验证码
        let email = $(".register-email").val();
        let emailReg = new RegExp("^([a-z0-9_-]+)@([\\da-z-]+)\\.([a-z]{2,6})$");
        let tipsObj = $(".tips-email");
        if (!emailReg.test(email) || email.length <= 0) {
            tipsObj.css("color", "red");
            tipsObj.text("* 请您先填入邮箱后再获取验证码")
            return false;
        } else if (emailExists) {
            tipsObj.css("color", "red");
            tipsObj.text("* 邮箱已被占用，请更换邮箱地址")
            return false;
        } else {
            tipsObj.css("color", "darkolivegreen");
        }

        // 将获取验证码按钮禁用
        let $btn = $(this);
        $btn.val("获取中");
        $btn.attr('disabled', true);

        if (emailLocked) {
            return false;
        } else {
            // 防止多次点击
            emailLocked = true;
            // 发起 ajax 请求让服务器发送随机验证码
            $.ajax({
                url: "emailServlet",
                data: "action=ajaxSendRegisterEmailCode&email=" + email,
                type: "POST",
                dataType: "text",
                success: function (data) {
                    if (data === "true") {
                        // 获取验证码按钮倒计时，将这个事件锁起来
                        emailLocked = true;
                        let secondsNode = 60;
                        let emailBtnObj = $("#emailCodeBtn");
                        let time = setInterval(function () {
                            secondsNode--;
                            emailBtnObj.val("重新获取（" + secondsNode + ")");
                            emailBtnObj.css("background-color", "grey")
                            if (secondsNode <= 0) {
                                $btn.attr('disabled', false);
                                emailBtnObj.val("获取验证码");
                                emailBtnObj.css("background-color", "lightskyblue")
                                emailLocked = false;
                                clearInterval(time);
                            }
                        }, 1000)
                    } else {
                        alert("验证码发送失败，请稍后重试！");
                        emailLocked = false;
                    }
                },
                error: function () {
                    alert("验证码发送失败，请稍后重试！");
                    emailLocked = false;
                }
            });
        }
    });

    // 注册按钮单击事件
    $(".register-btn").click(function () {
        /* 再次验证各表单项数据格式，预防提交空表单 */
        // 验证用户名格式
        let username = $(".register-username").val();
        let usernameExp = new RegExp("^[a-zA-Z][a-zA-Z0-9_-]{0,16}$");
        let $nameTips = $(".tips-username");
        if (!usernameExp.test(username) || username.length > 16 || username.length <= 0) {
            $nameTips.css("color", "red");
            return false;
        }
        // 验证密码
        let password = $(".register-password").val();
        let $pwdTips = $(".tips-password");
        // let pwdReg = new RegExp("^(?=.*[A-Za-z])(?=.*\d)(?=.*[@#$%&]){6,16}$")
        if (password.length < 6 || password.length > 16) {
            $pwdTips.css("color", "red");
            return false;
        } else {
            $pwdTips.css("color", "darkolivegreen");
        }
        // 验证邮箱格式
        let email = $(".register-email").val();
        let emailReg = new RegExp("^([a-z0-9_-]+)@([\\da-z-]+)\\.([a-z]{2,6})$");
        let $emailTips = $(".tips-email");
        if (!emailReg.test(email) || email.length <= 0) {
            $emailTips.css("color", "red");
            $emailTips.text("* 邮箱格式不正确")
            return false;
        } else {
            $emailTips.css("color", "darkolivegreen");
            $emailTips.text("* e.g bookhouse@bookhouse.com")
        }
        // 验证邮箱验证码格式
        let $emailCode = $(".emailVerifyCode").val()
        let $emailCodeTips = $(".tips-emailCode");
        if ($emailCode.length !== 6) {
            $emailCodeTips.css("color", "red");
        } else {
            $emailCodeTips.css("color", "darkolivegreen");
        }
        // 验证图片验证码格式
        let $imgCode = $(".imgVerifyCode").val()
        let $imgCodeTips = $(".tips-imgCode");
        if ($imgCode.length !== 5) {
            $imgCodeTips.css("color", "red");
        } else {
            $imgCodeTips.css("color", "darkolivegreen");
        }
        return true;
    });
});