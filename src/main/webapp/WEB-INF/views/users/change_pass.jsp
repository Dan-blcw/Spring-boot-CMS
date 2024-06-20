<%-- 
    Document   : event_list
    Created on : Sep 11, 2017, 8:37:16 AM
    Author     : vnpt2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Thông tin chung
            <small>Thay đổi mật khẩu</small>
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                    <div class="box-body">

                        <form action="" method="POST" class="form-horizontal" id="viewInfoForm" >
                            <div class="modal-body">

                                <div class="form-group">
                                    <label for="username-input" class="col-sm-3 control-label">Tên đăng nhập</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="viewForm-username" placeholder="username" name="username" 
                                               readonly="true"
                                               value="${fn:escapeXml(username)}"/>
                                    </div>
                                </div>   

                                <div class="form-group">
                                    <label for="oldPass-input" class="col-sm-3 control-label">Mật khẩu cũ</label>
                                    <div class="col-sm-8">
                                        <input type="password" class="form-control" id="viewForm-oldPass" autocomplete="new-password" 
                                               placeholder="oldPass" name="oldPass" required 
                                               pattern="^\S{6,}$" 
                                               maxlength="100">
                                    </div>
                                </div>   

                                <div class="form-group">
                                    <label for="newPass-input" class="col-sm-3 control-label">Mật khẩu mới</label>
                                    <div class="col-sm-8">
                                        <input type="password" class="form-control" id="viewForm-newPass" 
                                               placeholder="newPass" name="newPass" required
                                               pattern="^\S{6,}$" 
                                               onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Mật khẩu phải có ít nhất 6 ký tự' : '');
                                                       if (this.checkValidity())
                                                           form.renewPass.pattern = this.value;" maxlength="100">
                                    </div>
                                </div>   

                                <div class="form-group">
                                    <label for="renewPass-input" class="col-sm-3 control-label">Nhập lại mật khẩu mới</label>
                                    <div class="col-sm-8">
                                        <input type="password" class="form-control" id="viewForm-renewPass" placeholder="renewPass" name="renewPass" required
                                               pattern="^\S{6,}$"
                                               onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Mật khẩu nhập lại không đúng' : '');" 
                                               maxlength="100">
                                    </div>
                                </div>   


                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
                                <button type="submit" class="btn btn-primary">Cập nhật</button>
                            </div>
                        </form>
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
    document.getElementById("menu-changePass").className = "active";
    document.getElementById("menu-info-parent").className = "active";
    $("#viewForm-oldPass").val("");

    $(document).on('submit', '#viewInfoForm', function (e) {
        e.preventDefault();
        editInfoViaAjax();
    });

    function editInfoViaAjax() {
        var json = {};
        var headers = [];
        var csrfToken = $("meta[name='_csrf']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");  // THIS WAS ADDED
        headers[csrfHeader] = csrfToken;
        json["oldPass"] = $("#viewForm-oldPass").val().trim();
        json["newPass"] = $("#viewForm-newPass").val().trim();

        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/users/changePass",
            data: json,
            headers: headers,
            dataType: 'text',
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
                    window.location.href = "${pageContext.request.contextPath}/logout";
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


