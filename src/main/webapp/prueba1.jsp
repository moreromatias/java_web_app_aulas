<html>
<body>
<h2>Hello World!</h2>
<%! int numero=5;
String nombre="JSP"; %>
<%-- Comentario: %! Declaraci�n de variable JSP --%>
<form method="POST" action="Servlet1">
	<input type="text" name="parametro1" />
	<input type="submit" value="Enviar" />
</form>
<p>El valor del n�mero es: <%= numero %> </p>
<p>El nombre es: <%= nombre %> </p>
<%-- Comentario: %= Expresi�n de variable JSP --%>
</body>
</html>
