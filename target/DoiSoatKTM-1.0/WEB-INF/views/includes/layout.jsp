<%-- 
    Document   : layout
    Created on : Sep 9, 2017, 10:13:14 AM
    Author     : vnpt2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="_csrf" content="${_csrf.token}"/>
        <!-- default header name is X-CSRF-TOKEN -->
        <meta name="_csrf_header" content="${_csrf.headerName}"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title><tiles:getAsString name="title" /></title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.6 -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
        <!-- DataTables -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/AdminLTE.css">
        <!-- bootstrap datetimepicker -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/datetimepicker/bootstrap-datetimepicker.css">
        <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/skins/_all-skins.css">
        <!-- bootstrap datepicker -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/datepicker/datepicker3.css">
        <!-- bootstrap checkbox -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/build.css"/>
        <!-- daterange picker -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.css">
        <link href="${pageContext.request.contextPath}/plugins/datepicker/datepicker3.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/plugins/timepicker/bootstrap-timepicker.min.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/plugins/bootstrap-duallistbox/bootstrap-duallistbox.min.css" rel="stylesheet" type="text/css"/>
        <!-- button excel -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/datatables/buttons.dataTables.min.css">
        <!-- Select search -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/bootstrap-select.min.css">

        <link href="${pageContext.request.contextPath}/plugins/datatables/colReorder.dataTables.min.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/plugins/build/css/custom.css" rel="stylesheet">


        <!-- TreeCSS -->
        <link href="${pageContext.request.contextPath}/views/css/jquery.bootstrap.treeselect.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/views/css/selectize/selectize.css" rel="stylesheet" type="text/css"/>

        <script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>


        <script type="text/javascript" src="${pageContext.request.contextPath}/plugins/ckfinder/ckfinder.js"></script>    
        <script type="text/javascript" src="${pageContext.request.contextPath}/plugins/ckeditor/ckeditor.js"></script>
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">
            <tiles:insertAttribute name="header" />
            <tiles:insertAttribute name="menu" />
            <tiles:insertAttribute name="body" />
            <tiles:insertAttribute name="footer" />
            <tiles:insertAttribute name="sidebar" />
        </div>
    </body>


    <!-- Bootstrap 3.3.6 -->
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>


    <!--<script src="https://cdn.ckeditor.com/ckeditor5/23.1.0/classic/ckeditor.js"></script>-->
    <!-- DataTables -->
    <script src="${pageContext.request.contextPath}/plugins/datatables/jquery.dataTables.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.min.js"></script>
    <!-- SlimScroll -->
    <script src="${pageContext.request.contextPath}/plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <!-- FastClick -->
    <script src="${pageContext.request.contextPath}/plugins/fastclick/fastclick.js"></script>
    <!-- AdminLTE App -->
    <script src="${pageContext.request.contextPath}/dist/js/app.min.js"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="${pageContext.request.contextPath}/dist/js/demo.js"></script>
    <!-- page script -->
    <!-- bootstrap datepicker -->
    <script src="${pageContext.request.contextPath}/plugins/datepicker/bootstrap-datepicker.js"></script>
    <!-- InputMask -->
    <script src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.extensions.js"></script>
    <!-- moment -->
    <script src="${pageContext.request.contextPath}/plugins/daterangepicker/moment.js"></script>
    <!-- bootstrap datetimepicker -->
    <script src="${pageContext.request.contextPath}/plugins/datetimepicker/bootstrap-datetimepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/timepicker/bootstrap-timepicker.min.js" type="text/javascript"></script>
    <!-- boostrap dualistbox -->
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-duallistbox/jquery.bootstrap-duallistbox.min.js" type="text/javascript"></script>
    <!-- bootstrap daterangepicker -->
    <script src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/daterangepicker/moment.min.js" type="text/javascript"></script>
    <!-- Js table2exxcel -->
    <script src="${pageContext.request.contextPath}/views/js/jquery.table2excel.js" type="text/javascript" ></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/parsley.js/2.7.2/parsley.min.js" type="text/javascript" ></script>

    <!-- Js validate -->
    <script src="${pageContext.request.contextPath}/views/js/jquery.validate.js" type="text/javascript" ></script>

    <!-- button excel -->
    <script src="${pageContext.request.contextPath}/plugins/datatables/buttons.html5.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/datatables/dataTables.buttons.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/datatables/jszip.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/datatables/pdfmake.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/datatables/vfs_fonts.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/datatables/buttons.print.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/datatables/fileSaver.js"></script>
    <!-- Select Search -->
    <script src="${pageContext.request.contextPath}/dist/js/bootstrap-select.min.js"></script>

    <!-- waitingfor -->
    <script src="${pageContext.request.contextPath}/plugins/waitingfor/bootstrap-waitingfor.js"
    type="text/javascript"></script>
    <script>
        var myContextPath = "${pageContext.request.contextPath}";
    </script>
    <!-- main JS -->
    <script src="${pageContext.request.contextPath}/views/js/main.js" type="text/javascript" ></script>
    <script src="${pageContext.request.contextPath}/views/js/jquery_download.js" type="text/javascript" ></script>
    <!--<script src="${pageContext.request.contextPath}/plugins/jQueryUI/jquery-ui.min.js" type="text/javascript" ></script>-->
    <script src="${pageContext.request.contextPath}/views/js/dataTables.rowsGroup.js" type="text/javascript" >
    </script>

    <script src="${pageContext.request.contextPath}/plugins/datatables/dataTables.colReorder.min.js" type="text/javascript">
    </script>

    <script src="${pageContext.request.contextPath}/plugins/jQuery-Smart-Wizard/js/jquery.smartWizard.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/plugins/build/js/custom.min.js" type="text/javascript"></script>

    <!-- TreeSelect-->
    <script src="${pageContext.request.contextPath}/views/js/jquery.bootstrap.treeselect.js" type="text/javascript" ></script>
    <script src="${pageContext.request.contextPath}/views/js/selectize.min.js" type="text/javascript" ></script>

</html>
