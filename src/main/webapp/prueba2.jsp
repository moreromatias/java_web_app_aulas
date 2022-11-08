<%@ page import="java.util.Date" %>
<%@ include file="prueba3.jsp" %>
<!-- Para usar Expression Language debe cambiar la uri siguiente a
uri="http://java.sun.com/jstl/core"
uri="http://java.sun.com/jsp/jstl/core"
Tambien se debe usar una version 4.0 o superior en web.xml
-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Para usar taglib se baja la libreria JSTL en maven  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mi primera página JSP</title>
</head>
<body>
	<h1>Vista main de la aplicación</h1>
	<h3>Hoy es: <%= new Date() %></h3>
	<c:set var="numero" scope="session" value="${-1000}"></c:set>
	<p>El número es: <c:out value="${numero}"></c:out> </p>
	<c:if test="${numero > 0}">
		<p>El numero es mayor que 0</p>
	</c:if>
	<c:if test="${numero < 0}">
		<p>El numero es menor que 0</p>
	</c:if>
	
	<!-- Expression Language -->
	<p>El parametro de sesión definido es: ${parametroSesion2}</p>
	<p>El parametro de respuesta definido es: ${aula.nombre}</p>


</body>
</html>