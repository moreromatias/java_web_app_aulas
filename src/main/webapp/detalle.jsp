<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Detalle aula</title>
	<!-- jQuery 3.6.0 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!-- Bootstrap 4.6.2 -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
</head>
<body>
	<main class="p-4 d-flex flex-column align-items-center">
		<h1 class="mt-3">Datos del aula</h1>
		<div class="card col-sm-9 col-md-7 col-lg-6 col-xl-4">
			<div class="card-body">
				<form method="post" class="pb-4" action="aula">
					<c:choose>
						<c:when test="${aula.numaula != null}">
							<input type="hidden" name="action" value="editar" />
						</c:when>
						<c:otherwise>
							<input type="hidden" name="action" value="alta" />
						</c:otherwise>
					</c:choose>
					<label for="numaula">Número</label>
					<input type="number" class="form-control" id="numaula" name="numaula" value='${aula.numaula}' required
						<c:if test="${aula.numaula != null}">
							readonly
						</c:if>
					/> <br>
					<label for="nombre">Nombre</label>
					<input type="text" class="form-control" id="nombre" name="nombre" value='${aula.nombre}' required
						<c:if test="${user.perfil.equals('normal')}">
							readonly
						</c:if>
					/> <br>
					<label for="capacidad">Capacidad</label>
					<input type="number" class="form-control" id="capacidad" name="capacidad" value='${aula.capacidad}' required
						<c:if test="${user.perfil.equals('normal')}">
							readonly
						</c:if>
					/> <br>
					<label for="descripcion">Descripción</label>
					<textarea name="descripcion" id="descripcion" rows="4" class="form-control" style="width: 100%" required
						<c:if test="${user.perfil.equals('normal')}">
							readonly
						</c:if>
					>${aula.descripcion}</textarea> <br>
					<div class="p-4 d-flex flex-row justify-content-around">
						<c:if test="${aula.numaula != null}">
							<c:if test="${aula.estado == false}">
								<a href="aula?action=reservar&numaula=${aula.numaula}&idusuario=${user.idusuario}" class="btn btn-success" role="button" onclick="return confirm('Se va a proceder a la reserva del aula. ¿Desea continuar?')">Reservar</a>
							</c:if>
						</c:if>
						<c:if test="${user.perfil.equals('administrador')}">
							<c:choose>
								<c:when test="${aula.numaula != null}">
									<input type="submit" class="btn btn-primary" value="Modificar" />
									<a href="aula?action=eliminar&numaula=${aula.numaula}" class="btn btn-danger" title="Eliminar" onclick="return confirm('Se va a proceder a eliminar el aula. ¿Desea continuar?')">Eliminar</a>
								</c:when>
								<c:otherwise>
									<input type="submit" class="btn btn-primary" value="Crear aula" />
								</c:otherwise>
							</c:choose>
						</c:if>
					</div>
					<c:if test="${message != null}">
						<div class="alert alert-danger text-center" role="alert" >
							${message}
						</div>
					</c:if>					
				</form>
			</div>
		</div>
	</main>
</body>
</html>