package com.basma.Demo1.application;

import com.basma.Demo1.application.AppDTO.*;
import com.basma.Demo1.application.AppDTO.addAppDTO;
import com.basma.Demo1.application.AppDTO.AppResponseDTO;
import com.basma.Demo1.application.enums.State;
import com.basma.Demo1.application.exceptions.ApplicationAlreadyExistsException;
import com.basma.Demo1.exceptions.ApplicationNotFoundException;
import com.basma.Demo1.notifications.smtp.EmailService;
import com.basma.Demo1.user.User;
import com.basma.Demo1.user.UserRepository;
import com.basma.Demo1.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AppService {
    private final AppRepository appRepository;
    private final EmailService emailService;
    private final UserService userService;

    protected AppResponseDTO addApp(addAppDTO dto){
        // Check if app with the same name already exists
        Optional<Application> existingApp = appRepository.findByName(dto.getName());
        if (existingApp.isPresent()) {
            throw new ApplicationAlreadyExistsException("Application with name '" + dto.getName() + "' already exists.");
        }
        User developer = userService.GetUserByEmail(dto.getDevEmail());
        List<User> devs = new ArrayList<>();
        devs.add(developer);

        Application newApp = AppHelper.mapToEntity(dto);
        newApp.setDevelopers(devs);
        Application updateApp= appRepository.save(newApp);
        // Send notification email
        emailService.sendSimpleEmail(
                "basmanasma2005@gmail.com",
            "Application Added",
            "A new application '" + newApp.getName() + "' has been added."
        );
        return AppHelper.mapToResponse(updateApp);
    }



    // In your service
    public Page<Application> getAllApps(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return appRepository.findAll(pageable);
    }
    public List<Application> getAll() {
        return appRepository.findAll();
    }

    public AppResponseDTO getAppByName(String name) {
        Application app = appRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("App not found"));

        return AppHelper.mapToResponse(app);
    }
    public Application getAppByNameft(String name) {
        Application app = appRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("App not found"));

        return app;
    }

    private void checkIfAppExistsByCreationDate(LocalDateTime creationDate) {
        if (appRepository.findByCreationDate(creationDate).isEmpty()) {
            throw new ApplicationNotFoundException("No application found with creation date: " + creationDate);
        }
    }

    public List<AppResponseDTO> getAppByCreationDate(LocalDateTime creationDate) {
        checkIfAppExistsByCreationDate(creationDate);
        List<Application> apps = appRepository.findByCreationDate(creationDate);
        return AppHelper.mapToResponse(apps);
    }

    public AppResponseDTO updateApp(Long id, updateAppDTO dto) {
        Application app = appRepository.findById(id)
                .orElseThrow(() -> new ApplicationNotFoundException("App with id " + id + " not found"));

        // Optionally, check if the new name is already used by another app
        if (dto.getName() != null && !dto.getName().equals(app.getName())) {
            Optional<Application> appWithSameName = appRepository.findByName(dto.getName());
            if (appWithSameName.isPresent() && !appWithSameName.get().getId().equals(id)) {
                throw new ApplicationAlreadyExistsException("Another application with name '" + dto.getName() + "' already exists.");
            }
        }

        Application formatedApp = AppHelper.mapToEntityUpdate(app, dto);

        appRepository.save(formatedApp);
        // Send notification email
        emailService.sendSimpleEmail(
            "basmanasma2005@gmail.com",
            "Application Updated",
            "Application '" + app.getName() + "' has been updated."
        );
        return AppHelper.mapToResponseUpdate(app);
    }


    public void deleteApp(Long id) {
        appRepository.deleteById(id);
        // Send notification email
        emailService.sendSimpleEmail(
                "basmanasma2005@gmail.com",
            "Application Deleted",
            "An application with ID " + id + " has been deleted."
        );
    }

    public AppResponseDTO ActiveApp(String name){
        Optional<Application> app = appRepository.findByName(name);
        app.get().setState(State.ACTIVE);
        appRepository.save(app.get());
        return AppHelper.mapToResponse(app.get());
    }

    public AppResponseDTO MaintenanceApp(String name){
        Optional<Application> app = appRepository.findByName(name);
        app.get().setState(State.INMAINTENANCE);
        appRepository.save(app.get());
        return AppHelper.mapToResponse(app.get());
    }

    public AppResponseDTO DisactiveApp(String name){
        Optional<Application> app = appRepository.findByName(name);
        app.get().setState(State.DISACTIVE);
        appRepository.save(app.get());
        return AppHelper.mapToResponse(app.get());
    }
    public Integer getAppKPIs(String email){
        int total =appRepository.countApplicationsForDeveloper(email);
        return total;
    }
    public Integer getTotalApp(){
        int total =appRepository.countApplications();
        return total;
    }
    public Integer getTotalAppAtive(){
        int total =appRepository.countAppsByActive();
        return total;
    }
    public Integer getTotalAppDisactive(){
        int total =appRepository.countAppsByDisactive();
        return total;
    }
    public Integer getTotalAppMaintemance(){
        int total =appRepository.countAppsMaintenance();
        return total;
    }

    public Page<AppResponseDTO> getAppByUser(String userEmail, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<Application> appPage = appRepository.findByUserEmail(userEmail, pageable);

        return appPage.map(AppHelper::mapToResponse);
    }

    public List<AppResponseDTO> getByUser(String userEmail) {
        User user = userService.GetUserByEmail(userEmail);
        List<Application> app = user.getApps();
        return AppHelper.mapToResponse(app);
    }

    public AppResponseDTO addUserToApp(String userEmail, String name){
        User user = userService.GetUserByEmail(userEmail);
        Optional<Application> app = appRepository.findByName(name);
        if (user.getApps()== null){
            List<Application> list = new ArrayList<>();
            user.setApps(list);
        }
        user.getApps().add(app.get());
        userService.saveUser(user);

        return AppHelper.mapToResponse(app.get());
    }
    public Integer getTotalAppByActiveForUser(String userEmail) {
        return appRepository.countByStateAndUserEmail(State.ACTIVE, userEmail);
    }
    public Integer getTotalAppByDisactiveForUser(String userEmail) {
        return appRepository.countByStateAndUserEmail(State.DISACTIVE, userEmail);
    }
    public Integer getTotalAppByMaintForUser(String userEmail) {
        return appRepository.countByStateAndUserEmail(State.INMAINTENANCE, userEmail);
    }

//    public Integer getDevs(String email){
//        int total =appRepository.countApplicationsForDeveloper(email);
//        return total;
//    }

}
