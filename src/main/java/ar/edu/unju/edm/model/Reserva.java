package ar.edu.unju.edm.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="reservas")
public class Reserva implements Serializable{
	private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime fechaHora;
	private int consultorio;
	
	@ManyToOne
	@JoinColumn(name="paciente_id")
	private Paciente paciente;
	
	@ManyToOne
	@JoinColumn(name="medico_id")
	private Medico medico;
	
	public Reserva() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public int getConsultorio() {
		return consultorio;
	}

	public void setConsultorio(int consultorio) {
		this.consultorio = consultorio;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Reserva(LocalDateTime fechaHora, int consultorio, Paciente paciente, Medico medico) {
		super();
		this.fechaHora = fechaHora;
		this.consultorio = consultorio;
		this.paciente = paciente;
		this.medico = medico;
	}

	@Override
	public String toString() {
		return "RESERVA [id=" + id + ", fechaHora=" + fechaHora + ", consultorio=" + consultorio + ", PACIENTE="
				+ "id=" + getPaciente().getId() + ", Apellido=" + getPaciente().getApellido() + ", Nombre=" + getPaciente().getNombre() 
				+ ", DNI=" + getPaciente().getDni() + ", Edad=" + getPaciente().getEdad() + ", Teléfono=" + getPaciente().getTelefono() 
				+ ", Obra Social=" + getPaciente().getObraSocial().getNombre() + ", MEDICO=" + ", id=" + getMedico().getId()
				+ ", Apellido=" + getMedico().getApellido() + ", Nombre=" + getMedico().getNombre() + ", Matricula=" + getMedico().getMatricula() 
				+ ", DNI=" + getMedico().getDni() + ", Especialidad=" +getMedico().getEspecialidad().getNombre() + "]";
	}

}
