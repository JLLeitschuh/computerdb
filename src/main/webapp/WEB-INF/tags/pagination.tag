<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<%@ attribute name="page" required="true" type="Object"
	description="Pagination last display page"%>


<ul class="pagination">


	<li><a
		href="<c:url value="/dashboard">
		<c:choose>
			<c:when test="${page.currentPage - 1 > 0 }">
               <c:param name="page" value="${page.currentPage-1}"/>
             </c:when>
             <c:otherwise>
               <c:param name="page" value="${page.currentPage}"/>
             </c:otherwise>
             </c:choose>
               <c:if test ="${not empty page.search}">
               <c:param name="search" value="${page.search}"/>
					</c:if>
					<c:if test ="${not empty page.itemNumber}">
               <c:param name="itemNumber" value="${page.itemNumber}"/>
					</c:if>
						<c:if test ="${not empty page.orderBy}">
               <c:param name="orderby" value="${page.orderBy}"/>
					</c:if>
					<c:param name="sort" value="${page.sort}"/>
        						 </c:url>"
		aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
	</a></li>
	<c:if test="${page.currentPage > 0 && page.numberPage > 0 }">
		<c:choose>
			<c:when test="${ page.numberPage  <= 6  }">
				<c:set var="begin" scope="session" value="1" />
				<c:set var="end" scope="session" value="${page.numberPage}" />
			</c:when>
			<c:when test="${ page.currentPage  <= 3 }">
				<c:set var="begin" scope="session" value="1" />
				<c:set var="end" scope="session" value="6" />
			</c:when>
			<c:when
				test="${  page.currentPage > 3  &&    page.currentPage+3 < page.numberPage }">

				<c:set var="begin" scope="session" value="${ page.currentPage -2}" />
				<c:set var="end" scope="session" value="${ page.currentPage + 3 }" />

			</c:when>
			<c:when test="${  page.currentPage + 3 >= page.numberPage }">

				<c:set var="begin" scope="session" value="${page.numberPage-6}" />
				<c:set var="end" scope="session" value="${page.numberPage}" />

			</c:when>
		</c:choose>
		<c:forEach var="i" begin="${begin}" end="${end }">
			<mytag:paramtag page="${i}" search="${ page.search}" orderby="${ page.orderBy}"
				itemNumber="${ page.itemNumber}" order = "${  page.sort}" />
		</c:forEach>
	</c:if>
	<li><a
		href="<c:url value="/dashboard">
		<c:choose>
               <c:when test="${page.currentPage + 1 < page.numberPage }">
               <c:param name="page" value="${page.currentPage+1}"/>
               
             </c:when>
             <c:otherwise>
               <c:param name="page" value="${page.currentPage}"/>
             </c:otherwise>
             </c:choose>
             <c:if test ="${not empty page.search}">
               <c:param name="search" value="${page.search}"/>
					</c:if>
					<c:if test ="${not empty page.itemNumber}">
               <c:param name="itemNumber" value="${page.itemNumber}"/>
					</c:if>
						<c:if test ="${not empty page.orderBy}">
               <c:param name="orderby" value="${page.orderBy}"/>
					</c:if>
					<c:param name="sort" value="${page.sort}"/>
        						 </c:url>"
		aria-label="Next"> <span aria-hidden="true">&raquo;</span>
	</a></li>
</ul>