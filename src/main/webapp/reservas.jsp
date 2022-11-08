<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Mis reservas</title>
	<!-- jQuery 3.6.0 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!-- Bootstrap 4.6.2 -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>

</head>
<body>
	<%@ include file="header.jsp" %>
	<main class="p-4 d-flex flex-column">
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>Id. Reserva</th>
					<th>Nombre de usuario</th>
					<th>Nombre</th>
					<th>Email</th>
					<th>Num. de aula</th>
					<th>Nombre de aula</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${reservas}" var="reserva">
					<tr>
						<td>${reserva.idreserva}</td>
						<td>${reserva.usuario.username}</td>
						<td>${reserva.usuario.nombre}</td>
						<td>${reserva.usuario.email}</td>
						<td>${reserva.aula.numaula}</td>
						<td>${reserva.aula.nombre}</td>
						<td><a href="reserva?action=liberar&idreserva=${reserva.idreserva}" class="btn btn-success" role="button">Liberar</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</main>
</body>
</html>