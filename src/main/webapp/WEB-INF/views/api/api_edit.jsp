<%-- 
    Document   : api_edit
    Created on : Aug 18, 2023, 9:00:53 AM
    Author     : PHU MINH HUONG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<style>
    .modal-dialog {
        position: absolute;
        top: 60px;
        right: 70px;
        bottom: 0;
        left: 70px;
        z-index: 10040;
        overflow: auto;
        overflow-y: auto;
        width: 32%;
    }
</style>

<body>
    <div class="modal fade" id="ojectView" tabindex="-1" role="dialog" aria-labelledby="Cập nhật thông tin API" >
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" >Cập nhật thông tin API</h4>
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
                            <label for="api-input" class="col-sm-3 control-label">Tên API</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="viewForm-apiname" placeholder="apiname" name="apiname" readonly
                                       pattern=".*[^ ].*"  title="Nhập đầy đủ thông tin" maxlength="200">
                            </div>
                        </div>  
                        <div class="form-group">
                            <label for="authen-input" class="col-sm-3 control-label">Authentication</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="viewForm-authentication" placeholder="authentication" name="authentication" required
                                       pattern=".*[^ ].*" title="Nhập đầy đủ thông tin"
                                       oninvalid="this.setCustomValidity('Bạn vui lòng nhập authentication')"
                                       maxlength="100">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="request-input" class="col-sm-3 control-label">Request :</label>
                            <div>
                                <input type="text" class="form-control" id="addForm-request" placeholder="request" name="request" 
                                       maxlength="20">
                                <button type = "button"id="showTBoxButton">Add field</button>
                                <div id="tBoxContainer"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="response-input" class="col-sm-3 control-label">Response :</label>
                            <div>
                                <input type="text" class="form-control" id="addForm-response" placeholder="response" name="response"
                                       maxlength="20">
                                <button type = "button" id="showTBoxButton2">Add field</button>
                                <div id="tBoxContainer2"></div>
                            </div>
                        </div>     
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
                        <sec:authorize access="hasAnyRole('ROLE_API_UPDATE')">
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
        var textBoxContainer = document.getElementById("tBoxContainer");
        var showTextBoxButton = document.getElementById("showTBoxButton");
        var textBoxContainer2 = document.getElementById("tBoxContainer2");
        var showTextBoxButton2 = document.getElementById("showTBoxButton2");

        var n_request = 0;
        var n_response = 0;
        var dtarequest;
        var dtaresponse;
        var jsoutputRequest;
        var jsoutputResponse;
        function getApiAjax(objId) {
            var json = {
                "id": objId
            };
            var headers = [];
            var csrfToken = $("meta[name='_csrf']").attr("content");
            var csrfHeader = $("meta[name='_csrf_header']").attr("content");
            headers[csrfHeader] = csrfToken;
            $.ajax({
                type: "GET",
                contentType: "application/json; charset=utf-8",
            url: "${pageContext.request.contextPath}/api/detail",
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
            $("#viewForm-apiname").val(data.apiname);
            $("#viewForm-authentication").val(data.authentication);
            $("#addForm-request").val(data.request);
            $("#addForm-response").val(data.response);
            jsoutputRequest = $("#addForm-request").val();
            jsoutputResponse = $("#addForm-response").val();
            dtarequest = JSON.parse(jsoutputRequest);
            dtaresponse = JSON.parse(jsoutputResponse);
            createAutoTextbox();
            $('#ojectView').modal('show');

        }
        
        
        function createAutoTextbox() {
            while (textBoxContainer.firstChild) {
                textBoxContainer.removeChild(textBoxContainer.firstChild);
            }

            while (textBoxContainer2.firstChild) {
                textBoxContainer2.removeChild(textBoxContainer2.firstChild);
            }
            for (var key in dtarequest) {
                var textBoxKey = createAutoTB("Key", ++n_request, "text-box", key);
                var textBoxValue = createAutoTB("Value", n_request, "text-box", dtarequest[key]);
                var deleteButton = createDeleteButton(n_request);

                var container = createContainer(textBoxKey, textBoxValue, deleteButton);

                textBoxContainer.appendChild(container);
            }

            for (var key in dtaresponse) {
                var textBoxKey = createAutoTB("Key", ++n_response, "text-box-2", key);
                var textBoxValue = createAutoTB("Value", n_response, "text-box-2", dtaresponse[key]);
                var deleteButton = createDeleteButton(n_response);

                var container = createContainer(textBoxKey, textBoxValue, deleteButton);

                textBoxContainer2.appendChild(container);
            }
        }
        document.addEventListener("DOMContentLoaded", function () {


            showTextBoxButton.addEventListener("click", function () {
                var textBoxKey = createTextBox("Key", ++n_request, "text-box");
                var textBoxValue = createTextBox("Value", n_request, "text-box");
                var deleteButton = createDeleteButton(n_request);

                var container = createContainer(textBoxKey, textBoxValue, deleteButton);

                textBoxContainer.appendChild(container);
            });

            showTextBoxButton2.addEventListener("click", function () {
                var textBoxKey = createTextBox("Key", ++n_response, "text-box-2");
                var textBoxValue = createTextBox("Value", n_response, "text-box-2");
                var deleteButton = createDeleteButton(n_response);

                var container = createContainer(textBoxKey, textBoxValue, deleteButton);

                textBoxContainer2.appendChild(container);
            });

        });
        function createTextBox(type, index, name) {
            var textBox = document.createElement("input");
            textBox.type = "text";
            textBox.className = name;
            textBox.id = type + "-" + index;
            textBox.placeholder = type + " " + index;
            return textBox;
        }

        function createAutoTB(type, index, name, val) {
            var textBox = document.createElement("input");
            textBox.type = "text";
            textBox.className = name;
            textBox.id = type + "-" + index;
            textBox.placeholder = type + " " + index;
            textBox.value = val;
            return textBox;
        }
        function createDeleteButton(number) {
            var deleteButton = document.createElement("button");
            deleteButton.innerText = "X";
            deleteButton.className = "delete-button";
            deleteButton.addEventListener("click", function () {
                number--;
                deleteButton.parentElement.remove();
            });
            return deleteButton;
        }

        function createContainer(textBoxKey, textBoxValue, deleteButton) {
            var container = document.createElement("div");
            container.appendChild(textBoxKey);
            container.appendChild(textBoxValue);
            container.appendChild(deleteButton);
            return container;
        }

        function updateDataObject(name) {
            dtarequest = {};
            var textBoxes = document.getElementsByClassName(name);
            for (var i = 0; i < textBoxes.length; i += 2) {
                var key = textBoxes[i].value;
                var value = textBoxes[i + 1].value;
                if (key) {
                    dtarequest[key] = value;
                }
            }
            jsoutputRequest = JSON.stringify(dtarequest);
    //            console.log(jsonOutput);
        }

        function updateDataObject2(name) {
            dtaresponse = {};
            var textBoxes = document.getElementsByClassName(name);
            for (var i = 0; i < textBoxes.length; i += 2) {
                var key = textBoxes[i].value;
                var value = textBoxes[i + 1].value;
                if (key) {
                    dtaresponse[key] = value;
                }
            }
            jsoutputResponse = JSON.stringify(dtaresponse);
    //            console.log(jsonOutput);
        }
        function editViaAjax() {
            updateDataObject("text-box");
            updateDataObject2("text-box-2");
            var edit = {};
            var headers = [];
            var csrfToken = $("meta[name='_csrf']").attr("content");
            var csrfHeader = $("meta[name='_csrf_header']").attr("content");  // THIS WAS ADDED
            headers[csrfHeader] = csrfToken;
            edit["id"] = $("#viewForm-id").val().trim();
            edit["apiname"] = $("#viewForm-apiname").val().trim();
            edit["authentication"] = $("#viewForm-authentication").val().trim();
            edit["request"] = jsoutputRequest;
            edit["response"] = jsoutputResponse;

            $.ajax({
                type: "POST",
                contentType: "application/json",
            url: "${pageContext.request.contextPath}/api/update",
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
                    window.location.href = "${pageContext.request.contextPath}/api/list";
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
                    url: "${pageContext.request.contextPath}/api/delete",
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
                        window.location.href = "${pageContext.request.contextPath}/api/list";
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