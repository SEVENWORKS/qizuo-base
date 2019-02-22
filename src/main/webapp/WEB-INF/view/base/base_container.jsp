<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>七作</title>
    <!-- header static tag in -->
    <%@ include file="base_tags.jsp"%>
    <!-- frame base -->
    <link href="${staticPath}css/base/bootstrap.min.css" rel="stylesheet">
    <link href="${staticPath}css/base/animate.min.css" rel="stylesheet">
    <link href="${staticPath}css/base/font-awesome.min.css" rel="stylesheet">
    <link href="${staticPath}css/base/form.css" rel="stylesheet">
    <link href="${staticPath}css/base/calendar.css" rel="stylesheet">
    <link href="${staticPath}css/base/style.css" rel="stylesheet">
    <link href="${staticPath}css/base/icons.css" rel="stylesheet">
    <link href="${staticPath}css/base/generics.css" rel="stylesheet">
    <!-- base -->
    <link href="${staticPath}css/system/base.css" rel="stylesheet">
    <!-- ztree -->
    <link rel="stylesheet" href="${staticPath}js/ztree/css/zTreeStyle.css">
</head>
<!-- id代表皮肤 -->
<body id="skin-tectile">
    <!-- 头部分 -->
    <header id="header" class="media">
        <jsp:include page="base_header.jsp"></jsp:include>
    </header>
    <div class="clearfix"></div>

    <!-- 左侧菜单栏和右侧iframe主体 -->
    <section id="main" class="p-relative" role="main">
        <!-- 左侧菜单栏 -->
        <aside id="sidebar">
            <jsp:include page="base_leftbar.jsp"></jsp:include>
            <jsp:include page="base_leftbar2.jsp"></jsp:include>
        </aside>
        <!-- 头部分2 -->
        <section id="content" class="container">
            <jsp:include page="base_header2.jsp"></jsp:include>
            <!--iframe主体 -->
            <section id="baseFrame">
                <%--<iframe id="baseContainerIframe" src="" style="width:100%;height: 91%" scrolling="auto" frameborder="no"></iframe>--%>
            </section>
        </section>

    </section>

    <!-- 脚部分 -->
    <footer>

    </footer>

    <!-- script base -->
    <!-- jQuery -->
    <script src="${staticPath}js/base/jquery-ui.min.js"></script>
    <script src="${staticPath}js/base/jquery.easing.1.3.js"></script>
    <!-- Bootstrap -->
    <script src="${staticPath}js/base/bootstrap.min.js"></script>
    <!-- Charts -->
    <script src="${staticPath}js/base/charts/jquery.flot.js"></script> <!-- Flot Main -->
    <script src="${staticPath}js/base/charts/jquery.flot.time.js"></script> <!-- Flot sub -->
    <script src="${staticPath}js/base/charts/jquery.flot.animator.min.js"></script> <!-- Flot sub -->
    <script src="${staticPath}js/base/charts/jquery.flot.resize.min.js"></script> <!-- Flot sub - for repaint when resizing the screen -->
    <script src="${staticPath}js/base/sparkline.min.js"></script> <!-- Sparkline - Tiny charts -->
    <script src="${staticPath}js/base/easypiechart.js"></script> <!-- EasyPieChart - Animated Pie Charts -->
    <script src="${staticPath}js/base/charts.js"></script> <!-- All the above chart related functions -->
    <!-- Map -->
    <script src="${staticPath}js/base/maps/jvectormap.min.js"></script> <!-- jVectorMap main library -->
    <script src="${staticPath}js/base/maps/usa.js"></script> <!-- USA Map for jVectorMap -->
    <!-- DateTime -->
    <script src="${staticPath}js/base/datetimepicker.min.js"></script>
    <!-- Color -->
    <script src="${staticPath}js/base/colorpicker.min.js"></script>
    <!-- Textare autosize -->
    <script src="${staticPath}js/base/autosize.min.js"></script>
    <!-- TextArea Editor -->
    <script src="${staticPath}js/base/editor2.min.js"></script> <!-- WYSIWYG Editor -->
    <script src="${staticPath}js/base/markdown.min.js"></script> <!-- Markdown Editor -->
    <!-- Spinner智能数字加减 -->
    <script src="${staticPath}js/base/spinner.min.js"></script>
    <!-- Input进度条 -->
    <script src="${staticPath}js/base/slider.min.js"></script>
    <!-- File Upload -->
    <script src="${staticPath}js/base/fileupload.min.js"></script>
    <!-- Checkbox + Radio -->
    <script src="${staticPath}js/base/icheck.js"></script>
    <!-- Media -->
    <script src="${staticPath}js/base/media-player.min.js"></script> <!-- Video Player -->
    <script src="${staticPath}js/base/pirobox.min.js"></script> <!-- Lightbox -->
    <!-- select -->
    <script src="${staticPath}js/base/select.min.js"></script><!-- Custom Select(select插件) -->
    <script src="${staticPath}js/base/chosen.min.js"></script><!-- Custom Multi Select(select插件) -->
    <!-- scroll -->
    <script src="${staticPath}js/base/scroll.min.js"></script>
    <!--  Form validate -->
    <script src="${staticPath}js/base/validation/validate.min.js"></script>
    <script src="${staticPath}js/base/validation/validationEngine.min.js"></script>
    <!-- Input Mask(格式化input输入格式的) -->
    <script src="${staticPath}js/base/input-mask.min.js"></script>
    <!-- Other -->
    <script src="${staticPath}js/base/calendar.min.js"></script>
    <script src="${staticPath}js/base/feeds.min.js"></script>
    <!-- base frame functions -->
    <script src="${staticPath}js/base/functions.js"></script>
    <!-- pjax -->
    <script src="${staticPath}js/pjax/jquery.pjax.js"></script>
    <!-- art-template -->
    <script src="${staticPath}js/template/art-template.js"></script>
    <!-- base page -->
    <script src="${staticPath}js/system/base_page.js"></script>
    <!-- ztree -->
    <script src="${staticPath}js/ztree/js/jquery.ztree.core-3.5.min.js"></script>
    <script src="${staticPath}js/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
    <!-- 框架基本函数执行 -->
    <script>
        $(function(){
            $.post('${adminPath}system/login/frameData',{},function(data){
                backResult(data,function(data){
                    //头
                    baseHeader_msg(data.baseHeader_msgList);
                    //左侧菜单
                    baseLeftbar_menu(isNotBlank(data.userMsg)?data.userMsg.menuPoJos:null);
                    //用户信息
                    baseLeftBar2_user(data.userMsg);
                })
            })
            //头搜索
            baseHeader_mainSearch();
            //用户菜单
            baseLeftBar2_userMenu(userMenus);
            //目录和标题
            //baseHeader2_pageTitle(title,level);
        })

        /** 主体iframe跳转(用iframe或者其他异步的时候放开这个方法，同时放开leftbar中这个方法) */
        function baseContainerIframe(url){
            //iframe跳转
            /*if(isNotBlank(url)){
                if(-1!=url.indexOf(global$openWindow)){
                    url=url.split(global$openWindow).join("");
                    //框架页面
                    window.open(url);
                }else{
                    //普通
                    $("#baseContainerIframe").prop('src',url);
                }
            }*/
            //异步加载跳转
            //ajaxHtml(url,'#baseFrame');
        }

        /** pjax使用 */
        function pjax(){
            //普通加载
            //pjaxFuncForClick('.side-menu a',global$frameContainer);
            //控制在click事件中
            $(document).on('click', '.side-menu a', function(event) {
                var url=$(this).prop('href');
                var level=$(this).attr('level');
                //菜单
                var title='';
                var title2='';
                if(0==level){
                    title=$(this).find("span").text();
                }else{
                    title2=$(this).attr('bname');
                    title=$(this).text();
                }
                baseHeader2_pageTitle(title,level,title2);
                //跳转
                if(isNotBlank(url)){
                    if(-1!=url.indexOf(global$openWindow)){
                        url=url.split(global$openWindow).join("");
                        //框架页面
                        window.open(url);
                        return false;
                    }else{
                        //按钮清除
                        buttonRemove();
                        //普通
                        $.pjax.click(event, {push:false,replace:false,container: global$frameContainer});
                    }
                }else{
                    return false;
                }

            })
        }
    </script>
</body>
</html>
