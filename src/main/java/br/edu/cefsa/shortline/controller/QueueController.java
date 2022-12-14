package br.edu.cefsa.shortline.controller;

import br.edu.cefsa.shortline.controller.request.QueueRequest;
import br.edu.cefsa.shortline.persistence.entity.QueueEntity;
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
    public ResponseEntity<Void> updateQueue(@PathVariable("id") Long idQueue) {
        service.updateQueue(idQueue);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<QueueEntity> createQueue(@RequestBody QueueRequest request) {
        QueueEntity queueEntity = service.saveQueue(request);
        queueEntity.setCompanyEntity(null);
        URI uri = URI.create("/queues");

        return ResponseEntity.created(uri).body(queueEntity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QueueRequest> getQueue(@PathVariable("id") Long idQueue) {
        var queue = service.getQueueById(idQueue);
        return ResponseEntity.ok(queue);
    }


    @GetMapping
    public ResponseEntity<QueueEntity> getQueueParams(@RequestParam("address") String address) {
        var queue = service.getQueue(address);
        queue.setCompanyEntity(null);
        return ResponseEntity.ok(queue);
    }
}
