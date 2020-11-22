package ar.edu.unju.edm.principal;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import ar.edu.unju.edm.dao.IEspecialidadDao;
import ar.edu.unju.edm.dao.IMedicoDao;
import ar.edu.unju.edm.dao.IObraSocialDao;
import ar.edu.unju.edm.dao.IPacienteDao;
import ar.edu.unju.edm.dao.IReservaDao;
import ar.edu.unju.edm.dao.imp.EspecialidadDaoImp;
import ar.edu.unju.edm.dao.imp.MedicoDaoImp;
import ar.edu.unju.edm.dao.imp.ObraSocialDaoImp;
import ar.edu.unju.edm.dao.imp.PacienteDaoImp;
import ar.edu.unju.edm.dao.imp.ReservaDaoImp;
import ar.edu.unju.edm.model.Especialidad;
import ar.edu.unju.edm.model.Medico;
import ar.edu.unju.edm.model.ObraSocial;
import ar.edu.unju.edm.model.Paciente;
import ar.edu.unju.edm.model.Reserva;

		/*
		- Consultar las reservas de un m�dico entre dos fechas ingresadas. (se debe ingresar la
		matr�cula del m�dico), esto debe construirse como un m�todo dentro de la clase Medico, el
		m�todo debe retornar la lista de reservas mostrando por consola todo el detalle de la misma,
		el m�todo recibe 2 par�metros que son la fechaInicio y fechaFinal, las cuales deben cumplir
		con fechaInicio < fechaFinal, si no se cumple el m�todo retorna null
		- Mostrar los datos de los pacientes de un m�dico que son mayores a una edad ingresada (se
		debe ingresar la matr�cula del m�dico), esto se debe construir como un m�todo de la clase
		Medico, el m�todo recibe un par�metro que representa la edad a buscar, se debe validar
		que sea una edad v�lida, por ejemplo que est� dentro de un rango [1,100] a�os. Si la edad
		no cumple con este rango se retorna un null.
		 */

public class Principal {
	
	public static void main(String[] args) {
				
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		//Esta obra social se crea para el case 3, en caso de que el paciente no disponga de obra social
		//ID=1 DE LA TABLA OBRASSOCIALES ES LA ENTIDAD OBRA SOCIAL QUE SE USARA PARA REGISTRAR PACIENTES QUE NO
		//CUENTAN CON OBRA SOCIAL
		IObraSocialDao obraSocialDao = new ObraSocialDaoImp();
		ObraSocial obraSocial = new ObraSocial();
		obraSocial.setNombre("No dispone");
		obraSocial.setDireccion("No dispone");
		obraSocialDao.guardarObraSocial(obraSocial);
		int op=0;
		boolean ing;
		do {
			do {
				System.out.println("\n---------------------------MENU----------------------------");
				System.out.println("1. Agregar una especialidad ");
				System.out.println("2. Agregar un médico");
				System.out.println("3. Agregar un paciente");
				System.out.println("4. Agregar una obra social");
				System.out.println("5. Agregar una reserva.");
				System.out.println("6. Consultar las reservas de un médico entre dos fechas.");
				System.out.println("7. Mostrar pacientes de un médico mayores a una edad específica ");
				System.out.println("8. Listar todos los pacientes por una obra social específica");
				System.out.println("9. Eliminar una reserva ingresando el id. ");
				System.out.println("10. Listar todas las reservas. ");
				System.out.println("11. Listar todas los médicos. ");
				System.out.println("12. Listar todos las especialidades.");
				System.out.println("13. Listar todas las obras sociales. ");
				System.out.println("14. Listar todos los pacientes. ");
				System.out.println("15. Salir ");
				System.out.println("Ingrese opción: ");
				ing=true;
				try {
					op=sc.nextInt();
				}catch(InputMismatchException i){
					System.out.println("\nIngreso inválido. Debe ingresar una opción numérica.");
					sc.next();
					ing=false;
					}
			}while(ing==false);
			switch(op) {
			case 1: agregarEspecialidad();
				break;
			case 2: agregarMedico();
				break;
			case 3:	agregarPaciente();
				break;
			case 4: agregarObraSocial();
				break;
			case 5: agregarReserva();
				break;
			case 6:	consultarReservas();
				break;
			case 7: mostrarPacientesMayoresEdad();
				break;
			case 8: listarPacientesObraSocial();
				break;
			case 9:	eliminarReserva();
					break;
			case 10: listarReservas();
				break;
			case 11: listarMedicos();
				break;
			case 12: listarEspecialidades();
				break;
			case 13: listarObrasSociales();
				break;
			case 14: listarPacientes();
				break;
			case 15: System.out.println("\nSaliendo...");
				break;
			default: System.out.println("\nOpción inválida!");
				break;
			}
			
			
		}while(op!=15);
		
			
	}
	
	
	
