package br.edu.cefsa.shortline.service;

import br.edu.cefsa.shortline.controller.request.ReserveDto;
import br.edu.cefsa.shortline.persistence.entity.QueueEntity;
import br.edu.cefsa.shortline.persistence.entity.ReserveEntity;
import br.edu.cefsa.shortline.persistence.repository.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static br.edu.cefsa.shortline.config.util.BagUtil.ACCEPT;
import static br.edu.cefsa.shortline.config.util.BagUtil.PENDING;

@Service
@EnableScheduling
public class ReserveService {

    @Autowired
    private ReserveRepository repository;

    @Autowired
    private QueueService queueService;

    public List<ReserveDto> getAllReserves(String pending, String username, String reserveLogic,
                                           String isCompany) {
        List<ReserveEntity> reservesEntity;

        if (isCompany.equalsIgnoreCase("true") && username != null){
            reservesEntity = repository.findByQueueCompanyEntityUserUsername(username);
        } else if (pending.equalsIgnoreCase("true")){
            reservesEntity = repository.findByStatus(PENDING);
        } else if (reserveLogic.equalsIgnoreCase("true") && username != null) {
            reservesEntity = repository.findByStatusInAndUserUsername(List.of(PENDING, ACCEPT), username);
            if (reservesEntity.isEmpty()) {
                throw new RuntimeException();
            }
        } else if (username != null) {
            reservesEntity = repository.findByUserUsername(username);
        } else {
            reservesEntity = repository.findAll();
        }

        return reservesEntity.stream().map(ReserveDto::toReserveDto).toList();
    }

    public void saveReserve(ReserveDto request) {
        ReserveEntity reserveEntity = request.toNewReserveEntity();
        repository.save(reserveEntity);
    }

    public void updateReserve(ReserveDto request) {
        ReserveEntity reserveEntity = getReserveEntity(request.getId());

        if (isReserveAccept(request, reserveEntity)) {
            QueueEntity queue = queueService.getQueueEntityById(request.getIdQueue());
            queue.setLastCode(queue.getLastCode() + 1);
            queueService.saveQueue(queue);

            reserveEntity.setCode(queue.getLastCode());
            reserveEntity.setStatus(request.getStatus());
            repository.save(reserveEntity);
        } else {
            if(request.getCheckIn() != null)
                reserveEntity.setCheckIn(request.getCheckIn());

            if(request.getCheckOut() != null)
                reserveEntity.setCheckOut(request.getCheckOut());

            if (request.getStatus() != null && (request.getStatus().equalsIgnoreCase("R")
                    || request.getStatus().equalsIgnoreCase("O"))) {
                reserveEntity.setStatus(request.getStatus());
                repository.saveAndFlush(reserveEntity);

                QueueEntity queue = queueService.getQueueEntityById(request.getIdQueue());
                queue.setLastCode(queue.getLastCode() - 1);
                queueService.saveQueue(queue);

                List<ReserveEntity> reservasToBatch = repository.findByQueueId(request.getId());
                reservasToBatch.forEach(reserve -> reserve.setCode(reserve.getCode() - 1));
                repository.saveAllAndFlush(reservasToBatch);
            } else {
                repository.save(reserveEntity);
            }
        }
    }

    public void update(Long idQueue) {
        List<ReserveEntity> reservasToBatch = repository.findByQueueId(idQueue);
        reservasToBatch.forEach(reserve -> reserve.setCode(reserve.getCode() - 1));
        repository.saveAllAndFlush(reservasToBatch);
    }

    private static boolean isReserveAccept(ReserveDto request, ReserveEntity reserveEntity) {
        return request.getStatus().equalsIgnoreCase(ACCEPT) &&
                !reserveEntity.getStatus().equalsIgnoreCase(ACCEPT);
    }

    public ReserveDto getReserve(Long id) {
        ReserveEntity reserveEntity = getReserveEntity(id);
        return ReserveDto.toReserveDto(reserveEntity);
    }

    private ReserveEntity getReserveEntity(Long id) {
        return repository.findById(id)
                .orElseThrow();
    }

}
