<%-- 
    Document   : menu
    Created on : Sep 9, 2017, 10:48:41 AM
    Author     : vnpt2
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="${pageContext.request.contextPath}/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>${pageContext.request.userPrincipal.name}</p>
                <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
        </div>
        <!-- search form -->
        <!--        <div class="sidebar-form">
                    <div class="input-group">
                        <input type="text" name="q" class="form-control" placeholder="Search...">
                        <span class="input-group-btn">
                            <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                            </button>
                        </span>
                    </div>
                </div>-->
        <!-- /.search form -->
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="header">MENU</li>


            <sec:authorize access="hasAnyRole('ROLE_USER_LIST','ROLE_GROUP_LIST')"> 
                <li class="treeview" id="menu-user-parent">
                    <a href="#">
                        <i class="fa fa-viacoin"></i> <span>Quản lý hệ thống</span>
                        <span class="pull-right-container">
                            <span class="label label-primary pull-right"></span>
                        </span>
                    </a>
                    <ul class="treeview-menu">
                        <sec:authorize access="hasAnyRole('ROLE_USER_LIST')">
                            <li id="menu-user"><a href="${pageContext.request.contextPath}/users/list"><i class="fa fa-user"></i> Quản lý người dùng CMS</a></li>
                            </sec:authorize>

                        <sec:authorize access="hasAnyRole('ROLE_EMPLOYEE_LIST')">
                            <li id="menu-employee"><a href="${pageContext.request.contextPath}/tblEmployee/list"><i class="fa fa-user"></i> Quản lý tài khoản bán hàng</a></li>
                            </sec:authorize>

                        <sec:authorize access="hasAnyRole('ROLE_GROUP_LIST')">
                            <li id="menu-group"><a href="${pageContext.request.contextPath}/groups/list"><i class="fa fa-anchor"></i> Quản lý Nhóm quyền</a></li>
                            </sec:authorize>
                        <sec:authorize access="hasAnyRole('ROLE_API_LIST')">
                            <li id="menu-api"><a href="${pageContext.request.contextPath}/api/list"><i class="fa fa-edit"></i> Quản lý API</a></li>
                            </sec:authorize>
                        <sec:authorize access="hasAnyRole('ROLE_API_DELETE')">
                            <li id="menu-sf"><a href="${pageContext.request.contextPath}/storagefile/list"><i class="fa fa-viacoin"></i> History StorageFile</a></li>
                            </sec:authorize>
                    </ul>
                </li>
            </sec:authorize>
                
            <li class="treeview" id="menu-info-parent">
                <a href="#">
                    <i class="fa fa-info"></i>
                    <span>Thông tin</span>
                    <span class="pull-right-container">
                        <span class="label label-primary pull-right"></span>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li id="menu-updatePersonal">
                        <a href="${pageContext.request.contextPath}/users/updatePersonal" >
                            <i class="fa fa-edit"></i> <span>Cập nhật thông tin cá nhân</span>
                        </a>
                    </li>
                    <li id="menu-changePass">
                        <a href="${pageContext.request.contextPath}/users/changePass" >
                            <i class="fa fa-key"></i> <span>Đổi mật khẩu</span>
                        </a>
                    </li>
                    <li id="menu-logout">
                        <a href="${pageContext.request.contextPath}/logout" >
                            <i class="fa fa-sign-out"></i> <span>Đăng xuất</span>
                        </a>
                    </li>
                </ul>
            </li>

        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
