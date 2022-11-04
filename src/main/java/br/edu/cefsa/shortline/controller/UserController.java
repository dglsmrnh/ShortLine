package br.edu.cefsa.shortline.controller;

import br.edu.cefsa.shortline.config.security.UserDetailsServiceImpl;
import br.edu.cefsa.shortline.controller.request.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping
    public ResponseEntity<Void> saveUser(@RequestBody UserDto userDto){
        Long userId = userDetailsService.saveUser(userDto);
        URI uri = URI.create("/user/".concat(userId.toString()));
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers(){
        List<UserDto> users = userDetailsService.getUsers();
        return ResponseEntity.ok(users);
    }
}
