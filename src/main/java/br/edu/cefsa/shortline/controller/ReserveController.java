package br.edu.cefsa.shortline.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/reserves")
public class ReserveController {

    @PostMapping
    public ResponseEntity<Void> createReservePendingApprove(){

        var uri = URI.create("/reserves");
        return ResponseEntity.created(uri).build();
    }
}
