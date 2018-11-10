<!-- banner管理-->
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div>
            <!--向左对齐-->
            <div class="nav navbar-nav navbar-left">
                &nbsp;&nbsp;<button class="btn btn-primary navbar-btn add-banner">添加Banner</button>
            </div>
            <!--向右对齐-->
            <div class="nav navbar-nav navbar-right">
                <button class="btn btn-info navbar-btn refresh-banner">
                    <span class="glyphicon glyphicon-refresh"></span>刷新
                </button>
                &nbsp;&nbsp;
            </div>
        </div>
    </div>
</nav>

<div class="table-responsive">
    <table class="table">
        <thead>
        <tr>
            <th >排序</th>
            <th>图片预览</th>
            <th>视频id</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <#list pageInfo.list as banner>
            <tr>
                <td width="10%">${banner.id}</td>
                <td width="20%">
                    <a href="${banner.imgUrl!}" target="_blank"><img src="${banner.imgUrl!}" alt="banner预览" width="200px" height="100px"></a>
                </td>
                <td width="20%">${banner.videoId!}</td>
                <td width="10%">
                    <button class="btn btn-success update-banner" id="${banner.id}">修改</button>
                    <button class="btn btn-danger delete-banner" id="${banner.id}">删除</button>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>

<!--页码块-->
<footer class="footer">
    <ul class="pagination">
        <#if pageInfo.hasPreviousPage?then('true', 'false') == 'true'>
            <li class="page-item"><a class="page-link" href="${springMacroRequestContext.contextPath}/banner/list.do?pageNum=${pageInfo.pageNum-1}">Previous</a></li>
        <#else>
            <li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
        </#if>
        <#list pageInfo.navigatepageNums as page_num>
            <#if (page_num == pageInfo.pageNum)>
                <li class="page-item active"><a class="page-link" href="#">${page_num}</a></li>
            <#else>
                <li class="page-item"><a class="page-link" href="${springMacroRequestContext.contextPath}/banner/list.do?pageNum=${page_num}">${page_num}</a></li>
            </#if>
        </#list>
        <#if pageInfo.hasNextPage?then('true', 'false') == 'true'>
            <li class="page-item"><a class="page-link" href="${springMacroRequestContext.contextPath}/banner/list.do?pageNum=${pageInfo.pageNum+1}">Next</a></li>
        <#else>
            <li class="page-item disabled"><a class="page-link" href="#">Next</a></li>
        </#if>
    </ul>
</footer>
<script>
    $(function(){
        var ctx = '${springMacroRequestContext.contextPath}';
        $(".add-banner").click(function(){
            var url = ctx+'/banner/goto-add-banner.do';
            $.get(url,function(html){
                $(".content").html(html)
            });
        });
        $(".refresh-banner").click(function(){
            var url = ctx+'/banner/list.do?pageNum=1';
            $.get(url,function(html){
                $(".content").html(html)
            });
        });
        $(".update-banner").click(function(){
            var id = this.id;
            console.log(id);
            var url = ctx+'/banner/'+id+'/goto-update-banner.do';
            $.get(url,function(html){
                $(".content").html(html)
            });
        });
        $(".delete-banner").click(function(){
            var id = this.id;
            bootbox.confirm("确定要删除吗？", function(result){
                if(result === true){
                    var url = ctx+'/banner/'+id+'/delete.do';
                    $.ajax({
                        type: 'DELETE',
                        url: url,
                        success: function(json){
                            console.log(json);
                            if(json.result === 1){
                                bootbox.alert({
                                    message: "删除成功",
                                    size: 'small'
                                });
                            }else{
                                bootbox.alert({
                                    message: "删除失败",
                                    size: 'small'
                                });
                            }
                        }
                    });
                }
            });
        });

        // 分页点击
        $(".page-link").click(function() {
            // 显示右边内容
            var url = $(this).attr("href");
            $.get(url,function(html){
                $(".content").html(html)
            });
            return false;
        });
    })
</script>