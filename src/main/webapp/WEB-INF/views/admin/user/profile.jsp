<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../common/header.jsp"%>
<style>
    .col-sm-10 {
        padding: 7px 27px;
    }
</style>
</head>
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
                    <li class="active">用户信息</li>
                </ul>
            </div>

            <form class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label">头像</label>
                    <div class="col-sm-10">
                        <img src="${userDTO.avatar}" alt="" width="100" height="100">
                    </div>
                </div>
                <div class="form-group">
                    <label  class="col-sm-2 control-label">用户名</label>
                    <div class="col-sm-10">
                        ${userDTO.username}
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">昵称</label>
                    <div class="col-sm-10">
                        ${userDTO.nickname}
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">电子邮箱</label>
                    <div class="col-sm-10">
                       ${userDTO.email}
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">电话号码</label>
                    <div class="col-sm-10">
                        ${userDTO.phone}
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">个人主页</label>
                    <div class="col-sm-10">
                        <a href="${userDTO.website}" target="_blank">${userDTO.website}</a>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">创建时间</label>
                    <div class="col-sm-10">
                        ${userDTO.createTime}
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">状态</label>
                    <div class="col-sm-10">
                        <c:choose>
                            <c:when test="${userDTO.status==1}">
                                <span class="text-success">正常</span>
                            </c:when>
                            <c:when test="${userDTO.status==0}">
                                <span class="text-danger">禁用</span>
                            </c:when>
                            <c:otherwise>
                                ${userDTO.status}
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <a href="${pageContext.request.contextPath}/admin/user/edit/${userDTO.id}">
                            <button type="button" class="btn btn-default">编辑</button>
                        </a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>


<%@include file="../common/footer.jsp"%>