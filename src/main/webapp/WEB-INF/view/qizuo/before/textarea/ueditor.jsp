<%@ include file="../../../base/base_tags.jsp"%>
<!-- 前端插件 -->
<div class="block-area">

</div>
<!-- 编辑器 -->
<script id="container" name="content" type="text/plain">这里写你的初始化内容</script>
<!-- 执行js -->
<script>
    $(function(){
        var ue = UE.getEditor('container');
    })
</script>
