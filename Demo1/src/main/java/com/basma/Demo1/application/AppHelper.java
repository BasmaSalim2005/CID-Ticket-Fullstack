package com.basma.Demo1.application;

import com.basma.Demo1.application.AppDTO.updateAppDTO;
import com.basma.Demo1.application.enums.State;
import com.basma.Demo1.application.AppDTO.AppResponseDTO;
import com.basma.Demo1.application.AppDTO.addAppDTO;
import com.basma.Demo1.exceptions.ApplicationAlreadyExistsException;
import com.basma.Demo1.exceptions.ApplicationNotFoundException;
import com.basma.Demo1.exceptions.TicketAlreadyExistsExeception;
import com.basma.Demo1.exceptions.TicketNotFoundException;
import com.basma.Demo1.ticket.Ticket;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppHelper {
    protected static void displayAppNotFoundException(Application existingApp) {
        if (existingApp == null) {
            String message = "App Not Found!";
            throw new ApplicationNotFoundException(message);
        }
    }

    protected static void displayAppAlreadyExistsException(Application newApp) {
        if (newApp != null) {
            String message = "App Already Exists!";
            throw new ApplicationAlreadyExistsException(message);
        }
    }
    protected static AppResponseDTO mapToResponse(Application updatedApp){
        AppResponseDTO formattedApp=new AppResponseDTO();
        formattedApp.setName(updatedApp.getName());
        formattedApp.setDescription(updatedApp.getDescription());
        formattedApp.setTaigaLink(updatedApp.getTaigaLink());
        formattedApp.setGitLabLink(updatedApp.getGitLabLink());
        formattedApp.setCreationDate(updatedApp.getCreationDate());
        formattedApp.setUpdateDate(updatedApp.getUpdateDate());

        return formattedApp;
    }
    protected static List<AppResponseDTO> mapToResponse(List<Application> updatedApps){
        List<AppResponseDTO> formattedApps =new ArrayList<>();
        for (int i=0 ; i<updatedApps.size(); i++) {
            AppResponseDTO response = mapToResponse(updatedApps.get(i));
            formattedApps.add(response);
        }
        return formattedApps;
    }
    protected static Application mapToEntity(addAppDTO dto){
        Application newApp = new Application();
        newApp.setName(dto.getName());
        newApp.setDescription(dto.getDescription());
        newApp.setTaigaLink(dto.getTaigaLink());
        newApp.setGitLabLink(dto.getGitLabLink());
        LocalDateTime now = LocalDateTime.now();

        newApp.setCreationDate(now);
        newApp.setState(State.ACTIVE);

        return newApp;
    }
    protected static AppResponseDTO mapToResponseUpdate(Application updatedApp){
        AppResponseDTO formattedApp=new AppResponseDTO();
        formattedApp.setName(updatedApp.getName());
        formattedApp.setDescription(updatedApp.getDescription());
        formattedApp.setTaigaLink(updatedApp.getTaigaLink());
        formattedApp.setGitLabLink(updatedApp.getGitLabLink());
        formattedApp.setCreationDate(updatedApp.getCreationDate());
        formattedApp.setUpdateDate(updatedApp.getUpdateDate());

        return formattedApp;
    }
    protected static Application mapToEntityUpdate(Application newApp,updateAppDTO dto){
        newApp.setName(dto.getName());
        newApp.setDescription(dto.getDescription());
        newApp.setTaigaLink(dto.getTaigaLink());
        newApp.setGitLabLink(dto.getGitLabLink());
        LocalDateTime now = LocalDateTime.now();

        newApp.setUpdateDate(now);
        return newApp;
    }
}
