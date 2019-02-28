<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>PDF</title>
    <style type="text/css" mcs_bogus="1">
        .blankTr td{
            height: 25px;
        }
        .bigHTr tr{
            height: 5.2cm;
        }
        @page {
            size: a4;
        }
        .htr1 tr {
            height: 1.5cm;
        }
        .htr2 tr {
            height: 1.5cm;
        }
        .htr3 tr {
            height: 6.2cm;
        }
        .ftr {
            font-family: KaiTi;
        }
        .manyf {
            font-family: KaiTi;
            display:block;
            font-size: 0.5cm;
            text-indent:1cm;
            width: 100%;
            line-height: 1cm;
        }
    </style>
</head>
<body style="font-family:SimSun;font-size: 0.5cm">
<!--
    作者：fangl
    时间：2018-08-24
    描述：第一页头
-->
<div style="height: 5cm;width: 100%;">
    <div style="width: 20%;float: left;">
        <img src="theme.png"  width="150px" height="100px"/>
    </div>
    <div style="width: 20%; float: right;height: 100px;line-height: 100px;">
        编号:${projEnterApplyinfoEntity.applyId!""}
    </div>
</div>
<div style="height: 3.5cm" align="center">
    <h1>南京大学医学院附属鼓楼医院</h1>
    <h1>医疗新技术验收暨十佳新技术申报书</h1>
</div>
<table style="width: 80%;margin: 0px auto;" class="htr1 ftr">
    <tr>
        <td width="25%" valign="bottom">技 术 名 称：</td>
        <td width="75%" style="border-bottom: 1px solid #000000;" align="center" valign="bottom">${projEnterApplyinfoEntity.projCnNm!""}</td>
    </tr>
    <#--<tr>
        <td width="75%" style="border-bottom: 1px solid #000000;" align="center" valign="bottom">${projEnterApplyinfoEntity.projCnNm!""}</td>
    </tr>-->
    <tr>
        <td width="25%" valign="bottom">申 报 科 室：</td>
        <td width="75%" style="border-bottom: 1px solid #000000;" align="center" valign="bottom">${projEnterApplyinfoEntity.applyDepartNm!""}</td>
    </tr>
    <tr>
        <td width="25%" valign="bottom">主要完成人 ：</td>
        <td width="75%" style="border-bottom: 1px solid #000000;" align="center" valign="bottom">${projEnterApplyinfoEntity.principalNm!""}</td>
    </tr>
    <tr>
        <td width="25%" valign="bottom">答  辩  人 ：</td>
        <td width="75%" style="border-bottom: 1px solid #000000;" align="center" valign="bottom">${projEnterApplyinfoEntity.replyNmNm!""}</td>
    </tr>
    <tr>
        <td width="25%" valign="bottom">联 系 电 话：</td>
        <td width="75%" style="border-bottom: 1px solid #000000;" align="center" valign="bottom">${projEnterApplyinfoEntity.phone!""}</td>
    </tr>
    <tr>
        <td width="25%" valign="bottom">电 子 邮 箱：</td>
        <td width="75%" style="border-bottom: 1px solid #000000;" align="center" valign="bottom">${projEnterApplyinfoEntity.email!""}</td>
    </tr>
    <tr>
        <td width="25%" valign="bottom">科主任签字 ：</td>
        <td width="75%" style="border-bottom: 1px solid #000000;" align="center" valign="bottom"></td>
    </tr>
</table>
<div style="height: 2.5cm;" align="center">
    <h4 style="margin-top: 3.5cm">南京大学医学院附属鼓楼医院医务处制（2016版）</h4>
</div>

<!--
    作者：fangl
    时间：2018-08-24
    描述：第二页基本情况
-->
<div align="center">
    <h2>一、新技术基本情况</h2>
