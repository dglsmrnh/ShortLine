package br.edu.cefsa.shortline.controller;

import br.edu.cefsa.shortline.controller.request.QueueRequest;
import br.edu.cefsa.shortline.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/queues")
public class QueueController {

    @Autowired
    private QueueService service;

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateQueue(@PathVariable("id") Long idQueue,
                                            @RequestBody QueueRequest request) {
        request.setId(idQueue);
        service.updateQueue(request);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Void> createQueue(@RequestBody QueueRequest request) {
        service.saveQueue(request);

        URI uri = URI.create("/queues");

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QueueRequest> getQueue(@PathVariable("id") Long idQueue) {
        var queue = service.getQueueById(idQueue);
        return ResponseEntity.ok(queue);
    }
}
