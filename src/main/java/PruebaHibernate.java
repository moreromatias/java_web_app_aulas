// Para ejecutar este código: Boton derecho -> Run As -> 2 Java Application

import java.util.List;

import dao.AulaDAO;
import model.Aula;

public class PruebaHibernate {

	public static void main(String[] args) {
		AulaDAO aDao = new AulaDAO();
		
		// Selecciona todas las aulas
		List<Aula> aulas = aDao.seleccionarAulas();
		// Muestra las aulas en consola
		for (Aula a: aulas) {
			System.out.println("Listado: " + a.toString());
		}
		
		// Obtiene el aula nº 2
		Aula aula = aDao.obtenerAula(2);
		// Muestra el aula nº 2 en consola
		System.out.println("Aula seleccionada: " + aula.toString());
		
		aDao.crear(100,  "Aula de prueba", 100, "Esto es un aula de prueba");
		aDao.actualizar(100,  "Aula de prueba modificada", 50, "Esto es un aula de prueba");
		//aDao.eliminar(100);
	}

}
