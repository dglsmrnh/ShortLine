package br.edu.cefsa.shortline.controller;

import br.edu.cefsa.shortline.controller.request.ReserveDto;
import br.edu.cefsa.shortline.service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reserves")
public class ReserveController {

    @Autowired
    private ReserveService reserveService;

    @PostMapping
    public ResponseEntity<Void> createReservePendingApprove(@RequestBody ReserveDto request) {
        reserveService.saveReserve(request);
        var uri = URI.create("/reserves");
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateReserve(@PathVariable("id") Long id,
                                              @RequestBody ReserveDto request) {
        request.setId(id);
        reserveService.updateReserve(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ReserveDto>> getAllReserves() {
        List<ReserveDto> reserves = reserveService.getAllReserves();
        return ResponseEntity.ok(reserves);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReserveDto> getReserve(@PathVariable("id") Long id) {
        ReserveDto reserves = reserveService.getReserve(id);
        return ResponseEntity.ok(reserves);
    }
}
