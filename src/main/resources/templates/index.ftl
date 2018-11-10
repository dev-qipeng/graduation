<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="小程序后台">
    <meta name="keywords" content="祁朋 毕业设计 视频 小程序">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <title>小程序管理后台</title>
    <link rel="stylesheet" type="text/css" href="${springMacroRequestContext.contextPath}/statics/css/common.css" />
    <link rel="stylesheet" type="text/css" href="${springMacroRequestContext.contextPath}/statics/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${springMacroRequestContext.contextPath}/upload/upload.css"/>
    <script src="${springMacroRequestContext.contextPath}/statics/js/jquery-3.3.1.min.js"></script>
    <script src="${springMacroRequestContext.contextPath}/statics/js/bootstrap.min.js"></script>
    <script src="${springMacroRequestContext.contextPath}/statics/js/jquery.form.min.js"></script>
    <script src="${springMacroRequestContext.contextPath}/statics/js/bootbox.min.js"></script>

    <script>
        // 生命全局项目路径
        var ctx='${springMacroRequestContext.contextPath}';
    </script>
</head>
<body>
<div class="leftMenu" id="leftMenu">
    <div id="logoDiv">
        <p id="logoP"><#--<img id="logo" alt="后台logo" src="${springMacroRequestContext.contextPath}/statics/images/logo.png">--><span>小程序后台管理</span></p>
    </div>
    <div id="personInfo">
        <p id="userName">${currentUser.nickname !""}</p>
        <p>
            <a>退出登录</a>
        </p>
    </div>
    <div class="menu-title">视频管理</div>
    <div class="menu-item menu-item-active" href="${springMacroRequestContext.contextPath}/video/list.do?pageNum=1"><img src="${springMacroRequestContext.contextPath}/statics/images/icon_source.png">视频列表</div>
    <div class="menu-item" href="${springMacroRequestContext.contextPath}/category/list.do?pageNum=1"><img src="${springMacroRequestContext.contextPath}/statics/images/icon_user_grey.png">分类管理</div>
    <div class="menu-title">用户管理</div>
    <div class="menu-item" href="${springMacroRequestContext.contextPath}/user/list.do?pageNum=1"><img src="${springMacroRequestContext.contextPath}/statics/images/icon_chara_grey.png">用户管理</div>
    <div class="menu-item" href="#chan"><img src="${springMacroRequestContext.contextPath}/statics/images/icon_change_grey.png">修改密码</div>
    <div class="menu-title">界面管理</div>
    <div class="menu-item" href="${springMacroRequestContext.contextPath}/banner/list.do?pageNum=1"><img src="${springMacroRequestContext.contextPath}/statics/images/icon_rule_grey.png">banner设置</div>
</div>
<div id="rightContent">
    <a class="toggle-btn" id="nimei">
        <i class="glyphicon glyphicon-align-justify"></i>
    </a>
    <!-- Tab panes -->
    <div class="content">

    </div>
</div>
<script>
    $(function() {
        $(".menu-item").click(function() {
            $(".menu-item").removeClass("menu-item-active");
            $(this).addClass("menu-item-active");
            var itmeObj = $(".menu-item").find("img");
            itmeObj.each(function() {
                var items = $(this).attr("src");
                items = items.replace("_grey.png", ".png");
                items = items.replace(".png", "_grey.png")
                $(this).attr("src", items);
            });
            var attrObj = $(this).find("img").attr("src");
            attrObj = attrObj.replace("_grey.png", ".png");
            $(this).find("img").attr("src", attrObj);

            // 显示右边内容
            var url = $(this).attr("href");
            $.get(url,function(html){
                $(".content").html(html)
            });
        });
    });
    $(function(){
        $(".menu-item").first().click();
    })
</script>

</body>
</html>