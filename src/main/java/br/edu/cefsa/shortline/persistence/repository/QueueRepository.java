package br.edu.cefsa.shortline.persistence.repository;

import br.edu.cefsa.shortline.persistence.entity.QueueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QueueRepository extends JpaRepository<QueueEntity, Long> {

    List<QueueEntity> findByCompanyEntityId(Long id);

    List<QueueEntity> findByCompanyEntityUserUserId(Long id);
}
