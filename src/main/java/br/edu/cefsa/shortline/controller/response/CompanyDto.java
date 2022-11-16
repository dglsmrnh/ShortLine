package br.edu.cefsa.shortline.controller.response;

import br.edu.cefsa.shortline.persistence.entity.CompanyEntity;
import br.edu.cefsa.shortline.persistence.entity.QueueEntity;
import br.edu.cefsa.shortline.persistence.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyDto {

    @Setter
    private Long id;

    private String username;

    private Long idUser;
    private Long idQueue;

    private String name;

    private String postalCode;

    private Integer addressNumber;

    private BigDecimal latitude;

    private BigDecimal longitude;

    public CompanyEntity toEntity() {
        QueueEntity queueEntity = getQueueEntity();

        return CompanyEntity.builder()
                .addressNumber(addressNumber)
                .latitude(latitude)
                .longitude(longitude)
                .name(name)
                .queues(queueEntity)
                .postalCode(postalCode)
                .build();
    }

    private QueueEntity getQueueEntity() {
        return Objects.nonNull(idQueue) ?
                QueueEntity.getChild(idQueue) : null;
    }

    public CompanyEntity updateEntity(CompanyEntity entity) {
        entity.setQueues(getQueueEntity());
        entity.setAddressNumber(addressNumber);
        entity.setLatitude(latitude);
        entity.setLongitude(longitude);
        entity.setName(name);
        entity.setPostalCode(postalCode);
        return entity;
    }

    public static CompanyDto toResponse(CompanyEntity companyEntity){
        var queueId = isNull(companyEntity.getQueues()) ?
                null : companyEntity.getQueues().getId();

        return CompanyDto.builder()
                .id(companyEntity.getId())
                .addressNumber(companyEntity.getAddressNumber())
                .longitude(companyEntity.getLongitude())
                .latitude(companyEntity.getLatitude())
                .postalCode(companyEntity.getPostalCode())
                .idQueue(queueId)
                .name(companyEntity.getName())
                .idUser(companyEntity.getUser().getUserId())
                .build();
    }
}
