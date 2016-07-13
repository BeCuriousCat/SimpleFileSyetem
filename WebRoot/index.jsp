<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="com.czw.bean.User"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- 验证权限 -->
<%
	User user = (User) request.getSession().getAttribute("user");
	if (user == null) {
		response.sendRedirect(basePath + "login.jsp");
	}
%>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<title></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="js/html5.js"></script>
    <![endif]-->
<link class="bootstrap library" rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
<script class="bootstrap library" src="jquery/jquery-3.0.0.js"
	type="text/javascript"></script>
<script class="bootstrap library" src="bootstrap/js/bootstrap.min.js"
	type="text/javascript"></script>
</head>
<body>

	<div id="content">
		<div class="container">
			<div class="row">
				<div class="span10">

					<div class="widget-content">
						
						<div class="tabbable" style="overflow: hidden;">
							<ul class="nav nav-tabs">
								<li class="active"><a href="#1" data-toggle="tab">文件管理</a></li>
							</ul>
							<form action="${basePath }uploadfile" method="post"
								enctype="multipart/form-data">
								<label for="upLoadFile">文件上传：</label> <input type="file"
									class="btn" name="upload" /> <input type="submit" value="提交"
									class="btn">${result }
							</form>
							<br />
							<c:if test="${!empty dataList}">
								<c:forEach items="${dataList}" var="r" varStatus="s">
									<div>
										<ul class="inline unstyled">
											<h3>
												<i class="icon-file"></i><a
													href="${basePath }download?filename=${r[1] }">${r[0] }</a>
											</h3>
											<li class="pull-left" style="margin-left:15px;">上传时间：${r[2] }</li>
											<li class="pull-left" style="margin-left:15px;">下载次数：${r[3] }</li>
											<li class="pull-left" style="margin-left:15px;">上传者：${r[4] }</li>
										</ul>
									</div>
									<br>
								</c:forEach>
							</c:if>
						</div>
					</div>
				</div>
				<!-- /span9 -->
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /content -->


	<div id="footer">
		<div class="container">
			<hr />
			<p>&copy; 2015/1/14 西南交通大学.</p>
		</div>
		<!-- /container -->
	</div>
	<!-- /footer -->




	<!-- Le javascript
================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->

	<script src="./bootstrap/js/bootstrap.js"></script>
	
</body>
</html>