</div>
<table style="width: 100%;border-collapse:collapse;height: 25cm;text-align: center" class="baseTa htr1" border="1px">
    <tr>
        <td rowspan="2">技术<br/>名称</td>
        <td>中文</td>
        <td colspan="9" align="left">&nbsp;&nbsp;${projEnterApplyinfoEntity.projCnNm!""}</td>
    </tr>
    <tr>
        <td>英文</td>
        <td colspan="9" align="left">&nbsp;&nbsp;${projEnterApplyinfoEntity.projEnNm!""}</td>
    </tr>
    <tr>
        <td colspan="2">申报科室</td>
        <td colspan="9" align="left">&nbsp;&nbsp;${projEnterApplyinfoEntity.applyDepartNm!""}</td>
    </tr>
    <tr>
        <td colspan="2">主要完成人<br/>(限填3人)</td>
        <td colspan="9" align="left">&nbsp;&nbsp;${projEnterApplyinfoEntity.principalNm!""}</td>
    </tr>
    <tr>
        <td colspan="2">答 辩 人</td>
        <td colspan="3" align="left">&nbsp;&nbsp;${projEnterApplyinfoEntity.replyNmNm!""}</td>
        <td colspan="2">联系手机</td>
        <td colspan="4" align="left">&nbsp;&nbsp;${projEnterApplyinfoEntity.phone!""}</td>
    </tr>
    <tr>
        <td colspan="2">技术目的</td>
        <td colspan="9" align="left">
            <div style="width: 90%;margin: 0px auto">
                <input type="checkbox" <#if projEnterApplyinfoEntity.techPurposeCd??&&projEnterApplyinfoEntity.techPurposeCd=='10'>checked="checked"</#if>/>
                <label>预防</label>
                <input type="checkbox" <#if projEnterApplyinfoEntity.techPurposeCd??&&projEnterApplyinfoEntity.techPurposeCd=='11'>checked="checked"</#if>/>
                <label>筛检</label>
                <input type="checkbox" <#if projEnterApplyinfoEntity.techPurposeCd??&&projEnterApplyinfoEntity.techPurposeCd=='12'>checked="checked"</#if>/>
                <label>诊断</label>
                <input type="checkbox" <#if projEnterApplyinfoEntity.techPurposeCd??&&projEnterApplyinfoEntity.techPurposeCd=='13'>checked="checked"</#if>/>
                <label>鉴别诊断</label><br></br>
                <input type="checkbox" <#if projEnterApplyinfoEntity.techPurposeCd??&&projEnterApplyinfoEntity.techPurposeCd=='14'>checked="checked"</#if>/>
                <label>治疗</label>
                <input type="checkbox" <#if projEnterApplyinfoEntity.techPurposeCd??&&projEnterApplyinfoEntity.techPurposeCd=='15'>checked="checked"</#if>/>
                <label>康复</label>
                <input type="checkbox" <#if projEnterApplyinfoEntity.techPurposeCd??&&projEnterApplyinfoEntity.techPurposeCd=='16'>checked="checked"</#if>/>
                <label>护理</label>
                <input type="checkbox" <#if projEnterApplyinfoEntity.techPurposeCd??&&projEnterApplyinfoEntity.techPurposeCd=='17'>checked="checked"</#if>/>
                <label>其他</label>
            </div>
        </td>
    </tr>
    <tr>
        <td colspan="2">开展形式</td>
        <td colspan="9" align="left">
            <div style="width: 90%;margin: 0px auto">
                <input type="checkbox" <#if projEnterApplyinfoEntity.developFormCd??&&projEnterApplyinfoEntity.developFormCd=='10'>checked="checked"</#if>/>
                <label>自主创新</label>
                <input type="checkbox" <#if projEnterApplyinfoEntity.developFormCd??&&projEnterApplyinfoEntity.developFormCd=='11'>checked="checked"</#if>/>
                <label>引进改良</label>
                <input type="checkbox" <#if projEnterApplyinfoEntity.developFormCd??&&projEnterApplyinfoEntity.developFormCd=='12'>checked="checked"</#if>/>
                <label>技术集成</label>
            </div>
        </td>
    </tr>
    <tr>
        <td colspan="2">技术含量</td>
        <td colspan="9" align="left">
            <div style="width: 90%;margin: 0px auto">
                <input type="checkbox" <#if projEnterApplyinfoEntity.techContentCd??&&projEnterApplyinfoEntity.techContentCd=='10'>checked="checked"</#if>/>
                <label>手术操作为主</label>
                <input type="checkbox" <#if projEnterApplyinfoEntity.techContentCd??&&projEnterApplyinfoEntity.techContentCd=='11'>checked="checked"</#if>/>
                <label>器械设备为主</label>
                <input type="checkbox" <#if projEnterApplyinfoEntity.techContentCd??&&projEnterApplyinfoEntity.techContentCd=='12'>checked="checked"</#if>/>
                <label>药物制剂为主</label><br/>
                <input type="checkbox" <#if projEnterApplyinfoEntity.techContentCd??&&projEnterApplyinfoEntity.techContentCd=='13'>checked="checked"</#if>/>
                <label>其他</label>
            </div>
        </td>
    </tr>
    <tr>
        <td colspan="2">技术查新</td>
        <td colspan="9" align="left">
            <div style="width: 90%;margin: 0px auto">
                <input type="checkbox" <#if projEnterApplyinfoEntity.techCheckNew??&&projEnterApplyinfoEntity.techCheckNew=='10'>checked="checked"</#if>/>
                <label>填补国际空白</label>
                <input type="checkbox" <#if projEnterApplyinfoEntity.techCheckNew??&&projEnterApplyinfoEntity.techCheckNew=='11'>checked="checked"</#if>/>
                <label>填补国内空白</label>
                <input type="checkbox" <#if projEnterApplyinfoEntity.techCheckNew??&&projEnterApplyinfoEntity.techCheckNew=='12'>checked="checked"</#if>/>
                <label>填补省内空白</label><br></br>
                <input type="checkbox" <#if projEnterApplyinfoEntity.techCheckNew??&&projEnterApplyinfoEntity.techCheckNew=='13'>checked="checked"</#if>/>
                <label>填补市内空白</label>
                <input type="checkbox" <#if projEnterApplyinfoEntity.techCheckNew??&&projEnterApplyinfoEntity.techCheckNew=='14'>checked="checked"</#if>/>
                <label>填补院内空白</label>
            </div>
        </td>
    </tr>
    <tr>
        <td colspan="5">是否限制类医疗技术</td>
        <td colspan="6" align="left">
            <div style="width: 90%;margin: 0px auto">
                <input type="checkbox" <#if projEnterApplyinfoEntity.limitTech??&&projEnterApplyinfoEntity.limitTech=='10'>checked="checked"</#if>/>
                <label>第三类</label>
                <input type="checkbox" <#if projEnterApplyinfoEntity.limitTech??&&projEnterApplyinfoEntity.limitTech=='11'>checked="checked"</#if>/>
                <label>第二类</label>
                <input type="checkbox" <#if projEnterApplyinfoEntity.limitTech??&&projEnterApplyinfoEntity.limitTech=='12'>checked="checked"</#if>/>
                <label>其他类</label>
                <input type="checkbox" <#if projEnterApplyinfoEntity.limitTech??&&projEnterApplyinfoEntity.limitTech=='13'>checked="checked"</#if>/>
                <label>否</label>
            </div>
        </td>
    </tr>
    <tr>
        <td colspan="5">是否临床重点专科必备技术</td>
        <td colspan="6" align="left">
            <div style="width: 90%;margin: 0px auto">
                <input type="checkbox" <#if projEnterApplyinfoEntity.necessaryTech??&&projEnterApplyinfoEntity.necessaryTech=='10'>checked="checked"</#if>/>
                <label>国家级</label>
                <input type="checkbox" <#if projEnterApplyinfoEntity.necessaryTech??&&projEnterApplyinfoEntity.necessaryTech=='11'>checked="checked"</#if>/>
                <label>省级</label>
                <input type="checkbox" <#if projEnterApplyinfoEntity.necessaryTech??&&projEnterApplyinfoEntity.necessaryTech=='12'>checked="checked"</#if>/>
                <label>市级</label>
                <input type="checkbox" <#if projEnterApplyinfoEntity.necessaryTech??&&projEnterApplyinfoEntity.necessaryTech=='13'>checked="checked"</#if>/>
                <label>否</label>
            </div>
        </td>
    </tr>
    <tr>
        <td colspan="5">项目首创单位及时间</td>
        <td colspan="6" align="left">&nbsp;&nbsp;${projEnterApplyinfoEntity.firstCreateUnitTm!""}</td>
    </tr>
    <tr>
        <td colspan="5">项目首用单位及时间</td>
        <td colspan="6" align="left">&nbsp;&nbsp;${projEnterApplyinfoEntity.firstUseUnitTm!""}</td>
    </tr>
    <tr>
        <td colspan="5">国内首用单位及时间</td>
        <td colspan="6" align="left">&nbsp;&nbsp;${projEnterApplyinfoEntity.firstInUseUnitTm!""}</td>
    </tr>
    <tr>
        <td colspan="5">本项目组应用起始时间</td>
        <td colspan="6" align="left">&nbsp;&nbsp;${projEnterApplyinfoEntity.startTm!""}</td>
    </tr>
    <tr>
    </tr>