	public static void agregarEspecialidad() {
		IEspecialidadDao especialidadDao = new EspecialidadDaoImp();
		Scanner sc = new Scanner(System.in);
		Especialidad especialidad = new Especialidad();
		System.out.println("Ingrese nombre de la nueva especialidad:");
		especialidad.setNombre(sc.next());
		especialidadDao.guardarEspecialidad(especialidad);
		System.out.println("\nLa especialidad se ha creado con éxito!");
	}
	
	public static void agregarMedico() {
		IMedicoDao medicoDao = new MedicoDaoImp();
		IEspecialidadDao especialidadDao = new EspecialidadDaoImp();
		Scanner sc = new Scanner(System.in);
		//SE CONTROLA SI NO EXISTE YA EL MEDICO
		boolean ing1;
		int matriculaIngresada=0;
		do {
			ing1=true;
			System.out.println("Ingrese matrícula del nuevo médico: ");
			try {
				matriculaIngresada = sc.nextInt();
			}catch(InputMismatchException i) {
				System.out.println("\nINGRESO ERRONEO. Debe ingresar un NÚMERO de matrícula.\n");
				sc.next();
				ing1=false;
			}
		}while (ing1==false);
		if(medicoDao.buscarMedico(matriculaIngresada)!=null) {
			System.out.println("\nEL MÉDICO YA EXISTE!");
		}
		else {
			//se crea el médico
			Medico medico = new Medico();
			medico.setMatricula(matriculaIngresada);
			boolean c;
			//se controla el ingreso de un numero id
			Long idBusc=0l;
			do {
				c=true;
				System.out.println("Ingrese ID de la especialidad del nuevo médico:");
				try {
					idBusc = sc.nextLong();
				}catch(InputMismatchException i) {
					System.out.println("\nINGRESO ERRONEO. Debe ingresar un NÚMERO de ID.\n");
					sc.next();
					c=false;
				}
			}while (c==false);
			//se busca la especialidad
			if(especialidadDao.buscarEspecialidad(idBusc) == null) {
				System.out.println("\nNo existe la especialidad! Debe agregarla antes.");
				}
			else {
				System.out.print("\nLa especialidad es: ");
				System.out.println(especialidadDao.buscarEspecialidad(idBusc).getNombre()+"\n");
			medico.setEspecialidad(especialidadDao.buscarEspecialidad(idBusc));
			boolean ing2;
			int dniIngresado=0;
			do {
				ing2=true;
				System.out.println("Ingrese dni: ");
				try {
					dniIngresado = sc.nextInt();
				}catch(InputMismatchException i) {
					System.out.println("\nINGRESO ERRONEO. Debe ingresar un número de DNI.\n");
					sc.next();
					ing2=false;
				}
			}while (ing2==false);
			medico.setDni(dniIngresado);
			System.out.println("Ingrese nombre:");
			medico.setNombre(sc.next());
			System.out.println("Ingrese apellido:");
			medico.setApellido(sc.next());
			System.out.println("Ingrese teléfono:");
			medico.setTelefono(sc.next());
			medicoDao.guardarMedico(medico);
			System.out.println("\nEl médico se ha creado con éxito!");
			}
		}
	}
	
