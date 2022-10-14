package br.edu.cefsa.shortline.controller;

import br.edu.cefsa.shortline.controller.response.CompanyDto;
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
                                                                     Long userId) {
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
}
