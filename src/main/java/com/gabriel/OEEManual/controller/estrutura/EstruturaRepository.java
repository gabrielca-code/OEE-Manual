package com.gabriel.OEEManual.controller.estrutura;

import com.gabriel.OEEManual.domain.estrutura.Estrutura;
import com.gabriel.OEEManual.domain.estrutura.EstruturaID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EstruturaRepository extends JpaRepository<Estrutura, EstruturaID> {

    /*
    @Query("""
    SELECT e FROM estrutura e WHERE e.idProduto = :idProduto AND e.idMaquina = :idMaquina ORDER BY e.velocidade LIMIT 1
    """)
    Estrutura buscarEstrutura(Long idProduto, Long idMaquina);
    */

    Estrutura findFirstByIdProdutoAndIdMaquina(Long idProduto, Long idMaquina);

}