	public static void agregarPaciente() {
		IPacienteDao pacienteDao = new PacienteDaoImp();
		IObraSocialDao obraSocialDao = new ObraSocialDaoImp();
		Scanner sc = new Scanner(System.in);
		//SE CONTROLA SI NO EXISTE YA EL PACIENTE
		boolean ing3;
		int dniIngresado2=0;
		do {
			ing3=true;
			System.out.println("Ingrese dni del paciente: ");
			try {
				dniIngresado2 = sc.nextInt();
			}catch(InputMismatchException i) {
				System.out.println("\nINGRESO ERRONEO. Debe ingresar un número de DNI.\n");
				sc.next();
				ing3=false;
			}
		}while (ing3==false);
		if(pacienteDao.buscarPaciente(dniIngresado2)!=null) {
			System.out.println("\nEL PACIENTE YA EXISTE!");
		}
		else {
			//se crea el paciente
			Paciente paciente = new Paciente();
			paciente.setDni(dniIngresado2);
			String resp;
			boolean bienIngresado=false;
			do {
				System.out.println("\n¿El paciente cuenta con Obra social? Ingrese S(Sí) ó N(No)");
				resp=sc.next();
				if(resp.equalsIgnoreCase("s") || resp.equalsIgnoreCase("n")) {
					bienIngresado=true;
				}
				else {
					System.out.println("\nIngreso erróneo. Debe escribir S para (Sí) y N para (No)");
				}
			}while(bienIngresado==false);
			
			if(resp.equalsIgnoreCase("s")) {
				//se controla el ingreso de un NUMERO id de obra social
				Long idBuscad = controlarIdObraSocial();
				//se busca la obra social
				if(obraSocialDao.buscarObraSocial(idBuscad) == null) {
					System.out.println("\nNo existe la obra social!\n");
					}
				else {
					System.out.print("\nLa obra social es: ");
					System.out.println(obraSocialDao.buscarObraSocial(idBuscad).getNombre()+"\n");
					paciente.setObraSocial(obraSocialDao.buscarObraSocial(idBuscad));
					
					System.out.println("Ingrese nombre:");
					paciente.setNombre(sc.next());
					System.out.println("Ingrese apellido:");
					paciente.setApellido(sc.next());
					boolean ing4;
					int edadIngresada=0;
					do {
						ing4=true;
						System.out.println("Ingrese edad: ");
						try {
							edadIngresada = sc.nextInt();
							if(edadIngresada<1 || edadIngresada>100) {
								System.out.println("La edad no es válida. Ingrese una dentro del rango (1-100");
								ing4=false;
							}
						}catch(InputMismatchException i) {
							System.out.println("\nINGRESO ERRONEO. Debe ingresar el número que representa la edad.\n");
							sc.next();
							ing4=false;
						}
					}while (ing4==false);
					paciente.setEdad(edadIngresada);
					System.out.println("Ingrese teléfono:");
					paciente.setTelefono(sc.next());
					pacienteDao.guardarPaciente(paciente);
					System.out.println("\nEl paciente se ha creado con éxito!");
				}
			}else {
				//si se responde con N, el paciente no tiene obra social, por lo tanto se guarda ese atributo con la primer obra social registrada
				//en la base de datos, la cual es la que tiene como nombre y direccion "No dispone"
				paciente.setObraSocial(obraSocialDao.buscarObraSocial(1l));
//				continuarDatosPaciente(paciente);
				System.out.println("Ingrese nombre:");
				paciente.setNombre(sc.next());
				System.out.println("Ingrese apellido:");
				paciente.setApellido(sc.next());
				boolean ing4;
				int edadIngresada=0;
				do {
					ing4=true;
					System.out.println("Ingrese edad: ");
					try {
						edadIngresada = sc.nextInt();
						if(edadIngresada<1 || edadIngresada>100) {
							System.out.println("La edad no es válida. Ingrese una dentro del rango (1-100");
							ing4=false;
						}
					}catch(InputMismatchException i) {
						System.out.println("\nINGRESO ERRONEO. Debe ingresar el número que representa la edad.\n");
						sc.next();
						ing4=false;
					}
				}while (ing4==false);
				paciente.setEdad(edadIngresada);
				System.out.println("Ingrese teléfono:");
				paciente.setTelefono(sc.next());
				pacienteDao.guardarPaciente(paciente);
				System.out.println("\nEl paciente se ha creado con éxito!");
			}
			
		}
		
		
	}	
	
