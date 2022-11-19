package br.edu.cefsa.shortline.persistence.repository;

import br.edu.cefsa.shortline.persistence.entity.ReserveEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReserveRepository extends JpaRepository<ReserveEntity, Long> {

    List<ReserveEntity> findByStatus(String status);
    List<ReserveEntity> findByUserUsername(String username);
}
