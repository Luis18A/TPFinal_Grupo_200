package ar.edu.unju.edm.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import ar.edu.unju.edm.conf.EmfSingleton;
import ar.edu.unju.edm.dao.IPacienteDao;
import ar.edu.unju.edm.model.Paciente;
import ar.edu.unju.edm.model.Reserva;

public class PacienteDaoImp implements IPacienteDao {
	
	private static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();
	
	@Override
	public void guardarPaciente (Paciente paciente) {
		manager.getTransaction().begin();
		manager.persist(paciente);
		manager.getTransaction().commit();
		
	}
	
	@Override
	public List<Paciente> obtenerTodosPacientes(){
		@SuppressWarnings("unchecked")
		List<Paciente> pacientes= (List<Paciente>) manager.createQuery("SELECT e FROM Paciente e").getResultList();
		return pacientes;		
	}

	@Override
	public Paciente buscarPaciente(int dni) {
//		Query query = manager.createQuery("SELECT e FROM Paciente e WHERE e.dni = :dni").setParameter("dni", dni);
//		Paciente paciente = (Paciente) query.getSingleResult();
		
//		@SuppressWarnings("unchecked")
//		List<Paciente> pacientes = (List<Paciente>) manager.createQuery("SELECT e FROM Paciente e").getResultList();
//		Paciente paciente=null;
//		for(Paciente pacient:pacientes) {
//			System.out.println(pacient);
//			if(pacient.getDni()==dni) {
//				paciente=pacient;
//			}
//		}
		
		//SE ESCRIBE EL BLOQUE TRY CATCH PORQUE EN CASO DE QUE NO SE ENCUENTRA EL PACIENTE DEVUELVE UNA EXCEPTION QUE EXPLICA QUE NO SE ENCONTRÓ LA ENTIDAD ENTONCES, EN ESE CASO, VA A RETORNAR NULL.
		try {
			Paciente paciente = (Paciente) manager.createQuery("SELECT e FROM Paciente e WHERE e.dni = :dni").setParameter("dni", dni).getSingleResult();
			return paciente;

		}
		catch(Exception e){
			return null;
		}
	}

	@Override
	public List<Reserva> obtenerTodasReservas(Long id) {
		@SuppressWarnings("unchecked")
		List<Reserva> reservas = (List<Reserva>) manager.createQuery("SELECT p FROM Reserva p WHERE p.paciente.id = :id").setParameter("id",id).getResultList();
		return reservas;
	}

	
	

}
