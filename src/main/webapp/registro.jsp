<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Registro</title>
	<!-- jQuery 3.6.0 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!-- Bootstrap 4.6.2 -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
</head>
<body>
	<%@ include file="header.jsp" %>
	<main class="p-4 d-flex flex-column align-items-center">
		<div class="card col-sm-9 col-md-7 col-lg-6 col-xl-4">
			<div class="cald-body">
				<h3 class="text-center">Alta usuario</h3>
				<form method="post" action="usuario" class="pb-4">
					<label for="username">Nombre de usuario</label>
					<input type="text" class="form-control" id="username" name="username" placeholder="Usuario" required /> <br>
					<label for="password">Password</label>
					<input type="password" class="form-control" id="password" name="password" placeholder="Password" required /> <br>
					<label for="nombre">Nombre</label>
					<input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre" required /> <br>
					<label for="email">Email</label>
					<input type="text" class="form-control" id="email" name="email" placeholder="Email" required /> <br>
					<label for="puesto">Puesto</label>
					<input type="text" class="form-control" id="puesto" name="puesto" placeholder="Puesto" required /> <br>
					<label for="perfil" class="">Perfil</label>
					<select name="perfil" id="perfil" class="form-control">
						<option value="normal">Normal</option>
						<option value="administrador">Administrador</option>
					</select>
					<input type="hidden" name="action" value="registro"/>
					<div class="p-4 d-flex flex-row justify-content-around align-items-center">
						<input type="submit" class="btn btn-primary" value="Registro" />
					</div>
				</form>
				<c:if test="${message != null}">
				<div class="alert alert-danger text-center" role="alert" >
					${message}
				</div>
			</c:if>
			</div>
		</div>
	</main>
	
</body>
</html>