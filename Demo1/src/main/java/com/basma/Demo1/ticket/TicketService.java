package com.basma.Demo1.ticket;

import com.basma.Demo1.application.Application;
import com.basma.Demo1.feature.Feature;
import com.basma.Demo1.feature.FeatureService;
import com.basma.Demo1.notifications.smtp.EmailService;
import com.basma.Demo1.ticket.TicDTO.*;
import com.basma.Demo1.ticket.enums.Category;
import com.basma.Demo1.ticket.enums.Status;
import com.basma.Demo1.user.User;
import com.basma.Demo1.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.xml.sax.helpers.AttributesImpl;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.basma.Demo1.ticket.TicketHelper.SUPERVISOR_EMAIL;
import static com.basma.Demo1.ticket.enums.Status.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserService userService;
    private final EmailService emailService;
    private final FeatureService featureService;

    protected TicketResponseDTO addTicket(addticketdto dto) {

        User existingUser = userService.GetUserByEmail(dto.getUserEmail());
        Ticket newTicket = TicketHelper.mapToEntity(dto, existingUser);
        log.info("cqteeeeeeeeeeeegory");
        log.info(dto.getCategories().toString());
        log.info(newTicket.getCategories().toString());
        if (newTicket.getCategories() == Category.APP_RELATED) {
            log.info("add tic from app-related");
            Feature feature = featureService.getFeatById(dto.getFeature());
            User developer = feature.getDeveloper();
            newTicket.setAssignedTo(developer.getEmail());
            newTicket.setFirstname(developer.getFirstname());
            newTicket.setLastname(developer.getLastname());
            newTicket.setStatus(ASSIGNED);

            LocalDateTime now = LocalDateTime.now();
            newTicket.setAssignedAt(now);

        } else {
            log.info("add tic from help-des");
            assigneHelpDeskSupervisor(SUPERVISOR_EMAIL, newTicket);
            newTicket.setStatus(ASSIGNED);
            LocalDateTime now = LocalDateTime.now();
            newTicket.setAssignedAt(now);
        }
//        CheckIfTicketAlreadyExists(newTicket.getId());
        Ticket updatedTicket = ticketRepository.save(newTicket);
        // Send notification email
        emailService.sendSimpleEmail(
                "basmanasma2005@gmail.com",
                "Ticket Added",
                "A new ticket for user '" + existingUser.getFirstname() + "' has been added."
        );
        return TicketHelper.mapToResponse(updatedTicket, existingUser);
    }

    private void assigneHelpDeskSupervisor(String email, Ticket tic) {
        tic.setAssignedTo(email);
    }

    private void CheckIfTicketAlreadyExists(Long id) {
        Optional<Ticket> newTicket = ticketRepository.findById(id);
        TicketHelper.displayTicketAlreadyExistsException(newTicket.get());
    }

    protected Optional<Ticket> GetTicketById(Long id) {
        return ticketRepository.findById(id);
    }

    protected List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    public List<Ticket> GetTicketByUserEmail(String email) {
//        CheckIfTicketExistsEmail(email);
        return ticketRepository.findAllByUserEmail(email);
    }

    private void CheckIfTicketExists(Long id) {
        Optional<Ticket> existingTicket = ticketRepository.findById(id);
        TicketHelper.displayTicketNotFoundException(existingTicket.get());
    }

    private void CheckIfTicketExistsEmail(String email) {
        Ticket existingTicket = ticketRepository.findByUserEmail(email);
        TicketHelper.displayTicketNotFoundException(existingTicket);
    }

    protected TicketResponseDTO UpdateTicket(Long id, updateticketdto dto) {
        CheckIfTicketExists(id);
        Optional<Ticket> newTicket = ticketRepository.findById(id);
        TicketHelper.mapToEntityForUpdate(dto, newTicket.get());

        Ticket updatedTicket = ticketRepository.save(newTicket.get());
        // Send notification email
        emailService.sendSimpleEmail(
                "basmanasma2005@gmail.com",
                "Ticket Updated",
                "Ticket with ID " + id + " has been updated."
        );

        return TicketHelper.mapToResponseForUpdate(updatedTicket);
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
        // Send notification email
        emailService.sendSimpleEmail(
                "basmanasma2005@gmail.com",
                "Ticket Deleted",
                "Ticket with ID " + id + " has been deleted."
        );
    }

    // Business methods for ticket workflow
    public void approveTicket(Long ticketId) {
        Optional<Ticket> ticket = ticketRepository.findById(ticketId);
        if (ticket.get().getStatus() == SOLVED) {
            ticket.get().setStatus(APPROVED);
            LocalDateTime now = LocalDateTime.now();
            ticket.get().setApprovedAt(now);
            ticketRepository.save(ticket.get());
        }
    }

    public void closeTicket(Long ticketId) {
        Optional<Ticket> newTicket = ticketRepository.findById(ticketId);
        if (newTicket.get().getStatus() == APPROVED) {
            newTicket.get().setStatus(CLOSED);
            LocalDateTime now = LocalDateTime.now();
            newTicket.get().setClosedAt(now);
            ticketRepository.save(newTicket.get());

        }
    }

    public void ProgressTicket(Long ticketId) {
        Optional<Ticket> newTicket = ticketRepository.findById(ticketId);
        if (newTicket.get().getStatus() == ASSIGNED) {
            newTicket.get().setStatus(IN_PROGRESS);
            LocalDateTime now = LocalDateTime.now();
            newTicket.get().setInProgressAt(now);
            ticketRepository.save(newTicket.get());

        }
    }

    public void cancelTicket(Long ticketId) {
        Optional<Ticket> newTicket = ticketRepository.findById(ticketId);
        if (newTicket.get().getStatus() == ASSIGNED || newTicket.get().getStatus() == IN_PROGRESS) {
            newTicket.get().setStatus(CANCELLED);
            LocalDateTime now = LocalDateTime.now();
            newTicket.get().setCancelledAt(now);
            ticketRepository.save(newTicket.get());

        }
    }

    public void solveTicket(Long ticketId, String solution) {
        Optional<Ticket> newTicket = ticketRepository.findById(ticketId);
        if (newTicket.get().getStatus() == IN_PROGRESS) {
            newTicket.get().setStatus(SOLVED);
            newTicket.get().setSolution(solution);
            LocalDateTime now = LocalDateTime.now();
            newTicket.get().setSolvedAt(now);
            ticketRepository.save(newTicket.get());
        }
    }

    public List<TicketResponseDTO> getAssignedTicket(String devEmail) {
        List<Ticket> tickets = ticketRepository.findAllByAssignedTo(devEmail);

        List<TicketResponseDTO> dtos = new ArrayList<>();
        for (Ticket ticket : tickets) {
            User user = userService.GetUserByEmail(ticket.getCreatedBy()); // assumes userEmail is saved in ticket
            dtos.add(TicketHelper.mapToResponse(ticket, user));
        }
        return dtos;
    }


    public List<TicketResponseDTO> getClosedTicket(String devEmail) {
        User developer = userService.GetUserByEmail(devEmail);
        List<TicketResponseDTO> devTics = ticketRepository.findAllByStatus(CLOSED);
        return devTics;
    }

