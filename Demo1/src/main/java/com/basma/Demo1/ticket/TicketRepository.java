package com.basma.Demo1.ticket;

import com.basma.Demo1.ticket.TicDTO.TicketResponseDTO;
import com.basma.Demo1.ticket.enums.Status;
import com.basma.Demo1.user.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {




    Optional<Ticket> findById(Long id);
    Ticket findByUserEmail( String email);
    List<Ticket> findAllByUserEmail(String email);
    @Query("SELECT COUNT(t) FROM Ticket t JOIN t.user u WHERE u.email = :email AND t.status = :status")
    Integer countByStatus(Status status, String email);

    Optional<Object> findByTitle(String title);

    List<Ticket> findAllByAssignedTo(String developer);

    List<TicketResponseDTO> findAllByStatus(Status status);
    @Query("SELECT COUNT(t) FROM Ticket t WHERE t.assignedTo = :email")
    Long countTicketsAssignedTo(String email);
    @Query("SELECT COUNT(t) FROM Ticket t WHERE t.assignedTo = :email AND t.status = :status")
    Long countTicketsAssignedToByStatus(@Param("email") String email, @Param("status") Status status);

}
