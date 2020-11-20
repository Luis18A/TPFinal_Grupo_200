package ar.edu.unju.edm.dao.imp;

import java.util.List;
import javax.persistence.EntityManager;

import ar.edu.unju.edm.conf.EmfSingleton;
import ar.edu.unju.edm.dao.IMedicoDao;
import ar.edu.unju.edm.model.Medico;
import ar.edu.unju.edm.model.Reserva;

public class MedicoDaoImp implements IMedicoDao {
	
	private static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();
	
	@Override
	public void guardarMedico(Medico medico) {
		manager.getTransaction().begin();
		manager.persist(medico);
		manager.getTransaction().commit();	
	}
	
	@Override
	public List<Medico> obtenerTodosMedicos() {
		@SuppressWarnings("unchecked")
		List<Medico> medicos= (List<Medico>) manager.createQuery("SELECT e FROM Medico e").getResultList();
		return medicos;
	}
	
	@Override
	public Medico buscarMedico(int matricula) {	
		//SE ESCRIBE EL BLOQUE TRY CATCH PORQUE EN CASO DE QUE NO SE ENCUENTRA EL MEDICO SE DEVUELVE UNA EXCEPTION QUE EXPLICA QUE NO SE ENCONTRÓ LA ENTIDAD, ENTONCES, EN ESE CASO, VA A RETORNAR NULL.
		try {
			Medico medico = (Medico) manager.createQuery("SELECT e FROM Medico e WHERE e.matricula = :matricula").setParameter("matricula", matricula).getSingleResult();
			return medico;
		}
		catch(Exception e){
			return null;
		}
	}

//	sacar
	@Override
	public List<Reserva> obtenerTodasReservas(Long id) {
		@SuppressWarnings("unchecked")
		List<Reserva> reservas = (List<Reserva>) manager.createQuery("SELECT r FROM Reserva r WHERE r.medico.id = :id").setParameter("id",id).getResultList();
		return reservas;
	}

}
