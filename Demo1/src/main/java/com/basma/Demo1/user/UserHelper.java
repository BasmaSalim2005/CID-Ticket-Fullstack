package com.basma.Demo1.user;

import com.basma.Demo1.commun.CommunHelper;
import com.basma.Demo1.exceptions.CredentialsNotCorrectException;
import com.basma.Demo1.exceptions.EntityNotFoundException;
import com.basma.Demo1.user.dtos.UserAuthResponseDTO;
import com.basma.Demo1.user.dtos.UserResponseDTO;
import com.basma.Demo1.user.dtos.Userdto;
import com.basma.Demo1.user.dtos.userupdatedto;
import com.basma.Demo1.exceptions.UserAlreadyExistsExeception;
import com.basma.Demo1.exceptions.UserNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class UserHelper {

    protected static void displayUserNotFoundException(User existingUser) {
        if (existingUser == null) {
            String message = "User Not Found!";
            throw new UserNotFoundException(message);
        }
    }

    public static void displayUserNotFound() throws EntityNotFoundException {
        String message = "these user is not existing in DB";
        throw new EntityNotFoundException(message);
    }

    public static void displayCredentialsIncorrects() throws CredentialsNotCorrectException {
        String message = "email or password is incorect";
        throw new CredentialsNotCorrectException(message);
    }

    protected static void displayUserAlreadyExistsException(User newUser) {
        if (newUser != null) {
            String message = "User Already Exists!";
            throw new UserAlreadyExistsExeception(message);
        }
    }

    protected static UserResponseDTO mapToResponse(User updatedUser){
        UserResponseDTO formattedUser=new UserResponseDTO();
        formattedUser.setId(updatedUser.getId());
        formattedUser.setFirstname(updatedUser.getFirstname());
        formattedUser.setLastname(updatedUser.getLastname());
        formattedUser.setRole(updatedUser.getRole());
        formattedUser.setEmail(updatedUser.getEmail());

        return formattedUser;
    }

    protected static UserAuthResponseDTO mapToResponseAuth(User updatedUser, String token){
        UserAuthResponseDTO formattedUser=new UserAuthResponseDTO();
        formattedUser.setId(updatedUser.getId());
        formattedUser.setFirstname(updatedUser.getFirstname());
        formattedUser.setLastname(updatedUser.getLastname());
        formattedUser.setRole(updatedUser.getRole());
        formattedUser.setEmail(updatedUser.getEmail());
        formattedUser.setToken(token);

        return formattedUser;
    }
    protected static User mapToEntity(Userdto dto){
        User newUser = new User();
        newUser.setFirstname(dto.getFirstname());
        newUser.setLastname(dto.getLastname());
        newUser.setRole(dto.getRole());
        newUser.setEmail(dto.getEmail());
        String encryptedPw = CommunHelper.EncryptData(dto.getPassword());
        newUser.setPassword(encryptedPw);
        return newUser;
    }
    protected static UserResponseDTO mapToResponseForUpdate(User updatedUser){
        UserResponseDTO formattedUser=new UserResponseDTO();
        formattedUser.setFirstname(updatedUser.getFirstname());
        formattedUser.setLastname(updatedUser.getFirstname());
        formattedUser.setEmail(updatedUser.getEmail());
        formattedUser.setRole(updatedUser.getRole());

        return formattedUser;
    }
    protected static void mapToEntityForUpdate(userupdatedto dto, User newUser){
        newUser.setFirstname(dto.getFirstname());
        newUser.setLastname(dto.getLastname());
        newUser.setEmail(dto.getEmail());
        newUser.setRole(dto.getRole());

    }

}
