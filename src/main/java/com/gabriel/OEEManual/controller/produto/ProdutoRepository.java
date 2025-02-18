package com.gabriel.OEEManual.controller.produto;

import com.gabriel.OEEManual.domain.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    boolean findAtivoById(Long id);

}
