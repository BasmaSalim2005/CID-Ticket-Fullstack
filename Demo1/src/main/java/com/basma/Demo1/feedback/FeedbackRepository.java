package com.basma.Demo1.feedback;

import com.basma.Demo1.feedback.FeedbackDTO.SummaryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
//    @Query("SELECT u.lastname, u.firstname, u.email, " +
//            "f.usability, f.Satisfaction, f.performance, f.design, f.reliability, f.overall, f.comment " +
//            "FROM Feedback f JOIN f.user u WHERE f.app.id = :appId")
//    List<Feedback> getFeedbackRawByAppId(@Param("appId") Long appId);
@Query("SELECT new com.basma.Demo1.feedback.FeedbackDTO.SummaryDTO(u.lastname, u.firstname, u.email, " +
        "f.usability, f.Satisfaction, f.performance, f.design, f.reliability, f.overall, f.comment) " +
        "FROM Feedback f JOIN f.user u WHERE f.app.id = :appId")
List<SummaryDTO> getFeedbackRawByAppId(@Param("appId") Long appId);


}
