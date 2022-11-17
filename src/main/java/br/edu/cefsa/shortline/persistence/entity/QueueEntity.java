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

    @OneToOne
    private CompanyEntity companyEntity;

    private String description;

    private Integer maxSize;

    private Integer lastCode;

    private Integer waitInLine;

    private Integer vacancies;

    private Integer averageWaiting;

    private Boolean active;

    public static QueueEntity getChild(Long id){
        return QueueEntity.builder()
                .id(id)
                .build();
    }
}