</table>

<!--
    作者：fangl
    时间：2018-08-24
    描述：第三页人员情况
-->
<div align="center">
    <h2>二、项目组人员情况表</h2>
</div>
<table style="width: 100%;border-collapse:collapse;text-align: center" class="baseTa htr1" border="1px">
    <tr>
        <td width="15%">负责人姓名</td>
        <td width="17%">${projEnterPersoninfoEntity.name!""}</td>
        <td width="15%">性    别</td>
        <td width="15%">${projEnterPersoninfoEntity.sexNm!""}</td>
        <td width="15%">出生年月</td>
        <td width="23%" colspan="2">${projEnterPersoninfoEntity.birth!""}</td>
    </tr>
    <tr>
        <td>科    室</td>
        <td>${projEnterPersoninfoEntity.departNm!""}</td>
        <td>职    务</td>
        <td>${projEnterPersoninfoEntity.job!""}</td>
        <td>职    称</td>
        <td colspan="2">${projEnterPersoninfoEntity.positionNm!""}</td>
    </tr>
    <tr>
        <td>最高学历</td>
        <td>${projEnterPersoninfoEntity.eduNm!""}</td>
        <td>最高学位</td>
        <td>${projEnterPersoninfoEntity.degreeNm!""}</td>
        <td>毕业专业</td>
        <td colspan="2">${projEnterPersoninfoEntity.graduMajor!""}</td>
    </tr>
    <tr>
        <td>手    机</td>
        <td>${projEnterPersoninfoEntity.phone!""}</td>
        <td>邮    箱</td>
        <td colspan="4">${projEnterPersoninfoEntity.email!""}</td>
    </tr>
    <tr>
        <td colspan="7" height="50px" align="left">
            专业擅长<br></br>
            <span class="manyf">
            ${projEnterPersoninfoEntity.goodAtMajor!""}
            </span>
        </td>
    </tr>
    <tr>
        <td>联系人姓名</td>
        <td>${projEnterPersoninfoEntity.contactName!""}</td>
        <td>手机</td>
        <td style="font-size: 17px">${projEnterPersoninfoEntity.contactPhone!""}</td>
        <td>邮箱</td>
        <td colspan="2" style="font-size: 15px">${projEnterPersoninfoEntity.contactEmail!""}</td>
    </tr>
