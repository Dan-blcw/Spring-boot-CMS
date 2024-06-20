<%-- 
    Document   : api_add
    Created on : Aug 16, 2023, 2:28:40 PM
    Author     : PHU MINH HUONG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="pf" uri="http://java.sun.com/jsp/jstl/functions" %>
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


 Modal 
<body>
    <div class="modal fade" id="objectAdd" tabindex="-1" role="dialog" aria-labelledby="Thêm mới API"  >
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" >Thêm mới API</h4>
                </div>
                <form action="" method="POST" class="form-horizontal" id="addForm">
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="apiname-input" class="col-sm-3 control-label">Tên API: </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="addForm-name" placeholder="name" name="name"
                                           maxlength="100">
                            </div>
                        </div>   
                        <div class="form-group">
                            <label for="authen-input" class="col-sm-3 control-label">Authentication</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="addForm-authentication" placeholder="authentication" name="authentication" 
                                           maxlength="100">
                            </div>
                        </div>   
                        <div>
                            <label for="request-input" class="col-sm-3 control-label">Request :</label>
                            <div>
                                <input type="text" class="form-control" id="addForm-request" placeholder="request" name="request" 
                                           maxlength="100">
                                <button type = "button"id="showTextBoxButton">Add field</button>
                                <div id="textBoxContainer"></div>
                            </div>
                        </div>
                        
                        <div>
                            <label for="response-input" class="col-sm-3 control-label">Response :</label>
                            <div>
                                <input type="text" class="form-control" id="addForm-response" placeholder="response" name="response"
                                           maxlength="100">
                                <button type = "button" id="showTextBoxButton2">Add field</button>
                                <div id="textBoxContainer2"></div>
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
        var n_request = 0;
        var n_response = 0;
        var dtarequest;
        var dtaresponse;
        var jsoutputRequest;
        var jsoutputResponse;
        document.addEventListener("DOMContentLoaded", function () {
          var textBoxContainer = document.getElementById("textBoxContainer");
          var showTextBoxButton = document.getElementById("showTextBoxButton");
          var textBoxContainer2 = document.getElementById("textBoxContainer2");
          var showTextBoxButton2 = document.getElementById("showTextBoxButton2");
          
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
//-----------------------------------------------------------------------------------
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
//-----------------------------------------------------------------------------------------------------------------------------    
        function addViaAjax() {
            updateDataObject("text-box");
            updateDataObject2("text-box-2");
            var add = {};
            var headers = [];
            var csrfToken = $("meta[name='_csrf']").attr("content");
            var csrfHeader = $("meta[name='_csrf_header']").attr("content");  // THIS WAS ADDED
            headers[csrfHeader] = csrfToken;
            add["apiname"] = $("#addForm-name").val().trim();
            add["authentication"] = $("#addForm-authentication").val().trim();
            add["request"] = jsoutputRequest;
            add["response"] = jsoutputResponse;
            $.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "${pageContext.request.contextPath}/api/insert",
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
                        window.location.href = "${pageContext.request.contextPath}/api/list";
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
        
//        function addViaAjax() {
//            var add = {};
//            var headers = [];
//            var csrfToken = $("meta[name='_csrf']").attr("content");
//            var csrfHeader = $("meta[name='_csrf_header']").attr("content");  // THIS WAS ADDED
//            headers[csrfHeader] = csrfToken;
//            add["filename"] = 
//            $.ajax({
//                type: "POST",
//                contentType: "application/json; charset=utf-8",
//                url: "${pageContext.request.contextPath}/api/insert",
//                data: JSON.stringify(add),
//                dataType: 'text',
//                headers: headers,
//                timeout: 100000,
//                success: function (data) {
//                    if (data.includes("LOGIN_FINISH_TIME")) {
//                        alert("Phiên đăng nhập đã hết hạn");
//                        window.location.href = "${pageContext.request.contextPath}/login";
//                        return;
//                    }
//                    var type = $.trim(data.toString()).split('|')[1];
//                    if (type === undefined) {
//                        type = 'error';
//                    }
//                    if (type !== 'error') {
//                        $('#objectAdd').modal("hide");
//                        window.location.href = "${pageContext.request.contextPath}/api/list";
//                    } else {
//                        alert($.trim(data.toString()).split('|')[0]);
//                    }
//                },
//                error: function (e) {
//                    showError(e);
//                },
//                done: function (e) {
//                    console.log("DONE");
//                }
//            });
//
//            return false;
//        }
    </script>

</body>
