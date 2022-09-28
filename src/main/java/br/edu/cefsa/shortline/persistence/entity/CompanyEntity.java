package br.edu.cefsa.shortline.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

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

    private String name;

    private String postalCode;

    private Integer addressNumber;

    private BigDecimal latitude;

    private BigDecimal longitude;
}
