package ar.edu.unju.edm.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;

import ar.edu.unju.edm.conf.EmfSingleton;
import ar.edu.unju.edm.dao.IEspecialidadDao;
import ar.edu.unju.edm.model.Especialidad;
import ar.edu.unju.edm.model.Medico;

public class EspecialidadDaoImp implements IEspecialidadDao {
	
	public static EntityManager manager= EmfSingleton.getInstance().getEmf().createEntityManager();
	
	@Override
	public void guardarEspecialidad(Especialidad especialidad) {
		manager.getTransaction().begin();
		manager.persist(especialidad);
		manager.getTransaction().commit();
	}

	@Override
	public List<Especialidad> obtenerTodasEspecialidades() {
		@SuppressWarnings("unchecked")
		List<Especialidad> esp=(List<Especialidad>) manager.createQuery("SELECT e FROM Especialidad e").getResultList();
		return esp;
	}

	@Override
	public Especialidad buscarEspecialidad(Long id) {
		return manager.find(Especialidad.class, id);
	}

	@Override
	public List<Medico> obtenerTodosMedicos(Long id) {
		@SuppressWarnings("unchecked")
		List<Medico> medicos = (List<Medico>) manager.createQuery("SELECT m FROM Medico m WHERE m.especialidad.id = :id").setParameter("id",id).getResultList();
		return medicos;
	}

}
