package br.edu.cefsa.shortline.controller.request;

import br.edu.cefsa.shortline.persistence.entity.QueueEntity;
import br.edu.cefsa.shortline.persistence.entity.ReserveEntity;
import br.edu.cefsa.shortline.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReserveRequest {

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
}
