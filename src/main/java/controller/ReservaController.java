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

import dao.ReservaDAO;
import model.Reserva;
import model.Usuario;

/**
 * Servlet implementation class ReservaController
 */
@WebServlet("/reserva")
public class ReservaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ReservaDAO rDao = new ReservaDAO();
	
	RequestDispatcher rd;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservaController() {
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
		
		
		String action = request.getParameter("action");
		
		if (action.equals("listar")) {
			listar(request, response);
		}
		else if (action.equals("liberar")) {
			liberar(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	// Si le paso la petición por URL, lo toma el método doGet
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idusuario = Integer.parseInt(request.getParameter("idusuario"));
		List<Reserva> reservas = rDao.getReservas(idusuario);
		
		request.setAttribute("reservas", reservas);
		rd = request.getRequestDispatcher("/reservas.jsp");
		rd.forward(request, response);
	}
	
	protected void liberar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idreserva = Integer.parseInt(request.getParameter("idreserva"));
		
		boolean liberado = rDao.liberar(idreserva);
		
		request.setAttribute("liberado", liberado);
		
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario)session.getAttribute("user");
		
		rd = request.getRequestDispatcher("/reserva?action=listar&idusuario="+usuario.getIdusuario());
		rd.forward(request, response);
	}
}
