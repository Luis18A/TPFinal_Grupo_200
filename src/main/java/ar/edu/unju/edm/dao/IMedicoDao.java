package ar.edu.unju.edm.dao;

import java.util.List;

import ar.edu.unju.edm.model.Medico;
import ar.edu.unju.edm.model.Reserva;

public interface IMedicoDao {
	
	public void guardarMedico(Medico medico);
	public List<Medico> obtenerTodosMedicos();
	public Medico buscarMedico(int matricula);
	public List<Reserva> obtenerTodasReservas(Long id);
	
}
