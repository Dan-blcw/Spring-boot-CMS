<%-- 
    Document   : header
    Created on : Sep 9, 2017, 10:06:20 AM
    Author     : vnpt2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<header class="main-header">
    <!-- Logo -->
    <a href="#" class="logo">
        <!-- mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-mini"><b>D</b>S</span>
        <!-- logo for regular state and mobile devices -->
        <span class="logo-lg"><b>DS</b>KTM</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </a>

        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                <!-- User Account: style can be found in dropdown.less -->
                <li class="dropdown user user-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <img src="${pageContext.request.contextPath}/dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
                        <span class="hidden-xs">${pageContext.request.userPrincipal.name}</span>
                    </a>
                    <ul class="dropdown-menu">
                        <!-- User image -->
                        <li class="user-header">
                            <img src="${pageContext.request.contextPath}/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">

                            <p>
                                ${pageContext.request.userPrincipal.name}
                                <small>DateCreate: ${sessionScope.auth.createTime}</small>
                            </p>
                        </li>
                        
                        <!-- Menu Footer-->
                        <li class="user-footer">
                            <div class="pull-left">
                                <a href="${pageContext.request.contextPath}/users/updatePersonal" class="btn btn-default btn-flat">Hồ sơ</a>
                            </div>
                            <div class="pull-right">
                                <a href="${pageContext.request.contextPath}/logout" class="btn btn-default btn-flat">Đăng xuất</a>
                            </div>
                        </li>
                    </ul>
                </li>
              
            </ul>
        </div>
    </nav>
</header>
