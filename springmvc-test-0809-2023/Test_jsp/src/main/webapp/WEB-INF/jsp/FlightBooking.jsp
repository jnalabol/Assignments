<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<body>
<table>
   <tr>
       <th>FlightId</th>
       <th>Source</th>
       <th>Destination</th>
       <th>Fare</th>
       <th>Seats</th>
   </tr>


   <c:forEach var ="flg" items ="${flights}">
       <tr>
       <td>${flg.flightId}</td>
       <td>${flg.source}</td>
       <td>${flg.destination}</td>
       <td>${flg.fare}</td>
       <td>${flg.seatsAvailable}</td>
       </tr>
   </c:forEach>
 </table>
 <form action ="bookflight" method ="post">
  <tr>
    <td><input type="text"  value=flightId></td>
     <td><input type="text"   value="source"></td>
     <td><input type ="text" value="destination"></td>
      <td><input type="text"  value="fare"></td>
       <td><input type="text"   value="seats"></td>
   </tr>

 </form>
</body>
</html>