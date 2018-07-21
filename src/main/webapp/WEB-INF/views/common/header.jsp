<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>상단 배너</title>

	<link rel="stylesheet" href="<c:url value='/resources/css/ui/common/header.css'/>" />
	<script src="<c:url value='/resources/scripts/ui/common/header.js'/>"></script>
	
</head>
<body>

	<header class="header middle">
		<a href="<c:url value='/main' />" >
			<img id="banner" alt="배너" src="" />
		</a>
	</header>
	
</body>
</html>