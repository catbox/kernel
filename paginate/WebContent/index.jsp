<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Paginate</title>
<link rel="stylesheet" href="css/pagination.css" type="text/css">
</head>
<body>
    <table border="1" cellpadding="5" cellspacing="1" >
        <tr>
            <th>Semester ID</th>
            <th>Semester Name</th>
            <th>Date</th>
        </tr>
 
        <c:forEach var="semester" items="${semesterList}">
            <tr>
                <td>${semester.semesterId}</td>
                <td>${semester.semesterName}</td>
                <td>${semester.timestamp}</td>
            </tr>
        </c:forEach>
    </table>
 	<br>
 	
    <%-- Link to next page --%>
    <c:if test="${currentPage > 1}">
        <td><a href="semester?page=${currentPage - 1}">Previous</a></td>
    </c:if>
	
	<%--- Pagination --%>
    <c:forEach begin="1" end="${numOfPages}" var="i">
        <c:choose>
            <c:when test="${currentPage eq i}">
                <b>${i}</b>
            </c:when>
            <c:otherwise>
                <a href="semester?page=${i}">${i}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <%-- Link to next page --%>
    <c:if test="${currentPage lt numOfPages}">
        <td><a href="semester?page=${currentPage + 1}">Next</a></td>
    </c:if>
 
  
</body>
</html>