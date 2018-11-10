<!-- 视频管理-->
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div>
            <!--向左对齐-->
            <div class="nav navbar-nav navbar-left">
                &nbsp;&nbsp;<button class="btn btn-primary navbar-btn add-video">添加视频</button>
            </div>
            <form class="navbar-form navbar-left" role="search">
                <input type="text" class="form-control">
                <button type="submit" class="btn btn-default">
                    搜索
                </button>
            </form>
            <!--向右对齐-->
            <form class="navbar-form navbar-right" role="search">
                <label for="sort">类别:&nbsp;</label>
                <select id="sort" class="form-control">
                    <option>科幻</option>
                    <option>恐怖</option>
                    <option>动漫</option>
                </select>
            </form>
        </div>
    </div>
</nav>

<div class="table-responsive">
    <table class="table">
        <thead>
        <tr>
            <th >排序</th>
            <th>名称</th>
            <th>类别</th>
            <th>首图</th>
            <th>评分</th>
            <th>点赞数</th>
            <th>播放数</th>
            <th>上传时间</th>
            <th style="width: 40px">播放地址</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <#list pageInfo.list as video>
            <tr>
                <td width="10%">${video.id}</td>
                <td width="15%" style="word-break:break-all; word-wrap:break-word; white-space:inherit">${video.name !''}</td>
                <#list categoryList as category>
                    <#if (category.id == video.categoryId)>
                        <td width="10%">${category.name !}</td>
                    </#if>
                </#list>
                <td width="10%">
                    <a href="${video.poster !''}" target="_blank"><img src="${video.poster !''}" alt="封面" width="60px" height="60px"></a>
                </td>
                <td width="10%">${video.score !''}</td>
                <td width="10%">${video.likeNum !''}</td>
                <td width="10%">${video.playNum !''}</td>
                <td width="10%">${(video.createTime?string("yyyy-MM-dd"))!} </td>
                <td width="10%">
                    <a href="${video.url !''}" target="_blank">点击查看</a>
                </td>
                <td width="10%">
                    <button class="btn btn-success update-video" id="${video.id}">修改</button>
                    <button class="btn btn-danger delete-video" id="${video.id}">删除</button>
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
            <li class="page-item"><a class="page-link" href="${springMacroRequestContext.contextPath}/video/list.do?pageNum=${pageInfo.pageNum-1}">Previous</a></li>
        <#else>
            <li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
        </#if>
        <#list pageInfo.navigatepageNums as page_num>
            <#if (page_num == pageInfo.pageNum)>
                <li class="page-item active"><a class="page-link" href="#">${page_num}</a></li>
            <#else>
                <li class="page-item"><a class="page-link" href="${springMacroRequestContext.contextPath}/video/list.do?pageNum=${page_num}">${page_num}</a></li>
            </#if>
        </#list>
        <#if pageInfo.hasNextPage?then('true', 'false') == 'true'>
            <li class="page-item"><a class="page-link" href="${springMacroRequestContext.contextPath}/video/list.do?pageNum=${pageInfo.pageNum+1}">Next</a></li>
        <#else>
            <li class="page-item disabled"><a class="page-link" href="#">Next</a></li>
        </#if>
    </ul>
</footer>
<script>
    $(function(){
        var ctx = '${springMacroRequestContext.contextPath}';
        $(".add-video").click(function(){
            var url = ctx+'/video/goto-add-video.do';
            $.get(url,function(html){
                $(".content").html(html)
            });
        });
        $(".update-video").click(function(){
            var id = this.id;
            var url = ctx+'/video/'+id+'/goto-update-video.do';
            $.get(url,function(html){
                $(".content").html(html)
            });
        });
        $(".delete-video").click(function(){
            var id = this.id;
            bootbox.confirm("确定要删除吗？", function(result){
                if(result === true){
                    var url = ctx+'/video/'+id+'/delete.do';
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