</table>
<table style="width: 100%;border-collapse:collapse;text-align: center" class="baseTa htr1" border="1px">
    <tr>
        <td colspan="7" align="center">项目组人员情况</td>
    </tr>
    <tr>
        <td width="14%">姓名</td>
        <td width="8%">性别</td>
        <td width="14%">出生年月</td>
        <td width="14%">科室</td>
        <td width="14%">学历</td>
        <td width="16%">职称/职务</td>
        <td width="20%">承担工作</td>
    </tr>
    <#if (projEnterPersoninfoEntities ? exists) && (projEnterPersoninfoEntities ? size > 0)>
        <#list projEnterPersoninfoEntities as item>
            <tr class="blankTr">
                <td>${item.name!""}</td>
                <td>${item.sexNm!""}</td>
                <td>${item.birth!""}</td>
                <td>${item.departNm!""}</td>
                <td>${item.eduNm!""}</td>
                <td>${item.job!""}</td>
                <td>${item.undertakeJob!""}</td>
            </tr>
        </#list>
    </#if>
    <#if !(projEnterPersoninfoEntities ? exists) || (projEnterPersoninfoEntities ? size <= 0)>
        <tr class="blankTr">
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr class="blankTr">
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr class="blankTr">
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr class="blankTr">
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr class="blankTr">
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr class="blankTr">
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
    </#if>
