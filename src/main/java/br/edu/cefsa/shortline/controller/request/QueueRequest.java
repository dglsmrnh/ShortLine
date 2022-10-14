package br.edu.cefsa.shortline.controller.request;

import br.edu.cefsa.shortline.persistence.entity.CompanyEntity;
import br.edu.cefsa.shortline.persistence.entity.QueueEntity;
import br.edu.cefsa.shortline.persistence.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QueueRequest {

    @Setter
    private Long id;

    private Long idCompany;

    private String description;

    private Integer maxSize;

    private Integer vacancies;

    private Integer averageWaiting;

    private String status;

    public QueueEntity toNewEntity() {
        CompanyEntity companyEntity = nonNull(idCompany) ?
                CompanyEntity.builder().id(idCompany).build() : null;

        return builder().lastCode(0)
                .averageWaiting(0)
                .waitInLine(0)
                .companyEntity(companyEntity)
                .status("A")
                .build();
    }

    public QueueEntity toUpdateEntity(QueueEntity entity) {
        return builder().lastCode(entity.getLastCode())
                .averageWaiting(entity.getAverageWaiting())
                .waitInLine(entity.getWaitInLine())
                .status(status)
                .build();
    }

    public QueueEntity.QueueEntityBuilder builder() {
        return QueueEntity.builder()
                .description(description)
                .maxSize(maxSize)
                .vacancies(maxSize);
    }
}
