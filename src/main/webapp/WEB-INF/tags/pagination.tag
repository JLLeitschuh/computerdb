<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<%@ attribute name="page" required="true" type="Object"
	description="Pagination last display page"%>
<%@ attribute name="search" required="true" type="java.lang.String"
	description="search page"%>
<%@ attribute name="item_number" required="true" type="java.lang.String"
	description="number Of Item"%>
<%@ attribute name="orderBy" required="true" type="java.lang.String"
	description="sort by parameter name"%>

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
		aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
	</a></li>
	<c:if test="${page.currentPage > 0 && page.numberPage > 0 }">
		<c:choose>
			<c:when test="${ page.numberPage  <= 6  }">
				<c:forEach var="i" begin="1" end="${page.numberPage }">
					<mytag:paramtag page="${i}" search="${search}" orderby="${orderby}"
						itemNumber="${item_number}" />
				</c:forEach>
			</c:when>
			<c:when
				test="${ page.currentPage > 3  &&   page.currentPage +3 < page.numberPage }">
				<c:forEach var="i" begin="${page.currentPage -2}"
					end="${page.currentPage + 3 }">
					<mytag:paramtag page="${i}" search="${search}" orderby="${orderby}"
						itemNumber="${item_number}" />
				</c:forEach>
			</c:when>
			<c:when test="${ page.currentPage  <= 3 }">
				<c:forEach var="i" begin="1" end="6">
					<mytag:paramtag page="${i}" search="${search}" orderby="${orderby}"
						itemNumber="${item_number}" />
				</c:forEach>
			</c:when>
			<c:when test="${ page.currentPage + 3 >= page.numberPage }">
				<c:forEach var="i" begin="${page.numberPage-6}"
					end="${page.numberPage }">
					<mytag:paramtag page="${i}" search="${search}" orderby="${orderby}"
						itemNumber="${item_number}" />
				</c:forEach>
			</c:when>
		</c:choose>
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
		aria-label="Next"> <span aria-hidden="true">&raquo;</span>
	</a></li>
</ul>
