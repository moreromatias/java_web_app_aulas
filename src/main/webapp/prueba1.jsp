<html>
<body>
<h2>Hello World!</h2>
<%! int numero=5;
String nombre="JSP"; %>
<%-- Comentario: %! Declaración de variable JSP --%>
<form method="POST" action="Servlet1">
	<input type="text" name="parametro1" />
	<input type="submit" value="Enviar" />
</form>
<p>El valor del número es: <%= numero %> </p>
<p>El nombre es: <%= nombre %> </p>
<%-- Comentario: %= Expresión de variable JSP --%>
</body>
</html>
