package ar.edu.unju.edm.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ar.edu.unju.edm.dao.IMedicoDao;
import ar.edu.unju.edm.dao.IObraSocialDao;
import ar.edu.unju.edm.dao.imp.MedicoDaoImp;
import ar.edu.unju.edm.dao.imp.ObraSocialDaoImp;

@Entity
@Table(name="obras_sociales")
public class ObraSocial implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String direccion; 
	
	@OneToMany(mappedBy = "obraSocial", fetch=FetchType.EAGER)
	private List<Paciente> pacientes;
	
	public ObraSocial() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<Paciente> getPacientes() {
		IObraSocialDao obraSocialDao = new ObraSocialDaoImp();
		pacientes = obraSocialDao.obtenerTodosPacientes(getId());
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public ObraSocial(String nombre, String direccion, List<Paciente> pacientes) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.pacientes = pacientes;
	}

	@Override
	public String toString() {
		return "ObraSocial [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", PACIENTES=" + getPacientes()
				+ "]";
	}
	
	
	

}
