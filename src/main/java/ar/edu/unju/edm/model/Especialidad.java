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

import ar.edu.unju.edm.dao.IEspecialidadDao;
import ar.edu.unju.edm.dao.imp.EspecialidadDaoImp;

@Entity
@Table(name="especialidades")
public class Especialidad implements Serializable{

	private static final long serialVersionUID =1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String nombre;
	
	@OneToMany(mappedBy = "especialidad", fetch=FetchType.EAGER)
	private List<Medico> medicos;
	
	public Especialidad() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Medico> getMedicos() {
		IEspecialidadDao especialidadDao = new EspecialidadDaoImp();
		medicos = especialidadDao.obtenerTodosMedicos(getId());
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	public Especialidad(String nombre, List<Medico> medicos) {
		super();
		this.nombre = nombre;
		this.medicos = medicos;
	}

	@Override
	public String toString() {
		return "Especialidad [id=" + id + ", nombre=" + nombre + ", MEDICOS="+ getMedicos()+"]";
	}
	
	
	
	
	
}
