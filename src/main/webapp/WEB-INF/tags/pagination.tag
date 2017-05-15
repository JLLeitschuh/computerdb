<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="startPage" required="true" type="java.lang.String"
	description="Pagination first page display"%>
<%@ attribute name="endPage" required="true" type="java.lang.String"
	description="Pagination last display page"%>
<%@ attribute name="currentPage" required="true" type="java.lang.String"
	description="Pagination last display page"%>

<ul class="pagination">
	<li><a
		href="<c:url value="/dashboard">
               <c:param name="page" value="${currentPage-1}"/>
        						 </c:url>"
		aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
	</a></li>

	<c:forEach var="i" begin="${startPage}" end="${endPage}">
		<li><a
			href="<c:url value="/dashboard">
               <c:param name="page" value="${i}"/>
        						 </c:url>"
			onclick="">${i}</a></li>
	</c:forEach>
	<li><a
		href="<c:url value="/dashboard">
               <c:param name="page" value="${currentPage+1}"/>
        						 </c:url>"
		aria-label="Next"> <span aria-hidden="true">&raquo;</span>
	</a></li>
</ul>
