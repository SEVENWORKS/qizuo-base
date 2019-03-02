<%@ include file="../../../base/base_tags.jsp"%>
<!-- 前端插件 -->
<div class="block-area">
    <textarea class="form-control overflow m-b-10"></textarea>
</div>

<!-- 执行js -->
<script>
    try {
        $(function(){
            $('textarea').niceScroll();
        })
    }catch (error){
        console.log(error);
    }
</script>
