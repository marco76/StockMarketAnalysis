<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head><title>Simple jsp page</title></head>
  <body>Place your content here</body>
  First page showed succesfully
  <br/>
  List of available stocks:

  <c:forEach var="stockDetails" items="${stocks}">
  <br/>
      ${stockDetails.stockName}
  </c:forEach>
  <br/>
<img src="chart.htm"/>
</html>