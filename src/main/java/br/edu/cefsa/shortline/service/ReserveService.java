package br.edu.cefsa.shortline.service;

import br.edu.cefsa.shortline.controller.request.ReserveDto;
import br.edu.cefsa.shortline.persistence.entity.QueueEntity;
import br.edu.cefsa.shortline.persistence.entity.ReserveEntity;
import br.edu.cefsa.shortline.persistence.repository.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReserveService {

    @Autowired
    private ReserveRepository repository;

    @Autowired
    private QueueService queueService;

    public List<ReserveDto> getAllReserves(){
        List<ReserveEntity> reservesEntity = repository.findAll();

        return reservesEntity.stream().map(ReserveDto::toReserveDto).toList();
    }

    public void saveReserve(ReserveDto request) {
        ReserveEntity reserveEntity = request.toNewReserveEntity();
        repository.save(reserveEntity);
    }

    public void updateReserve(ReserveDto request) {
        ReserveEntity reserveEntity = repository.findById(request.getId())
                .orElseThrow();

        if (request.getStatus().equalsIgnoreCase("A") &&
                !reserveEntity.getStatus().equalsIgnoreCase("A")) {
            QueueEntity queue = queueService.getQueueEntityById(request.getIdQueue());
            reserveEntity.setCode(queue.getLastCode() + 1);
            repository.save(reserveEntity);
        }
    }
}
