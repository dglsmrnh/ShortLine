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

    public void updateQueue(QueueRequest request){
        QueueEntity queueEntity = repository.findById(request.getId())
                .orElseThrow();
        repository.save(request.toUpdateEntity(queueEntity));
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

        if (queueExiste.isEmpty())
            return repository.save(request.toNewEntity());

        return queueExiste.get();
    }
}
