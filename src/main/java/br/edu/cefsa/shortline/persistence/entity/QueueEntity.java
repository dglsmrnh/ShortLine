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
public class QueueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private LocalDateTime beginDate;

    private LocalDateTime endDate;

    private Integer maxSize;

    private Long lastCode;

    private Long waitInLine;

    private Long vacancies;

    private Integer averageWaiting;
}
