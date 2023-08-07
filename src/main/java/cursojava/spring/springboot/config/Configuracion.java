package cursojava.spring.springboot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(
	basePackages = {
		"cursojava.jpahibernate.orm.modelocompras"
	}
)
@EnableJpaRepositories(
	basePackages = {
		"cursojava.jpahibernate.orm.modelocompras.repositorios"
	}
)
public class Configuracion {

}
