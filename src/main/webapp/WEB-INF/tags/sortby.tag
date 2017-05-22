<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="order" required="true" type="java.lang.String"
	description="total number item"%>

<%@ attribute name="orderby" required="true" type="java.lang.String"
	description="total number item"%>
<a
	href="<c:url value="/dashboard">
              <c:param name="orderby" value="${orderby}"/>  
              <c:choose>
	<c:when test ="${ order == 0 || empty order  }">
	 <c:set value="fa fa-sort-up" var="cssClass"></c:set>
	 <c:param name="order" value="1"/>   
   </c:when>
      <c:otherwise>
      <c:set value="fa fa-sort-down" var="cssClass"></c:set> 
      <c:param name="order" value="0"/>     
      </c:otherwise>
      </c:choose>          
      </c:url>"
	class="${cssClass}"> </a>
