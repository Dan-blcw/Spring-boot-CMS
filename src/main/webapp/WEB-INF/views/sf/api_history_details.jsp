<%-- 
    Document   : api_history_details
    Created on : Nov 27, 2023, 4:53:46 PM
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
            History StorageFile
            <small>File</small>
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                    <div class="box-body">
                        <form class="form-group-sm" action="${pageContext.request.contextPath}/storagefile/list" id="searchForm" >
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
                        </form>

                        <div class="table-responsive">
                            <table id="example4" class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>File Name</th>
                                        <th>Status</th>
                                        <th>Date Created</th>
                                        <th>Success</th>
                                        <th>fail</th>
                                        <th>Creator</th>
                                        <th class="edit-actions">Cài đặt</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${list}" var="ob" varStatus="varStatus">
                                        <tr>
                                            <td>${varStatus.count}</td>
                                            <td>${fn:escapeXml(ob.filename)}</td>
                                            <td>${fn:escapeXml(ob.status)}</td>
                                            <td>
                                                <fmt:formatDate value="${ob.createTime}" pattern="dd/MM/yyyy HH:mm"/>
                                            </td>
                                            <td>${fn:escapeXml(ob.success)}</td>
                                            <td>${fn:escapeXml(ob.fail)}</td>
                                            <td>${fn:escapeXml(ob.userinput)}</td>
                                            <td class="center">
                                                <button type="button" class="btn btn-xs btn-success edit-Template " data-toggle="modal" 
                                                    data-placement="top" title="Sửa" onclick="">
                                                <span class="fa fa-pencil" aria-hidden="true"></span>
                                                
                                                <button type="button" class="btn btn-xs btn-danger edit-Template " data-toggle="modal" 
                                                            data-placement="top" title="Xóa" onclick="" id="delete-employee">
                                               <span class="fa-download" aria-hidden="true"></span>
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
//    getSFAjax(${ob.id})
    document.getElementById("menu-user-parent").className = "active";
    document.getElementById("menu-sf").className = "active";
    
    $(function () {
        
        
        $("#example4").DataTable({
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

</script>

<!-- ADD VIEW FORM -->
<%--<%@include file="api_history_details.jsp"%>--%>
