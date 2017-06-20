<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
	<title>Computer Database</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta charset="utf-8">
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
		<a class="navbar-brand" href="/dashboard"> Application - Computer
			Database </a>
	</div>
</header>
Language : <a href="?lang=en">English</a>|<a href="?lang=fr">French</a>

<section id="main">
	<div class="container">
		<h1 id="homeTitle">${page.numberTotalItems} <spring:message code="dashboard.computerfound" text="default text" /> </h1>
		<div id="actions" class="form-horizontal">
			<div class="pull-left">
				<form id="searchForm" action="#" method="GET" class="form-inline">

					<input type="search" id="searchbox" name="search"
						   class="form-control" placeholder="Search name" /> <input
						type="submit" id="searchsubmit" value="Filter by name"
						class="btn btn-primary" />
				</form>
			</div>
			<div class="pull-right">
				<a class="btn btn-success" id="addComputer"
				   href="<c:url value="addComputer">
              </c:url>"><spring:message code="add.computer" text="default text" />
				</a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();"><spring:message code="edit" text="default text" /> </a>
			</div>
		</div>
	</div>

	<c:url var="post_url" value="/dashboard" />
	<form id="deleteForm" action="${post_url}" method="POST">
		<input type="hidden" name="selection" value="">
	</form>

	<div class="container" style="margin-top: 10px;">
		<table class="table table-striped table-bordered">
			<thead>
			<tr>
				<!-- Variable declarations for passing labels as parameters -->
				<!-- Table header for Computer Name -->

				<th class="editMode" style="width: 60px; height: 22px;"><input
						type="checkbox" id="selectall" /> <span
						style="vertical-align: top;"> - <a href="#"
														   id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
						class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
				<th><spring:message code="computer.name" text="default text" /><mytag:sortby sort="${page.pageRequest.sort}"
																							 orderby="cmp.name" />
				</th>
				<th><spring:message code="computer.introduced" text="default text" /> <mytag:sortby sort="${page.pageRequest.sort}"
																									orderby="introduced" />
				</th>

				<!-- Table header for Discontinued Date -->
				<th><spring:message code="computer.discontinued" text="default text" /> <mytag:sortby sort="${page.pageRequest.sort}"
																									  orderby="discontinued" />
				</th>

				<!-- Table header for Company -->
				<th><spring:message code="company.name" text="default text" />  <mytag:sortby sort="${page.pageRequest.sort}"
																							  orderby="cmpy.name" />
				</th>

			</tr>
			</thead>
			<!-- Browse attribute computers -->
			<tbody id="results">
			<c:forEach items="${page.items}" var="computer">
				<tr>
					<td class="editMode"><input type="checkbox" name="cb"
												class="cb" value="${computer.id}"></td>
					<td><a
							href="<c:url value="/editComputer">
               <c:param name="id" value="${computer.id}"/>
        						 </c:url>"
							onclick="">${computer.name}</a></td>
					<td>${computer.introduced}</td>
					<td>${computer.discontinued}</td>
					<td>${computer.companyName}</td>

				</tr>
			</c:forEach>

			</tbody>
		</table>
	</div>
</section>

<footer class="navbar-fixed-bottom">
	<div class="container text-center">
		<mytag:pagination page="${page}" />
		<mytag:itemNumber page="${page}" />
	</div>
</footer>
<spring:url value="/resources/js/jquery.min.js" var="jquery" />
<spring:url value="/resources/js/bootstrap.min.js" var="bootstrap" />
<spring:url value="/resources/js/dashboard.js" var="dashboard" />
<script src="${jquery}"></script>
<script src="${bootstrap}"></script>
<script src="${dashboard}"></script>
</body>
</html>