<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<%@ attribute name="page" required="true" type="Object"
	description="Pagination last display page"%>


<ul class="pagination">


	<li><a
		href="<c:url value="/dashboard">
		<c:choose>
			<c:when test="${page.pageRequest.page - 1 > 0 }">
               <c:param name="page" value="${page.pageRequest.page-1}"/>
             </c:when>
             <c:otherwise>
               <c:param name="page" value="${page.pageRequest.page}"/>
             </c:otherwise>
             </c:choose>
               <c:if test ="${not empty page.pageRequest.research}">
               <c:param name="search" value="${page.pageRequest.research}"/>
					</c:if>
					<c:if test ="${not empty page.pageRequest.itemNumber}">
               <c:param name="item_number" value="${page.pageRequest.itemNumber}"/>
					</c:if>
						<c:if test ="${not empty page.pageRequest.orderBy}">
               <c:param name="orderby" value="${page.pageRequest.orderBy}"/>
					</c:if>
					<c:param name="order" value="${page.pageRequest.sort}"/>
        						 </c:url>"
		aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
	</a></li>
	<c:if test="${page.pageRequest.page > 0 && page.numberPage > 0 }">
		<c:choose>
			<c:when test="${ page.numberPage  <= 6  }">
				<c:set var="begin" scope="session" value="1" />
				<c:set var="end" scope="session" value="${page.numberPage}" />
			</c:when>
			<c:when test="${ page.pageRequest.page  <= 3 }">
				<c:set var="begin" scope="session" value="1" />
				<c:set var="end" scope="session" value="6" />
			</c:when>
			<c:when
				test="${  page.pageRequest.page > 3  &&    page.pageRequest.page+3 < page.numberPage }">

				<c:set var="begin" scope="session" value="${ page.pageRequest.page -2}" />
				<c:set var="end" scope="session" value="${ page.pageRequest.page + 3 }" />

			</c:when>
			<c:when test="${  page.pageRequest.page + 3 >= page.numberPage }">

				<c:set var="begin" scope="session" value="${page.numberPage-6}" />
				<c:set var="end" scope="session" value="${page.numberPage}" />

			</c:when>
		</c:choose>
		<c:forEach var="i" begin="${begin}" end="${end }">
			<mytag:paramtag page="${i}" search="${ page.pageRequest.research}" orderby="${ page.pageRequest.orderBy}"
				itemNumber="${ page.pageRequest.itemNumber}" order = "${  page.pageRequest.sort}" />
		</c:forEach>
	</c:if>
	<li><a
		href="<c:url value="/dashboard">
		<c:choose>
               <c:when test="${page.pageRequest.page + 1 < page.numberPage }">
               <c:param name="page" value="${page.pageRequest.page+1}"/>
               
             </c:when>
             <c:otherwise>
               <c:param name="page" value="${page.pageRequest.page}"/>
             </c:otherwise>
             </c:choose>
             <c:if test ="${not empty page.pageRequest.research}">
               <c:param name="search" value="${page.pageRequest.research}"/>
					</c:if>
					<c:if test ="${not empty page.pageRequest.itemNumber}">
               <c:param name="item_number" value="${page.pageRequest.itemNumber}"/>
					</c:if>
						<c:if test ="${not empty page.pageRequest.orderBy}">
               <c:param name="orderby" value="${page.pageRequest.orderBy}"/>
					</c:if>
					<c:param name="order" value="${page.pageRequest.sort}"/>
        						 </c:url>"
		aria-label="Next"> <span aria-hidden="true">&raquo;</span>
	</a></li>
</ul>