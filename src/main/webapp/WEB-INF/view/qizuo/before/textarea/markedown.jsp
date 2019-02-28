<%@ include file="../../../base/base_tags.jsp"%>
<!-- 前端插件 -->
<div class="block-area">
    <textarea class="markdown-editor"></textarea>
</div>

<!-- 执行js -->
<script>
    $(function(){
        $('.markdown-editor').markdown({
            autofocus:false,
            savable:false
        });
    })
</script>
