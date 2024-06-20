<%-- 
    Document   : users_edit
    Created on : Oct 17, 2016, 8:34:38 AM
    Author     : vnpt2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style>
    .modal-dialog {
        position: absolute;
        top: 60px;
        right: 50px;
        bottom: 0;
        left: 50px;
        z-index: 10040;
        overflow: auto;
        overflow-y: auto;
    }
</style>




<!-- Modal -->
<body>
    <div class="modal fade" id="ojectView" tabindex="-1" role="dialog" aria-labelledby="Cập nhật thông tin người dùng" >
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" >Cập nhật thông tin người dùng</h4>
                </div>
                <form action="" method="POST" class="form-horizontal" id="viewForm">
                    <div class="modal-body">

                        <div class="form-group hidden"> 
                            <label for="id-input" class="col-sm-3 control-label">ID</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="viewForm-id" placeholder="id" name="id" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="username-input" class="col-sm-3 control-label">Tên đăng nhập</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="viewForm-username" placeholder="username" name="username" readonly
                                       pattern=".*[^ ].*"  title="Nhập đầy đủ thông tin" maxlength="200">
                            </div>
                        </div>   

                        <div class="form-group">
                            <label  class="col-sm-3 control-label">Nhóm quyền (*)</label>
                            <div class="col-sm-8">
                                <select id="viewForm-groupId"  class="form-control"  required="required">
                                    <c:if test="${not empty listGroups}"> 
                                        <c:forEach var="groups" items="${listGroups}">
                                            <option value="${groups.getId()}" label="${groups.getName()}" />
                                        </c:forEach>
                                    </c:if>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email-input" class="col-sm-3 control-label">Email</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="viewForm-email" placeholder="email" name="email" 
                                       pattern=".*[^ ].*"  title="Nhập đầy đủ thông tin" maxlength="200">
                            </div>
                        </div>   
                        <div class="form-group">
                            <label for="phone-input" class="col-sm-3 control-label">Số điện thoại (*)</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="viewForm-phone" placeholder="phone" name="phone" required="required"
                                       pattern="[0-9]+" title="Vui lòng nhập đầu số chỉ chứa các số" maxlength="10" minlength="9"
                                       oninvalid="this.setCustomValidity('Nhập số điện thoại đúng định dạng')"
                                       oninput="setCustomValidity('')">
                            </div>
                        </div>   
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
                        <sec:authorize access="hasAnyRole('ROLE_USER_UPDATE')">
                            <button type="submit" class="btn btn-primary">Cập nhật</button>
                        </sec:authorize>
                    </div>
                </form>

            </div>
        </div>
    </div>

    <script>
        $(document).on('submit', '#viewForm', function (e) {
            e.preventDefault();
            editViaAjax();
        });
        function getViaAjax(objId) {
            var json = {
                "id": objId
            };
            var headers = [];
            var csrfToken = $("meta[name='_csrf']").attr("content");
            var csrfHeader = $("meta[name='_csrf_header']").attr("content");  // THIS WAS ADDED
            headers[csrfHeader] = csrfToken;
            $.ajax({
                type: "GET",
                contentType: "application/json; charset=utf-8",
                url: "${pageContext.request.contextPath}/users/detail",
                data: json,
                headers: headers,
                dataType: 'json',
                timeout: 100000,
                success: function (data) {
                    if (data.length !== 0) {
                        showForm(data);
                    } else if (data.length !== 0) {
                        new PNotify({
                            title: "Thông báo",
                            text: "Có lỗi xảy ra",
                            type: "error",
                            delay: 3000,
                            styling: "jqueryui",
                            addclass: 'custom-notif',
                            mouse_reset: false,
                            buttons: {
                                sticker: false,
                                closer_hover: false
                            }
                        });
                    } else {
                        window.location.href = "${pageContext.request.contextPath}/logout";
                    }
                },
                error: function (e) {
                    showError(e);
                }
            });
        }
        function showForm(data) {
            $("#viewForm-id").val(data.id);
            $("#viewForm-username").val(data.username);
            $("#viewForm-email").val(data.email);
            $("#viewForm-phone").val(data.phone);
            $("#viewForm-fullName").val(data.fullName);
            $("#viewForm-groupId").val(data.groupId);
            $('#ojectView').modal('show');
        }
        function editViaAjax() {
            var edit = {};
            edit["id"] = $("#viewForm-id").val().trim();
            edit["username"] = $("#viewForm-username").val().trim();
            edit["email"] = $("#viewForm-email").val().trim();
            edit["phone"] = $("#viewForm-phone").val().trim();
            edit["groupId"] = $("#viewForm-groupId").val().trim();

            var headers = [];
            var csrfToken = $("meta[name='_csrf']").attr("content");
            var csrfHeader = $("meta[name='_csrf_header']").attr("content");  // THIS WAS ADDED
            headers[csrfHeader] = csrfToken;
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "${pageContext.request.contextPath}/users/update",
                data: JSON.stringify(edit),
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
                        window.location.href = "${pageContext.request.contextPath}/users/list";
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

        function deleteViaAjax(objId) {
            if (objId === null || objId === "") {
                objId = parseInt($("#viewForm-id").val());
            }
            var headers = [];
            var csrfToken = $("meta[name='_csrf']").attr("content");
            var csrfHeader = $("meta[name='_csrf_header']").attr("content");  // THIS WAS ADDED
            headers[csrfHeader] = csrfToken;
            if (objId !== null) {
                var confirm_check = confirm("Bạn có chắc chắn muốn xóa không?");
                if (confirm_check === true) {
                    $.ajax({
                        type: "POST",
                        url: "${pageContext.request.contextPath}/users/delete",
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
    </script>

</body>