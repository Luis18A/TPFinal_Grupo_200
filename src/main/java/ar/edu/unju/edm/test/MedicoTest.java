package ar.edu.unju.edm.test;

import static org.junit.Assert.*;

//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;

import org.junit.Test;

import ar.edu.unju.edm.model.Medico;
//import ar.edu.unju.edm.model.Reserva;

public class MedicoTest {
	private Medico medico= new Medico();
	@Test
	public void mostrarPacientes() {
		assertEquals(null,medico.mostrarPacientes(0));
	}
	
	@Test
	public void mostrarPacientes1() {
		assertEquals(null,medico.mostrarPacientes(101));
	}
	
	@Test
	public void mostrarPacientes2() {
		assertEquals(true,medico.mostrarPacientes(99).isEmpty());
	}
	
	@Test
	public void mostrarPacientes3() {
		assertEquals(true,medico.mostrarPacientes(10).isEmpty());
	}
	
	@Test
	public void mostrarPacientes4() {
		assertEquals(true,medico.mostrarPacientes(80).isEmpty());
	}

	
	/*
 * @Test
	public void mostrarReservas() {
		assertEquals(null,medico.consultarReservas(2012/12/12, 2012/12/11);
	}
	@Test
	public void mostrarReservas1() {
		assertEquals(null,medico.consultarReservas(2020/08/12,2020/08/12 );
	}
	@Test
	public void mostrarReservas2() {
		assertEquals(null,medico.consultarReservas(2015/08/04, 2015/08/01);
	}
	
	@Test
	public void mostrarReservas3() {
		assertEquals(true,medico.consultarReservas(2015/08/04, 2015/08/10).isEmpty());
	}
	
	@Test
	public void mostrarReservas4() {
		assertEquals(true,medico.consultarReservas(2020/08/01, 2020/08/12).isEmpty());
	}

	@Test
	public void mostrarReservas4() {
		assertEquals(true,medico.consultarReservas(2020/10/01, 2020/10/12).isEmpty());
	}
	
 */
	
	
}
