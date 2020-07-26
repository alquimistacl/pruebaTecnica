package com.prueba.tecnica;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(PruebaTecnicaApplication.class)
class PruebaTecnicaApplicationTests {

	@Test
	public void backendAppTest() {
		PruebaTecnicaApplication.main(new String[0]);
		assertTrue(Boolean.TRUE);
	}

}
