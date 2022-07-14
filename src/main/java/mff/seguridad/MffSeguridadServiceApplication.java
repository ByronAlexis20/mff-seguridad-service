package mff.seguridad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class MffSeguridadServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MffSeguridadServiceApplication.class, args);
	}

}
