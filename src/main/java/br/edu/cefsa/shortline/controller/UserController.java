package br.edu.cefsa.shortline.controller;

import br.edu.cefsa.shortline.config.security.UserDetailsServiceImpl;
import br.edu.cefsa.shortline.controller.request.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserDetailsServiceImpl userDetailsService;

    @PostMapping
    public ResponseEntity<Void> saveUser(@RequestBody UserDto userDto){
        userDetailsService.saveUser(userDto);
        URI uri = URI.create("/user");
        return ResponseEntity.created(uri).build();
    }
}
