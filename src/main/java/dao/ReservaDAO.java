package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.Aula;
import model.Reserva;
import utils.HibernateUtil;

public class ReservaDAO {

	public List<Reserva> getReservas(int idusuario) {
		List<Reserva> reservas = new ArrayList<Reserva>();
		
		SessionFactory sessFact = HibernateUtil.getSessionFactory();
		Session session = sessFact.getCurrentSession();
		Transaction tr = null;
		
		try {
			tr = session.beginTransaction();
			Query<Reserva> query = (Query<Reserva>)session.createQuery("Select r from Reserva r where r.usuario.idusuario = " + idusuario);
			reservas = query.list();
		}
		catch(Exception ex) {
			if (tr != null) {
				tr.rollback();
			}
			ex.printStackTrace();			
		}
		finally {
			session.close();
			sessFact.close();
		}
		return reservas;
	}
	
	public boolean liberar(int idreserva) {
		
		SessionFactory sessFact = HibernateUtil.getSessionFactory();
		Session session = sessFact.getCurrentSession();
		Transaction tr = null;
		
		try {
			tr = session.beginTransaction();
			
			// En el sig. select, se utiliza get ya que busca por clave primaria. De lo contrario se utilizaría HQL para un select mas complejo 
			Reserva reserva = (Reserva)session.get(Reserva.class, idreserva);
			
			int numaula = reserva.getAula().getNumaula();
			Aula aula = (Aula)session.get(Aula.class, numaula);
			aula.setEstado(false);
			session.update(aula);
			
			session.delete(reserva);
			
			tr.commit();
			
			return true;
		}
		catch(Exception ex) {
			if (tr != null) {
				tr.rollback();
			}
			ex.printStackTrace();	
			return false;
		}
		finally {
			session.close();
			sessFact.close();
		}
	}
	
}
