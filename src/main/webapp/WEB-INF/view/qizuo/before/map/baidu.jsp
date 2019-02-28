<%@ include file="../../../base/base_tags.jsp"%>
<!-- 前端插件 -->
<div class="block-area">

</div>

<!-- 执行js -->
<script>
    $(function(){
        //百度地图 异步加载地图
        function loadJScript() {
            var script = document.createElement("script");
            script.type = "text/javascript";
            script.src = "http://api.map.baidu.com/api?v=2.0&ak=fOyPn3hbgEBvqm2zI0UnLgOG3M1mE1rM&callback=init";//赋予密钥和回调函数
            document.body.appendChild(script);
        }
        window.onload = loadJScript;

        function init() {
            map= new BMap.Map("allmap");            // 创建Map实例
            var point ='';
            if('${business.longitude}'!=''&&'${business.latitude}'!=''){
                point = new BMap.Point('${business.longitude}', '${business.latitude}');
            }else{
                point = new BMap.Point(116.404, 39.915); // 创建点坐标
            }
            map.centerAndZoom(point,11);				//初始化并赋予地图等级
            //map.enableScrollWheelZoom();                 //启用滚轮放大缩小
            //版权
            /*var cr = new BMap.CopyrightControl({anchor: BMAP_ANCHOR_TOP_RIGHT});
            cr.addCopyright({id:1,content:'七作',bounds:''});
            map.addControl(cr);*/
            //定位
            if('${business.longitude}'==''||'${business.latitude}'==''){
                var myCity = new BMap.LocalCity();
                myCity.get(myFun);
            }
            //搜索
            local = new BMap.LocalSearch('乌鲁木齐', {
                renderOptions:{map: map}
            });
            local.setPageCapacity(2);
            local.setSearchCompleteCallback(jw);

            //地理位置改变时
            changeSerach();
            $('#businessAddr').change(changeSerach);
            //初始化位置
            /*if($("#businessAddr").val()!=''){
                local.search($("#businessAddr").val());
            }*/
        }
    })
</script>
