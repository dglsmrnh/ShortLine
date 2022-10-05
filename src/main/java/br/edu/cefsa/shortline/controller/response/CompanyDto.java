package br.edu.cefsa.shortline.controller.response;

import br.edu.cefsa.shortline.persistence.entity.CompanyEntity;
import br.edu.cefsa.shortline.persistence.entity.QueueEntity;
import br.edu.cefsa.shortline.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class CompanyDto {

    private Long id;

    private Long idUser;

    private List<Long> queues;

    private String name;

    private String postalCode;

    private Integer addressNumber;

    private BigDecimal latitude;

    private BigDecimal longitude;

    public CompanyEntity toEntity(){
        return CompanyEntity.builder()
                .user(UserEntity.builder().userId(idUser).build())
                .addressNumber(addressNumber)
                .latitude(latitude)
                .longitude(longitude)
                .name(name)
                .queues(List.of())
                .postalCode(postalCode)
                .build();
    }
}
