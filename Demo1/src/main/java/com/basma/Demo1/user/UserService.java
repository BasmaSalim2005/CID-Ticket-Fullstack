package com.basma.Demo1.user;


import com.basma.Demo1.commun.CommunHelper;
import com.basma.Demo1.config.security.JwtService;
import com.basma.Demo1.exceptions.CredentialsNotCorrectException;
import com.basma.Demo1.exceptions.EntityNotFoundException;
import com.basma.Demo1.notifications.smtp.EmailService;
import com.basma.Demo1.user.dtos.*;
import com.basma.Demo1.user.enums.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.basma.Demo1.user.UserHelper.mapToResponseAuth;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public User getUserIfExists(String email) throws EntityNotFoundException {
        User existingUser = userRepository.findByEmail(email);
        if (!CommunHelper.CheckIfExists(existingUser))
            UserHelper.displayUserNotFound();

        return existingUser;
    }

    public UserAuthResponseDTO authenticate(UserAuthenticationDTO authenticationRequest) throws CredentialsNotCorrectException, EntityNotFoundException {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        if (!authentication.isAuthenticated())
            UserHelper.displayCredentialsIncorrects();

        User existingUser = getUserIfExists(authenticationRequest.getEmail());
        String token = jwtService.generateToken(authenticationRequest.getEmail());

        UserAuthResponseDTO formattedResponse = mapToResponseAuth(existingUser, token);
        return formattedResponse;
    }
    protected UserResponseDTO AddUser(Userdto dto){
        CheckIfUserAlreadyExists(dto.getEmail());
        User newUser= UserHelper.mapToEntity(dto);
//        log.info("email", newUser.getEmail());
        User updatedUser = userRepository.save(newUser);
        UserResponseDTO formattedUser= UserHelper.mapToResponse(updatedUser);
        // Send notification email
        emailService.sendSimpleEmail(
                "basmanasma2005@gmail.com",
            "User Added",
            "A new user '" + newUser.getFirstname() + "' has been added."
        );

        return formattedUser;
    }
    private void CheckIfUserAlreadyExists(String email){
        User newUser = userRepository.findByEmail(email);
        UserHelper.displayUserAlreadyExistsException(newUser);
    }
    protected Optional<User> GetUserById(Long id){
        return userRepository.findById(id);
    }
    protected List<User> getAll(){return userRepository.findAll();}
    public User GetUserByEmail(String email){
        CheckIfUserExists(email);
        return userRepository.findByEmail(email);
    }
    private void CheckIfUserExists(String email){
      User existingUser = userRepository.findByEmail(email);
      UserHelper.displayUserNotFoundException(existingUser);
    }
    protected Optional<User> GetUserByFirstName(String firstname){
        return userRepository.findByfirstname(firstname);
    }
    protected UserResponseDTO UpdateUser(Long id, userupdatedto dto){
        Optional<User> newUser= userRepository.findById(id) ;
        UserHelper.mapToEntityForUpdate(dto, newUser.get());
        // Send notification email
        emailService.sendSimpleEmail(
                "basmanasma2005@gmail.com",
            "User Updated",
            "User with ID " + id + " has been updated."
        );
        User updatedUser = userRepository.save(newUser.get());

        return UserHelper.mapToResponseForUpdate(updatedUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
        // Send notification email
        emailService.sendSimpleEmail(
                "basmanasma2005@gmail.com",
            "User Deleted",
            "User with ID " + id + " has been deleted."
        );
    }
    public UserResponseDTO userAuthentification(String email, String password){
        User existingUser = userRepository.findByEmailAndPassword(email, password);
        UserHelper.displayUserNotFoundException(existingUser);
        return UserHelper.mapToResponse(existingUser);
    }
    public void saveUser(User user){
        userRepository.save(user);
    }

    public List<User> getDevelopers() {
        return userRepository.findByRoleIn(Arrays.asList(Role.DEVELOPER, Role.TECHNICIAN));
    }



}
