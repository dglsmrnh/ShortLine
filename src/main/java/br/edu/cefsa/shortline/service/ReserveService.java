package br.edu.cefsa.shortline.service;

import br.edu.cefsa.shortline.controller.request.ReserveRequest;
import br.edu.cefsa.shortline.persistence.entity.ReserveEntity;
import br.edu.cefsa.shortline.persistence.repository.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReserveService {

    @Autowired
    private ReserveRepository repository;

    public void saveReserve(ReserveRequest request){
        ReserveEntity reserveEntity = request.toNewReserveEntity();
        repository.save(reserveEntity);
    }
}
