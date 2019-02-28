<%@ include file="../../../base/base_tags.jsp"%>
<!-- 前端插件 -->
<div class="block-area">
    <div class="wysiwye-editor"></div>
</div>

<!-- 执行js -->
<script>
    $(function(){
        $('.wysiwye-editor').summernote({
            height: 200
        });
    })
</script>
