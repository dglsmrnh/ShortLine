package br.edu.cefsa.shortline.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private UserEntity user;

    @OneToOne(fetch = FetchType.LAZY)
    private QueueEntity queues;

    private String name;

    private String postalCode;

    private Integer addressNumber;

    private BigDecimal latitude;

    private BigDecimal longitude;
}
