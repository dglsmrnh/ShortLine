package br.edu.cefsa.shortline.service;

import br.edu.cefsa.shortline.persistence.entity.CompanyEntity;
import br.edu.cefsa.shortline.persistence.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CompanyService {

    private CompanyRepository repository;

    public List<CompanyEntity> getAll(Long userId) {
        if (Objects.nonNull(userId))
            return List.of(repository.getCompanyFromUser(userId).orElseThrow());

        return repository.findAll();
    }
}
