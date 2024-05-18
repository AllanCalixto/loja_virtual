package br.com.solvetech.loja_virtual.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import br.com.solvetech.loja_virtual.model.Acesso;
import br.com.solvetech.loja_virtual.service.AcessoService;

@RestController
public class AcessoController {
	
	@Autowired
	private AcessoService acessoService;
	
	public Acesso salvarAcesso(Acesso acesso) {
		return acessoService.save(acesso);
	}

}