	public static void agregarObraSocial() {
		IObraSocialDao obraSocialDao = new ObraSocialDaoImp();
		Scanner sc = new Scanner(System.in);
		ObraSocial obraSocial = new ObraSocial();
		System.out.println("Ingrese nombre de la Obra social:");
		obraSocial.setNombre(sc.next());
		System.out.println("Ingrese dirección: ");
		obraSocial.setDireccion(sc.next());
		obraSocialDao.guardarObraSocial(obraSocial);
		System.out.println("\nLa obra social se ha creado con éxito!");
	}
	
	
	public static void agregarReserva() {
		Scanner sc = new Scanner(System.in);
		IMedicoDao medicoDao = new MedicoDaoImp();
		IObraSocialDao obraSocialDao = new ObraSocialDaoImp();
		IPacienteDao pacienteDao = new PacienteDaoImp();
		IReservaDao reservaDao = new ReservaDaoImp();
		
		Reserva reserva = new Reserva();
		System.out.println("\n----FECHA DE LA RESERVA----");
		int anio=0,mes=0,dia=0,hora=0,minutos=0;	
		boolean ingr1,ingr2,ingr3,ingr4,ingr5,fechaHoraIngB;
		LocalDateTime fechaHoraIng=null;
		do {
//			la fecha y hora empieza con valor true, o sea como correcta.
			fechaHoraIngB=true;
			do {
				ingr1=true;
				System.out.println("\nIngrese año: ");
				try {
					anio = sc.nextInt();
				}catch(InputMismatchException i){
					System.out.println("\nINGRESO ERRONEO. Debe ingresar el NÚMERO de año..\n");
					sc.next();
					ingr1=false;
				}
			}while(ingr1==false);	
			do {
				ingr2=true;
				System.out.println("Ingrese mes: ");
				try {
					mes = sc.nextInt();
				}catch(InputMismatchException i){
					System.out.println("\nINGRESO ERRONEO. Debe ingresar el NÚMERO del mes.\n");
					sc.next();
					ingr2=false;
				}
			}while(ingr2==false);	
			do {
				ingr3=true;
				System.out.println("Ingrese día: ");
				try {
					dia= sc.nextInt();
				}catch(InputMismatchException i){
					System.out.println("\nINGRESO ERRONEO. Debe ingresar el NÚMERO de día.\n");
					sc.next();
					ingr3=false;
				}
			}while(ingr3==false);
			System.out.println("\n----HORARIO DE RESERVA (HORAS Y MINUTOS)----");
			do {
				ingr4=true;
				try {
					do {
						System.out.println("\nIngrese hora (8 a 13): ");
						hora= sc.nextInt();
					}while(hora<8 || hora>13);
				}catch(InputMismatchException i){
					System.out.println("\nINGRESO ERRONEO. Debe ingresar el NÚMERO de hora.\n");
					sc.next();
					ingr4=false;
				}
			}while(ingr4==false);
			boolean cond;
			do {
				ingr5=true;
				try {
					boolean x;
					do {
						x=true;
						System.out.println("Ingrese minutos (Se permite: 00 || 15 || 30 || 45): ");
						minutos= sc.nextInt();
						if(hora==13 && minutos!=00) {
							System.out.println("\nEl horario de atención es hasta las 13:00!");
						}
						else {
							if(minutos!=00 && minutos!=15 && minutos!=30 && minutos!=45) {
								System.out.println("\nLos turnos se registran con diferencia de 15 minutos, a partir de hs 8:00. Ingrese correctamente.\n");
								x=false;
							}
						}
					}while((hora==13 && minutos!=00) || x==false);
				}catch(InputMismatchException i){
					System.out.println("\nINGRESO ERRONEO. Debe ingresar el NÚMERO de minutos.\n");
					sc.next();
					ingr5=false;
				}
			}while(ingr5==false);
			try {
				fechaHoraIng = LocalDateTime.of(anio,mes,dia,hora,minutos);
				//se crea un calendario para ver si se cumple con un día de semana (lunes a viernes)
				Calendar calendario = Calendar.getInstance();
				//Se pone "mes-1" porque en Calendar el mes se cuenta desde 0 que es enero hasta 11 que es diciembre.
				calendario.set(anio, mes-1,dia);
				int diaSemana = calendario.get(Calendar.DAY_OF_WEEK);
				//si es domingo(1) o sabado(7)
				if(diaSemana==1 || diaSemana==7) {
					System.out.println("\nDebe ingresar una fecha que sea día de semana (LUNES A VIERNES)!");
					fechaHoraIngB=false;
				}
				else {
					//SI LA FECHA Y HORA SON CORRECTAS, SE COMPRUEBA QUE SE HAGA CON UNA SEMANA DE ANTICIPACIÓN COMO MÍNIMO
					//se crea un LocalDate de la fecha de la reserva con el turno que se creó, para proceder a hacer la comparación
					LocalDate fechaIng = LocalDate.of(anio, mes, dia);
					//se saca la fecha del momento en que se pide el turno
					LocalDate fechaHoy = LocalDate.now();
					//se crea la fecha minima que debe tener el turno, ya que se pide con 7 dias de anticipacion
					LocalDate fechaMinima = fechaHoy.plusDays(7); 
					//se calcula la diferencia entre la fecha del turno y la fecha en que se lo pide.
					Period periodo = Period.between(fechaHoy, fechaIng);
					//se analiza si la fecha minima esta antes de la fecha que se ingresó al pedir el turno,  y si hay una diferencia minima de 7 dias
					if((fechaMinima.isBefore(fechaIng) || fechaMinima.isEqual(fechaIng)) && periodo.getDays()>=7){
						//SI SE HACE CON UNA SEMANA DE ANTICIPACIÓN, SE COMPRUEBA QUE ESTÉ DENTRO DEL MES ACTUAL
						if(fechaIng.getMonthValue()!=fechaHoy.getMonthValue()) {
							System.out.println("\nNO SE PUEDE REALIZAR LA RESERVA PORQUE NO ESTÁ DENTRO DEL MES ACTUAL! Ingrese nuevos datos.");
							fechaHoraIngB=false;
						}
						else {
							//SI TODOS LOS DATOS SON CORRECTOS, POR ULTIMO, SE CONTROLA SI YA EXISTE UN TURNO EN EL MISMO DIA Y HORARIO
							boolean b=false;
							System.out.println("Ingrese matrícula del médico: ");
							int matriculaIng = sc.nextInt();
							//se pregunta por el medico, si existe hay que analizar si no tiene asignado ya un turno en ese dia y horario
							if(medicoDao.buscarMedico(matriculaIng)!= null) {
								List<Reserva> reservasMedico = medicoDao.obtenerTodasReservas(medicoDao.buscarMedico(matriculaIng).getId());
								for(Reserva reserv:reservasMedico) {
									if(reserv.getFechaHora().isEqual(fechaHoraIng)) {
										b=true;
									}
								}
								//si no hay un turno con ese medico en la misma fecha y horario se siguen pidiendo los datos
								if(b==false) {
									reserva.setFechaHora(fechaHoraIng);
									reserva.setMedico(medicoDao.buscarMedico(matriculaIng));
									System.out.println("Ingrese DNI del paciente: ");
									int dniIng = sc.nextInt();
									//se pregunta por el paciente, si existe se continuan pidiendo los demas datos
									if(pacienteDao.buscarPaciente(dniIng)!= null) {
										reserva.setPaciente(pacienteDao.buscarPaciente(dniIng));
										System.out.println("Ingrese número de consultorio: ");
										reserva.setConsultorio(sc.nextInt());
										reservaDao.guardarReserva(reserva);
										System.out.println("\nReserva creada con éxito!");
									}
									else {
										System.out.println("\nEl paciente no existe!");
										System.out.println("\nNo se ha creado la reserva. Cree una nueva con datos correctos.");
									}
								}
								else {
									System.out.println("\nYA EXISTE UNA RESERVA EN ESE DÍA Y HORARIO CON EL MÉDICO SELECCIONADO! Ingrese nuevos datos.");
									fechaHoraIngB=false;
								}	
							}
							else {
								System.out.println("\nEl médico no existe!");
								System.out.println("\nNo se ha creado la reserva. Cree una nueva con datos correctos.");
							}
							
							
							
						}
					}
					else {
						System.out.println("\nNo se puede realizar la reserva! No tiene una semana de anticipación! Ingrese nuevos datos. ");
						fechaHoraIngB=false;	
					}
				}
			}catch(DateTimeException e) {
				if(dia<1 || dia>31) {
					System.out.println("\nEl día está fuera del rango (1-31)");
				}
				if(mes<1 || mes>12) {
					System.out.println("\nEl mes está fuera del rango (1-12)");
				}
				if(hora<0 || hora>59) {
					System.out.println("\nLos minutos están fuera del rango (00-59)");
				}
				System.out.println("\nLa fecha es inválida. Ingrese minutos, horas, día, mes y año dentro del rango!\n");
				fechaHoraIngB=false;
			}
		}while(fechaHoraIngB==false);
	}
	
	
	public static void consultarReservas() {
		//SE CONTROLA SI EXISTE EL MEDICO
		Medico medicoBuscado = controlarMedico();
		if(medicoBuscado==null) {
			System.out.println("\nEL MÉDICO NO EXISTE!");
		}
		else {
			System.out.println("\n----FECHA INICIAL---: \n");
			LocalDate fechaInicio = ingresarFecha();
			System.out.println("\n----FECHA FINAL: \n");
			LocalDate fechaFinal = ingresarFecha();	
			System.out.println("\nLas reservas que existen entre las fechas ingresadas son: \n");
			List<Reserva> reservas = medicoBuscado.consultarReservas(fechaInicio,fechaFinal);
			if(reservas==null) {
				System.out.println("\nLA FECHA INICIAL DEBE SER ANTERIOR A LA FECHA FINAL!");
			}else {
				if(reservas.isEmpty()) {
					System.out.println("\nNo hay reservas entre las dos fechas ingresadas.");
				}else {
					reservas.stream().forEach(System.out::println);
				}
			}
		}
	}
	

