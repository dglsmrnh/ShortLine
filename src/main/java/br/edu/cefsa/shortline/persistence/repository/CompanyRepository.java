package br.edu.cefsa.shortline.persistence.repository;

import br.edu.cefsa.shortline.persistence.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

    @Query("SELECT company FROM CompanyEntity company " +
            "where company.user.id = :userId")
    Optional<CompanyEntity> getCompanyFromUser(Long userId);

    @Query("SELECT company FROM CompanyEntity company " +
            "where company.user.username = :username")
    Optional<CompanyEntity> getCompanyFromUser(String username);
}
