package ar.edu.unju.edm.dao;

import java.util.List;

import ar.edu.unju.edm.model.Paciente;
import ar.edu.unju.edm.model.Reserva;

public interface IPacienteDao {
	public void guardarPaciente(Paciente paciente);
	public List<Paciente> obtenerTodosPacientes();
	public Paciente buscarPaciente (int dni);
	public List<Reserva> obtenerTodasReservas(Long id);
}