</table>
<table style="width: 100%;border-collapse:collapse;text-align: center" class="baseTa htr1" border="1px">
    <tr>
        <td colspan="7" align="center">分类统计</td>
    </tr>
    <tr>
        <td>总人数</td>
        <td>高级</td>
        <td>中级</td>
        <td>初级</td>
        <td>博士</td>
        <td>硕士</td>
        <td>学士</td>
    </tr>
    <tr class="blankTr">
        <td>${countArr[0]}</td>
        <td>${countArr[1]}</td>
        <td>${countArr[2]}</td>
        <td>${countArr[3]}</td>
        <td>${countArr[4]}</td>
        <td>${countArr[5]}</td>
        <td>${countArr[6]}</td>
    </tr>
</table>

<!--
    作者：fangl
    时间：2018-08-24
    描述：第四页技术概况
-->
<div align="center">
    <h2>三、技术概况</h2>
</div>
<table style="width: 100%;border-collapse:collapse;" class="baseTa bigHTr" border="1px">
    <tr valign="top">
        <td>1、技术简介，开展该技术的必要性<br></br>
            <span class="manyf">
            ${projEnterOutlineEntity.projBrief!""}
            </span>
        </td>
    </tr>
    <tr valign="top">
        <td>2、与同类技术相比，该技术优缺点（安全性、有效性、经济性、可及性、疗程等）<br></br>
            <span class="manyf">
            ${projEnterOutlineEntity.projMeritFault!""}
            </span>
        </td>
    </tr>
    <tr valign="top">
        <td>3、该技术的国内外应用情况（包含开展时间、例次、疗效）<br></br>
            <span class="manyf">
            ${projEnterOutlineEntity.projUseCondition!""}
            </span>
        </td>
    </tr>
    <tr valign="top">
        <td>4、该技术的适用范围（适应症及禁忌症）<br></br>
            <span class="manyf">
            ${projEnterOutlineEntity.projUseRange!""}
            </span>
        </td>
    </tr>
    <tr style="height: 4.45cm" valign="top">
        <td>5、该技术的质量、疗效判定标准<br></br>
            <span class="manyf">
            ${projEnterOutlineEntity.projJudgeStandard!""}
            </span>
        </td>
    </tr>
    <tr valign="top">
        <td>6、该技术的标准操作规程（SOP）<br></br>
            <span class="manyf">
            ${projEnterOutlineEntity.operateStandard!""}
            </span>
        </td>
    </tr>
    <tr valign="top">
        <td>7、关键技术难点及要点<br></br>
            <span class="manyf">
            ${projEnterOutlineEntity.projKeypoint!""}
            </span>
        </td>
    </tr>
    <#--<tr style="height: 15.6cm">

    </tr>-->
</table>

<!--
    作者：fangl
    时间：2018-08-24
    描述：第五页项目组拟应用该技术的工作方案
-->
<div align="center">
    <h2>四、本项目组对该技术临床应用情况</h2>
