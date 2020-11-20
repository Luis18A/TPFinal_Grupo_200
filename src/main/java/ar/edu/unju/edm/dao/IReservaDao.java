package ar.edu.unju.edm.dao;

import java.util.List;

import ar.edu.unju.edm.model.Reserva;

public interface IReservaDao {
	
	public void guardarReserva(Reserva reserva);
	public List<Reserva> obtenerTodasReservas();
	public Reserva buscarReserva(Long id);
	public void eliminarReserva(Reserva reserva);
}
