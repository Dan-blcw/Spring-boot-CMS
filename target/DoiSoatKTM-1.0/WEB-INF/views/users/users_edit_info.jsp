<%-- 
    Document   : event_list
    Created on : Sep 11, 2017, 8:37:16 AM
    Author     : vnpt2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Quản lý hệ thống
            <small>Cập nhật thông tin cá nhân</small>
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                    <div class="box-body">

                        <form:form action="update" method="POST" class="form-horizontal" id="viewInfoForm" modelAttribute="usersDetail">
                            <div class="modal-body">

                                <div class="form-group hidden"> 
                                    <label for="id-input" class="col-sm-3 control-label">ID</label>
                                    <div class="col-sm-8">
                                        <form:input type="text" class="form-control" id="viewForm-id" placeholder="id" name="id" path="id" />
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label for="username-input" class="col-sm-3 control-label">Tên đăng nhập</label>
                                    <div class="col-sm-8">
                                        <form:input type="text" class="form-control" id="viewForm-username" placeholder="username" name="username" 
                                                    readonly="true"
                                                    pattern=".*[^ ].*"  title="Nhập đầy đủ thông tin" maxlength="100" path="username" />
                                    </div>
                                </div>   

                                <div class="form-group">
                                    <label for="email-input" class="col-sm-3 control-label">Email</label>
                                    <div class="col-sm-8">
                                        <form:input type="text" class="form-control" id="viewForm-email" placeholder="email" name="email" 
                                                    pattern=".*[^ ].*"  title="Nhập đầy đủ thông tin" maxlength="100" path="email" />
                                    </div>
                                </div> 

                                <div class="form-group">
                                    <label for="phone-input" class="col-sm-3 control-label">Số điện thoại</label>
                                    <div class="col-sm-8">
                                        <form:input type="text" class="form-control" id="viewForm-phone" placeholder="phone" name="phone" 
                                                    pattern=".*[^ ].*"  title="Nhập đầy đủ thông tin" maxlength="100" path="phone" />
                                    </div>
                                </div>     
                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
                                <button type="submit" class="btn btn-primary">Cập nhật</button>
                            </div>
                        </form:form>
                    </div>
                    <!-- /.box-body -->
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
    document.getElementById("menu-info-parent").className = "active";
    document.getElementById("menu-updatePersonal").className = "active";

    $(document).on('submit', '#viewInfoForm', function (e) {
        e.preventDefault();
        editInfoViaAjax();
    });

    function editInfoViaAjax() {
        var edit = {};
        var headers = [];
        var csrfToken = $("meta[name='_csrf']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");  // THIS WAS ADDED
        headers[csrfHeader] = csrfToken;
        edit["id"] = $("#viewForm-id").val().trim();
        edit["username"] = $("#viewForm-username").val().trim();
        edit["email"] = $("#viewForm-email").val().trim();
        edit["phone"] = $("#viewForm-phone").val().trim();

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "${pageContext.request.contextPath}/users/updatePersonal",
            data: JSON.stringify(edit),
            dataType: 'text',
            headers: headers,
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

                if (type !== 'error') {
                    window.location.href = "${pageContext.request.contextPath}/users/updatePersonal";
                }
            },
            error: function (e) {
                showError(e);
            },
            done: function (e) {
                console.log("DONE");
            }
        });
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


