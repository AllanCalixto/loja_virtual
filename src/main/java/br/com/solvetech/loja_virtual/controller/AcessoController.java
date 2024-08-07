package br.com.solvetech.loja_virtual.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.solvetech.loja_virtual.model.Acesso;
import br.com.solvetech.loja_virtual.repository.AcessoRepository;
import br.com.solvetech.loja_virtual.service.AcessoService;

@RestController
public class AcessoController {
	
	@Autowired
	private AcessoService acessoService;
	
	@Autowired
	private AcessoRepository acessoRepository;
	
	@PostMapping("**/salvarAcesso")
	@ResponseBody
	public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso) {
		Acesso acessoSalvo = acessoService.save(acesso);
		return new ResponseEntity<Acesso>(acessoSalvo, HttpStatus.OK);
		
	}
	
	@PostMapping("**/deleteAcesso")
	@ResponseBody
	public ResponseEntity<?> deleteAcesso(@RequestBody Acesso acesso) {
		acessoRepository.deleteById(acesso.getId());
		return new ResponseEntity("Acesso removido!", HttpStatus.OK);
	}
	

	//@CrossOrigin(origins = "https://www.jdevtreinamentos.com.br")
	//@Secured({ "ROLE_GERENTE", "ROLE_ADMIN" })
	@DeleteMapping("**/deleteAcessoPorId/{id}")
	@ResponseBody
	public ResponseEntity<?> deleteAcessoPorId(@PathVariable("id") Long id) {
		acessoRepository.deleteById(id);
		return new ResponseEntity("Acesso removido!", HttpStatus.OK);
	}
	
	@GetMapping("**/obterAcesso/{id}")
	@ResponseBody
	public ResponseEntity<Acesso> obterAcesso(@PathVariable("id") Long id) {
		Acesso acesso = acessoRepository.findById(id).get();
		return new ResponseEntity<Acesso>(acesso, HttpStatus.OK);
	}
	
	@GetMapping("**/obterPorDesc/{desc}")
	@ResponseBody
	public ResponseEntity<List<Acesso>> obterPorDesc(@PathVariable("desc") String desc) {
		List<Acesso> acesso = acessoRepository.buscarAcessoDesc(desc);
		return new ResponseEntity<List<Acesso>>(acesso, HttpStatus.OK);
	}

}
