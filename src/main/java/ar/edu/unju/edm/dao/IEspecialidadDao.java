package ar.edu.unju.edm.dao;

import java.util.List;

import ar.edu.unju.edm.model.Especialidad;
import ar.edu.unju.edm.model.Medico;

public interface IEspecialidadDao {
	
	public void guardarEspecialidad(Especialidad especialidad);
	public List<Especialidad> obtenerTodasEspecialidades();
	public Especialidad buscarEspecialidad(Long id);
	public List<Medico> obtenerTodosMedicos(Long id);
}
