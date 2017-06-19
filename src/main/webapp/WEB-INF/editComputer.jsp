<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags/form" %>
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
			<a class="navbar-brand" href="<c:url value="/dashboard"></c:url>">
				Application - Computer Database </a>
		</div>
	</header>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">id: ${id}</div>
					<h1><spring:message code="edit.computer"  /></h1>
					<spr:form action="editComputer" modelAttribute="computer" id="form-editComputer" accept-charset="utf-8" method="POST">
						<spr:input type="hidden" path="id" />
						<!-- TODO: Change this value with the computer id -->
						<fieldset>
							<div class="form-group">
								<label for="name"><spring:message code="computer.name"  /></label>
								<spr:input path="name" class="form-control"/>
								<spr:errors path="name" class="form-control" cssClass="has-error" />
							</div>
							<div class="form-group">
								<label for="introduced"><spring:message code="computer.introduced"  /></label>
								<spr:input path="introduced" class="form-control"/>
								<spr:errors path="introduced" class="form-control" cssClass="has-error" />

							</div>
							<div class="form-group">
								<label for="discontinued"><spring:message code="computer.discontinued"  /></label>
								<spr:input path="discontinued" class="form-control"/>
								<spr:errors path="discontinued" class="form-control" cssClass="has-error" />
							</div>
							<div class="form-group">
								<label for="companyId"><spring:message code="company.name"  /></label> <select
									class="form-control" id="companyId" name="companyId">
									<c:forEach items="${companyList}" var="company">
										<option value="${company.id}"
											${computer.companyId == company.id ? 'selected' : ' '}><c:out
												value="${company.name}"></c:out></option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" id="edit" value="<spring:message code="edit"  />"
								class="btn btn-primary"> <spring:message code="or"  /> <a
								href="<c:url value="/dashboard"></c:url>"
								class="btn btn-default"><spring:message code="cancel"  /></a>
						</div>
					</spr:form>
				</div>
			</div>
		</div>
	</section>
</body>
</html>