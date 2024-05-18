package br.com.solvetech.loja_virtual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackages = "br.com.solvetech.loja_virtual.model") /* MAPEIA ONDE ESTÃO AS CLASSES DE MODELO */
@ComponentScan(basePackages = {"br.com.*"}) /* VARRE TODA BASE DOS PACOTES PROCURANDO POR ANOTAÇÕES E RECURSOS DO SPRINGBOOT */
@EnableJpaRepositories(basePackages = {"br.com.solvetech.loja_virtual.repository"}) /* PARA EVITAR ERROS COM JPA, APONTAMOS A CLASSE ONDE ESTÃO OS REPOSITORYS  */
@EnableTransactionManagement /* EVITA ERROS DE TRANSAÇÕES COMO INSERT E DELETE */
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
