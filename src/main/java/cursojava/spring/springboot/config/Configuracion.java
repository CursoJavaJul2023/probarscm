package cursojava.spring.springboot.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
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
@EntityScan(
	basePackages = {
		"cursojava.jpahibernate.orm.modelocompras.entidades",
		"cursojava.jpahibernate.orm.modelocompras.dto"
	}
)
public class Configuracion {

}
