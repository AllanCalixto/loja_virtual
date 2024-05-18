package br.com.solvetech;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.solvetech.loja_virtual.Application;
import br.com.solvetech.loja_virtual.controller.AcessoController;
import br.com.solvetech.loja_virtual.model.Acesso;

@SpringBootTest(classes = Application.class)
class LojaVirtualApplicationTests {
	
	@Autowired
	private AcessoController acessoController;

	
	@Test
	public void testCadastraAcesso() {
		
		Acesso acesso = new Acesso();
		acesso.setDescricao("ROLE_ADMIN");
		acessoController.salvarAcesso(acesso);
		
	}

}
