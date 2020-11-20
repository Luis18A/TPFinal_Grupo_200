package ar.edu.unju.edm.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;

import ar.edu.unju.edm.conf.EmfSingleton;
import ar.edu.unju.edm.dao.IObraSocialDao;
import ar.edu.unju.edm.model.ObraSocial;
import ar.edu.unju.edm.model.Paciente;
import ar.edu.unju.edm.model.Reserva;

public class ObraSocialDaoImp implements IObraSocialDao {
	
	public static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();
	
	@Override
	public void guardarObraSocial(ObraSocial obraSocial) {
		manager.getTransaction().begin();
		manager.persist(obraSocial);
		manager.getTransaction().commit();
	}

	@Override
	public List<ObraSocial> obtenerTodasObrasSociales() {
		@SuppressWarnings("unchecked")
		List<ObraSocial> obrasSociales= (List<ObraSocial>) manager.createQuery("SELECT o FROM ObraSocial o").getResultList();
		return obrasSociales;
	}

	@Override
	public ObraSocial buscarObraSocial(Long id) {
		return manager.find(ObraSocial.class, id);
	}

	@Override
	public List<Paciente> obtenerPacientesObraSocial(Long idBuscado) {
		@SuppressWarnings("unchecked")
		List<Paciente> pacientes = (List<Paciente>) manager.createQuery("SELECT p FROM Paciente p WHERE p.obraSocial.id = :idBuscado").setParameter("idBuscado",idBuscado).getResultList();
		return pacientes;
	}

	@Override
	public List<Paciente> obtenerTodosPacientes(Long id) {
		@SuppressWarnings("unchecked")
		List<Paciente> pacientes = (List<Paciente>) manager.createQuery("SELECT p FROM Paciente p WHERE p.obraSocial.id = :id").setParameter("id",id).getResultList();
		return pacientes;
	}

}
