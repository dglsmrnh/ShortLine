package br.edu.cefsa.shortline.service;

import br.edu.cefsa.shortline.controller.response.CompanyDto;
import br.edu.cefsa.shortline.persistence.entity.CompanyEntity;
import br.edu.cefsa.shortline.persistence.entity.UserEntity;
import br.edu.cefsa.shortline.persistence.repository.CompanyRepository;
import br.edu.cefsa.shortline.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

import static java.util.Objects.isNull;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QueueService queueService;

    public CompanyDto getByUserId(Long userId) {
        if (isNull(userId))
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);

        var companyEntity = repository.getCompanyFromUser(userId)
                .orElseThrow();
        return CompanyDto.toResponse(companyEntity);
    }

    public void saveCompany(CompanyDto request) {
        UserEntity userEntity = userRepository.findByUsername(request.getUsername()).orElseThrow();
        CompanyEntity companyEntity = request.toEntity();
        companyEntity.setUser(userEntity);
        repository.save(companyEntity);
    }

    public void updateCompany(CompanyDto request) {
        CompanyEntity companyEntity = repository.findById(request.getId())
                .orElseThrow();

        CompanyEntity entityUpdated = request.updateEntity(companyEntity);

        repository.save(entityUpdated);
    }
}
