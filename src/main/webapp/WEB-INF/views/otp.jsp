<%-- 
    Document   : otp
    Created on : Aug 28, 2018, 9:49:02 AM
    Author     : tranl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
                <a href="${pageContext.request.contextPath}/login"><b>SMS </b>BroadCast</a>
            </div>
            <!-- /.login-logo -->
            <div class="login-box-body">

                <form method="post" action=""  id="viewForm">
                    <label for="exampleInputEmail1">OTP</label>  
                    <div class="input-group input-group-sm">
                        <input type="text" id="viewForm-otp" class="form-control">
                        <span class="input-group-btn ">    
                            <button type="button" class="btn btn-info btn-flat"></button>
                        </span>
                    </div>
                    <div style="display: none" class="form-group has-error" id="viewForm-has-error">
                        <span class="danger" style="color: red;font-weight: bold" id="otp_message_error_id">OTP không chính xác</span>
                    </div>

                    <!-- /.box-body -->
                    <div class="box-footer">
                        <button type="submit" class="btn btn-primary">Đăng nhập</button>                           
                    </div>
                </form>
            </div>
        </div>

    </body>
    <!-- jQuery 2.2.3 -->
    <script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
    <!-- Bootstrap 3.3.6 -->
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <!-- iCheck -->
    <script src="${pageContext.request.contextPath}/plugins/iCheck/icheck.min.js"></script>

    <script>
        $(document).on('submit', '#viewForm', function (e) {
            e.preventDefault();
            checkOtp();
        });

        function checkOtp() {
            var json = {};
            json["otp"] = $("#viewForm-otp").val().trim();
            
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/checkOtp",
                data: json,
                dataType: 'text',
                timeout: 100000,
                success: function (data) {
                    var type = $.trim(data.toString()).split('|')[1];
                    if (type === undefined) {
                        type = 'error';
                    }
                    if (type !== 'error') {
                        if (type === 'login') {
                            window.location.href = "${pageContext.request.contextPath}/login";
                        }
                        window.location.href = "${pageContext.request.contextPath}/users/updatePersonal";
                    } else {
                        $("#viewForm-otp").val('');
                        $("#viewForm-otp").focus();
                        $('#viewForm-has-error').css("display", "block");
                        $('#otp_message_error_id').text($.trim(data.toString()).split('|')[0]);
                    }
                },
                error: function (e) {
                    console.log("ERROR: ", e);
                    alert("Có lỗi xảy ra");
                }
            });
        }
    </script>
</html>
