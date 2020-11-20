package ar.edu.unju.edm.dao;

import java.util.List;

import ar.edu.unju.edm.model.ObraSocial;
import ar.edu.unju.edm.model.Paciente;

public interface IObraSocialDao {
	public void guardarObraSocial(ObraSocial obraSocial);
	public List<ObraSocial> obtenerTodasObrasSociales();
	public ObraSocial buscarObraSocial(Long id);
	public List<Paciente> obtenerPacientesObraSocial(Long idBuscado);
	public List<Paciente> obtenerTodosPacientes(Long id);
}
