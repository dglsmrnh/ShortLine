package br.edu.cefsa.shortline.config.security;

import br.edu.cefsa.shortline.persistence.entity.UserEntity;
import br.edu.cefsa.shortline.persistence.repository.UserRepository;
import br.edu.cefsa.shortline.controller.request.UserDto;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntityModel = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return new User(userEntityModel.getUsername(), userEntityModel.getPassword(),
                true, true, true, true,
                userEntityModel.getAuthorities());
    }

    public void saveUser(UserDto userDto){
        UserEntity user = userDto.toEntity();
        if (user.getType().equalsIgnoreCase("A")){
            user.setPassword(user.getUsername() + userDto.getKey());
        }

        userRepository.save(user);
    }
}