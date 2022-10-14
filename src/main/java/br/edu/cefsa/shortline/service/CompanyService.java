package br.edu.cefsa.shortline.service;

import br.edu.cefsa.shortline.controller.response.CompanyDto;
import br.edu.cefsa.shortline.persistence.entity.CompanyEntity;
import br.edu.cefsa.shortline.persistence.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository repository;

    @Autowired
    private QueueService queueService;

    public CompanyDto getByUserId(Long userId) {
        if (Objects.isNull(userId))
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);

        var companyEntity = repository.getCompanyFromUser(userId).orElseThrow();
        return CompanyDto.toResponse(companyEntity);
    }

    public void saveCompany(CompanyDto request) {
        repository.save(request.toEntity());
    }

    public void updateCompany(CompanyDto request) {
        CompanyEntity companyEntity = repository.findById(request.getId())
                .orElseThrow();

        CompanyEntity entityUpdated = request.updateEntity(companyEntity);

        repository.save(entityUpdated);
    }
}
