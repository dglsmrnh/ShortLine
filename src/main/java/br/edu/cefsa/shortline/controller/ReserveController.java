package br.edu.cefsa.shortline.controller;

import br.edu.cefsa.shortline.controller.request.ReserveRequest;
import br.edu.cefsa.shortline.service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/reserves")
public class ReserveController {

    @Autowired
    private ReserveService reserveService;

    @PostMapping
    public ResponseEntity<Void> createReservePendingApprove(@RequestBody ReserveRequest request){
        reserveService.saveReserve(request);
        var uri = URI.create("/reserves");
        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<Void> updateReserve(@RequestBody ReserveRequest request){
        reserveService.saveReserve(request);
        var uri = URI.create("/reserves");
        return ResponseEntity.created(uri).build();
    }
}
