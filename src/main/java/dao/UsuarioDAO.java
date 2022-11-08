package dao;




import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.Usuario;
import utils.HibernateUtil;

public class UsuarioDAO {

	public Usuario login(String username, String password) {
		
		Usuario usuario = null;
		SessionFactory sessFact = HibernateUtil.getSessionFactory();
		Session session = sessFact.getCurrentSession();
		Transaction tr = null;
		
		try {
			tr = session.beginTransaction();
			// createQuery utiliza lenguaje HQL, similar a SQL
			Query<Usuario> query = (Query<Usuario>)session.createQuery("Select u from Usuario u where u.username = '" + username + "' and u.password = '" + password + "'");
			List<Usuario> usuarios = query.list();
			
			if (usuarios.size() > 0) {
				usuario = usuarios.get(0);
				return usuario;
			}
			else {
				return null;
			}
		}			
		catch(Exception ex) {
			if (tr != null) {
				tr.rollback();
			}
			ex.printStackTrace();
			return null;
			
		}
		finally {
			session.close();
			sessFact.close();
		}
	}
	
	public boolean editar(int idusuario, String nombre, String password, String email, String puesto) {
		
		SessionFactory sessFact = HibernateUtil.getSessionFactory();
		Session session = sessFact.getCurrentSession();
		Transaction tr = null;
		
		try {
			tr = session.beginTransaction();
			
			Usuario usuario = (Usuario)session.get(Usuario.class, idusuario);
			usuario.setNombre(nombre);
			usuario.setPassword(password);
			usuario.setEmail(email);
			usuario.setPuesto(puesto);

			session.update(usuario);
			
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
	
	public boolean registrar(String username, String password, String nombre, String email, String puesto, String perfil) {
		
		SessionFactory sessFact = HibernateUtil.getSessionFactory();
		Session session = sessFact.getCurrentSession();
		Transaction tr = null;
		
		try {
			tr = session.beginTransaction();
			
			Query<Usuario> query = (Query<Usuario>)session.createQuery("Select u from Usuario u where u.username = '" + username + "'");
			List<Usuario> usuarios = query.list();
			
			if(usuarios.size() > 0) {
				return false;
			}
			
			Usuario usuario = new Usuario();
			usuario.setUsername(username);
			usuario.setPassword(password);
			usuario.setNombre(nombre);
			usuario.setEmail(email);
			usuario.setPuesto(puesto);
			usuario.setPerfil(perfil);

			session.save(usuario);
			
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
