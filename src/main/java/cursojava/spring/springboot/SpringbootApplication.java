package cursojava.spring.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
// Equivale a estas tres:
//@EnableAutoConfiguration // Activar el mecanismo de auto configuración en cada dependencia starter
//@Configuration
//@ComponentScan // Por defecto se utiliza como paquete base el paquete de la clase que lo incorpora
               // cursojava.spring.springboot
public class SpringbootApplication {

	public static void main(String[] args) {
		
		ApplicationContext ctx = SpringApplication.run(SpringbootApplication.class, args);
		
		
	}

}
