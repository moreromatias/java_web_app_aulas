package utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	public static SessionFactory getSessionFactory() {

		  SessionFactory sessionFactory;
		  try {

			  // Create the SessionFactory from hibernate.cfg.xml
			  sessionFactory = new Configuration().configure().buildSessionFactory();
	
			  } catch (Throwable ex) {
				  // Make sure you log the exception, as it might be swallowed
				  System.err.println("Error al crear la factoría de sesiones." + ex);
				  throw new ExceptionInInitializerError(ex);
			  }
			  return sessionFactory;

		 }
}
