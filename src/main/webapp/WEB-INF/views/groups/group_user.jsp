<%-- 
    Document   : event_list
    Created on : Sep 11, 2017, 8:37:16 AM
    Author     : vnpt2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style>
    .state-icon {
        left: -5px;
    }
    .list-group-item-primary {
        color: rgb(255, 255, 255);
        background-color: rgb(66, 139, 202);
    }

</style>

<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Quản lý nhóm quyền
            <small>Gán users</small>
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                    <div class="box-body">
                        <form class="form-group" action="" id="viewInfoForm" >

                            <div class="box-body">
                                <div class="form-group hidden"> 
                                    <label for="id-input" class="col-sm-3 control-label">ID</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="viewForm-id" placeholder="cpId" name="cpId" value="${Groups.id}">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group col-sm-6">
                                        <label for="name" class="col-sm-3 control-label">Tên nhóm quyền: </label>
                                        <div class="col-sm-8">
                                            <input type='text' class="form-control" id="viewForm-name" name="name" value="${fn:escapeXml(Groups.name)}" disabled="disabled"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group col-sm-12">
                                        <label for="users" class="col-sm-3 control-label">Người dùng: </label>
                                        <div class="col-sm-8">
                                            <div class="checkbox checkbox-primary">
                                                <input id="checkAll" class="styled" type="checkbox">
                                                <label for="checkAll">
                                                    Tất cả
                                                </label>
                                            </div>

                                            <c:forEach items="${listUsersAll}" var="obj" varStatus="varStatus">
                                                <div class="checkbox checkbox-primary col-sm-4">
                                                    <c:choose>
                                                        <c:when test="${obj.assignGroup == 'true'}">
                                                            <input id="${obj.id}" class="styled checkUser" type="checkbox" checked="checked" >

                                                        </c:when> 

                                                        <c:otherwise>
                                                            <input id="${obj.id}" class="styled checkUser" type="checkbox">
                                                        </c:otherwise>
                                                    </c:choose>

                                                    <label for="${obj.id}">
                                                        ${obj.username}
                                                    </label>
                                                </div>
                                            </c:forEach>

                                        </div>
                                    </div>
                                </div>

                                <div class="col-sm-4">
                                    <div class="form-group col-sm-6">
                                        <div class="input-group">
                                            <button type="submit" class="btn btn-info btn" id="btnSearch">
                                                <span class="glyphicon glyphicon-save" aria-hidden="true"></span> Cập nhật
                                            </button>
                                        </div>
                                    </div>

                                </div>
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
    document.getElementById("menu-user-parent").className = "active";
    document.getElementById("menu-group").className = "active";

    $("#checkAll").change(function () {
        $("input:checkbox").prop('checked', $(this).prop("checked"));
        $("input:checkbox:disabled").prop('checked', true);
    });

    //".checkbox" change 
    $('.checkUser').change(function () {
        //uncheck "select all", if one of the listed checkbox item is unchecked
        if (false === $(this).prop("checked")) { //if this item is unchecked
            //console.log("OK2");
            $("#checkAll").prop('checked', false); //change "select all" checked status to false
        }
        //check "select all" if all checkbox items are checked
        if ($('.checkUser:checked').length === $('.checkUser').length) {
            //console.log("OK4");
            $("#checkAll").prop('checked', true);
        }
    });

    $(document).on('submit', '#viewInfoForm', function (e) {
        e.preventDefault();
        editInfoViaAjax();
    });




    function editInfoViaAjax() {
        var edit = {};
        var listUsers = [];
        $(".checkUser:checked:not(:disabled)").each(function () {
            listUsers.push({id: $(this).attr('id')});
        });

        edit["id"] = $("#viewForm-id").val().trim();
        edit["name"] = $("#viewForm-name").val().trim();
        edit["listUsers"] = listUsers;
        
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "${pageContext.request.contextPath}/groups/updateListUser",
            data: JSON.stringify(edit),
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

    function showError(e) {
        if (e.responseText.includes("LOGIN_FINISH_TIME")) {
            alert("Phiên đăng nhập đã hết hạn");
            window.location.href = "${pageContext.request.contextPath}/login";
            return;
        }
        if (e.status === 403) {
            alert("Bạn không có quyền thực hiện chức năng này");
            $('#ojectView').modal("hide");
            return;
        }
        console.log("ERROR: ", e);
        alert("Có lỗi xảy ra");
    }

</script>


