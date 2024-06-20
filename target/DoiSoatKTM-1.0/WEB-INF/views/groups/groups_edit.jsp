<%-- 
    Document   : users_edit
    Created on : Oct 17, 2016, 8:34:38 AM
    Author     : vnpt2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
    <div class="modal fade" id="ojectView" tabindex="-1" role="dialog" aria-labelledby="Cập nhật thông tin nhóm quyền" >
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" >Cập nhật thông tin nhóm quyền</h4>
                </div>
                <form action="" method="POST" class="form-horizontal" id="viewForm">
                    <div class="modal-body">
                        <spring:bind path="id">
                            <div class="form-group hidden"> 
                                <label for="id-input" class="col-sm-3 control-label">ID</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="viewForm-id" placeholder="id" name="id" >
                                </div>
                            </div>
                        </spring:bind>

                        <spring:bind path="name">
                            <div class="form-group">
                                <label for="name-input" class="col-sm-3 control-label">Tên nhóm (*)</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="viewForm-name" placeholder="name" name="name" required="required"
                                           oninvalid="this.setCustomValidity('Bạn vui lòng nhập tên nhóm')"
                                           oninput="setCustomValidity('')"
                                           pattern=".*[^ ].*"  title="Nhập đầy đủ thông tin" maxlength="100">
                                </div>
                            </div>   
                        </spring:bind>

                        <spring:bind path="description">
                            <div class="form-group">
                                <label for="description-input" class="col-sm-3 control-label">Mô tả</label>
                                <div class="col-sm-8">
                                    <textarea class="form-control" id="viewForm-description" placeholder="description" name="description" rows="3"
                                              maxlength="200"></textarea>
                                </div>
                            </div>   
                        </spring:bind>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
                        <sec:authorize access="hasAnyRole('ROLE_GROUP_UPDATE')">
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
                url: "${pageContext.request.contextPath}/groups/detail",
                data: json,
                headers: headers,
                dataType: 'json',
                timeout: 100000,
                success: function (data) {
                    console.log("SUCCESS: ", data);
                    if (data.length !== 0) {
                        showForm(data);
                    } else if (data.length !== 0) {
                        alert("Có lỗi xảy ra");
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
            $("#viewForm-name").val(data.name);
            $("#viewForm-description").val(data.description);
            $('#ojectView').modal('show');
        }


        function editViaAjax() {
            var edit = {};
            var headers = [];
            var csrfToken = $("meta[name='_csrf']").attr("content");
            var csrfHeader = $("meta[name='_csrf_header']").attr("content");  // THIS WAS ADDED
            headers[csrfHeader] = csrfToken;
            edit["id"] = $("#viewForm-id").val().trim();
            edit["name"] = $("#viewForm-name").val().trim();
            edit["description"] = $("#viewForm-description").val().trim();

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "${pageContext.request.contextPath}/groups/update",
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
                        window.location.href = "${pageContext.request.contextPath}/groups/list";
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
                        url: "${pageContext.request.contextPath}/groups/delete",
                        dataType: 'text',
                        headers: headers,
                        data: {
                            "id": objId
                        },
                        timeout: 100000,
                        success: function (data) {
                            var type = $.trim(data.toString()).split('|')[1];
                            if (type === undefined) {
                                type = 'error';
                            }
                            alert($.trim(data.toString()).split('|')[0]);
                            window.location.href = "${pageContext.request.contextPath}/groups/list";
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