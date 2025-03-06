package com.gabriel.OEEManual.controller.estrutura;

import com.gabriel.OEEManual.domain.estrutura.Estrutura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstruturaRepository extends JpaRepository<Estrutura, Long> {

}