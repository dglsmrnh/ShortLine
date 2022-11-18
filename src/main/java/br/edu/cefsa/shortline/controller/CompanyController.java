package br.edu.cefsa.shortline.controller;

import br.edu.cefsa.shortline.controller.response.CompanyDto;
import br.edu.cefsa.shortline.persistence.entity.QueueEntity;
import br.edu.cefsa.shortline.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/users/{userId}/companies")
    public ResponseEntity<CompanyDto> getAllCompany(@PathVariable("userId")
                                                                     String userId) {
        var companies = companyService.getByUserId(userId);

        return ResponseEntity.ok(companies);
    }

    @PostMapping("/companies")
    public ResponseEntity<Void> saveCompany(@RequestBody CompanyDto company) {
        companyService.saveCompany(company);

        URI uri = URI.create("/companies");

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/companies/{id}")
    public ResponseEntity<Void> updateCompany(@PathVariable("id") Long id,
                                              @RequestBody CompanyDto company) {
        company.setId(id);
        companyService.updateCompany(company);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/companies/{id}/queues")
    public ResponseEntity<QueueEntity> getQueue(@PathVariable("id") Long id) {
        QueueEntity queue = companyService.getQueueByCompanyId(id);
        queue.setCompanyEntity(null);
        return ResponseEntity.ok(queue);
    }
}
