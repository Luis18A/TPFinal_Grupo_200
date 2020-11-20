package ar.edu.unju.edm.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import ar.edu.unju.edm.conf.EmfSingleton;
import ar.edu.unju.edm.dao.IReservaDao;
import ar.edu.unju.edm.model.Reserva;

public class ReservaDaoImp implements IReservaDao {
	
	private static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();
	
	@Override
	public void guardarReserva(Reserva reserva) {
		manager.getTransaction().begin();
		manager.persist(reserva);
		manager.getTransaction().commit();
	}

	@Override
	public List<Reserva> obtenerTodasReservas() {
		@SuppressWarnings("unchecked")
		List<Reserva> reservas = (List<Reserva>) manager.createQuery("SELECT r FROM Reserva r").getResultList();
		return reservas;
	}

	@Override
	public Reserva buscarReserva(Long id) {
		return manager.find(Reserva.class, id);
	}
	
	@Override
	public void eliminarReserva(Reserva reserva) {
//		manager.getTransaction().begin();
//		Query query = manager.createQuery("DELETE FROM Reserva r WHERE r.id = :id");
//		int deletedCount = query.setParameter("id", id).executeUpdate();
//		manager.getTransaction().commit();
		manager.getTransaction().begin();
		manager.remove(reserva);
		manager.getTransaction().commit();
		
	}
}