//    public User assignTicket(addticketdto dto) {
//        Optional<Ticket> newTicket = ticketRepository.findById(tic.getId());
//        Application app = newTicket.get().getApp();
//
//        newTicket.get().setAssignedTo(developer.getId());
//        newTicket.get().setStatus(ASSIGNED);
//        return developer;
//    }

    public void notApproveTicket(Long ticketId) {
        Optional<Ticket> newTicket = ticketRepository.findById(ticketId);
        if (newTicket.get().getStatus() == SOLVED) {
            newTicket.get().setStatus(DELAYED);
            LocalDateTime now = LocalDateTime.now();
            newTicket.get().setNotApprovedAt(now);
            ticketRepository.save(newTicket.get());
        }
    }

    public Integer countSolvedTic(String email) {
        Integer total = ticketRepository.countByStatus(SOLVED, email);
        return total;
    }

    public Integer countAssignedTic(String email) {
        Integer total = ticketRepository.countByStatus(ASSIGNED, email);
        return total;
    }

    public Integer countClosedTic(String email) {
        Integer total = ticketRepository.countByStatus(CLOSED, email);
        return total;
    }

    public Integer countCancelledTic(String email) {
        Integer total = ticketRepository.countByStatus(CANCELLED, email);
        return total;
    }

    public Integer countProgressTic(String email) {
        Integer total = ticketRepository.countByStatus(IN_PROGRESS, email);
        return total;
    }

    public Integer countApprovedTic(String email) {
        Integer total = ticketRepository.countByStatus(APPROVED, email);
        return total;
    }

    public TicketStatusDetailsDTO getStatusDetails(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        TicketStatusDetailsDTO dto = new TicketStatusDetailsDTO();
        dto.setTicketId(ticket.getId());
        dto.setStatus(ticket.getStatus()); // ✅ Include the current status!
        dto.setAssignedAt(ticket.getAssignedAt());
        dto.setInProgressAt(ticket.getInProgressAt());
        dto.setSolvedAt(ticket.getSolvedAt());
        dto.setApprovedAt(ticket.getApprovedAt());
        dto.setNotApprovedAt(ticket.getNotApprovedAt());
        dto.setClosedAt(ticket.getClosedAt());
        dto.setCancelledAt(ticket.getCancelledAt());
        dto.setCreatedAt(ticket.getCreationDate());

        return dto;
    }

    public List<devOrTechDTO> getAllDevOrTechTicketCounts() {
        List<User> users = userService.getDevelopers(); // adjust role names as needed
        List<devOrTechDTO> result = new ArrayList<>();

        for (User user : users) {
            Long count = ticketRepository.countTicketsAssignedTo(user.getEmail());
            Long inpg = ticketRepository.countTicketsAssignedToByStatus(user.getEmail(), Status.IN_PROGRESS);
            Long delay = ticketRepository.countTicketsAssignedToByStatus(user.getEmail(), Status.DELAYED);
            Long inprogress = inpg + delay;
            String fullname = user.getFirstname() + " " + user.getLastname();
            result.add(new devOrTechDTO(fullname, user.getEmail(), count, inprogress));
        }

        return result;
    }



}
