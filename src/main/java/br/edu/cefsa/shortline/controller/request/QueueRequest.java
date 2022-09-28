package br.edu.cefsa.shortline.controller.request;

import br.edu.cefsa.shortline.persistence.entity.QueueEntity;
import br.edu.cefsa.shortline.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class QueueRequest {

    @Setter
    private Long id;

    private String description;

    private LocalDateTime beginDate;

    private LocalDateTime endDate;

    private Long maxSize;

    private Long vacancies;

    private Integer averageWaiting;
}
