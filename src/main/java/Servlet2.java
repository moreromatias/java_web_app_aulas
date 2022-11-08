// Servlet de ejemplo

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Aula;

/**
 * Servlet implementation class Servlet2
 */

// Al ser dynamic Web module 4.0 se debe indicar la siguiente sentencia:
// Ya no es necesario web.xml en WEB-INF
@WebServlet("/Servlet2")
public class Servlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	RequestDispatcher rd;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parametro1 = request.getParameter("parametro1");
		System.out.println("Recibiendo petición con metodo GET del Servlet2 con parámetro: " + parametro1);
		String param = request.getParameter("param");
		System.out.println("Metodo GET del Servlet2, ha recibido parámetro: " + param);
		response.sendRedirect("prueba2.jsp");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parametro1 = request.getParameter("parametro1");
		System.out.println("Recibiendo petición con metodo POST del Servlet2 con parámetro: " + parametro1);

		
		HttpSession session = request.getSession(); //Objeto tipo HttpSession
		
		Aula a = new Aula();
		a.setNumaula(600);
		a.setCapacidad(30);
		a.setNombre("Aula de prueba");
		
		// Lo siguiente se usa en el expression Languaje de main.jsp
		// Se debe tener RequestDispatcher rd; al inicio del servelet
		
		session.setAttribute("parametroSesion2", "nuevoValorSesion"); // Parametro que necesitemos a lo largo de la ejecución de la aplicación. Habitual guardar identificación del usuario
		request.setAttribute("aula", a);
		
		rd = request.getRequestDispatcher("/prueba2.jsp");
		rd.forward(request, response);
		
		String param = request.getParameter("param");
		System.out.println("Metodo POST del Servlet2, ha recibido parámetro: " + param);
		
		
	}

}
