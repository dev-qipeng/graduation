<!-- 视频管理-->
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div>
            <!--向左对齐-->
            <div class="nav navbar-nav navbar-left">
                &nbsp;&nbsp;<button class="btn btn-primary navbar-btn add-category">添加分类</button>
            </div>
            <!--向右对齐-->
            <div class="nav navbar-nav navbar-right">
                <button class="btn btn-info navbar-btn refresh-cate">
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
            <th>名称</th>
            <th>创建时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <#list pageInfo.list as category>
            <tr>
                <td width="10%">${category.id}</td>
                <td width="20%">${category.name !''}</td>
                <td width="20%">${(category.createTime?string("yyyy-MM-dd"))!} </td>
                <td width="10%">
                    <button class="btn btn-success update-category" id="${category.id}">修改</button>
                    <button class="btn btn-danger delete-category" id="${category.id}">删除</button>
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
            <li class="page-item"><a class="page-link" href="${ctx}/category/list.do?pageNum=${pageInfo.pageNum-1}">Previous</a></li>
        <#else>
            <li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
        </#if>
        <#list pageInfo.navigatepageNums as page_num>
            <#if (page_num == pageInfo.pageNum)>
                <li class="page-item active"><a class="page-link" href="#">${page_num}</a></li>
            <#else>
                <li class="page-item"><a class="page-link" href="${ctx}/category/list.do?pageNum=${page_num}">${page_num}</a></li>
            </#if>
        </#list>
        <#if pageInfo.hasNextPage?then('true', 'false') == 'true'>
            <li class="page-item"><a class="page-link" href="${ctx}/category/list.do?pageNum=${pageInfo.pageNum+1}">Next</a></li>
        <#else>
            <li class="page-item disabled"><a class="page-link" href="#">Next</a></li>
        </#if>
    </ul>
</footer>
<script>
    $(function(){
        var ctx = '${ctx}';
        $(".add-category").click(function(){
            var url = ctx+'/category/goto-add-category.do';
            $.get(url,function(html){
                $(".content").html(html)
            });
        });
        $(".refresh-cate").click(function(){
            var url = ctx+'/category/list.do?pageNum=1';
            $.get(url,function(html){
                $(".content").html(html)
            });
        });
        $(".update-category").click(function(){
            var id = this.id;
            console.log(id);
            var url = ctx+'/category/'+id+'/goto-update-category.do';
            $.get(url,function(html){
                $(".content").html(html)
            });
        });
        $(".delete-category").click(function(){
            var id = this.id;
            bootbox.confirm("确定要删除吗？", function(result){
                if(result === true){
                    var url = ctx+'/category/'+id+'/delete.do';
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