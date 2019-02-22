<!-- jsp common tags -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- set some value -->
<c:set var="gobalPath" value="${pageContext.request.contextPath}/"/>
<c:set var="staticPath" value="${pageContext.request.contextPath}/static/"/>
<c:set var="jumpPath" value="${pageContext.request.contextPath}/jump/"/>
<c:set var="adminPath" value="${pageContext.request.contextPath}/admin/"/>

<!-- css base -->

<!-- script base -->
<!-- jQuery -->
<script src="${staticPath}js/base/jquery.min.js"></script>

<!-- layer.js -->
<script src="${staticPath}js/layui/layer/layer.js"></script>

<!-- script base system -->
<script src="${staticPath}js/system/base_Global.js"></script>
<script src="${staticPath}js/system/base_functions.js"></script>
<script src="${staticPath}js/system/base_utils.js"></script>