package com.basma.Demo1.ticket;

import com.basma.Demo1.exceptions.TicketAlreadyExistsExeception;
import com.basma.Demo1.exceptions.TicketNotFoundException;
import com.basma.Demo1.ticket.TicDTO.TicketResponseDTO;
import com.basma.Demo1.ticket.TicDTO.addticketdto;
import com.basma.Demo1.ticket.TicDTO.updateticketdto;
import com.basma.Demo1.ticket.enums.Status;
import com.basma.Demo1.user.User;

import java.time.LocalDateTime;

public class TicketHelper {
    public static final String SUPERVISOR_EMAIL = "sniny@gmail.com";

    protected static void displayTicketNotFoundException(Ticket existingTicket) {
        if (existingTicket == null) {
            String message = "Ticket Not Found!";
            throw new TicketNotFoundException(message);
        }
    }

    protected static void displayTicketAlreadyExistsException(Ticket newTicket) {
        if (newTicket != null) {
            String message = "Ticket Already Exists!";
            throw new TicketAlreadyExistsExeception(message);
        }
    }
    protected static Ticket mapToEntity(addticketdto dto, User existingUser){
        Ticket newTicket = new Ticket();
        newTicket.setDescription(dto.getDescription());
        newTicket.setTitle(dto.getTitle());
        LocalDateTime now = LocalDateTime.now();
        newTicket.setCreationDate(now);
        newTicket.setPriority(dto.getPriority());
        newTicket.setUser(existingUser);
//        newTicket.setAssignedTo(dev.getId());
        newTicket.setCategories(dto.getCategories());
        newTicket.setLocation(dto.getLocation());
//        newTicket.setType(dto.getType());
        newTicket.setStatus(Status.NEW);
        newTicket.setCreatedBy(dto.getCreatedBy());
        newTicket.setFirstname(dto.getFirstname());
        newTicket.setLastname(dto.getLastname());
        return newTicket;
    }
//    protected static TicketResponseDTO map
    protected static TicketResponseDTO mapToResponse (Ticket updatedTicket, User existingUser){
        TicketResponseDTO formattedTicket = new TicketResponseDTO();
        formattedTicket.setId(updatedTicket.getId());
        formattedTicket.setDescription(updatedTicket.getDescription());
        formattedTicket.setTitle(updatedTicket.getTitle());
        formattedTicket.setCreationDate(updatedTicket.getCreationDate());
        formattedTicket.setUserEmail(existingUser.getEmail());
        formattedTicket.setAssignedTo(updatedTicket.getAssignedTo());
        formattedTicket.setFirstname(updatedTicket.getFirstname());
        formattedTicket.setLastname(updatedTicket.getLastname());
        formattedTicket.setStatus(updatedTicket.getStatus());
        formattedTicket.setCategories(updatedTicket.getCategories());
        formattedTicket.setLocation(updatedTicket.getLocation());
        formattedTicket.setPriority(updatedTicket.getPriority());
//        formattedTicket.setType(updatedTicket.getType())
        formattedTicket.setCreatedBy(existingUser.getLastname() + " " +existingUser.getFirstname());
        formattedTicket.setAssignedAt(updatedTicket.getAssignedAt());
        formattedTicket.setInProgressAt(updatedTicket.getInProgressAt());
        formattedTicket.setSolvedAt(updatedTicket.getSolvedAt());
        formattedTicket.setApprovedAt(updatedTicket.getApprovedAt());
        formattedTicket.setClosedAt(updatedTicket.getClosedAt());
        formattedTicket.setCancelledAt(updatedTicket.getCancelledAt());

        return formattedTicket;
    }
    protected static TicketResponseDTO mapToResponseForUpdate(Ticket updatedTicket){
        TicketResponseDTO formattedticket = new TicketResponseDTO();
        formattedticket.setDescription(updatedTicket.getDescription());
        formattedticket.setTitle(updatedTicket.getTitle());
        formattedticket.setStatus(updatedTicket.getStatus());

        return formattedticket;
    }
    protected static void mapToEntityForUpdate(updateticketdto dto, Ticket newTicket){
        newTicket.setTitle(dto.getTitle());
        newTicket.setDescription(dto.getDescription());
    }
}
