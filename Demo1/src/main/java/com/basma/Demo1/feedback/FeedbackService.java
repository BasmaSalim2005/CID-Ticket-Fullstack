package com.basma.Demo1.feedback;

import com.basma.Demo1.application.AppService;
import com.basma.Demo1.application.Application;
import com.basma.Demo1.feedback.FeedbackDTO.FeedbackResponseDTO;
import com.basma.Demo1.feedback.FeedbackDTO.SummaryDTO;
import com.basma.Demo1.feedback.FeedbackDTO.addFeedbackDTO;
import com.basma.Demo1.feedback.FeedbackDTO.updateFeedbackDTO;
import com.basma.Demo1.notifications.smtp.EmailService;
import com.basma.Demo1.user.User;
import com.basma.Demo1.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final EmailService emailService;
    private final UserService userservice;
    private final AppService appservice;

    public FeedbackResponseDTO addFeedback(addFeedbackDTO dto) {
        User user = userservice.GetUserByEmail(dto.getUserEmail());
        Application app= appservice.getAppByNameft(dto.getAppName());

        Feedback newFeedback = FeedbackHelper.mapToEntity(dto);
        newFeedback.setUser(user);
        newFeedback.setApp(app);
        Feedback savedFeedback = feedbackRepository.save(newFeedback);
        // Send notification email
        emailService.sendSimpleEmail(
                "basmanasma2005@gmail.com",
            "Feedback Added",
            "A new feedback has been added."
        );
        return FeedbackHelper.mapToResponse(savedFeedback);
    }

    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    public FeedbackResponseDTO getFeedbackById(Long id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));
        return FeedbackHelper.mapToResponse(feedback);
    }

    public FeedbackResponseDTO updateFeedback(Long id, updateFeedbackDTO dto) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));
        FeedbackHelper.mapToEntityForUpdate(dto,feedback);
        Feedback updatedFeedback = feedbackRepository.save(feedback);
        // Send notification email
        emailService.sendSimpleEmail(
                "basmanasma2005@gmail.com",
            "Feedback Updated",
            "Feedback with ID " + id + " has been updated."
        );
        return FeedbackHelper.mapToResponse(updatedFeedback);
    }

    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
        // Send notification email
        emailService.sendSimpleEmail(
                "basmanasma2005@gmail.com",
            "Feedback Deleted",
            "Feedback with ID " + id + " has been deleted."
        );
    }

    public List<SummaryDTO> getFeedbacksByApps(Long appId){
        List<SummaryDTO> rawList = feedbackRepository.getFeedbackRawByAppId(appId);
//        List<SummaryDTO> summaries = FeedbackHelper.mapToResponseList(rawList);
        return rawList;
    }
}
