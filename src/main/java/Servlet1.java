// Servlet de ejemplo

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//Al ser dynamic Web module 4.0 se debe indicar la siguiente sentencia:
//Ya no es necesario web.xml en WEB-INF
@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	RequestDispatcher rd;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parametro1 = request.getParameter("parametro1");
		request.setAttribute("parametro1", "python");
		System.out.println("Recibiendo petición con metodo GET del Servlet 1 con parámetro: " + parametro1);
		
		HttpSession session = request.getSession(); //Objeto tipo HttpSession
		session.setAttribute("parametroSesion", "usuario1"); // Parametro que necesitemos a lo largo de la ejecución de la aplicación. Habitual guardar identificación del usuario
		
		//System.out.println(session);
		
		String usuario = (String)session.getAttribute("parametroSesion");
		
		System.out.println("Parámetro de sesión: " + usuario);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parametro1 = request.getParameter("parametro1");
		System.out.println("Recibiendo petición con metodo POST del Servlet1 con parámetro: " + parametro1);
		//response.sendRedirect("main.jsp"); //response.sendRedirect(request.getContextPath())
		
		// Al usar ?param=valor envia ese parametro luego del signo ?
		rd = request.getRequestDispatcher("/Servlet2?param=valor");
		rd.forward(request, response);
				

	}

}
