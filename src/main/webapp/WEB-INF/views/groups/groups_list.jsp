<%-- 
    Document   : event_list
    Created on : Sep 11, 2017, 8:37:16 AM
    Author     : vnpt2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Quản lý người dùng
            <small>Nhóm quyền</small>
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                    <div class="box-body">

                        <form class="form-group-sm" action="${pageContext.request.contextPath}/groups/list" id="searchForm" >
                            <table class="table table-hover" style="width: 60%">
                                <tr>
                                    <td style="width: 90%">
                                        <div class="form-group">
                                            <label for="groupName" class="col-sm-4 control-label">Tên nhóm: </label>
                                            <div class="col-sm-8">
                                                <input type='text' class="form-control" id="groupName" required="required" 
                                                       oninvalid="this.setCustomValidity('Bạn vui lòng nhập tên nhóm')"
                                                       oninput="setCustomValidity('')"
                                                       name="groupName" value="${fn:escapeXml(groupName)}"/>
                                            </div>
                                        </div>
                                    </td>

                                    <td>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <button type="submit" class="btn btn-info btn-sm" id="btnSearch">
                                                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span> Tra cứu
                                                </button>
                                            </div>
                                        </div>
                                    </td>
                                    <sec:authorize access="hasAnyRole('ROLE_GROUP_INSERT')">
                                        <td>
                                            <div class="form-group">
                                                <div class="input-group">

                                                    <a data-toggle="modal" href="#insert-modal" id ="add-form">
                                                        <button type="button" class="btn btn-info btn-sm" data-toggle="modal" 
                                                                data-placement="top" title="Thêm mới" data-target="#objectAdd" >
                                                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Thêm mới
                                                        </button>
                                                    </a>
                                                </div>
                                            </div>
                                        </td>
                                    </sec:authorize>
                                </tr>
                            </table> 
                        </form>
                        <div class="table-responsive">
                            <table id="example2" class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>Tên nhóm</th>
                                        <th>Mô tả</th>
                                        <th>Ngày khởi tạo</th>
                                        <th>Ngày cập nhật</th>
                                        <th>Gán quyền</th>
                                        <th class="edit-actions">Cài đặt</th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <c:forEach items="${list}" var="obj" varStatus="varStatus">
                                        <tr>
                                            <td>${varStatus.count}</td>
                                            <td>${fn:escapeXml(obj.name)}</td>
                                            <td>${fn:escapeXml(obj.description)}</td>
                                            <td>
                                                <fmt:formatDate value="${obj.createTime}" pattern="dd/MM/yyyy HH:mm"/>
                                            </td>
                                            <td>
                                                <fmt:formatDate value="${obj.updateTime}" pattern="dd/MM/yyyy HH:mm"/>
                                            </td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/groups/listRoles?id=${obj.id}" class="btn btn-xs btn-success">Gán quyền</a>
                                            </td>
                                            <td class="center">
                                                <sec:authorize access="hasAnyRole('ROLE_GROUP_DETAIL')">
                                                    <button type="button" class="btn btn-xs btn-success edit-Template " data-toggle="modal" 
                                                            data-placement="top" title="Sửa" onclick="getViaAjax(${obj.id});">
                                                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                                    </button>
                                                </sec:authorize>
                                                <sec:authorize access="hasAnyRole('ROLE_GROUP_DELETE')">
                                                    <button type="button" class="btn btn-xs btn-danger edit-Template " data-toggle="modal" 
                                                            data-placement="top" title="Xóa" onclick="deleteViaAjax(${obj.id});" id="delete-employee">
                                                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                                    </button>
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
    document.getElementById("menu-group").className = "active";

    $(function () {
        $("#example2").DataTable({
            "paging": true,
            "lengthChange": false,
            "ordering": false,
            "info": true,
            "autoWidth": false,
            "searching": false,
            "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
            "columnDefs": [
                {"width": "10%", "targets": 0},
                {"width": "15%", "targets": 1},
                {"width": "15%", "targets": 2},
                {"width": "15%", "targets": 3},
                {"width": "15%", "targets": 4},
                {"width": "10%", "targets": 5},
                {"width": "10%", "targets": 6}
            ]
        });
    });

    function showError(e) {
        if (e.responseText.includes("LOGIN_FINISH_TIME")) {
            alert("Phiên đăng nhập đã hết hạn");
            window.location.href = "${pageContext.request.contextPath}/login";
            return;
        }
        if (e.status === 403) {
            alert("Bạn không có quyền thực hiện chức năng này");
            $('#ojectView').modal("hide");
            return;
        }
        console.log("ERROR: ", e);
        alert("Có lỗi xảy ra");
    }
</script>


<!-- ADD VIEW FORM -->
<%@include file="groups_edit.jsp"%>

<!-- ADD ADD FORM -->
<%@include file="groups_add.jsp"%>