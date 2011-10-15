package es.uah.mat.sigueme.simulador;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

public class RecepcionTarjetasTest {

	private ArrayList<TarjetaRFID> tarjetas;

	@Before
	public void setUpBeforeTest () {
		tarjetas = new ArrayList<TarjetaRFID>();
		
		tarjetas.add(new TarjetaRFID("1234567890"));
		tarjetas.add(new TarjetaRFID("1234567891"));
		tarjetas.add(new TarjetaRFID("1234567891"));
	}
	@Test
	public void deberíaDarmeLaTarjetaQueNoSeEsteUtilizando() throws Exception {
		RecepcionTarjetas recepcion = new RecepcionTarjetas(tarjetas);
		
		final TarjetaRFID tarjeta = recepcion.recogerTarjeta();
		
		assertEquals(2, recepcion.getNumeroTarjetasLibres());
		assertNotNull(tarjeta);
	}
	
	@Test
	public void deberiaRetornarNullCuandoNoExisteMasTarjetasLibrs() throws Exception {
		List<TarjetaRFID> tarjetas = new ArrayList<TarjetaRFID>();
		RecepcionTarjetas recepcion = new RecepcionTarjetas(tarjetas);
		
		final TarjetaRFID tarjeta = recepcion.recogerTarjeta();
		
		assertNull(tarjeta);
	}
	
	@Test
	public void deberiaAnadirLaTarjetaALibresCuandoDevueltoLaTarjeta() throws Exception {
		RecepcionTarjetas recepcion = new RecepcionTarjetas(tarjetas);
		
		final TarjetaRFID tarjeta = recepcion.recogerTarjeta();
		recepcion.devolverTarjeta(tarjeta);
		
		assertEquals(3, recepcion.getNumeroTarjetasLibres());
		assertEquals(0, recepcion.getNumeroTarjetasUsandose());

	}
}
