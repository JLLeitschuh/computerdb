<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="/dashboard"> Application - Computer
				Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${page.numberTotalItems}Computersfound</h1>
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
						href="<c:url value="/addComputer">
              </c:url>">Add
						Computer</a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
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
						<th>Computer name <a
							href="<c:url value="/dashboard">
               				<c:param name="orderby" value="cmp.name ASC"/>              
        				</c:url>">
								<button type="button" class="fa fa-sort-up"></button>
						</a> <a
							href="<c:url value="/dashboard">
               				<c:param name="orderby" value="cmp.name DESC"/>              
        				</c:url>">
								<button type="button" class="fa fa-sort-down"></button>
						</a>

						</th>
						<th>Introduced date <a
							href="<c:url value="/dashboard">
               				<c:param name="orderby" value="introduced ASC"/>              
        				</c:url>">
								<button type="button" class="fa fa-sort-up"></button>
						</a> <a
							href="<c:url value="/dashboard">
               				<c:param name="orderby" value="introduced DESC"/>              
        				</c:url>">
								<button type="button" class="fa fa-sort-down"></button>
						</a>
						</th>

						<!-- Table header for Discontinued Date -->
						<th>Discontinued date <a
							href="<c:url value="/dashboard">
               				<c:param name="orderby" value="discontinued ASC"/>              
        				</c:url>">
								<button type="button" class="fa fa-sort-up"></button>
						</a> <a
							href="<c:url value="/dashboard">
               				<c:param name="orderby" value="discontinued DESC"/>              
        				</c:url>">
								<button type="button" class="fa fa-sort-down"></button>
						</a>

						</th>

						<!-- Table header for Company -->
						<th>Company <a
							href="<c:url value="/dashboard">
               				<c:param name="orderby" value="cmpy.name ASC"/>              
        				</c:url>">
								<button type="button" class="fa fa-sort-up"></button>
						</a> <a
							href="<c:url value="/dashboard">
               				<c:param name="orderby" value="cmpy.name DESC"/>              
        				</c:url>">
								<button type="button" class="fa fa-sort-down"></button>
						</a>

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
               <c:param name="computerId" value="${computer.id}"/>
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
			<mytag:pagination page="${page}" search="${search}"
				orderBy="${orderby}" item_number="${ item_number }" />
			<mytag:itemNumber search="${search}" orderby="${orderby}" />
		</div>
	</footer>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/dashboard.js"></script>
</body>
</html>