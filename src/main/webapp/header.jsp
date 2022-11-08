<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<!-- Font Awesome -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
</head>
<body>
	<nav class="navbar navbar-expand -sm bg-dark navbar-dark">
		<a href="#" class="navbar-brand">Matias Morero</a>
		<ul class="navbar-nav">
			<li class="nav-item"><a href="aula?action=listar" class="nav-link">Home</a></li>
			<li class="nav-item"><a href="reserva?action=listar&idusuario=${user.idusuario}" class="nav-link">Mis reservas</a></li>
			<li class="nav-item"><a href="perfil.jsp" class="nav-link">Mi perfil</a></li>
			<c:if test="${user.perfil.equals('administrador')}">
				<li class="nav-item dropdown">
					<a href="#" class="nav-link dropdown-toggle" id="navbardrop" data-toggle="dropdown">
						Administración
					</a>
					<div class="dropdown-menu">
						<a href="detalle.jsp" class="dropdown-item">Alta aula</a>
						<a href="registro.jsp" class="dropdown-item">Alta usuario</a>
					</div>
				</li>
			</c:if>
		</ul>
		<ul class="navbar-nav ml-auto">
			<li class="nav-item"><a href="usuario?action=cerrar" class="nav-link"><i class="fa-solid fa-right-from-bracket"></i>Log out</a></li>
		</ul>
	</nav>
</body>
</html>