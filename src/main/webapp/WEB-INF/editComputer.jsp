<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="<c:url value="/dashboard"></c:url>" > Application -
				Computer Database </a>
		</div>
	</header>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">id: 0</div>
					<h1>Edit Computer</h1>

					<form action="editComputer" method="POST">
						<input type="hidden" value="0" id="id" />
						<!-- TODO: Change this value with the computer id -->
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label> <input
									type="text" class="form-control" name="computerName"
									id="computerName"
									<c:if test="${not empty computer.name }" >
									value="<c:out value="${computer.name}"/>"
									</c:if>
									placeholder="Computer name" />
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date</label> <input
									type="date" class="form-control" name="introduced"
									id="introduced" data-validation="date"
									data-validation-format="yyyy-mm-dd"
									placeholder="Introduced date"
									<c:if test="${not empty computer.introduced }" >
									value="<c:out value="${computer.introduced}"/>"
									</c:if> />

							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label> <input
									type="date" class="form-control" name="discontinued"
									id="discontinued" data-validation="date"
									data-validation-format="yyyy-mm-dd"
									<c:if test="${not empty computer.discontinued }" >
									value="<c:out value="${computer.discontinued}"/>"
									</c:if>
									placeholder="Discontinued date">
							</div>
							<div class="form-group">
								<label for="companyId">Company</label> <select
									class="form-control" id="companyId">
									<c:forEach items="${companyList}" var="company">
										<option value="${company.id}"
											${computer.companyId == company.id ? 'selected' : ' '}><c:out
												value="${company.name}"></c:out></option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
						<input type="hidden" name="computerId" value="${computerId}" /> 
							<input type="submit" value="Edit" class="btn btn-primary">
							or <a href="<c:url value="/dashboard"></c:url>" class="btn btn-default">Cancel</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
</body>
</html>