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

    public void saveUser(UserDto userDto) {
        UserEntity user = userDto.toEntity();
        validatePassword(userDto, user);

        userRepository.save(user);
    }

    private void validatePassword(UserDto userDto, UserEntity user) {
        if (user.getType().equalsIgnoreCase("OAuth")) {
            user.setPassword(user.getUsername() + userDto.getKey());
        } else if (Objects.isNull(user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Usuário padrão sem senha!");
        }
    }

    public List<UserDto> getUsers(){
        List<UserEntity> users = userRepository.findAll();
        return users.stream().map(UserDto::toDto).toList();
    }
}