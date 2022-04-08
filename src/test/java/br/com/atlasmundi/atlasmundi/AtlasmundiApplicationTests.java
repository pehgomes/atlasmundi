package br.com.atlasmundi.atlasmundi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ActiveProfiles("local")
@ExtendWith(SpringExtension.class)
class AtlasmundiApplicationTests {

	@Test
	void contextLoads() {
	}

}
