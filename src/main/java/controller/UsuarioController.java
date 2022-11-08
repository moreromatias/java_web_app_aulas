package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

import dao.AulaDAO;
import dao.UsuarioDAO;
import model.Aula;
import model.Usuario;

/**
 * Servlet implementation class UsuarioController
 */

//Si le paso la petición por URL, lo toma el método doGet

@WebServlet("/usuario")
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UsuarioDAO uDao = new UsuarioDAO();
	AulaDAO aDao = new AulaDAO();
	RequestDispatcher rd;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// Para que no se pueda acceder a ningun link cuando el usuario no este logueado
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario)session.getAttribute("user");
		if(usuario == null) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		
		
		String action =request.getParameter("action");
		
		if (action.equals("editar")) {
			editar(request, response);
		}
		else if (action.equals("cerrar")) {
			cerrar_sesion(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action =request.getParameter("action");
		
		if (action.equals("login")) {
			login(request, response);
		}
		else if (action.equals("registro")) {
			registro(request, response);
		}
	}

	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario = request.getParameter("username");
		String password = DigestUtils.md5Hex(request.getParameter("password"));
		
		HttpSession session = request.getSession(); // Crea la session del usuario
		// Los parámetros de seccion son accesibles de cualquier parte del programa
		
		Usuario logged =  uDao.login(usuario, password);
		List<Aula> aulas = aDao.getAulas("");
		
		if(logged != null) {
			session.setAttribute("user", logged);
			session.setAttribute("aulas", aulas);			
			rd = request.getRequestDispatcher("/main.jsp");
			rd.forward(request, response);
		}
		else {
			String msg = "Usuario y/o password incorrectos";
			request.setAttribute("message", msg);
			rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}
	}
	
	protected void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(); // Crea la session del usuario
		// Los parámetros de seccion son accesibles de cualquier parte del programa
		
		Usuario usuario = (Usuario)session.getAttribute("user");
		int idusuario = usuario.getIdusuario();
		String nombre = request.getParameter("nombre");
		String password = request.getParameter("password");
		if(!password.equals(usuario.getPassword())) {
			password = DigestUtils.md5Hex(password).toString();			
		}
		String email = request.getParameter("email");
		String puesto = request.getParameter("puesto");
		
		boolean editado = uDao.editar(idusuario, nombre, password, email, puesto);
		
		if (editado) {
			usuario.setNombre(nombre);
			usuario.setPassword(password);
			usuario.setEmail(email);
			usuario.setPuesto(puesto);
			
			session.setAttribute("user", usuario);
		}
		
		rd = request.getRequestDispatcher("/perfil.jsp");
		rd.forward(request, response);

	}
	
	protected void registro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = DigestUtils.md5Hex(request.getParameter("password"));
		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");
		String puesto = request.getParameter("puesto");
		String perfil = request.getParameter("perfil");
		
		boolean registrado = uDao.registrar(username, password, nombre, email, puesto, perfil);
		
		
		if (registrado) {
			rd = request.getRequestDispatcher("/aula?action=listar");
			rd.forward(request, response);
		}
		else {
			String msg = "Error al dar del alta el usuario. Usuario Existente";
			request.setAttribute("message", msg);
			rd = request.getRequestDispatcher("/registro.jsp");
			rd.forward(request, response);
		}
	
	}
	
	protected void cerrar_sesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath());
			
	}
	
}
