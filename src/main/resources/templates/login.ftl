<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="statics/css/bootstrap.min.css">
    <script src="statics/js/jquery-3.3.1.min.js" type="text/javascript"></script>
    <script src="statics/js/bootstrap.min.js" type="text/javascript"></script>

    <title>程序管理后台</title>
    <style>
        body {
            background: url("statics/images/login/login_bg.jpg");
        }

        .login-content {
            background: rgba(150, 150, 150, 0.2);
            width: 400px;
            margin: 100px auto 0px;
            padding-bottom: 5px;
        }
        .login-form{
            width: 300px;
            margin: 30px auto;
        }

        /*div {*/
            /*border: 1px solid red;*/
        /*}*/
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col text-center">
            <h1>欢迎登录小程序后台</h1>
        </div>
    </div>
    <div class="login-content">
        <h3 class="align-center">LOGIN</h3>
        <form class="login-form" role="form" action="${springMacroRequestContext.contextPath}/login" method="post">
                <div class="form-group">
                    <label class="sr-only control-label" for="username">用户名：</label>
                    <input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名"/>
                </div>
                <div class="form-group">
                    <label class="sr-only control-label" for="password">密码：</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码"/>
                </div>
                <div class="form-group">
                    <button type="button" class="btn btn-primary form-control" onclick="check(this.form)">Sign in
                    </button>
                </div>
        </form>
    </div>
</div>
<script>
    function check(form) {
        if (form.username.value == '') {
            alert("请输入用户名");
            form.username.value.focus();
            return false;
        }
        if (form.password.value == '') {
            alert("请输入密码");
            form.password.value.focus();
            return false;
        }
        $(form).submit();
    }
</script>
</body>
</html>
