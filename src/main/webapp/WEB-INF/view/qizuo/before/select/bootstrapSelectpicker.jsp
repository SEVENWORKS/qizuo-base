<%@ include file="../../../base/base_tags.jsp"%>
<!-- 前端插件 -->
<div class="block-area">
    <select multiple="" class="form-control m-b-10">
        <option>1</option>
        <option>2</option>
        <option>3</option>
        <option>4</option>
        <option>5</option>
    </select>
</div>

<!-- 执行js -->
<script>
    $(function(){
        $('select').selectpicker();
    })
</script>