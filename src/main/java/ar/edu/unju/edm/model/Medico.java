package ar.edu.unju.edm.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ar.edu.unju.edm.dao.IMedicoDao;
import ar.edu.unju.edm.dao.imp.MedicoDaoImp;

@Entity
@Table(name="medicos")
public class Medico implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private int matricula;
	private int dni;
	private String nombre;
	private String apellido;
	private String telefono;
	
	@ManyToOne
	@JoinColumn(name="especialidad_id")
	private Especialidad especialidad;
	
	@OneToMany(mappedBy = "medico", fetch=FetchType.EAGER)
	private List<Reserva> reservas = new ArrayList<Reserva>();
	
	public Medico() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public List<Reserva> getReservas() {
		//si no se agrega lo siguiente devuelve listas vacias
		IMedicoDao medicoDao = new MedicoDaoImp();
		reservas = medicoDao.obtenerTodasReservas(getId());
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		
		this.reservas = reservas;
	}

	public Medico(int matricula, int dni, String nombre, String apellido, String telefono, Especialidad especialidad,
			List<Reserva> reservas) {
		super();
		this.matricula = matricula;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.especialidad = especialidad;
		this.reservas = reservas;
	}

	@Override
	public String toString() {
		return "MEDICO [id=" + id + ", matricula=" + matricula + ", dni=" + dni + ", nombre=" + nombre + ", apellido="
				+ apellido + ", telefono=" + telefono + ", ESPECIALIDAD=" + getEspecialidad().getNombre() + ", RESERVAS=" + getReservas()
				+ "]";
	}
	
	public List<Reserva> consultarReservas (LocalDate fechaInicio, LocalDate fechaFinal){
		if(fechaInicio.isAfter(fechaFinal) || fechaInicio.isEqual(fechaFinal)) {
			return null;
		}
		else {
		//se crean dos listas, la primera va a almacenar las reservas que cumplan la condicion de estar entre las 2 fechas
		//la segunda lista contiene todas las reservas del medico en cuestion	
			List<Reserva> reservasEntreFechas = new ArrayList<Reserva>();
			List<Reserva> todasReservasMedico = getReservas();
			//se recorre la lista de todas las reservas del medico y se pregunta si cumple con la condicion
			for(Reserva reserv:todasReservasMedico) {
				//creacion de un LocalDate a partir del LocalDateTime de la reserva
				LocalDate fReserva = LocalDate.of( reserv.getFechaHora().getYear(),  reserv.getFechaHora().getMonthValue(),  reserv.getFechaHora().getDayOfMonth());
				if((fReserva.isAfter(fechaInicio) || fReserva.isEqual(fechaInicio)) && (fReserva.isBefore(fechaFinal) || fReserva.isEqual(fechaFinal))) {
					reservasEntreFechas.add(reserv);
				}
			}
			return reservasEntreFechas;
		}
	}
	
	
	public List<Paciente> mostrarPacientes(int edad){
		if(edad<1 || edad >100) {
			return null;
		}else {
//			se crea la lista que va a almacenar todos los pacientes del medico
			List<Paciente> todosPacientes = new ArrayList<>();
			List<Paciente> pacientesMayorEdad = new ArrayList<>();
			for(Reserva reserv:getReservas()) {
				Paciente paciente = reserv.getPaciente();
				todosPacientes.add(paciente);
			}
			for(Paciente pacient:todosPacientes) {
				if(pacient.getEdad()>edad) {
					pacientesMayorEdad.add(pacient);
				}
			}
			return pacientesMayorEdad;
		}
		
	}
	
}
