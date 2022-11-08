<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<!-- Bootstrap 4.6.2 -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
</head>
<body>
	<main class="p-4 d-flex flex-column align-items-center">
		<div class="card col-sm-9 col-md-7 col-lg-6 col-xl-4 ">
			<img src="img/aula.jpg" alt="aula" />
		</div>
		<div class="card col-sm-9 col-md-7 col-lg-6 col-xl-4 " >
			<form method="post" class="pb-4" action="usuario">
				<h3 class="text-center">Iniciar sesión</h3>
				<label for="username">Nombre de usuario</label>
				<input type="text" class="form-control" id="username" name="username" placeholder="Usuario" required autofocus /><br>
				<label for="password">Password</label>
				<input type="password" class="form-control" id="password" name="password" placeholder="Password" required autofocus /><br>
				<input type="hidden" name="action" value="login" />
				<div class="p-4 d-flex flex-row justify-content-around">
					<input type="submit" value="Login" class="btn btn-primary" />
				</div>
			</form>
			<c:if test="${message != null}">
				<div class="alert alert-danger text-center" role="alert" >
					${message}
				</div>
			</c:if>
		</div>
	</main>
</body>
</html>