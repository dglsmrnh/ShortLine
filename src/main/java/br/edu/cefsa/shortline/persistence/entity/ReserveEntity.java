package br.edu.cefsa.shortline.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReserveEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserEntity idUser;

    @ManyToOne
    private QueueEntity idQueue;

    private LocalDateTime registerIn;

    private LocalDateTime checkIn;

    private LocalDateTime checkOut;

    private Integer code;

    private String status;
}