	public static void mostrarPacientesMayoresEdad() {
		Scanner sc = new Scanner(System.in);
		//SE CONTROLA SI EXISTE EL MEDICO
		Medico medicoBuscado2 = controlarMedico();
			if(medicoBuscado2==null) {
				System.out.println("\nEL MÉDICO NO EXISTE!");
			}else {
//				se controla que no exista alguna excepcion en el ingreso
				boolean ing9;
				int edad=0;
				do {
					ing9=true;
					System.out.println("Ingrese una edad (entre 1 y 100): ");
					try {
						edad = sc.nextInt();
					}catch(InputMismatchException i) {
						System.out.println("\nINGRESO ERRONEO. Debe ingresar un NÚMERO ENTERO.\n");
						sc.next();
						ing9=false;
					}
				}while (ing9==false);
//				se crea la lista de pacientes que tienen la edad mayor 
				List<Paciente> pacientes = medicoBuscado2.mostrarPacientes(edad);
				if(pacientes==null) {
					System.out.println("\nLA EDAD NO ESTÁ DENTRO DEL RANGO");
				}else {
					if(pacientes.isEmpty()) {
						System.out.println("\nNo hay pacientes mayores a la edad ingresada.");
					}else {
						pacientes.stream().forEach(System.out::println);
					}
				}
				
			}
	}
	
	
	public static void listarPacientesObraSocial(){
		IPacienteDao pacienteDao = new PacienteDaoImp();
		IObraSocialDao obraSocialDao = new ObraSocialDaoImp();
		//se controla el ingreso del id a buscar
		Long idBuscado = controlarIdObraSocial();
		//se busca la obra social
		if(obraSocialDao.buscarObraSocial(idBuscado) == null) {
			System.out.println("\nNo existe la obra social!");
			}
		else {
			System.out.print("\nLa obra social es: ");
			System.out.println(obraSocialDao.buscarObraSocial(idBuscado).getNombre()+"\n");
	
			if(pacienteDao.obtenerTodosPacientes().stream().filter(f->f.getObraSocial().getId()==idBuscado).count()==0) {
				System.out.println("\nNo existen pacientes registrados con esta Obra Social!");
			}
			else {
				pacienteDao.obtenerTodosPacientes().stream().filter(f->f.getObraSocial().getId()==idBuscado).forEach(System.out::println);
			}
			
			
			//SACARRRR, son pruebas
//			System.out.println(medicoDao.buscarMedico(2).getReservas());
//			List<Paciente> pacientesObraSocial = obraSocialDao.obtenerPacientesObraSocial(idBuscado);
//			for(Paciente pacient:pacientesObraSocial) {
//				//el problema esta en el getReservas() de cada paciente, sale un stackoverflow
//					System.out.println(pacient);
//			}
		}
	}
	
	
	public static void eliminarReserva() {
		IReservaDao reservaDao = new ReservaDaoImp();
		Scanner sc = new Scanner(System.in);
		Long idReservaElim=0l;
		boolean b;
		do {
			b=true;
			System.out.println("\nIngrese ID de la reserva que desea eliminar: ");
			try {
				idReservaElim= sc.nextLong();
			}catch(InputMismatchException i) {
				System.out.println("\nINGRESO ERRONEO. Debe ingresar un NÚMERO de ID.\n");
				sc.next();
				b=false;
			}
		}while (b==false);
//		 recuperar una entidad de la tabla reservas
		Reserva reservaEliminada = reservaDao.buscarReserva(idReservaElim);
		if(reservaEliminada != null) {
			System.out.print("\nSe eliminará la siguiente reserva: ");
			System.out.println(reservaEliminada+"\n");

//					 eliminar la entidad de la tabla reservas
			reservaDao.eliminarReserva(reservaEliminada);
			System.out.println("\nLa reserva fue eliminada");
		}else {
			System.out.println("\nNo existe la reserva!");
		}
	}
	
