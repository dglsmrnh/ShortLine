package br.edu.cefsa.shortline.config.security;

import br.edu.cefsa.shortline.persistence.entity.UserEntity;
import br.edu.cefsa.shortline.persistence.repository.UserRepository;
import br.edu.cefsa.shortline.controller.request.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static br.edu.cefsa.shortline.config.util.BagUtil.encoder;

@Service
public record UserDetailsServiceImpl(UserRepository userRepository) implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntityModel = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return new User(userEntityModel.getUsername(), userEntityModel.getPassword(),
                true, true, true, true,
                userEntityModel.getAuthorities());
    }

    public Long saveUser(UserDto userDto) {
        UserEntity user = userDto.toEntity();
        validatePassword(userDto, user);

        return userRepository.save(user).getUserId();
    }

    public void atualiza(UserDto userDto, String username){
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow();

        userEntity.setUsername(userDto.getUsername());
        userEntity.setDate(userDto.getDate());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setAddress(userDto.getAddress());
        userEntity.setGender(userDto.getGender());
        userEntity.setName(userDto.getName());
        userEntity.setCpfCnpj(userDto.getCpfCnpj());
        userEntity.setLastname(userDto.getLastname());

        if(userDto.getPassword() != null && !userDto.getPassword().isBlank() && !userDto.getPassword().contains("*"))
            userEntity.setPassword(userDto.getPassword());

        userEntity.setTelephone(userDto.getTelephone());

        userRepository.save(userEntity);
    }

    private void validatePassword(UserDto userDto, UserEntity user) {
        if (user.getType().equalsIgnoreCase("OAuth")) {
            String password = encoder.encode(user.getUsername() + userDto.getKey());
            user.setPassword(password);
        } else if (Objects.isNull(user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Usu??rio padr??o sem senha!");
        }
    }

    public List<UserDto> getUsers(){
        List<UserEntity> users = userRepository.findAll();
        return users.stream().map(UserDto::toDto).toList();
    }

    public UserDto getUserByUsername(String username){
        var user = userRepository.findByUsername(username).orElseThrow();
        return UserDto.toDto(user);
    }
}