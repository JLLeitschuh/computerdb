<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="search" required="true" type="java.lang.String"
	description="search page"%>
	<%@ attribute name="orderby" required="true" type="java.lang.String"
	description="sort item"%>

<div class="btn-group btn-group-sm pull-right" id="item_number"
	role="group">
	<a
		href="<c:url value="/dashboard">
               <c:param name="item_number" value="10"/>
                <c:if test ="${not empty search}">
               <c:param name="search" value="${search}"/>
					</c:if>
					<c:if test ="${not empty orderby}">
               <c:param name="orderby" value="${orderby}"/>
					</c:if>
        						 </c:url>">
		<button type="button" class="btn btn-default">10</button>
	</a> <a
		href="<c:url value="/dashboard">
               <c:param name="item_number" value="50"/>
                <c:if test ="${not empty search}">
               <c:param name="search" value="${search}"/>
					</c:if>
					<c:if test ="${not empty orderby}">
               <c:param name="orderby" value="${orderby}"/>
					</c:if>
               </c:url>">
		<button type="button" class="btn btn-default">50</button>
	</a> <a
		href="<c:url value="/dashboard">
               <c:param name="item_number" value="100"/>
                <c:if test ="${not empty search}">
               <c:param name="search" value="${search}"/>
					</c:if>
					<c:if test ="${not empty orderby}">
               <c:param name="orderby" value="${orderby}"/>
					</c:if>
               </c:url>">
		<button type="button" class="btn btn-default" value="100">100</button>

	</a>
</div>