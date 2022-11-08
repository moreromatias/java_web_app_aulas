// Curso hibernate
// http://www.cursohibernate.es/doku.php?id=unidades:02_hibernate:04_usando_hibernate

package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session; //Esta clase contiene métodos para leer, guardar o borrar entidades sobre la base de datos
import org.hibernate.SessionFactory; // Antes de poder usar Session, hace falta crear el objeto SessionFactory que nos dará acceso a Session.
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.Aula;
import model.Reserva;
import model.Usuario;
import utils.HibernateUtil;

public class AulaDAO {

	public List<Aula> seleccionarAulas(){
		List<Aula> aulas = new ArrayList<Aula>();
		
		SessionFactory sessFact = HibernateUtil.getSessionFactory();
		Session session = sessFact.getCurrentSession();
		Transaction tr = null;
		
		try {
			tr = session.beginTransaction();
			// createQuery utiliza lenguaje HQL, similar a SQL
			aulas = session.createQuery("Select a from Aula a",Aula.class).getResultList();
		}
		catch(Exception ex) {
			if (tr != null) {
				tr.rollback();
			}
			ex.printStackTrace();
			aulas = null;
			
		}
		finally {
			session.close();
			sessFact.close();
		}
		
		return aulas;
	}
	
	public Aula obtenerAula(int numAula) {
		
		Aula aula = new Aula();
		
		SessionFactory sessFact = HibernateUtil.getSessionFactory();
		Session session = sessFact.getCurrentSession();
		Transaction tr = null;
		
		try {
			tr = session.beginTransaction();
			// En el sig. select, se utiliza get ya que busca por clave primaria. De lo contrario se utilizaría HQL para un select mas complejo 
			aula = session.get(Aula.class, numAula);
		}
		catch(Exception ex) {
			if (tr != null) {
				tr.rollback();
			}
			ex.printStackTrace();
			aula = null;
			
		}
		finally {
			session.close();
			sessFact.close();
		}
		
		return aula;
	}
	
	public boolean crear(int numaula, String nombre, int capacidad, String descripcion) {
		
		SessionFactory sessFact = HibernateUtil.getSessionFactory();
		Session session = sessFact.getCurrentSession();
		Transaction tr = null;
		
		try {
			tr = session.beginTransaction();
			Aula aula = new Aula();
			aula.setNumaula(numaula);
			aula.setNombre(nombre);
			aula.setCapacidad(capacidad);
			aula.setDescripcion(descripcion);
			
			session.save(aula);
			tr.commit();
			// commit() interface Transaction. Flush the associated Session and end the unit of work
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
	
	public boolean actualizar(int numaula, String nombre, int capacidad, String descripcion) {
		
		SessionFactory sessFact = HibernateUtil.getSessionFactory();
		Session session = sessFact.getCurrentSession();
		Transaction tr = null;
		
		try {
			tr = session.beginTransaction();
			Aula aula = session.get(Aula.class, numaula);
			aula.setNombre(nombre);
			aula.setCapacidad(capacidad);
			aula.setDescripcion(descripcion);
			
			session.update(aula);
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
	
	public boolean eliminar(int numaula) {
		
		SessionFactory sessFact = HibernateUtil.getSessionFactory();
		Session session = sessFact.getCurrentSession();
		Transaction tr = null;
		
		try {
			tr = session.beginTransaction();
			
			// Eliminar el aula de la tabla Reserva
			Query<Reserva> query = (Query<Reserva>)session.createQuery("Delete Reserva r where numaula = " + numaula);
			query.executeUpdate();
			
			// Eliminar el aula de la tabla Aula
			Aula aula = session.get(Aula.class, numaula);
			session.delete(aula);
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
	
	public List<Aula> getAulas(String filter) {
		
		List<Aula> aulas = new ArrayList<Aula>();
		
		SessionFactory sessFact = HibernateUtil.getSessionFactory();
		Session session = sessFact.getCurrentSession();
		Transaction tr = null;
		
		try {
			tr = session.beginTransaction();
			String sql = "Select a from Aula a";
			
			if (filter != null && !filter.equals("")) {
				sql += " where descripcion like '%" + filter + "%'";
			}
			Query<Aula> query = (Query<Aula>)session.createQuery(sql);
			aulas = query.list();
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
		return aulas;
		
	}
	
	public boolean reservar(int numaula, int idusuario) {
		
		SessionFactory sessFact = HibernateUtil.getSessionFactory();
		Session session = sessFact.getCurrentSession();
		Transaction tr = null;
		
		try {
			tr = session.beginTransaction();
			Aula aula = (Aula)session.get(Aula.class, numaula);
			aula.setEstado(true);
			session.update(aula);
			
			Usuario usuario = (Usuario)session.get(Usuario.class, idusuario);
			Reserva reserva = new Reserva();
			reserva.setAula(aula);
			reserva.setUsuario(usuario);
			session.save(reserva);
			
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
