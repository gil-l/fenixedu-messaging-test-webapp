<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<f:setLocale value="en"/>
<f:setBundle basename="resources.MessagingResources" var="bundle"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title><f:message key="title.application" bundle="${bundle}"/></title>
		<script type="text/javascript" src="bennu-portal/js/jquery.min.js"></script>
		<script type="text/javascript" src="bennu-portal/portal.js"></script>
	</head>
	<body>
  		<div id="modal"></div>
  		<div id="portal-container">
    		<h1><f:message key="message.welcome" bundle="${bundle}"/></h1>
  		</div>
	</body>
</html>