<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Perfil</title>
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
				<h3 class="text-center">Datos de usuario</h3>
				<form method="get" action="usuario" class="pb-4">
					<label for="username">Nombre de usuario</label>
					<input type="text" class="form-control" id="username" name="username" value="${user.username}" disabled /> <br>
					<label for="password">Password</label>
					<input type="password" class="form-control" id="password" name="password" value="${user.password}" /> <br>
					<label for="nombre">Nombre</label>
					<input type="text" class="form-control" id="nombre" name="nombre" value="${user.nombre}" /> <br>
					<label for="email">Email</label>
					<input type="text" class="form-control" id="email" name="email" value="${user.email}" /> <br>
					<label for="puesto">Puesto</label>
					<input type="text" class="form-control" id="puesto" name="puesto" value="${user.puesto}" /> <br>
					<input type="hidden" name="action" value="editar"/>
					<div class="p-4 d-flex flex-row justify-content-around">
						<input type="submit" class="btn btn-primary" value="Modificar datos" />
					</div>
				</form>
			</div>
		</div>
	</main>
	
</body>
</html>