package br.edu.cefsa.shortline.service;

import br.edu.cefsa.shortline.config.util.BagUtil;
import br.edu.cefsa.shortline.controller.request.ReserveDto;
import br.edu.cefsa.shortline.persistence.entity.QueueEntity;
import br.edu.cefsa.shortline.persistence.entity.ReserveEntity;
import br.edu.cefsa.shortline.persistence.repository.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.edu.cefsa.shortline.config.util.BagUtil.ACCEPT;

@Service
public class ReserveService {

    @Autowired
    private ReserveRepository repository;

    @Autowired
    private QueueService queueService;

    public List<ReserveDto> getAllReserves() {
        List<ReserveEntity> reservesEntity = repository.findAll();

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
            reserveEntity.setCode(queue.getLastCode() + 1);
            reserveEntity.setStatus(request.getStatus());
            repository.save(reserveEntity);
        } else {
            reserveEntity.setCheckIn(request.getCheckIn());
            reserveEntity.setCheckOut(request.getCheckOut());
        }
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
