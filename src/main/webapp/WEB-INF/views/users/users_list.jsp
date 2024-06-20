<%-- 
    Document   : event_list
    Created on : Sep 11, 2017, 8:37:16 AM
    Author     : vnpt2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Quản lý người dùng
            <small>Người dùng</small>
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                    <div class="box-body">
                        <form class="form-group-sm" action="${pageContext.request.contextPath}/users/list" id="searchForm" >
                            <div class="row">
                                <div class="col-xs-6 form-group">
                                    <label>Trạng thái:  </label>  
                                    <select  class="form-control selectpicker" id="status" name="status"
                                             data-show-subtext="true" data-live-search="true" >
                                        <option value="-1">Tất cả</option>
                                        <option value="1">Mở khóa </option>
                                        <option value="0">Khóa</option>
                                    </select>
                                </div>
                                <div class="col-xs-6 form-group">
                                    <label>Từ khóa:  </label>  
                                    <input type='text' class="form-control" id="filter" name="filter" value="${fn:escapeXml(filter)}"/>
                                </div>
                            </div>
                            
                            <div class="row">
                                <div class="col-xs-3">
                                    <div class="form-group">
                                        <button type="submit" class="btn btn-info btn-sm" id="btnSearch">
                                            <span class="glyphicon glyphicon-search" aria-hidden="true"></span> Tra cứu
                                        </button>

                                    </div>
                                </div>
                                <sec:authorize access="hasAnyRole('ROLE_USER_INSERT')">
                                    <div class="col-xs-3">
                                        <a data-toggle="modal" href="#insert-modal" id ="add-form">
                                            <button type="button" class="btn btn-info btn-sm" data-toggle="modal" 
                                                    data-placement="top" title="Thêm mới" data-target="#objectAdd" >
                                                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Thêm mới
                                            </button>
                                        </a>
                                    </div>
                                </sec:authorize>
                            </div>
                        </form>

                        <div class="table-responsive">
                            <table id="example2" class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>Tên đăng nhập</th>
                                        <th>Email</th>
                                        <th>Số điện thoại</th>
                                        <th>Nhóm quyền</th>
                                        
                                        <th>Ngày tạo</th>
                                        <th>Trạng thái</th>
                                        <th class="edit-actions">Cài đặt</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${list}" var="obj" varStatus="varStatus">
                                        <tr>
                                            <td>${varStatus.count}</td>
                                            <td>${fn:escapeXml(obj.username)}</td>
                                            <td>${fn:escapeXml(obj.email)}</td>
                                            <td>${fn:escapeXml(obj.phone)}</td>
                                            <td>${fn:escapeXml(obj.groupName)}</td>
                                            
                                            <td>
                                                <fmt:formatDate value="${obj.createTime}" pattern="dd/MM/yyyy HH:mm"/>
                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${obj.status == 1}">
                                                        Mở khóa
                                                        <sec:authorize access="hasAnyRole('ROLE_USER_ACTIVE')">
                                                            |
                                                            <c:choose>
                                                                <c:when test="${sessionScope.auth.username == obj.username}">
                                                                    <button type="button" class="btn btn-xs btn-danger edit-Template disabled" data-toggle="modal" 
                                                                            data-placement="top" disable="disable" title="Khóa" >
                                                                        <span class="fa fa-lock" aria-hidden="true"></span>
                                                                    </button>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <button type="button" class="btn btn-xs btn-danger edit-Template " data-toggle="modal" 
                                                                            data-placement="top" title="Khóa" onclick="lock(${obj.id});">
                                                                        <span class="fa fa-lock" aria-hidden="true"></span>
                                                                    </button>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </sec:authorize>
                                                    </c:when>
                                                    <c:otherwise>
                                                        Khóa
                                                        <sec:authorize access="hasAnyRole('ROLE_USER_ACTIVE')">
                                                            |
                                                            <c:choose>
                                                                <c:when test="${sessionScope.auth.username == obj.username}">
                                                                    <button type="button" class="btn btn-xs btn-success edit-Template disabled" data-toggle="modal" 
                                                                            data-placement="top" disable="disable" title="Mở khóa" >
                                                                        <span class="fa fa-unlock" aria-hidden="true"></span>
                                                                    </button>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <button type="button" class="btn btn-xs btn-success edit-Template " data-toggle="modal" 
                                                                            data-placement="top" title="Mở khóa" onclick="active(${obj.id});">
                                                                        <span class="fa fa-unlock" aria-hidden="true"></span>
                                                                    </button>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </sec:authorize>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>

                                            <td class="center">
                                                <sec:authorize access="hasAnyRole('ROLE_USER_DETAIL')">
                                                    <button type="button" class="btn btn-xs btn-success edit-Template " data-toggle="modal" 
                                                            data-placement="top" title="Sửa" onclick="getViaAjax(${obj.id});">
                                                        <span class="fa fa-pencil" aria-hidden="true"></span>
                                                    </button>
                                                </sec:authorize>
                                                <sec:authorize access="hasAnyRole('ROLE_USER_DELETE')">
                                                    <button type="button" class="btn btn-xs btn-danger edit-Template " data-toggle="modal" 
                                                            data-placement="top" title="Xóa" onclick="deleteViaAjax(${obj.id});" id="delete-employee">
                                                        <span class="fa fa-trash" aria-hidden="true"></span>
                                                    </button><br>
                                                </sec:authorize>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>
                        </div>
                    </div>

                </div>
                <!-- /.box -->
            </div>
            <!-- /.col -->
        </div>
        <!-- /.row -->
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
<script>
    document.getElementById("menu-user-parent").className = "active";
    document.getElementById("menu-user").className = "active";

    $(function () {
        $('#status').val('${fn:escapeXml(status)}');

        $("#example2").DataTable({
            "paging": true,
            "lengthChange": false,
            "ordering": false,
            "info": true,
            "autoWidth": false,
            "searching": false,
            "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
            "columnDefs": [
                {"width": "3%", "targets": 0},
                {"width": "8%", "targets": 1},
                {"width": "8%", "targets": 2},
                {"width": "8%", "targets": 3},
                {"width": "8%", "targets": 4},
                {"width": "8%", "targets": 5},
                {"width": "8%", "targets": 6},
                {"width": "8%", "targets": 7}
            ]
        });
    });

    function deleteViaAjax(objId) {
        if (objId === null || objId === "") {
            objId = parseInt($("#viewForm-id").val());
        }

        if (objId !== null) {
            var confirm_check = confirm("Bạn có chắc chắn muốn xóa không?");
            if (confirm_check === true) {
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/users/delete",
                    dataType: 'text',
                    data: {
                        "id": objId
                    },
                    timeout: 100000,
                    success: function (data) {
                        if (data.includes("LOGIN_FINISH_TIME")) {
                            alert("Phiên đăng nhập đã hết hạn");
                            window.location.href = "${pageContext.request.contextPath}/login";
                            return;
                        }
                        var type = $.trim(data.toString()).split('|')[1];
                        if (type === undefined) {
                            type = 'error';
                        }
                        alert($.trim(data.toString()).split('|')[0]);
                        window.location.href = "${pageContext.request.contextPath}/users/list";
                    },
                    error: function (e) {
                        showError(e);
                    },
                    done: function (e) {
                        console.log("DONE");
                    }
                });

            } else {
                return;
            }
        } else {
            return;
        }
    }

    function active(objId) {

        if (objId !== null) {
            var confirm_check = confirm("Bạn có muốn mở khóa người dùng này không?");
            var headers = [];
            var csrfToken = $("meta[name='_csrf']").attr("content");
            var csrfHeader = $("meta[name='_csrf_header']").attr("content");  // THIS WAS ADDED
            headers[csrfHeader] = csrfToken;
            if (confirm_check === true) {
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/users/active",
                    dataType: 'text',
                    headers: headers,
                    data: {
                        "id": objId
                    },
                    timeout: 100000,
                    success: function (data) {
                        if (data.includes("LOGIN_FINISH_TIME")) {
                            alert("Phiên đăng nhập đã hết hạn");
                            window.location.href = "${pageContext.request.contextPath}/login";
                            return;
                        }
                        var type = $.trim(data.toString()).split('|')[1];
                        if (type === undefined) {
                            type = 'error';
                        }
                        alert($.trim(data.toString()).split('|')[0]);
                        window.location.href = "${pageContext.request.contextPath}/users/list";
                    },
                    error: function (e) {
                        showError(e);
                    }
                });

            } else {
                return;
            }
        } else {
            return;
        }
    }

    function lock(objId) {

        if (objId !== null) {
            var confirm_check = confirm("Bạn có muốn khóa người dùng này không?");
            var headers = [];
            var csrfToken = $("meta[name='_csrf']").attr("content");
            var csrfHeader = $("meta[name='_csrf_header']").attr("content");  // THIS WAS ADDED
            headers[csrfHeader] = csrfToken;
            if (confirm_check === true) {
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/users/lock",
                    dataType: 'text',
                    headers: headers,
                    data: {
                        "id": objId
                    },
                    timeout: 100000,
                    success: function (data) {
                        if (data.includes("LOGIN_FINISH_TIME")) {
                            alert("Phiên đăng nhập đã hết hạn");
                            window.location.href = "${pageContext.request.contextPath}/login";
                            return;
                        }
                        var type = $.trim(data.toString()).split('|')[1];
                        if (type === undefined) {
                            type = 'error';
                        }
                        alert($.trim(data.toString()).split('|')[0]);
                        window.location.href = "${pageContext.request.contextPath}/users/list";
                    },
                    error: function (e) {
                        showError(e);
                    }
                });

            } else {
                return;
            }
        } else {
            return;
        }
    }

    function showError(e) {
        if (e.responseText.includes("LOGIN_FINISH_TIME")) {
            alert("Phiên đăng nhập đã hết hạn");
            window.location.href = "${pageContext.request.contextPath}/login";
            return;
        }
        if (e.status === 403) {
            alert("Bạn không có quyền thực hiện chức năng này");
            return;
        }
        console.log("ERROR: ", e);
        alert("Có lỗi xảy ra");
    }
</script>

<!-- ADD VIEW FORM -->
<%@include file="users_edit.jsp"%>

<!-- ADD ADD FORM -->
<%@include file="users_add.jsp"%>
