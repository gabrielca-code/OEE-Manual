package com.gabriel.OEEManual.controller.maquina;

import com.gabriel.OEEManual.domain.maquina.Maquina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaquinaRepository extends JpaRepository<Maquina, Long> {

    boolean findAtivoById(Long id);

}