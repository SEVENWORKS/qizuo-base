<%@ include file="../../../base/base_tags.jsp"%>
<!-- 前端插件 -->
<div class="block-area">
    <div style="width: 100%;height: 100%">
        <table id="tab1" width="100%" border="0" align="left" cellpadding="0" cellspacing="0" class="Pu_table">
            <tr>
                <th>文件<span style="color:#F00;font-weight:bold;font-size:12px;"> ( 支持文档、图片、视屏等 ) </span></th>
                <th width="33%">备注说明</th>
                <th width="10%">选择文件</th>
            </tr>
            <tr style="height: 70%">
                <td>
                    <div id="some_file_queue">
                        <!-- 遍历已经上传的附件,并排序 -->
                        <c:forEach items="${dataAppeList}" var="appe" varStatus="s">
                            <div class="uploadify-queue-item" id="fileitem_${s.index}" fileName="${appe.name}">
                                <c:if test="${status!=null&&(status=='2'||status=='3') }">
                                    <div class="cancel"><a href="javascript:void(0)" onclick="return delFile('fileitem_${s.index}','${appe.id}')">X</a></div>
                                </c:if>
                                <input type="button" class="btn2 btn_blue52" style="float:right;margin-right:5px;position:relative;top:-3px;" value="预览" onclick="openUr('${appe.resourceNameStr}')" />
                                <span class="fileName"><a title="${appe.name}" onclick="openUr('${appe.resourceNameStr}')">${appe.name}</a></span><span class="data"></span>
                            </div>
                        </c:forEach>
                        <c:if test="${fn:length(dataAppeList)<=0}">
                            <label id="saym">暂时没有相关上传文件</label>
                        </c:if>
                    </div>
                </td>
                <td id="remarksContainer">
                    <c:forEach items="${dataAppeList}" var="appe" varStatus="s">
                        <div class="uploadify-queue-item" style="padding: 3px"><input class="form-control remarksInput" value="${appe.remarks}" type="text" filename="${appe.name}" style="height: 24px"></div>
                    </c:forEach>
                </td>
                <td style="vertical-align:middle;"><input id="fileUploadD" type="file"/></td>
            </tr>
            <tr>
                <td colspan="3" style="vertical-align:middle;">
                    <button class="btn btn-sm btn-default" id="filesButton" style="" onclick="doUpload_start();">开始上传</button>
                </td>
            </tr>
        </table>
    </div>
</div>

