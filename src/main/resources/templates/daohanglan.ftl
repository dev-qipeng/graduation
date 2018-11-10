<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>导航栏</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="statics/css/bootstrap.min.css">
    <script src="statics/js/jquery-3.3.1.min.js"></script>
    <script src="statics/js/popper.min.js"></script>
    <script src="statics/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container">
    <!--导航栏-->
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark navbar-fixed-top">
        <!--Logo-->
        <a class="navbar-brand" href="#">
            <img src="http://static.runoob.com/images/mix/bird.jpg" alt="Logo" style="width:40px;">
        </a>
        <!--可折叠的-->
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <!--链接-->
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Link 1</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Link 1</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Link 1</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Link 1</a>
                </li>
            </ul>
        </div>
        <!--导航栏表单按钮-->
        <form class="form-inline">
            <input type="text" class="form-control" placeholder="Search">
            <button type="button" class="btn btn-success">Search</button>
        </form>
    </nav>



<!--导航-->
    <div>
        <ul class="nav nav-tabs">
            <li class="nav-item">
                <a class="nav-link" href="#">link 1</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">link 1</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="#">link 1</a>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" href="#">link 1</a>
            </li>
        </ul>
    </div>
    <div>
        <ul class="nav nav-pills">
            <li class="nav-item">
                <a class="nav-link" href="#">link 1</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="#">link 1</a>
            </li>
            <li class="nav-item">
                <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown">link 1</a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="#">Link 1</a>
                    <a class="dropdown-item" href="#">Link 2</a>
                    <a class="dropdown-item" href="#">Link 3</a>
                    <a class="dropdown-item" href="#">Link 4</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" href="#">link 1</a>
            </li>
        </ul>
    </div>
<!--下拉菜单-->
    <div class="dropdown">
        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">下拉菜单</button>
        <div class="dropdown-menu">
            <a class="dropdown-item" href="#">Link 1</a>
            <a class="dropdown-item" href="#">Link 2</a>
            <a class="dropdown-item" href="#">Link 3</a>
            <a class="dropdown-item" href="#">Link 4</a>
        </div>
    </div>
</div>

</body>
</html>