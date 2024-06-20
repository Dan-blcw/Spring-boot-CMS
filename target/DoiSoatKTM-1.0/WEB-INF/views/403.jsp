<%-- 
    Document   : 403
    Created on : Sep 19, 2017, 3:21:12 PM
    Author     : vnpt2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    
    <!-- Main content -->
    <section class="content">
        <div class="error-page">
            <h2 class="headline text-info"> 403</h2>

            <div class="error-content">
                <h3><i class="fa fa-warning text-info"></i> Từ chối truy cập.</h3>

                <p>
                    Bạn không có quyền thực hiện chức năng này.
                    ${pageContext.request.userPrincipal.name}, bạn có thể quay lại trang thông tin cá nhân <a href="${pageContext.request.contextPath}/users/updatePersonal">tại đây</a>.
                </p>
                <form class="search-form">
                    <div class="input-group">
                        <input type="text" name="search" class="form-control" placeholder="Search">

                        <div class="input-group-btn">
                            <button type="submit" name="submit" class="btn btn-info btn-flat"><i class="fa fa-search"></i>
                            </button>
                        </div>
                    </div>
                    <!-- /.input-group -->
                </form>

            </div>
            <!-- /.error-content -->
        </div>
        <!-- /.error-page -->
    </section>
    <!-- /.content -->
</div>
