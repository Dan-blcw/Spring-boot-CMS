<%-- 
    Document   : 403
    Created on : Oct 6, 2016, 11:32:15 AM
    Author     : vnpt2
--%>

<!DOCTYPE html>

<%@ page language="java" pageEncoding="UTF-8"%>


<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Main content -->
    <section class="content">
        <div class="error-page">
            <h2 class="headline text-red"> 500</h2>

            <div class="error-content">
                <h3><i class="fa fa-warning text-red"></i> Đã xảy ra sự cố.</h3>

                <p>
                    Chúng tôi sẽ cố gắng khắc phục ngay. Bạn có thể quay lại trang thông tin cá nhân <a href="${pageContext.request.contextPath}/users/updatePersonal">tại đây</a>.
                </p>
                <form class="search-form">
                    <div class="input-group">
                        <input type="text" name="search" class="form-control" placeholder="Search">

                        <div class="input-group-btn">
                            <button type="submit" name="submit" class="btn btn-danger btn-flat"><i class="fa fa-search"></i>
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
<!-- /.content-wrapper -->



