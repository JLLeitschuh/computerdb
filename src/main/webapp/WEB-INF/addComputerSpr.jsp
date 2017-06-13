<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
           prefix="spr"%>
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
        <a class="navbar-brand" href=""<c:url value="/dashboard"></c:url>" "> Application -Computer Database </a>
    </div>
</header>
<section id="main">
    <div class="container">
        <div class="row">
            <div class="col-xs-8 col-xs-offset-2 box">
                <h1>Add Computer</h1>
                <spr:form action="addComputer" commandName="computer" id="form-addComputer" accept-charset="utf-8" method="POST">
                    <fieldset>
                        <div class="form-group">
                            <label for="name">Computer name Test</label>
                            <spr:input path="name" />
                            <spr:errors path="name"  />

                        </div>
                        <div class="form-group">
                            <label for="introduced">Introduced date (yyyy-mm-dd)</label>
                            <spr:input path="introduced" />
                            <spr:errors path="introduced"  />
                        </div>
                        <div class="form-group">
                            <label for="discontinued">Discontinued date (yyyy-mm-dd)</label>
                            <spr:input path="discontinued" />
                            <spr:errors path="discontinued"  />
                        </div>
                        <div class="form-group">
                            <label for="companyId">Company</label> <select
                                class="form-control" name="companyId" id="companyId">
                            <c:forEach items="${companyList}" var="company">
                                <option value="${company.id}">${company.name}</option>
                            </c:forEach>
                        </select>
                        </div>
                    </fieldset>
                    <div class="actions pull-right">
                        <input type="submit" value="Add" id="addComputer" class="btn btn-primary">
                        or <a href="<c:url value="/dashboard"></c:url>" class="btn btn-default">Cancel</a>
                    </div>
                </spr:form>
            </div>
        </div>
    </div>
</section>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.min.js"></script>
<script>
    $.validate({
        lang: 'fr'
    });
</script>
</body>
</html>




<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
           prefix="spr"%>
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
        <a class="navbar-brand" href=""<c:url value="/dashboard"></c:url>" "> Application -Computer Database </a>
    </div>
</header>
<section id="main">
    <div class="container">
        <div class="row">
            <div class="col-xs-8 col-xs-offset-2 box">
                <h1>Add Computer</h1>
                <form action="addComputer" id="form-addComputer" accept-charset="utf-8" method="POST">
                    <fieldset>
                        <div class="form-group">
                            <label for="name">Computer name Test</label>

                            <input
                                    type="text" class="form-control" name="name"
                                    id="name" placeholder="Computer name"  required>
                        </div>
                        <div class="form-group">
                            <label for="introduced">Introduced date (yyyy-mm-dd)</label> <input
                                type="date" class="form-control" name="introduced"
                                id="introduced" placeholder="Introduced date" data-validation="date" data-validation-format="yyyy-mm-dd">
                        </div>
                        <div class="form-group">
                            <label for="discontinued">Discontinued date (yyyy-mm-dd)</label> <input
                                type="date" class="form-control" name="discontinued"
                                id="discontinued" placeholder="Discontinued date" data-validation="date" data-validation-format="yyyy-mm-dd">
                        </div>
                        <div class="form-group">
                            <label for="companyId">Company</label> <select
                                class="form-control" name="companyId" id="companyId">
                            <c:forEach items="${companyList}" var="company">
                                <option value="${company.id}">${company.name}</option>
                            </c:forEach>
                        </select>
                        </div>
                    </fieldset>
                    <div class="actions pull-right">
                        <input type="submit" value="Add" id="addComputer" class="btn btn-primary">
                        or <a href="<c:url value="/dashboard"></c:url>" class="btn btn-default">Cancel</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.min.js"></script>
<script>
    $.validate({
        lang: 'fr'
    });
</script>
</body>
</html>