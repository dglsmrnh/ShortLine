package br.edu.cefsa.shortline.service;

import br.edu.cefsa.shortline.controller.request.QueueRequest;
import br.edu.cefsa.shortline.persistence.entity.QueueEntity;
import br.edu.cefsa.shortline.persistence.repository.QueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QueueService {

    @Autowired
    private QueueRepository repository;

    public void updateQueue(Long id){
        QueueEntity queueEntity = repository.findById(id)
                .orElseThrow();
        queueEntity.setActive(false);
        repository.save(queueEntity);
    }

    public QueueRequest getQueueById(Long id){
        QueueEntity queueEntity = getQueueEntityById(id);
        return QueueRequest.toResponse(queueEntity);
    }

    public QueueEntity getQueueEntityById(Long id){
        return repository.findById(id)
                .orElseThrow();
    }

    public QueueEntity saveQueue(QueueRequest request){
        Optional<QueueEntity> queueExiste = repository.findByCompanyEntityId(request.getIdCompany())
                .stream().findFirst();

        if (queueExiste.isEmpty()){
            repository.save(request.toNewEntity());
        }

        QueueEntity queueEntity = queueExiste.get();
        queueEntity.setMaxSize(request.getMaxSize());
        queueEntity.setLastCode(0);
        queueEntity.setVacancies(request.getMaxSize());
        queueEntity.setAverageWaiting(0);
        queueEntity.setWaitInLine(0);
        queueEntity.setActive(true);

        return repository.save(queueEntity);
    }
}
