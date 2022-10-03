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

    private Integer maxSize;

    private Integer vacancies;

    private Integer averageWaiting;

    public QueueEntity toNewEntity(){
        return QueueEntity.builder()
                .averageWaiting(0)
                .beginDate(beginDate)
                .description(description)
                .lastCode(0L)
                .endDate(endDate)
                .maxSize(maxSize)
                .vacancies(maxSize.longValue())
                .waitInLine(0L)
                .build();
    }
}
