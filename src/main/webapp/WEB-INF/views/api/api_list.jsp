<%-- 
    Document   : api_list
    Created on : Aug 18, 2023, 9:00:35 AM
    Author     : PHU MINH HUONG
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
            Quản lý API
            <small>API</small>
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                    <div class="box-body">
                        <form class="form-group-sm" action="${pageContext.request.contextPath}/api/list" id="searchForm" >
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
                                <sec:authorize access="hasAnyRole('ROLE_API_INSERT')">
                                    <div class="col-xs-3">
                                        <a data-toggle="modal" href="#insert-modal" id ="add-form">
                                            <button type="button" class="btn btn-info btn-sm" data-toggle="modal" 
                                                    data-placement="top" title="Thêm mới" data-target="#objectAdd" >
                                                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Thêm mới
                                            </button>
                                        </a>
                                    </div>

                                </sec:authorize>
                                <input type="file" id="fileInput" name="file" />
                                <button type="button" onclick="uploadFile()">Upload File</button>
                        </form>
                        <button type="button" onclick="templateExcel()">Template</button>
                        <button type="button" onclick="reportExcel()">Report</button>
                        <div class="table-responsive">
                            <table id="example3" class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>API Name</th>
                                        <th>Authentication</th>
                                        <th>Request</th>
                                        <th>Response</th>
                                        <th>Date Created</th>
                                        <th>Status</th>
                                        <th>Creator</th>
                                        <th class="edit-actions">Cài đặt</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${list}" var="obj" varStatus="varStatus">
                                        <tr>
                                            <td>${varStatus.count}</td>
                                            <td>${fn:escapeXml(obj.apiname)}</td>
                                            <td>${fn:escapeXml(obj.authentication)}</td>
                                             <td>${fn:escapeXml(obj.request)}</td>
                                              <td>${fn:escapeXml(obj.response)}</td>
                                            <td>
                                                <fmt:formatDate value="${obj.createTime}" pattern="dd/MM/yyyy HH:mm"/>
                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${obj.status == 1}">
                                                        Mở khóa
                                                        <sec:authorize access="hasAnyRole('ROLE_API_ACTIVE')">
                                                            |
                                                            <c:choose>
                                                                <c:when test="${sessionScope.auth.username == obj.apiname}">
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
                                                        <sec:authorize access="hasAnyRole('ROLE_API_ACTIVE')">
                                                            |
                                                            <c:choose>
                                                                <c:when test="${sessionScope.auth.username == obj.apiname}">
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
                                            <td>${fn:escapeXml(obj.usercreate)}</td>
                                            <td class="center">
                                                <sec:authorize access="hasAnyRole('ROLE_API_DETAIL')">
                                                    <button type="button" class="btn btn-xs btn-success edit-Template " data-toggle="modal" 
                                                            data-placement="top" title="Sửa" onclick="getApiAjax(${obj.id})">
                                                        <span class="fa fa-pencil" aria-hidden="true"></span>
                                                    </button>
                                                </sec:authorize>
                                                <sec:authorize access="hasAnyRole('ROLE_API_DELETE')">
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
<script>
    document.getElementById("menu-user-parent").className = "active";
    document.getElementById("menu-api").className = "active";
    
    $(function () {
        $("#example3").DataTable({
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
    
//    function handleButtonClick() {
//        // Gửi một yêu cầu AJAX đến điểm cuối `/import`
//        var xhr = new XMLHttpRequest();
//        var formData = new FormData();
//        var fileInput = document.getElementById('fileInput');
//        formData.append("file", fileInput.files[0]);
//
//        xhr.open("POST", "/import", true);
//        xhr.send(formData);
//    }
//    $(document).on('submit', '#viewForm', function (e) {
//            e.preventDefault();
//            editViaAjax();
//        });

//    $(document).ready(
//            function(){
//                $("form").submit(
//                        function(event){
//                            event.preventDefault();
//                            var formData = new FormData(this);
//                            $.ajax({
//                                url : "${pageContext.request.contextPath}/import",
//                                type : "POST",
//                                data : formData,
//                                timeout : 100000,
//                                success: function(){
//                                            
//                                        }
//                            });
//                        });
//            });
//    
//    function uploadFile() {
//            var inputFile = document.getElementById("fileInput");
//            var file = inputFile.files[0];
//            
//            var formData = new FormData();
//            formData.append("file", file);
//            
//            var xhr = new XMLHttpRequest();
//            
//            xhr.onreadystatechange = function() {
//                if (xhr.readyState === 4) {
//                    if (xhr.status === 200) {
//                        // Handle the success response
//                        console.log("File uploaded successfully!");
//                    } else {
//                        // Handle the error response
//                        console.error("Error uploading file");
//                    }
//                }
//            };
//            
//            xhr.open("POST", "${pageContext.request.contextPath}/importExcel", true);
//            xhr.send(formData);
//    }
//        
//    function uploadFile() {
//            var fileInput = document.getElementById('fileInput');
//            var file = fileInput.files[0];
//
//            if (file) {
//                var formData = new FormData();
//                formData.append('file', file);
//
//                var xhr = new XMLHttpRequest();
//                xhr.open('POST', '${pageContext.request.contextPath}/api/importExcel', true);
//
//                xhr.onload = function () {
//                    if (xhr.status === 200) {
//                        alert('File uploaded successfully!');
//                    } else {
//                        alert('Error uploading file. Status: ' + xhr.status);
//                    }
//                };
//
//                xhr.send(formData);
//            } else {
//                alert('Please choose a file to upload.');
//            }
//        }
        function templateExcel() {
            window.location.href = "${pageContext.request.contextPath}/api/template";
        }
        function reportExcel() {
            window.location.href = "${pageContext.request.contextPath}/api/report";
        }
        
        function uploadFile() {
            var fileInput = document.getElementById('fileInput');
            var file = fileInput.files[0];

            if (file) {
                // Get CSRF token and header from meta tags
                var csrfToken = $("meta[name='_csrf']").attr("content");
                var csrfHeader = $("meta[name='_csrf_header']").attr("content");

                // Create FormData and append the file
                var formData = new FormData();
                formData.append('file', file);

                // Create XMLHttpRequest
                // XMLHttpRequest sử dụng để tạo và quản lý các yêu cầu HTTP từ một trang web đến máy chủ và xử lý phản hồi
                // cho phép trang web tương tác với máy chủ và cập nhật nội dung trang web mà không cần tải lại toàn bộ trang.
                var xhr = new XMLHttpRequest();
                xhr.open('POST', '${pageContext.request.contextPath}/api/importExcel', true);

                // Set CSRF headers
                xhr.setRequestHeader(csrfHeader, csrfToken);

                // Define the callback function for handling the response
                xhr.onload = function () {
                    if (xhr.status === 200) {
                        alert('File uploaded successfully!');
                        location.reload();
                    } else {
                        alert('Error uploading file. Status: ' + xhr.status);
                    }
                };

                // Send the request with FormData
                xhr.send(formData);
            } else {
                alert('Please choose a file to upload.');
            }
            
        }

    function deleteViaAjax(objId) {
        if (objId === null || objId === "") {
            objId = parseInt($("#viewForm-id").val());
        }

        if (objId !== null) {
            var confirm_check = confirm("Bạn có chắc chắn muốn xóa không?");
            if (confirm_check === true) {
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/api/delete",
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
//        function uploadFile() {
//        var fileInput = document.getElementById('fileInput');
//        var file = fileInput.files[0];
//        var formData = new FormData();
//        formData.append('file', file);
//
//        fetch('/import', {
//            method: 'POST',
//            body: formData
//        })
//        .then(response => {
//            if (response.ok) {
//                console.log('File uploaded successfully.');
//            } else {
//                console.error('Error uploading file.');
//            }
//        })
//        .catch(error => {
//            console.error('Error uploading file:', error);
//        });
//    }

    function active(objId) {

        if (objId !== null) {
            var confirm_check = confirm("Bạn có muốn mở khóa api này không?");
            var headers = [];
            var csrfToken = $("meta[name='_csrf']").attr("content");
            var csrfHeader = $("meta[name='_csrf_header']").attr("content");  // THIS WAS ADDED
            headers[csrfHeader] = csrfToken;
            if (confirm_check === true) {
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/api/active",
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
                        window.location.href = "${pageContext.request.contextPath}/api/list";
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
            var confirm_check = confirm("Bạn có muốn khóa Api này không?");
            var headers = [];
            var csrfToken = $("meta[name='_csrf']").attr("content");
            var csrfHeader = $("meta[name='_csrf_header']").attr("content");  // THIS WAS ADDED
            headers[csrfHeader] = csrfToken;
            if (confirm_check === true) {
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/api/lock",
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
                        window.location.href = "${pageContext.request.contextPath}/api/list";
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
</script>

<!-- ADD VIEW FORM -->
<%@include file="api_edit.jsp"%>

<!-- ADD ADD FORM -->
<%@include file="api_add.jsp"%>