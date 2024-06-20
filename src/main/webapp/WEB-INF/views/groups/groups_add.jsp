<%-- 
    Document   : users_add
    Created on : Oct 17, 2017, 8:34:38 AM
    Author     : vnpt2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
    <div class="modal fade" id="objectAdd" tabindex="-1" role="dialog" aria-labelledby="Thêm mới thể loại"  >
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" >Thêm mới nhóm quyền</h4>
                </div>
                <form action="" method="POST" class="form-horizontal" id="addForm">
                    <div class="modal-body">

                        <spring:bind path="name">
                            <div class="form-group">
                                <label for="name-input" class="col-sm-3 control-label">Tên nhóm (*)</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="addForm-name" placeholder="name" name="name" required
                                           pattern=".*[^ ].*" title="Nhập đầy đủ thông tin"
                                           oninvalid="this.setCustomValidity('Bạn vui lòng nhập tên nhóm')"
                                           oninput="setCustomValidity('')"
                                           maxlength="100">
                                </div>
                            </div>   
                        </spring:bind>



                        <spring:bind path="description">
                            <div class="form-group">
                                <label for="description-input" class="col-sm-3 control-label">Mô tả</label>
                                <div class="col-sm-8">
                                    <textarea class="form-control" id="addForm-description" placeholder="description" name="description" rows="3"
                                              maxlength="200"></textarea>
                                </div>
                            </div>   
                        </spring:bind>


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
            add["name"] = $("#addForm-name").val().trim();
            add["description"] = $("#addForm-description").val().trim();

            $.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "${pageContext.request.contextPath}/groups/insert",
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
                        window.location.href = "${pageContext.request.contextPath}/groups/list";
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