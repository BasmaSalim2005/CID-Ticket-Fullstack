package com.basma.Demo1.feedback;

import com.basma.Demo1.exceptions.FeedbackAlreadyExistsException;
import com.basma.Demo1.exceptions.FeedbackNotFoundException;
import com.basma.Demo1.feedback.FeedbackDTO.FeedbackResponseDTO;
import com.basma.Demo1.feedback.FeedbackDTO.SummaryDTO;
import com.basma.Demo1.feedback.FeedbackDTO.addFeedbackDTO;

import com.basma.Demo1.feedback.FeedbackDTO.updateFeedbackDTO;
import com.basma.Demo1.ticket.TicDTO.updateticketdto;
import com.basma.Demo1.ticket.Ticket;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FeedbackHelper {
    protected static void displayFeedbackNotFoundException(Feedback existingFeedback) {
        if (existingFeedback == null) {
            String message = "Feedback Not Found!";
            throw new FeedbackNotFoundException(message);
        }
    }

    protected static void displayFeedbackAlreadyExistsException(Feedback newFeedback) {
        if (newFeedback != null) {
            String message = "Feedback Already Exists!";
            throw new FeedbackAlreadyExistsException(message);
        }
    }
    protected static Feedback mapToEntity(addFeedbackDTO dto){
        Feedback newFeedback = new Feedback();
//        newFeedback.setType(dto.getType());
        newFeedback.setComment(dto.getComment());
        newFeedback.setDesign(dto.getDesign());
        newFeedback.setPerformance(dto.getPerformance());
        newFeedback.setReliability(dto.getReliability());
        newFeedback.setUsability(dto.getUsability());
        newFeedback.setSatisfaction(dto.getSatisfaction());

        // Calculate overall: simple average (or weighted if you want)
        Integer sum = dto.getUsability() + dto.getSatisfaction() +
                dto.getPerformance() + dto.getDesign() +
                dto.getReliability();
        Integer average = Math.round(sum / 5.0f);  // float is fine too
        newFeedback.setOverall(average);

        LocalDateTime now = LocalDateTime.now();
        newFeedback.setCreationDate(now);


        return newFeedback;
    }

//    protected static List<SummaryDTO> mapToResponseList(List<Feedback> feedbackList) {
//        List<SummaryDTO> resultList = new ArrayList<>();
//
//        for (Feedback feedback : feedbackList) {
//            SummaryDTO dto = mapToResponseSum(feedback);
//            resultList.add(dto);
//        }
//
//        return resultList;
//    }

    protected static FeedbackResponseDTO mapToResponse (Feedback updatedFeedback){
        FeedbackResponseDTO formattedFeedback = new FeedbackResponseDTO();
//        formattedFeedback.setType(updatedFeedback.getType());
        formattedFeedback.setComment(updatedFeedback.getComment());
        formattedFeedback.setDesign(updatedFeedback.getDesign());
        formattedFeedback.setPerformance(updatedFeedback.getPerformance());
        formattedFeedback.setReliability(updatedFeedback.getReliability());
        formattedFeedback.setUsability(updatedFeedback.getUsability());
        formattedFeedback.setSatisfaction(updatedFeedback.getSatisfaction());


        formattedFeedback.setOverall(updatedFeedback.getOverall());

        formattedFeedback.setCreationDate(updatedFeedback.getCreationDate());

        return formattedFeedback;
    }
//    protected static SummaryDTO mapToResponseSum (Feedback updatedFeedback){
//        SummaryDTO formattedFeedback = new SummaryDTO();
////        formattedFeedback.setType(updatedFeedback.getType());
//        formattedFeedback.setComment(updatedFeedback.getComment());
//        formattedFeedback.setDesign(updatedFeedback.getDesign());
//        formattedFeedback.setPerformance(updatedFeedback.getPerformance());
//        formattedFeedback.setReliability(updatedFeedback.getReliability());
//        formattedFeedback.setUsability(updatedFeedback.getUsability());
//        formattedFeedback.setSatisfaction(updatedFeedback.getSatisfaction());
//
//
//        formattedFeedback.setOverall(updatedFeedback.getOverall());
//
//        formattedFeedback.setCreationDate(updatedFeedback.getCreationDate());
//
//        return formattedFeedback;
//    }
    protected static FeedbackResponseDTO mapToResponseForUpdate(Feedback updatedFeedback){
        FeedbackResponseDTO formattedFeedback = new FeedbackResponseDTO();
        formattedFeedback.setCreationDate(updatedFeedback.getCreationDate());
//        formattedFeedback.setType(updatedFeedback.getType());
        formattedFeedback.setComment(updatedFeedback.getComment());
        formattedFeedback.setDesign(updatedFeedback.getDesign());
        formattedFeedback.setPerformance(updatedFeedback.getPerformance());
        formattedFeedback.setReliability(updatedFeedback.getReliability());
        formattedFeedback.setUsability(updatedFeedback.getUsability());
        formattedFeedback.setSatisfaction(updatedFeedback.getSatisfaction());
        // Calculate overall: simple average (or weighted if you want)
        Integer sum = updatedFeedback.getUsability() + updatedFeedback.getSatisfaction() +
                updatedFeedback.getPerformance() + updatedFeedback.getDesign() +
                updatedFeedback.getReliability();
        Integer average = Math.round(sum / 5.0f);  // float is fine too
        formattedFeedback.setOverall(average);

        return formattedFeedback;
    }
    protected static void mapToEntityForUpdate(updateFeedbackDTO dto, Feedback newFeedback){
//        newFeedback.setType(dto.getType());
        newFeedback.setComment(dto.getComment());
        newFeedback.setDesign(dto.getDesign());
        newFeedback.setPerformance(dto.getPerformance());
        newFeedback.setReliability(dto.getReliability());
        newFeedback.setUsability(dto.getUsability());
        newFeedback.setSatisfaction(dto.getSatisfaction());
        // Calculate overall: simple average (or weighted if you want)
        Integer sum = dto.getUsability() + dto.getSatisfaction() +
                dto.getPerformance() + dto.getDesign() +
                dto.getReliability();
        Integer average = Math.round(sum / 5.0f);  // float is fine too
        dto.setOverall(average);
    }
}
