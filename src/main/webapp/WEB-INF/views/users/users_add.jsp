<%-- 
    Document   : users_add
    Created on : Oct 17, 2017, 8:34:38 AM
    Author     : vnpt2
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style>
    .modal-dialog {
        position: absolute;
        top: 40px;
        right: 50px;
        bottom: 0;
        left: 50px;
        z-index: 10040;
        overflow: auto;
        overflow-y: auto;
        width: 70%;
    }
</style>


<!-- Modal -->
<body>
    <div class="modal fade" id="objectAdd" tabindex="-1" role="dialog" aria-labelledby="Thêm mới người dùng"  >
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" >Thêm mới người dùng</h4>
                </div>
                <form action="" method="POST" class="form-horizontal" id="addForm">
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="username-input" class="col-sm-3 control-label">Tên đăng nhập (*)</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="addForm-username" placeholder="username" name="username" required
                                       pattern="[a-zA-Z0-9_-]{6,999}" title="Vui lòng nhập username lớn hơn 6 ký tự và không có ký tự đặc biệt"
                                       maxlength="200">
                            </div>
                        </div>   
                        <div class="form-group">
                            <label for="password-input" class="col-sm-3 control-label">Mật khẩu (*)</label>
                            <div class="col-sm-8">
                                <input type="password" class="form-control" id="addForm-password" 
                                       placeholder="password" name="password" required
                                       pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,20}$" 
                                       onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Mật khẩu không thỏa mãn điều kiện' : '');
                                               if (this.checkValidity())
                                                   form.rePassword.pattern = this.value;" maxlength="200">
                                <i>Mật khẩu phải có ít nhất 1 ký tự hoa, 1 ký tự thường, 1 chữ số, 1 ký tự đặc biệt và dài ít nhất 8 ký tự </i>
                            </div>
                        </div>   
                        <div class="form-group">
                            <label for="rePassword-input" class="col-sm-3 control-label">Nhập lại MK (*)</label>
                            <div class="col-sm-8">
                                <input type="password" class="form-control" id="addForm-rePassword" placeholder="rePassword" name="rePassword" required
                                       pattern="^\S{6,}$"
                                       onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Mật khẩu nhập lại không đúng' : '');" 
                                       maxlength="200">
                            </div>
                        </div>   
                        <div class="form-group">
                            <label for="groupsId-input" class="col-sm-3 control-label">Nhóm quyền (*)</label>
                            <div class="col-sm-8">
                                <select id="addForm-groupId"  class="form-control"  required="required">
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
                                <input type="email" class="form-control" id="addForm-email" placeholder="email" name="email" 
                                       maxlength="200">
                            </div>
                        </div>   
                        <div class="form-group">
                            <label for="phone-input" class="col-sm-3 control-label">Số điện thoại (*)</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="addForm-phone" placeholder="phone" name="phone" required="required"
                                       pattern="[0-9]+" title="Vui lòng nhập đầu số chỉ chứa các số"
                                       maxlength="10" minlength="9" oninvalid="this.setCustomValidity('Nhập số điện thoại đúng định dạng')"
                                       oninput="setCustomValidity('')">
                            </div>
                        </div>   
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
                        <button type="submit" class="btn btn-primary">Thêm mới</button>
                    </div>
                </form>

            </div>
        </div>
    </div>

    <script>
        $(document).on('submit', '#addForm', function (e) {
            e.preventDefault();
            addViaAjax();
        });
        function addViaAjax() {
            var add = {};
            var headers = [];
            var csrfToken = $("meta[name='_csrf']").attr("content");
            var csrfHeader = $("meta[name='_csrf_header']").attr("content");  // THIS WAS ADDED
            headers[csrfHeader] = csrfToken;
            add["password"] = $("#addForm-password").val().trim();
            add["username"] = $("#addForm-username").val().trim();
            add["email"] = $("#addForm-email").val().trim();
            add["phone"] = $("#addForm-phone").val().trim();
            add["groupId"] = $("#addForm-groupId").val().trim();
            
            $.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "${pageContext.request.contextPath}/users/insert",
                data: JSON.stringify(add),
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
                    if (type !== 'error') {
                        $('#objectAdd').modal("hide");
                        window.location.href = "${pageContext.request.contextPath}/users/list";
                    } else {
                        alert($.trim(data.toString()).split('|')[0]);
                    }
                },
                error: function (e) {
                    showError(e);
                },
                done: function (e) {
                    console.log("DONE");
                }
            });

            return false;
        }


    </script>

</body>