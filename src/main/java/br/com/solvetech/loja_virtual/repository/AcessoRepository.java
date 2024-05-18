package br.com.solvetech.loja_virtual.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.solvetech.loja_virtual.model.Acesso;

@Repository
@Transactional
public interface AcessoRepository extends JpaRepository<Acesso, Long>{

}
