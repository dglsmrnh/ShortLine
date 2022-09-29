package br.edu.cefsa.shortline.controller;

import br.edu.cefsa.shortline.controller.request.QueueRequest;
import br.edu.cefsa.shortline.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/queues")
public class QueueController {

    @Autowired
    private QueueService service;

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateQueue(@PathVariable("id") Long idQueue,
                                            @RequestBody QueueRequest request){
        request.setId(idQueue);
        service.updateQueue(request);

        return ResponseEntity.noContent().build();
    }
}