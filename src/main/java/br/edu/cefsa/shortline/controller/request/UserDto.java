package br.edu.cefsa.shortline.controller.request;

import br.edu.cefsa.shortline.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserDto {

    private Long userId;

    private String username;

    private String password;

    private String type;

    private String key;

    private Boolean isCompany;

    public UserEntity toEntity(){
        return UserEntity.builder()
                .userId(userId)
                .username(username)
                .password(password)
                .type(type)
                .isCompany(isCompany)
                .build();
    }
}
