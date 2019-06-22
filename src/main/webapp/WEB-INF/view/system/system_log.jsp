<%@ include file="../frame/base_tags.jsp"%>
<!-- 内容区域 -->
<!-- 有悬浮效果的表格 -->
<div class="block-area" id="tableHover">
    <%--<h3 class="block-title">Table with Hover Style</h3>--%>
    <div class="table-responsive overflow">
        <table class="table table-bordered table-hover tile" id="dataContainer">
            <thead>
            <tr>
                <th>类型</th>
                <th>创建人</th>
                <th>创建时间</th>
                <th>创建IP</th>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>
</div>

<!-- 分页 -->
<div class="block-area">
    <div class="row">
        <div id="pageContainer" class="col-md-12 text-center">
            <ul class="pagination" id="page_ul">

            </ul>
        </div>
    </div>
</div>

<!-- 数据模板 -->
<script id="baseTpl" type="text/html">
    {{if null!=$data&&$data.length>0}}
    {{each $data}}
    <tr onclick="buttonPanel('查看','updateDate(\'${jumpPath}system/logDo?baseId={{$value.baseId}}\')');">
        <td>{{$value.typeCd}}</td>
        <td>{{$value.baseCreateUserId}}</td>
        <td>{{$value.baseCreateTime}}</td>
        <td>{{$value.baseCreateIp}}</td>
    </tr>
    {{/each}}
    {{else}}
    <tr><td colspan="{{$imports.th_length}}" style="text-align: center">暂无数据</td></tr>
    {{/if}}
</script>
<!-- 执行js -->
<script>
    globalJs(function(){
        /** ************************************************************ */
        //分页(传入获取分页数据的方法,可传分页size和分页no)
        pageHtml(qPage,10,1);
        //分页数据查询(都默认一个函数)
        function qPage(func,pageNo,pageSize){
            $.post('${modulePath}system/log/page',{pageNo:pageNo,pageSize:pageSize},function(data){
                backResult(data,function(data){
                    //模板(数据，容器，模板)(当出现不在返回元素中值的时候，可以往对象中添加数据，毕竟从java返回过来后就是一个js对象)
                    tplFuncTable(data.entitys);
                    //执行分页
                    func(data);
                })
            })
        }
        /** ************************************************************ */
    })
    /** ********************************************** */
</script>
