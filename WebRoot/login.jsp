<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
	<div class="container">
		<section class="loginBox row-fluid">
			<div class="tabbable" id="tabs-634549">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#panel-60560" data-toggle="tab">帐号登录</a></li>
					<li ><a href="#panel-549981" data-toggle="tab">注册</a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active"  id="panel-60560">
						<form class="form-horizontal" action="/*">
							<div class="form-group">
								<label for="username" class="col-sm-2 control-label">用戶名</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="username"
										placeholder="username">
								</div>
							</div>
							<div class="form-group">
								<label for="password" class="col-sm-2 control-label">密碼</label>
								<div class="col-sm-10">
									<input type="password" class="form-control" id="password"
										placeholder="Password">
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button id="login_sub"  class="btn btn-default">Sign in</button>
								</div>
							</div>
							
							<div class="error-box">
							</div>
							<div id="" style="color: red;"></div>
						</form>
					</div>
					<div class="tab-pane" id="panel-549981">
						<form class="form-horizontal" action="register">
							<div class="form-group">
								<label for="input3" class="col-sm-2 control-label">用戶名</label>
								<div class="col-sm-10">
									<input type="" class="form-control" id="R_username"
										placeholder="username">
								</div>
							</div>
							<div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label">密碼</label>
								<div class="col-sm-10">
									<input type="password" class="form-control" id="R_password"
										placeholder="Password">
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button id="register-sub"  class="btn btn-default">Register</button>
								</div>
							</div>
							<div class="error-box-register">
							</div>
							<div id="" style="color: red;"></div>
						</form>
					</div>
				</div>
			</div>
		</section>
		<!-- /loginBox -->
	</div>
	<!-- /container -->
</body>
<style>
* {
	margin: 0;
	padding: 0;
}

body {
	background: #444 url(image/carbon_fibre_big.png)
}

.loginBox {
	width: 420px;
	height: 230px;
	padding: 0 20px;
	border: 1px solid #fff;
	color: #000;
	margin-top: 40px;
	border-radius: 8px;
	background: white;
	box-shadow: 0 0 15px #222;
	background: -moz-linear-gradient(top, #fff, #efefef 8%);
	background: -webkit-gradient(linear, 0 0, 0 100%, from(#f6f6f6),
		to(#f4f4f4));
	font: 11px/1.5em 'Microsoft YaHei';
	position: absolute;
	left: 50%;
	top: 50%;
	margin-left: -210px;
	margin-top: -115px;
}

.loginBox h2 {
	height: 45px;
	font-size: 20px;
	font-weight: normal;
}

.loginBox .left {
	border-right: 1px solid #ccc;
	height: 100%;
	padding-right: 20px;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$("#login_sub").click(function(){
			userLogin('${basePath}');
		});
		$("#register-sub").click(function(){
			userRegister('${basePath}');
		});
		function userLogin(path){
			var username = $("#username").val();
			var password = $("#password").val();
			if(username == "" || password == ""){
				$(".error-box").html("用户名或密码不能为空！");
				if($("#username").val() == ""){
					$("#username").focus();
				}else{
					$("#password").focus();
				}
				return;
			}
			var url_string = "login!login";
			$.ajax({
				type: "post",
				url: url_string,
				data: {'username':username, 'password':password},
				dataType:'json',
				beforeSend:function(){
		   			$("#login_sub").attr("disabled", true);
		   			$(".error-box").html("登录中，请稍等！");
				},
				success:function(json) {
					var result = json.result;
					if (result == "1"){
						location.href="${basePath}downloadfile!listDownloadFiles";
					}
					else if (result == "0"){
						$(".error-box").html("用户名或密码错误！");
						$("#pwd").val("");
						$("#pwd").focus();
					}
					else {
						$(".error-box").html("发生内部错误，请重试！");
					}
				},
				complete:function(){
					$("#login_sub").attr("disabled", false);
				},
				error:function(){
					$(".error-box").html("发生内部错误，请重试！");
				}
			});
		}
		
		function userRegister(path){

			var username = $("#R_username").val();
			var password = $("#R_password").val();
			
			
			if(username == "" || password == ""){
				$(".error-box-register").html("用户名或密码不能为空！");
				if($("#R_username").val() == ""){
					$("#R_username").focus();
				}else{
					$("#R_password").focus();
				}
				return;
			}
			var url_string = "register";
			$.ajax({
				type: "post",
				url: url_string,
				data: {'username':username, 'password':password},
				dataType:'json',
				beforeSend:function(){
		   			$("#register-sub").attr("disabled", true);
		   			$(".error-box-register").html("注册中！请稍等...");
				},
				success:function(json) {
					var result = json.result;
					if (result == "1"){
						location.href="${basePath}login.jsp";
					}
					else if (result == "0"){
						$(".error-box-register").html("该用户已存在！");
						$("#R_password").val("");
						$("#R_password").focus();
					}
					else {
						$(".error-box-register").html("发生内部错误，请重试！");
					}
				},
				complete:function(){
					$("#register-sub").attr("disabled", false);
				},
				error:function(){
					$(".error-box-register").html("发生内部错误，请重试！");
				}
			});
		}
		
		/* document.onkeydown = function(e){ 
		    var ev = document.all ? window.event : e;
		    if(ev.keyCode==13) {
		    	
		    	userLogin('${basePath}');
		     }
		} */
	});
	</script>
</html>