</div>
<table style="width: 100%;border-collapse:collapse;" class="baseTa bigHTr" border="1px">
    <tr valign="top">
        <td>1、本项目组应用该技术的起始时间及预期目标<br></br>
            <span class="manyf">
            ${clinicalApplicationEntity.startTmPopurse!""}
            </span>
        </td>
    </tr>
    <tr valign="top">
        <td>2、项目组应用该技术的方法与步骤，及主要改进之处<br></br>
            <span class="manyf">
            ${clinicalApplicationEntity.improveMethod!""}
            </span>
        </td>
    </tr>
    <tr valign="top">
        <td>3、项目组应用该技术过程中采取的质量监控措施<br></br>
            <span class="manyf">
            ${clinicalApplicationEntity.qualityControlPlan!""}
            </span>
        </td>
    </tr>
    <tr valign="top">
        <td>4、项目组对该技术的风险预判及应急预案<br></br>
            <span class="manyf">
            ${clinicalApplicationEntity.riskEmergencyPlan!""}
            </span>
        </td>
    </tr>
    <tr style="height: 4.98cm" valign="top">
        <td>5、项目组应用该技术完成的病例统计（时间、病案号）<br></br>
            <span class="manyf">
            ${clinicalApplicationEntity.medicalRecordCount!""}
            </span>
        </td>
    </tr>
    <tr valign="top">
        <td>6、项目组应用该技术过程中不良反应或不良事件的讨论评估及处理情况<br></br>
            <span class="manyf">
            ${clinicalApplicationEntity.adverseEventMan!""}
            </span>
        </td>
    </tr>
    <tr valign="top">
        <td>7、项目组应用该技术后，患者随访监测情况及疗效分析<br></br>
            <span class="manyf">
            ${clinicalApplicationEntity.followUpPlan!""}
            </span>
        </td>
    </tr>
    <tr valign="top">
        <td>8、项目组应用该技术的收费标准及年度成本、效益分析<br></br>
            <span class="manyf">
            ${clinicalApplicationEntity.benefitAnalyse!""}
            </span>
        </td>
    </tr>
    <tr valign="top" style="height: 5.55cm">
        <td>9、项目组应用该技术后，医疗效率情况分析（平均住院日、病床周转等）<br></br>
            <span class="manyf">
            ${clinicalApplicationEntity.medicalEfficiencyAnalyse!""}
            </span>
        </td>
    </tr>
    <tr valign="top" style="height: 6cm">
        <td>10、项目组应用该技术后，发表的应用论文，获得的专利、奖项等<br></br>
            <span class="manyf">
            ${clinicalApplicationEntity.award!""}
            </span>
        </td>
    </tr>
    <tr valign="top">
        <td>11、项目组目前在该技术领域的先进性，国内外查新报告结论（半年内）<br></br>
            <span class="manyf">
            ${clinicalApplicationEntity.noveltySearchReport!""}
            </span>
        </td>
    </tr>
    <tr valign="top">
        <td>12、项目组应用该技术后，对操作规范、流程的修订情况<br></br>
            <span class="manyf">
            ${clinicalApplicationEntity.flowRevise!""}
            </span>
        </td>
    </tr>
    <tr valign="top">
        <td>13、项目组应用该技术后，对本院医务人员的技术培训、考核及授权情况<br></br>
            <span class="manyf">
            ${clinicalApplicationEntity.trainingTest!""}
            </span>
        </td>
    </tr>
    <tr valign="top">
        <td>14、项目组对该技术的推广应用情况（学术交流、技术指导等）<br></br>
            <span class="manyf">
            ${clinicalApplicationEntity.application!""}
            </span>
        </td>
    </tr>
    <tr valign="top">
        <td>15、项目组应用该技术至今的工作总结报告摘要及下一步工作计划<br></br>
            <span class="manyf">
            ${clinicalApplicationEntity.workSummaryPlan!""}
            </span>
        </td>
    </tr>
</table>

<!--
    作者：fangl
    时间：2018-08-24
    描述：第六页审核意见表
-->
<div align="center">
    <h2>五、新技术验收暨十佳新技术审核意见表</h2>
</div>
<table style="width: 100%;border-collapse:collapse;line-height: 1cm;" class="baseTa htr3" border="1px">
    <tr>
        <td width="20%">科室医疗技术<br/>管理小组意见</td>
        <td width="80%">
            <div style="height: 4cm;width: 90%;margin: 0px auto">
                <input type="checkbox" />
                <label>情情况属实，同意上报；</label><br></br>
                <input type="checkbox" />
                <label>暂不予上报。</label>
            </div>
            <div style="float: right;width: 30%;">
                科主任签字：<br></br>
                年   月   日
            </div>
        </td>
    </tr>
    <tr>
        <td>医务处审核意见</td>
        <td>
            <div style="height: 4cm;width: 90%;margin: 0px auto">
                <input type="checkbox" />
                <label>简易程序，验收合格，转常规技术；</label><br></br>
                <input type="checkbox" />
                <label>审查合格，上报医院验收；</label><br></br>
                <input type="checkbox" />
                <label>修改后再审。</label>
            </div>
            <div style="float: right;width: 30%;">
                处长签字：<br></br>
                公     章<br></br>
                年   月   日
            </div>
        </td>
    </tr>
    <tr>
        <td>医院医疗技术委员会审核意见</td>
        <td>
            <div style="height: 4cm;width: 90%;margin: 0px auto">
                <input type="checkbox" />
                <label>验收优秀，建议十佳新技术；</label><br></br>
                <input type="checkbox" />
                <label>验收合格，转常规技术；</label><br></br>
                <input type="checkbox" />
                <label>验收不合格，延期再审；</label><br></br>
                <input type="checkbox" />
                <label>验收不合格，暂停应用。</label>
            </div>
            <div style="float: right;width: 30%;">
                主任签字：<br></br>
                公     章<br></br>
                年   月   日
            </div>
        </td>
    </tr>
</table>
</body>
</html>