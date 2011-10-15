package es.uah.mat.sigueme.simulador;

import static org.junit.Assert.*;

import org.junit.*;

public class PuertaTest {

	@Test
	public void deberiaRetornarDireccionCortaAPartirDeLosCuatroCaracteresDeLaDireccionLarga() throws Exception {
		Puerta puerta = new Puerta("1234567890");
		
		assertEquals("7890", puerta.getDireccionCorta());
	}
}
