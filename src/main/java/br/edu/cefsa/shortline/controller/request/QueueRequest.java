package br.edu.cefsa.shortline.controller.request;

import br.edu.cefsa.shortline.persistence.entity.CompanyEntity;
import br.edu.cefsa.shortline.persistence.entity.QueueEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import static java.util.Objects.nonNull;

@Getter
@Setter
@NoArgsConstructor
@Builder
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

        return builderDefault().lastCode(0)
                .averageWaiting(0)
                .waitInLine(0)
                .companyEntity(companyEntity)
                .status("A")
                .build();
    }

    public QueueEntity toUpdateEntity(QueueEntity entity) {
        return builderDefault().lastCode(entity.getLastCode())
                .averageWaiting(entity.getAverageWaiting())
                .waitInLine(entity.getWaitInLine())
                .status(status)
                .build();
    }

    public static QueueRequest toResponse(QueueEntity queueEntity){
        Long idCompany = queueEntity.getCompanyEntity() == null ? null : queueEntity.getCompanyEntity().getId();

        return QueueRequest.builder()
                .id(queueEntity.getId())
                .status(queueEntity.getStatus())
                .idCompany(idCompany)
                .averageWaiting(queueEntity.getAverageWaiting())
                .maxSize(queueEntity.getMaxSize())
                .vacancies(queueEntity.getVacancies())
                .description(queueEntity.getDescription())
                .build();
    }

    public QueueEntity.QueueEntityBuilder builderDefault() {
        return QueueEntity.builder()
                .description(description)
                .maxSize(maxSize)
                .vacancies(maxSize);
    }
}