	public static void listarReservas() {
		IReservaDao reservaDao = new ReservaDaoImp();
		if(reservaDao.obtenerTodasReservas().isEmpty()) {
			System.out.println("No hay reservas cargadas!");
		}else {
			reservaDao.obtenerTodasReservas().stream().forEach(System.out::println);
		}
	}
	
	
	public static void listarMedicos() {
		IMedicoDao medicoDao = new MedicoDaoImp();
		if(medicoDao.obtenerTodosMedicos().isEmpty()) {
			System.out.println("No hay médicos cargados!");
		}else {
			medicoDao.obtenerTodosMedicos().stream().forEach(System.out::println);
			
//			SACARRR
//			medicoDao.obtenerTodosMedicos().stream().forEach((m)->{m.toString();});
//			medicoDao.obtenerTodasReservas().stream().forEach(System.out::println);
//			List<Reserva> reservas = medicoDao.obtenerTodasReservas(1l);
//			reservas.stream().forEach(System.out::println);
		}
	}
	
	
	public static void listarEspecialidades(){
		IEspecialidadDao especialidadDao = new EspecialidadDaoImp();
		if(especialidadDao.obtenerTodasEspecialidades().isEmpty()) {
			System.out.println("No hay especialidades cargadas!");
		}else {
			especialidadDao.obtenerTodasEspecialidades().stream().forEach(System.out::println);
		}
	}
	
	
	public static void listarObrasSociales(){
		IObraSocialDao obraSocialDao = new ObraSocialDaoImp();
		if(obraSocialDao.obtenerTodasObrasSociales().isEmpty()) {
			System.out.println("No hay obras sociales cargadas!");
		}else {
			obraSocialDao.obtenerTodasObrasSociales().stream().forEach(System.out::println);
		}
	}
	
	
	public static void listarPacientes() {
		IPacienteDao pacienteDao = new PacienteDaoImp();
		if(pacienteDao.obtenerTodosPacientes().isEmpty()) {
			System.out.println("No hay pacientes cargados!");
		}else {
			pacienteDao.obtenerTodosPacientes().stream().forEach(System.out::println);
		}
	}
	
	

	
	
	
	
