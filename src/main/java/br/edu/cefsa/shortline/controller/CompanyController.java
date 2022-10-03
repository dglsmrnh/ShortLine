package br.edu.cefsa.shortline.controller;

import br.edu.cefsa.shortline.controller.response.CompanyDto;
import br.edu.cefsa.shortline.persistence.entity.CompanyEntity;
import br.edu.cefsa.shortline.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<CompanyEntity>> getAllCompany(Long userId) {
        List<CompanyEntity> companies = companyService.getAll(userId);

        return ResponseEntity.ok(companies);
    }

    @PostMapping
    public ResponseEntity<Void> saveCompany(CompanyDto company){
        companyService.saveCompany(company);

        URI uri = URI.create("/companies");

        return ResponseEntity.created(uri).build();
    }
}
