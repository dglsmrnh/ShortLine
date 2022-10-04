package br.edu.cefsa.shortline.service;

import br.edu.cefsa.shortline.controller.request.QueueRequest;
import br.edu.cefsa.shortline.persistence.entity.QueueEntity;
import br.edu.cefsa.shortline.persistence.repository.QueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueueService {

    @Autowired
    private QueueRepository repository;

    public void updateQueue(QueueRequest request){
        QueueEntity queueEntity = repository.findById(request.getId())
                .orElseThrow();
        repository.save(request.toUpdateEntity(queueEntity));
    }

    public QueueEntity getQueueById(Long id){
        return repository.findById(id)
                .orElseThrow();
    }

    public void saveQueue(QueueRequest request){
        repository.save(request.toNewEntity());
    }
}
