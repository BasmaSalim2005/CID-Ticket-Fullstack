package com.basma.Demo1.ticket;

import com.basma.Demo1.ticket.TicDTO.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/demo1/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @PostMapping("/add")
    protected TicketResponseDTO addTicket(@RequestBody @Valid addticketdto dto){
        TicketResponseDTO apiResponse = ticketService.addTicket(dto);
        return apiResponse;
    }

    @PutMapping("/update/{id}")
    public TicketResponseDTO updateTicket(@PathVariable Long id, @RequestBody @Valid updateticketdto dto) {
        return ticketService.UpdateTicket(id, dto);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
    }
    @GetMapping("/getall")
    public List<Ticket> getAll(){
        return ticketService.getAll();
    }
    @GetMapping("/getbyid/{id}")
    public Optional<Ticket> getById(@PathVariable Long id){
        return ticketService.GetTicketById(id);
    }
    @GetMapping("/getbyemail/{userEmail}")
    public List<Ticket> getByUserEmail(@PathVariable String userEmail){
        return ticketService.GetTicketByUserEmail(userEmail);
    }
    @PutMapping("/approve/{id}")
    public void approveTic(@PathVariable Long id){
        ticketService.approveTicket(id);
    }
    @PutMapping("/close/{id}")
    public void closeTic(@PathVariable Long id){
        ticketService.closeTicket(id);
    }
    @PutMapping("/notapprove/{id}")
    public void notapproveTic(@PathVariable Long id){ticketService.notApproveTicket(id);}
    @PutMapping("/solve/{id}/{sol}")
    public void solveTic(@PathVariable Long id,@PathVariable String sol){ticketService.solveTicket(id, sol);}
    @PutMapping("/inprogress/{id}")
    public void inProgressTic(@PathVariable Long id){
        ticketService.ProgressTicket(id);
    }

    @GetMapping("/count/approve")
    public Integer countApproved(@RequestParam String email){
        return ticketService.countApprovedTic(email);
    }
    @GetMapping("/count/solve")
    public Integer countSolved(@RequestParam String email){
        return ticketService.countSolvedTic(email);
    }
    @GetMapping("/count/assigne")
    public Integer countAssigned(@RequestParam String email){
        return ticketService.countAssignedTic(email);
    }
    @GetMapping("/count/close")
    public Integer countClosed(@RequestParam String email){
        return ticketService.countClosedTic(email);
    }
    @GetMapping("/count/cancel")
    public Integer countCancelled(@RequestParam String email){
        return ticketService.countCancelledTic(email);
    }
    @GetMapping("/count/inprogress")
    public Integer countInprogress(@RequestParam String email){
        return ticketService.countProgressTic(email);
    }
//    @PostMapping("/assign")
//    public User assignToDev(@RequestBody @Valid addticketdto dto){
//        return ticketService.assignTicket(dto);
//    }
    @GetMapping("/status/detail/{id}")
    public TicketStatusDetailsDTO getStatusDetails(@PathVariable Long id){
        return ticketService.getStatusDetails(id);
    }
    @GetMapping("/assignedto/{devEmail}")
    public List<TicketResponseDTO> getAssingedTic(@PathVariable String devEmail){
        return ticketService.getAssignedTicket(devEmail);
    }
    @GetMapping("/getclosed/{devEmail}")
    public List<TicketResponseDTO> getClosedTic(@PathVariable String devEmail){
        return ticketService.getClosedTicket(devEmail);
    }

    @GetMapping("/assigned/count/all")
    public List<devOrTechDTO> getAllAssignedTicketCounts() {
        List<devOrTechDTO> result = ticketService.getAllDevOrTechTicketCounts();
        return result;
    }

    @PutMapping("/cancel/{id}")
    public void cancelTic (@PathVariable Long id ){
        ticketService.cancelTicket(id);
    }

}
