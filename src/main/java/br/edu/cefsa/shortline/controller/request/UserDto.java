package br.edu.cefsa.shortline.controller.request;

import br.edu.cefsa.shortline.config.util.BagUtil;
import br.edu.cefsa.shortline.persistence.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private Long userId;

    private String username;

    private String address;
    private String name;

    private String lastname;

    private String cpfCnpj;

    private String date;

    private String gender;

    private String baseImage;

    private String telephone;
    private String password;
    private String type;

    private String key;

    private String email;

    private Boolean isCompany;

    public UserEntity toEntity(){
        String passEncrypt = Objects.nonNull(password) ? BagUtil.encoder.encode(password) : null;

        return UserEntity.builder()
                .userId(userId)
                .address(address)
                .username(username)
                .password(passEncrypt)
                .type(type)
                .isCompany(isCompany)
                .baseImage(baseImage)
                .cpfCnpj(cpfCnpj)
                .date(date)
                .gender(gender)
                .lastname(lastname)
                .name(name)
                .email(email)
                .telephone(telephone)
                .build();
    }

    public static UserDto toDto(UserEntity user){
        return UserDto.builder()
                .email(user.getEmail())
                .address(user.getAddress())
                .telephone(user.getTelephone())
                .userId(user.getUserId())
                .username(user.getUsername())
                .isCompany(user.getIsCompany())
                .baseImage(user.getBaseImage())
                .cpfCnpj(user.getCpfCnpj())
                .date(user.getDate())
                .gender(user.getGender())
                .name(user.getName())
                .lastname(user.getLastname())
                .build();
    }
}
