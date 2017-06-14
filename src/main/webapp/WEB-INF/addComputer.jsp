<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spr"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>

	<title>Computer Database</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- Bootstrap -->
	<spring:url value="/resources/css/bootstrap.min.css" var="bootstrap" />
	<spring:url value="/resources/css/main.css" var="main" />
	<spring:url value="/resources/css/font-awesome.css" var="font_awsome" />
	<link href="${bootstrap}" rel="stylesheet" media="screen">
	<link href="${font_awsome}" rel="stylesheet" media="screen">
	<link href="${main}" rel="stylesheet" media="screen">

</head>
<body>
<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<a class="navbar-brand" href=""<c:url value="/dashboard"></c:url>" "> Application -Computer Database </a>
	</div>
</header>
<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				<h1><spring:message code="add.computer" text="default text" /></h1>
				<spr:form action="addComputer" commandName="computer" id="form-addComputer" accept-charset="utf-8" method="POST">
					<fieldset>
						<div class="form-group">
							<label for="name"><spring:message code="computer.name" text="default text" /></label>
							<spr:input path="name" class="form-control" />
							<spr:errors path="name" class="form-control" />
						</div>
						<div class="form-group">
							<label for="introduced"><spring:message code="computer.introduced" text="default text" /> (yyyy-mm-dd)</label>
							<spr:input path="introduced" class="form-control" />
							<spr:errors path="introduced" class="form-control"  />
						</div>
						<div class="form-group">
							<label for="discontinued"><spring:message code="computer.discontinued" text="default text" /> (yyyy-mm-dd)</label>
							<spr:input path="discontinued" class="form-control"  />
							<spr:errors path="discontinued" class="form-control"  />
						</div>
						<div class="form-group">
							<label for="companyId"><spring:message code="company.name" text="default text" /></label> <select
								class="form-control" name="companyId" id="companyId">
							<c:forEach items="${companyList}" var="company">
								<option value="${company.id}">${company.name}</option>
							</c:forEach>
						</select>
						</div>
					</fieldset>
					<div class="actions pull-right">
						<input type="submit" value="<spring:message code="add" text="default text" />" id="addComputer" class="btn btn-primary">
						<spring:message code="or" text="default text" /> <a href="<c:url value="/dashboard"></c:url>" class="btn btn-default"><spring:message code="cancel" text="default text" /></a>
					</div>
				</spr:form>
			</div>
		</div>
	</div>
</section>
<spring:url value="/resources/js/jquery.min.js" var="jquery" />
<spring:url value="/resources/js/bootstrap.min.js" var="bootstrap" />
<spring:url value="/resources/js/dashboard.js" var="dashboard" />
<script src="${jquery}"></script>
<script src="${bootstrap}"></script>
<script src="${dashboard}"></script>
<script>
    $.validate({
        lang: 'fr'
    });
</script>
</body>
</html>







