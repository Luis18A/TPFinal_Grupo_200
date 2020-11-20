package ar.edu.unju.edm.model;
import java.io.Serializable;
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

import ar.edu.unju.edm.dao.IPacienteDao;
import ar.edu.unju.edm.dao.imp.PacienteDaoImp;

@Entity
@Table(name="pacientes")
public class Paciente implements Serializable{
	public static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private int dni;
	private String nombre;
	private String apellido; 
	private int edad;
	private String telefono;
	
	@ManyToOne
	@JoinColumn(name="obra_social_id")
	private ObraSocial obraSocial;
	
	@OneToMany(mappedBy = "paciente", fetch=FetchType.EAGER)
	private List<Reserva> reservas;
	
	public Paciente() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public ObraSocial getObraSocial() {
		return obraSocial;
	}

	public void setObraSocial(ObraSocial obraSocial) {
		this.obraSocial = obraSocial;
	}

	public List<Reserva> getReservas() {
	//si no se agrega lo siguiente devuelve listas vacias
		IPacienteDao pacienteDao = new PacienteDaoImp();
		reservas = pacienteDao.obtenerTodasReservas(getId());
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public Paciente(int dni, String nombre, String apellido, int edad, String telefono, ObraSocial obraSocial,
			List<Reserva> reservas) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.telefono = telefono;
		this.obraSocial = obraSocial;
		this.reservas = reservas;
	}

	@Override
	public String toString() {
		return "PACIENTE [id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", edad="
				+ edad + ", telefono=" + telefono + ", OBRA SOCIAL=" + getObraSocial().getNombre() + ", RESERVAS="  + getReservas() + "]";
	}
	
	
	
	
}