	public static Medico controlarMedico() {
		IMedicoDao medicoDao = new MedicoDaoImp();
		Scanner sc = new Scanner(System.in);
		boolean ing6;
		int matriculaIngresada2=0;
		do {
			ing6=true;
			System.out.println("Ingrese matrícula del médico: ");
			try {
				matriculaIngresada2 = sc.nextInt();
			}catch(InputMismatchException i) {
				System.out.println("\nINGRESO ERRONEO. Debe ingresar un NÚMERO de matrícula.\n");
				sc.next();
				ing6=false;
			}
		}while (ing6==false);
		Medico medicoBuscado = medicoDao.buscarMedico(matriculaIngresada2);
		return medicoBuscado;
	}

	public static Long controlarIdObraSocial() {
		Scanner sc = new Scanner(System.in);
		boolean d;
		Long idBuscad=0l;
		do {
			d=true;
			try {
				System.out.println("Ingrese ID de la obra social:");	
				idBuscad = sc.nextLong();
			}catch(InputMismatchException i) {
				System.out.println("\nINGRESO ERRONEO. Debe ingresar un NÚMERO de ID.\n");
				sc.next();
				d=false;
			}
		}while (d==false);		
		return idBuscad;
	}
	
	public static LocalDate ingresarFecha() {
		Scanner sc = new Scanner(System.in);
		int dia=0,mes=0,anio=0;
		boolean ingr1,ingr2,ingr3,fechaIng;
		LocalDate fecha=null;
		do {
			do {
				ingr3=true;
				System.out.println("Ingrese año: ");
				try {
					anio = sc.nextInt();
				}catch(InputMismatchException i){
					System.out.println("\nINGRESO ERRONEO. Debe ingresar el NÚMERO del año.\n");
					sc.next();
					ingr3=false;
				}
			}while(ingr3==false);	
			do {
				ingr2=true;
				System.out.println("Ingrese mes: ");
				try {
					mes = sc.nextInt();
				}catch(InputMismatchException i){
					System.out.println("\nINGRESO ERRONEO. Debe ingresar el NÚMERO del mes.\n");
					sc.next();
					ingr2=false;
				}
			}while(ingr2==false);
			do {
				ingr1=true;
				System.out.println("Ingrese día: ");
				try {
					dia = sc.nextInt();
				}catch(InputMismatchException i){
					System.out.println("\nINGRESO ERRONEO. Debe ingresar el NÚMERO del día del mes.\n");
					sc.next();
					ingr1=false;
				}
			}while(ingr1==false);	
			
			try {
				fecha = LocalDate.of(anio,mes,dia);
				fechaIng=true;
			}catch(DateTimeException e) {
				if(dia<1 || dia>31) {
					System.out.println("\nEl día está fuera del rango (1-31)");
				}
				if(mes<1 || mes>12) {
					System.out.println("\nEl mes está fuera del rango (1-12)");
				}
				System.out.println("\nLa fecha es inválida. Ingrese números para día, mes y/o año dentro del rango!\n");
				fechaIng=false;
			}
		}while(fechaIng==false);
		
		return fecha;
	}
	
	
	
}
