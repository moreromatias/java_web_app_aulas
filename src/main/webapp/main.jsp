<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
	<!-- jQuery 3.6.0 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!-- Bootstrap 4.6.2 -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
</head>
<body>
	<main class="p-4 d-flex flex-column">
		<form method="post" class="form-inline" action="aula">
			<input type="text" class="form-control" id="query" name="query" placeholder="Buscar-aula"/>
			<input type="hidden" name="action" value="listar" />
			<button type="submit" class="btn btn-info">Buscar</button>
		</form>
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>Num. Aula</th>
					<th>Capacidad</th>
					<th>Nombre</th>
					<th>Descripción</th>
					<th>Estado</th>
					<th></th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${aulas}" var="aula">
					<tr>
						<td>${aula.numaula}</td>
						<td>${aula.capacidad}</td>
						<td>${aula.nombre}</td>
						<td>${aula.descripcion}</td>
						<td>
							<c:choose>
								<c:when test="${aula.estado == false}">
									Libre
								</c:when>
								<c:otherwise>
									Reservada
								</c:otherwise>
							</c:choose>
						</td>
						<td><a href="aula?action=ver&numaula=${aula.numaula}" class="btn btn-primary" role="button">Ver detalles</a></td>
						<td>
							<c:if test="${aula.estado  == false}">
								<a href="aula?action=reservar&numaula=${aula.numaula}&idusuario=${user.idusuario}" class="btn btn-success" role="button" onclick="return confirm('Se va a proceder a la reserva del aula. ¿Desea continuar?')">Reservar</a>
							</c:if>
						</td>
						<td>
							<c:if test="${user.perfil.equals('administrador')}">
								<a href="aula?action=eliminar&numaula=${aula.numaula}" class="btn btn-danger" role="button" onclick="return confirm('Se va a proceder a eliminar el aula. ¿Desea continuar?')">Eliminar</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</main>
</body>
</html>