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

    private String status;

    public QueueEntity toNewEntity() {
        return builder().lastCode(0)
                .averageWaiting(0)
                .waitInLine(0)
                .status("A")
                .build();
    }

    public QueueEntity toUpdateEntity(QueueEntity entity) {
        return builder().lastCode(entity.getLastCode())
                .averageWaiting(entity.getAverageWaiting())
                .waitInLine(entity.getWaitInLine())
                .status(status)
                .build();
    }

    public QueueEntity.QueueEntityBuilder builder() {
        return QueueEntity.builder()
                .description(description)
                .maxSize(maxSize)
                .vacancies(maxSize);
    }
}
