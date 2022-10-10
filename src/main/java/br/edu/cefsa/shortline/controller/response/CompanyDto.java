package br.edu.cefsa.shortline.controller.response;

import br.edu.cefsa.shortline.persistence.entity.CompanyEntity;
import br.edu.cefsa.shortline.persistence.entity.QueueEntity;
import br.edu.cefsa.shortline.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
public class CompanyDto {

    @Setter
    private Long id;

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
                .user(UserEntity.builder().userId(idUser).build())
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
}
