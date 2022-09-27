package br.edu.cefsa.shortline.request;

import br.edu.cefsa.shortline.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserDto {

    private UUID userId;

    private String username;

    private String password;

    private String type;

    private String key;

    public UserEntity toEntity(){
        return UserEntity.builder()
                .userId(userId)
                .username(username)
                .password(password)
                .type(type)
                .build();
    }
}