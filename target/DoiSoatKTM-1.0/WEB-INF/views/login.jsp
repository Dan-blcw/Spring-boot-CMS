<%-- 
    Document   : login
    Created on : Sep 13, 2017, 2:36:35 PM
    Author     : vnpt2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="_csrf" content="${_csrf.token}"/>
        <!-- default header name is X-CSRF-TOKEN -->
        <meta name="_csrf_header" content="${_csrf.headerName}"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Đăng nhập</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.6 -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/AdminLTE.min.css">
        <!-- iCheck -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/iCheck/square/blue.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body class="hold-transition login-page">
        <div class="login-box">
            <div class="login-logo">
                <a href="${pageContext.request.contextPath}/login"><b>DS</b>KTM</a>
            </div>
            <!-- /.login-logo -->
            <div class="login-box-body">
                <p class="login-box-msg">Đăng nhập để bắt đầu phiên của bạn</p>

                <form action="${pageContext.request.contextPath}/j_spring_security_check" method="post" id="loginForm">
                    <div class="form-group has-feedback">
                        <input type="text" class="form-control" placeholder="UserName" name="userName">
                        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input type="password" class="form-control" placeholder="Password" name="password">
                        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                    </div>
                    <!--                    <div class="form-group" >
                                            <div class="g-recaptcha" data-sitekey="6LfcgDoUAAAAAKUt7NCrga8nMDQjBxnXLbNRTjIf"></div>
                                        </div>-->
                    <div class="hidden">LOGIN_FINISH_TIME</div>
                    <input type="hidden"
                           name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                    <c:if test="${param.error == 'true'}">
                        <div class="form-group has-error">
                            <label>Tài khoản bị khóa hoặc thông tin đăng nhập không đúng </label>
                        </div>
                    </c:if>
<!--                    <div class="form-group has-error">
                        <label id="errorCaptcha" class="hidden"></label>
                    </div>
                    <div class="form-group" >
                        <div class="g-recaptcha" data-sitekey="6LfcgDoUAAAAAKUt7NCrga8nMDQjBxnXLbNRTjIf"></div>
                    </div>-->
                    <div class="row">
                        <!-- /.col -->
                        <div class="col-xs-4">
                            <button type="submit" class="btn btn-primary btn-block btn-flat" id="btnLogin" >Đăng nhập</button>
                        </div>
                        <!-- /.col -->
                    </div>
                </form>

            </div>
            <!-- /.login-box-body -->
        </div>
        <!-- /.login-box -->

        <!-- jQuery 2.2.3 -->
        <script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
        <!-- Bootstrap 3.3.6 -->
        <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
        <!-- iCheck -->
        <script src="${pageContext.request.contextPath}/plugins/iCheck/icheck.min.js"></script>
        <script src='https://www.google.com/recaptcha/api.js?hl=vi' type="text/javascript" ></script>
<!--        <script>
            $(function () {
                $('input').iCheck({
                    checkboxClass: 'icheckbox_square-blue',
                    radioClass: 'iradio_square-blue',
                    increaseArea: '20%' // optional
                });
            });

            $('#btnLogin').on('click', function (e) {
                e.preventDefault();
                console.log("grecaptcha: " + grecaptcha.getResponse());
                var json = {
                    "grecaptcha": grecaptcha.getResponse()
                };
                var headers = [];
                var csrfToken = $("meta[name='_csrf']").attr("content");
                var csrfHeader = $("meta[name='_csrf_header']").attr("content");  // THIS WAS ADDED
                headers[csrfHeader] = csrfToken;

                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/checkCaptcha",
                    data: json,
                    dataType: 'text',
                    timeout: 100000,
                    headers: headers,
                    success: function (data) {
                        console.log(data);
                        if (data === 'true') {
                            $('#loginForm').submit();
                        } else {
                            var html = "Bạn chưa xác nhận mã captcha";
                            $('#errorCaptcha').html(html);
                            $('#errorCaptcha').removeClass("hidden");
                            console.log(html);
                            grecaptcha.reset();
                        }
                    },
                    error: function (e) {
                        console.log("e, " + e);
                        grecaptcha.reset();
                    },
                    done: function (e) {
                        console.log("DONE");
                        grecaptcha.reset();
                    }
                });

            });

        </script>-->
    </body>
</html>
