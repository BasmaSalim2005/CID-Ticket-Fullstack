package com.basma.Demo1.user;

import com.basma.Demo1.exceptions.CredentialsNotCorrectException;
import com.basma.Demo1.exceptions.EntityNotFoundException;
import com.basma.Demo1.user.dtos.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/demo1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/authenticate")
    public ResponseEntity<UserAuthResponseDTO> authenticate(@RequestBody @Valid UserAuthenticationDTO authenticationRequest) throws CredentialsNotCorrectException, EntityNotFoundException, CredentialsNotCorrectException {
        UserAuthResponseDTO authenticationResponse = userService.authenticate(authenticationRequest);

        ResponseEntity<UserAuthResponseDTO> apiResponse = new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
        return apiResponse;

    }

    @PostMapping("/add")
    public UserResponseDTO AddUser(@RequestBody @Valid Userdto dto){
        UserResponseDTO apiResponse = userService.AddUser(dto);
        return apiResponse;
    }
    @GetMapping("/getall")
    public List<User> getAll(){
        return userService.getAll();
    }
    @GetMapping("/getbyid/{id}")
    public Optional<User> GetUserById (@PathVariable Long id){
        return userService.GetUserById(id);
    }

    @GetMapping("/getbyemail/{email}")
    public User GetUserByEmail (@PathVariable String email){
        return userService.GetUserByEmail(email);
    }

    @GetMapping("/getbyfname/{id}")
    public Optional<User> GetUserByFirstName (@PathVariable String firstname){
        return userService.GetUserByFirstName(firstname);
    }

    @PutMapping("/update1/{id}")
    public UserResponseDTO UpdateUser (@PathVariable Long id, @RequestBody@Valid userupdatedto dto){
        return userService.UpdateUser(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public void DeleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @PostMapping("/login")
    public UserResponseDTO userAthetification(@RequestParam("email") String email, @RequestParam("password") String password){
        return userService.userAuthentification(email, password);
    }

    @GetMapping("/getdev")
    public List<User> getDevs(){
        return userService.getDevelopers();
    }
}
