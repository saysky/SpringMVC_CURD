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
                    <li class="active">
                        <a href="${pageContext.request.contextPath}/admin/user/list">用户列表</a>
                    </li>
                </ul>
            </div>
            <br>
            <div id="content">
                <div class="table-responsive">
                    <form class="form-inline" method="get" action="${pageContext.request.contextPath}/admin/user/search">
                        <div class="form-group">
                            <label for="Search" class="sr-only">Search</label>
                            <input type="text" name="query" class="form-control" id="Search" placeholder="Search"
                                   value="${query}">
                        </div>
                        <button type="submit" class="btn btn-default">查询</button>
                        <a href="${pageContext.request.contextPath}/admin/user/add">
                            <button type="button" class="btn btn-info">添加</button>
                        </a>
                        <button type="button" class="btn btn-danger" onclick="deleteUserMore()">批量删除</button>
                    </form>
                    <br>
                    <c:choose>


                        <c:when test="${userDTOPage.numberOfElements > 0}">
                            <table class="table table-bordered">
                                <tr>
                                    <th><input type="checkbox" id="allSelect" onclick="DoCheck()"></th>
                                    <th>ID</th>
                                    <th>用户名</th>
                                    <th>昵称</th>
                                    <th>邮箱</th>
                                    <th>电话</th>
                                    <th>个人主页</th>
                                    <th>创建时间</th>
                                    <th>状态</th>
                                    <th>操作</th>
                                </tr>
                                <c:forEach var="user" items="${userDTOPage.content}">
                                    <tr>
                                        <td><input type="checkbox" name="ids" value="${user.id}"></td>
                                        <td>${user.id}</td>
                                        <td>${user.username}</td>
                                        <td>${user.nickname}</td>
                                        <td>${user.email}</td>
                                        <td>${user.phone}</td>
                                        <td><a href="${user.website}" target="_blank">${user.website}</a></td>
                                        <td>${user.createTime}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${user.status==1}">
                                                    <span class="text-success">正常</span>
                                                </c:when>
                                                <c:when test="${user.status==0}">
                                                    <span class="text-danger">禁用</span>
                                                </c:when>
                                                <c:otherwise>
                                                    ${user.status}
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/admin/user/profile/${user.id}">
                                                <button type="button" class="btn btn-success btn-xs">查看</button>
                                            </a>
                                            <button type="button" class="btn btn-danger btn-xs"
                                                    onclick="deleteUser(${user.id})">删除
                                            </button>
                                            <a href="${pageContext.request.contextPath}/admin/user/edit/${user.id}">
                                                <button type="button" class="btn btn-primary btn-xs">编辑</button>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>

                            <%--总共页数：${userDTOPage.totalPages} <br>--%>
                            <%--记录总数：${userDTOPage.totalElements} <br>--%>
                            <%--当前页号：${userDTOPage.number} <br>--%>
                            <%--是否为首页：${userDTOPage.first} <br>--%>
                            <%--是否为尾页：${userDTOPage.last} <br>--%>
                            <%--每页显示的数量：${userDTOPage.numberOfElements} <br>--%>
                            <%--分页 start--%>
                            <nav aria-label="Page navigation">
                                <ul class="pagination">
                                    <c:choose>
                                        <c:when test="${userDTOPage.totalPages <= 3 }">
                                            <c:set var="begin" value="1"/>
                                            <c:set var="end" value="${userDTOPage.totalPages }"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="begin" value="${userDTOPage.number+1-1 }"/>
                                            <c:set var="end" value="${userDTOPage.number+1 + 2}"/>
                                            <c:if test="${begin < 2 }">
                                                <c:set var="begin" value="1"/>
                                                <c:set var="end" value="3"/>
                                            </c:if>
                                            <c:if test="${end > userDTOPage.totalPages}">
                                                <c:set var="begin" value="${userDTOPage.totalPages-2 }"/>
                                                <c:set var="end" value="${userDTOPage.totalPages}"/>
                                            </c:if>
                                        </c:otherwise>
                                    </c:choose>

                                        <%--如果当前为首页，隐藏上一页--%>
                                    <c:choose>
                                        <c:when test="${userDTOPage.first}">
                                            <%--当前页为第一页，隐藏上一页按钮--%>
                                        </c:when>
                                        <c:otherwise>
                                            <li>
                                                <a href="?query=${query}&page=${userDTOPage.number}" aria-label="Previous">
                                                    <span aria-hidden="true">&laquo;</span>
                                                </a>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>

                                        <%--显示第一页的页码--%>
                                    <c:if test="${begin >= 2 }">
                                        <li><a href="?query=${query}&page=1">1</a></li>
                                    </c:if>

                                        <%--显示点点点--%>
                                    <c:if test="${begin  > 2 }">
                                        <li class="disabled"><a>…</a></li>
                                    </c:if>

                                        <%--打印 页码--%>
                                    <c:forEach begin="${begin }" end="${end }" var="i">
                                        <c:choose>
                                            <c:when test="${i eq userDTOPage.number+1}">
                                                <li class="active"><a href="?query=${query}&page=${i}">${i}<span
                                                        class="sr-only">(current)</span></a></li>
                                            </c:when>
                                            <c:otherwise>
                                                <li><a href="?query=${query}&page=${i}">${i}</a></li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>

                                        <%-- 显示点点点 --%>
                                    <c:if test="${end < userDTOPage.totalPages-1 }">
                                        <li class="disabled"><a>…</a></li>
                                    </c:if>

                                        <%-- 显示最后一页的数字 --%>
                                    <c:if test="${end < userDTOPage.totalPages}">
                                        <li>
                                            <a href="?query=${query}&page=${userDTOPage.totalPages}">${userDTOPage.totalPages}</a>
                                        </li>
                                    </c:if>

                                        <%--如果当前页为尾页，隐藏下一页--%>
                                    <c:choose>
                                        <c:when test="${userDTOPage.number+1 eq userDTOPage.totalPages}">
                                            <%--到了尾页隐藏，下一页按钮--%>
                                        </c:when>
                                        <c:otherwise>
                                            <li>
                                                <a href="?query=${query}&page=${userDTOPage.number+2}" aria-label="Next">
                                                    <span aria-hidden="true">&raquo;</span>
                                                </a>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>
                                </ul>
                            </nav>
                            <%--分页 end--%>
                        </c:when>
                        <c:otherwise><%--如果没有文章--%>
                            <div class="alert alert-warning">
                                <a href="#" class="close" data-dismiss="alert">
                                    &times;
                                </a>
                                <strong>警告！</strong>这里什么都没有。哼，小坏坏！
                            </div>
                        </c:otherwise>
                    </c:choose>
                    <div class="alert alert-success">
                        <a href="#" class="close" data-dismiss="alert">
                            &times;
                        </a>
                        <strong>温馨提示&nbsp;</strong> <br>
                        1、如果数据被删除了，可以点击上方的"<strong>数据还原</strong>"按钮，后台会在数据库中重新添加29条记录 <br>
                        2、查询功能：根据用户名、昵称、邮箱、电话、个人主页模糊查询 <br>
                        3、当前user表中有${userDTOPage.totalElements}条记录,共${userDTOPage.totalPages}页
                        4、可以通过page和size参数来跳转指定页码和设置每页显示数量，如list?page=2&size=10
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="../common/footer.jsp" %>