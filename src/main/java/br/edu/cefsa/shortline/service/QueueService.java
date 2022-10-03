package br.edu.cefsa.shortline.service;

import br.edu.cefsa.shortline.controller.request.QueueRequest;
import br.edu.cefsa.shortline.persistence.repository.QueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueueService {

    @Autowired
    private QueueRepository repository;

    public void updateQueue(QueueRequest request){

    }

    public void saveQueue(QueueRequest request){
        repository.save(request.toNewEntity());
    }
}
