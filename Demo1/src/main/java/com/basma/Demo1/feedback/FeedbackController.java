package com.basma.Demo1.feedback;

import com.basma.Demo1.feedback.FeedbackDTO.FeedbackResponseDTO;
import com.basma.Demo1.feedback.FeedbackDTO.SummaryDTO;
import com.basma.Demo1.feedback.FeedbackDTO.addFeedbackDTO;
import com.basma.Demo1.feedback.FeedbackDTO.updateFeedbackDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/demo1/feedback")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;

    @PostMapping("/add")
    public FeedbackResponseDTO addFeedback(@RequestBody addFeedbackDTO dto) {
        return feedbackService.addFeedback(dto);
    }

    @GetMapping("/all")
    public List<Feedback> getAllFeedback() {
        return feedbackService.getAllFeedback();
    }

    @GetMapping("/{id}")
    public FeedbackResponseDTO getFeedbackById(@PathVariable Long id) {
        return feedbackService.getFeedbackById(id);
    }

    @GetMapping("/byapp/{id}")
    public List<SummaryDTO> getFeedbackByApp(@PathVariable Long id) {
        return feedbackService.getFeedbacksByApps(id);
    }


    @PutMapping("/update/{id}")
    public FeedbackResponseDTO updateFeedback(@PathVariable Long id, @RequestBody updateFeedbackDTO dto) {
        return feedbackService.updateFeedback(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
    }


}
