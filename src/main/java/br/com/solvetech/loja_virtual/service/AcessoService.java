package br.com.solvetech.loja_virtual.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.solvetech.loja_virtual.model.Acesso;
import br.com.solvetech.loja_virtual.repository.AcessoRepository;

@Service
public class AcessoService {
	
	@Autowired
	private AcessoRepository acessoRepository;
	
	public Acesso save(Acesso acesso) {
		return acessoRepository.save(acesso);
	}

}
