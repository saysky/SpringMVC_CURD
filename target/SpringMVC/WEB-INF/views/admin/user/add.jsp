<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../common/header.jsp"%>
<body>

<%@include file="../common/nav.jsp"%>

<div class="container-fluid">
    <div class="row">

        <%@include file="../common/sidebar.jsp"%>
        
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <div id="myBreadcrumb">
                <ul class="breadcrumb">
                    <li>
                        <a href="#">首页</a> <span class="divider">></span>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/admin/user/list">用户列表</a> <span class="divider">></span>
                    </li>
                    <li class="active">添加用户</li>
                </ul>
            </div>

            <br>
            <form class="form-horizontal" id="addUserForm" method="post" action="${pageContext.request.contextPath}/admin/user/addUser" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="file" class="col-sm-2 control-label">头像</label>
                    <div class="col-sm-2">
                        <input type="file" name="file" id="file" onchange="uploadImg()">
                        <input type="hidden" name="avatar" id="avatar">
                        <br>
                        <img src="" alt="" id="avatarShow" width="100px" height="100px">
                    </div>
                </div>
                <div class="form-group">
                    <label for="username" class="col-sm-2 control-label">用户名 <span class="text-danger">*</span></label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="username" id="username" placeholder="username" onblur="checkUsername()">
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label">密码 <span class="text-danger">*</span></label>
                    <div class="col-sm-4">
                        <input type="password" class="form-control" name="password" id="password" placeholder="password" >
                    </div>
                </div>
                <div class="form-group">
                    <label for="confirmPassword" class="col-sm-2 control-label">确认密码 <span class="text-danger">*</span></label>
                    <div class="col-sm-4">
                        <input type="password" class="form-control" name="confirmPassword" id="confirmPassword" placeholder="confirmPassword" >
                    </div>
                </div>
                <div class="form-group">
                    <label for="nickname" class="col-sm-2 control-label">昵称 <span class="text-danger">*</span></label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="nickname" id="nickname" placeholder="nickname">
                    </div>
                </div>
                <div class="form-group">
                    <label for="email" class="col-sm-2 control-label">电子邮箱</label>
                    <div class="col-sm-4">
                        <input type="email" class="form-control" name="email" id="email" placeholder="email">
                    </div>
                </div>
                <div class="form-group">
                    <label for="phone" class="col-sm-2 control-label">电话号码</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="phone" id="phone" placeholder="phone">
                    </div>
                </div>
                <div class="form-group">
                    <label for="website" class="col-sm-2 control-label">个人主页</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="website" id="website" placeholder="website">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary">添加</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>


<%@include file="../common/footer.jsp"%>