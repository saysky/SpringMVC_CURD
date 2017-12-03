<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../common/header.jsp" %>
</head>
<body>

<%@include file="../common/nav.jsp" %>

<div class="container-fluid">
    <div class="row">

        <%@include file="../common/sidebar.jsp" %>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div id="myBreadcrumb">
                <ul class="breadcrumb">
                    <li>
                        <a href="#">首页</a> <span class="divider">></span>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/admin/user/list">用户列表</a> <span class="divider">></span>
                    </li>
                    <li class="active">编辑用户</li>
                </ul>
            </div>
            <br>
            <form class="form-horizontal" id="addUserForm" method="post"
                  action="${pageContext.request.contextPath}/admin/user/editUser">
                <input type="hidden" name="id" id="id" value="${userDTO.id}">
                <div class="form-group">
                    <label for="avatar" class="col-sm-2 control-label">头像</label>
                    <div class="col-sm-2">
                        <img src="${userDTO.avatar}" alt="" id="avatarShow" width="100px" height="100px">
                        <input type="file" name="file" id="file" onchange="uploadImg()">
                        <input type="hidden" name="avatar" id="avatar" value="${userDTO.avatar}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="username" class="col-sm-2 control-label">用户名</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="username" id="username" placeholder="username"
                               value="${userDTO.username}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="nickname" class="col-sm-2 control-label">昵称</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="nickname" id="nickname" placeholder="nickname"
                               value="${userDTO.nickname}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="email" class="col-sm-2 control-label">电子邮箱</label>
                    <div class="col-sm-4">
                        <input type="email" class="form-control" name="email" id="email" placeholder="email"
                               value="${userDTO.email}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="phone" class="col-sm-2 control-label">电话号码</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="phone" id="phone" placeholder="phone"
                               value="${userDTO.phone}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="website" class="col-sm-2 control-label">个人主页</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="website" id="website" placeholder="website"
                               value="${userDTO.website}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">状态</label>
                    <div class="col-sm-4">
                        <label class="radio-inline">
                            <input type="radio" name="status"  value="1" <c:if test="${userDTO.status==1}">checked</c:if>> 正常 &nbsp;
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="status"  value="0" <c:if test="${userDTO.status==0}">checked</c:if>> 禁用
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary">保存</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>



<%@include file="../common/footer.jsp" %>