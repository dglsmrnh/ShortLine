package br.edu.cefsa.shortline.controller;

import br.edu.cefsa.shortline.controller.response.CompanyDto;
import br.edu.cefsa.shortline.persistence.entity.CompanyEntity;
import br.edu.cefsa.shortline.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/users/{userId}/companies")
    public ResponseEntity<List<CompanyEntity>> getAllCompany(@PathVariable("userId")
                                                                         Long userId) {
        List<CompanyEntity> companies = companyService.getAll(userId);

        return ResponseEntity.ok(companies);
    }

    @PostMapping("/companies")
    public ResponseEntity<Void> saveCompany(CompanyDto company){
        companyService.saveCompany(company);
        URI uri = URI.create("/companies");

        return ResponseEntity.created(uri).build();
    }
}
