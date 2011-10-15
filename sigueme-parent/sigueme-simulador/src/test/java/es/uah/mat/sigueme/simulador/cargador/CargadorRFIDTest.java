package es.uah.mat.sigueme.simulador.cargador;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

import es.uah.mat.sigueme.simulador.*;

public class CargadorRFIDTest {

	@Test
	public void deberiaObtenerElFicheroConLasDireccionesDeLasTarjetasASimular() throws Exception {
		CargadorRFID cargador = new CargadorRFID ();
		
		assertNotNull(cargador.getFicheroDireccionesTarjetasRFID());
	}
	
	@Test
	public void deberiaDevolverTarjetasRFIDAPartirDeLasDireccionesDelFichero() throws Exception {
		CargadorRFID cargador = new CargadorRFID();
		List<TarjetaRFID> tarjetas = cargador.getTarjetasRFID();
		assertNotNull(tarjetas);
		assertEquals(70,tarjetas.size());
	}
	
	@Test
	public void deberiaDevolverLaListaDePuertas() throws Exception {
		CargadorRFID cargador = new CargadorRFID();
		List<Puerta> puertas = cargador.getPuertasRFID();
		
		assertEquals(4, puertas.size());
	}
}
