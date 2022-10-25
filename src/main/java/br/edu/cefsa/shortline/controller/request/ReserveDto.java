package br.edu.cefsa.shortline.controller.request;

import br.edu.cefsa.shortline.persistence.entity.QueueEntity;
import br.edu.cefsa.shortline.persistence.entity.ReserveEntity;
import br.edu.cefsa.shortline.persistence.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReserveDto {

    private Long id;

    @NotNull
    private Long idUser;

    @NotNull
    private Long idQueue;

    private LocalDateTime registerIn;

    private LocalDateTime checkIn;

    private LocalDateTime checkOut;

    private Integer code;

    private String status;

    public ReserveEntity toNewReserveEntity(){
        return ReserveEntity.builder()
                .registerIn(LocalDateTime.now())
                .idQueue(QueueEntity.builder().id(idQueue).build())
                .idUser(UserEntity.builder().userId(idUser).build())
                .status("P")
                .build();
    }

    public static ReserveDto toReserveDto(ReserveEntity entity){
        return ReserveDto.builder()
                .id(entity.getId())
                .idQueue(entity.getIdQueue().getId())
                .idUser(entity.getIdUser().getUserId())
                .code(entity.getCode())
                .checkIn(entity.getCheckIn())
                .checkOut(entity.getCheckOut())
                .status(entity.getStatus())
                .build();
    }
}
