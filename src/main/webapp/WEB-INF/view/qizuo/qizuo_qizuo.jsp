<%@ include file="../base/base_tags.jsp"%>
<!-- 内容区域 -->
<div class="block-area" id="dataContainer">
	<!-- layui -->
	<div class="m-b-10 col-lg-2">
		<h3 class="block-title m-b-5">layui</h3>
		<select name="dataScopeCds" class="select">
			<option value=""></option>
		</select>
	</div>
</div>

<!-- 执行js -->
<script>
    $(function(){
        //多选
        if($('.select')[0]) {
			$('.select').selectpicker();
        }
        //跳转
		$("option").on("click",function(){
		    if(isNotBlank($(this).val())){
                pjaxFunc($(this).val())
			}
		})
    })
</script>
