<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<%@ attribute name="page" required="true" type="Object"
	description="Pagination last display page"%>
<%@ attribute name="search" required="true" type="java.lang.String"
	description="search page"%>
<%@ attribute name="itemNumber" required="true" type="java.lang.String"
	description="total number item"%>
<%@ attribute name="orderby" required="true" type="java.lang.String"
	description="sort by parameter selected"%>

<li><a
	href="<c:url value="/dashboard">
               <c:param name="page" value="${page}"/>
               <c:if test ="${not empty search}">
               <c:param name="search" value="${search}"/>
					</c:if>
					<c:if test ="${not empty item_number}">
               <c:param name="item_number" value="${item_number}"/>
					</c:if>
								<c:if test ="${not empty orderby}">
               <c:param name="orderby" value="${orderby}"/>
					</c:if>
        						 </c:url>"
	onclick="">${page}</a></li>