<!-- 执行js -->
<script type="text/javascript" src="${staticPath}/js/uploadify/jquery.uploadify.js"></script>
<script>
    globalJs(function(){
        /** 上传配置 */
        var parame = {
            'auto'     : false,
            'height'   : 30,
            'width'    : 80,
            'successTimeout' : 5,
            'simUploadLimit' : 3,
            'timeoutuploadLimit' : 3,
            'multi'    : true,
            'queueID'  : 'some_file_queue',//浏览后的文件显示在哪个标识的html元素中
            'queueSizeLimit' : 999,//默认999当前队列里面等待上传的文件数量限制
            'uploadLimit' : 999, //上传的文件数量限制
            'fileSizeLimit' : '1000MB',
            'fileTypeDesc' : '事件文件上传',
            //'fileTypeExts' : '*.pdf',.jpg;*.jpeg;*.png;*.gif;
            'removeCompleted' : false, //默认true
            //'removeTimeout' : 10, //上传完成后的删除延时
            //'overrideEvents' : ['onUploadSuccess'],
            //'progressData' : 'percentage', //‘percentage’ or ‘speed’.
            //'buttonClass' : 'btn',
            'buttonImage': '${ctxStatic}/css/uploadify/browse-btn.png',
            //'buttonText' : '选择文件',//图片覆盖了文字,所以注释
            'swf'        : '${ctxStatic}/css/uploadify/uploadify.swf',
            'uploader'   : '${ctx}/inf/upload/uploadFile_pd_single',
            /*'itemTemplate':'<div id="SWFUpload_0_0" class="uploadify-queue-item" filename="pdfText.pdf">' +
            '<div class="cancel"><a href="javascript:$(\'#fileUploadD\').uploadify(\'cancel\', \'SWFUpload_0_0\')">X</a></div>' +
            '<span class="fileName">(5KB)</span><span class="data"></span>' +
            '<div class="uploadify-progress"><div class="uploadify-progress-bar"></div></div>' +
            '</div>*/
            //'checkExisting' : './fileCheckExisting',
            'method'     : 'post',
            'fileObjName': 'files',
            //'formData'   : null,
            'onSelect' : function(file) {
                if($("#saym")){
                    $("#saym").remove();
                }
                /*var fileId='';
                $(".uploadify-queue-item").each(function(){
                    var fn=$(this).attr('filename');
                    if(fn==file.name){
                        fileId=$(this).attr("id");
                    }
                })*/
                var remarksHtml='<div class="uploadify-queue-item" style="padding: 4px"><input class="form-control remarksInput" type="text" filename="'+file.name+'" style="24px"></div>';
                //备注信息
                $("#remarksContainer").append(remarksHtml);
            },'onCancel' : function(file) {
                var max=new Array();
                $(".remarksInput").each(function(){
                    var fn=$(this).attr('filename');
                    if(fn==file.name){
                        max.push(this);
                    }
                })
                $(max[0]).parent().remove();
            },'onClearQueue' : function(queueItemCount) {
                //alert(queueItemCount + ' file(s) were removed from the queue');
            },'onSelectError' : function(file, errorCode, errorMsg) {
                switch(errorCode) {
                    case -100:
                        alert("文件数量超出系统限制:"+parame.queueSizeLimit+"个");
                        break;
                    case -110:
                        alert("文件 ["+file.name+"] 大小超出系统限制的"+parame.fileSizeLimit+"大小！");
                        break;
                    case -120:
                        alert("文件 ["+file.name+"] 大小异常！");
                        break;
                    case -130:
                        alert("文件 ["+file.name+"] 类型不正确！");
                        break;
                }

            },'onUploadStart' : function(file) {
                /* with($('#' + $('#'+file.id).parent().attr("fileupload"))){
                     fd = uploadify('settings','formData');
                     uploadify('settings','formData',$.extend(fd,{'sortNum':1+fd.sortNum}));
                 }*/
                var remaks='';
                $(".remarksInput").each(function(){
                    var fl=$(this).attr("filename");
                    if(file.name==fl){
                        remaks=$(this).val();
                    }
                })
                allfileupload.uploadify('settings','formData',{
                    'remarks' :remaks,'dataId' :'${dataId}','dataType':'${dataType}','type':'${hosAddr}'
                });
            },'onUploadSuccess' : function(file, data, response) {
            },'onUploadError' : function(file, errorCode, errorMsg, errorString) {
            },'onUploadComplete' : function(file) {
            },'onUploadProgress' : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {
            },'onQueueComplete' : function(queueData) {
                location.reload();//所有执行完毕后执行刷新操作,为了使删除文件功能有效

            },'onFallback':function(){
                //检测FLASH失败调用
                alert("您未安装FLASH控件，无法上传文件！请安装FLASH控件后再试。");
            }
        };
        /** 上传对象初始化 */
        var allfileupload;
        $(function() {
            allfileupload=$('input[type=file]');
            allfileupload.uploadify($.extend(parame,{
                formData:{}
            }));
        });
        /** 上传 */
        function doUpload_start() {
            allfileupload.uploadify('upload',"*");
        }
        /** 删除上传 */
        function delFile(fileitem,id){
            if(confirm('是否确认要删除文件： ' + $('#'+fileitem).attr("fileName") + ' ?')){
                $.post(
                    '${ctx}/inf/upload/uploadFile_Delete_single',
                    {
                        'dataId':'${dataId}',
                        'id':id
                    },
                    function(data,textState,XHR){
                        if(data&&data.ret==1){
                            allfileupload.uploadify('cancel',fileitem);
                            var max=new Array();
                            $(".remarksInput").each(function(){
                                var fn=$(this).attr('filename');
                                if(fn==$('#'+fileitem).attr("fileName")){
                                    max.push(this);
                                }
                            })
                            $(max[0]).parent().remove();
                        }
                        else{
                            alert('文件删除失败，请稍后再试。');
                        }
                    },
                    'JSON');
            }
            return false;
        }

        //打开
        function openUr(url){
            window.open(url);
        }
    })
    /** ********************************************** */
</script>
