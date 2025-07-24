package com.basma.Demo1.application;

import com.basma.Demo1.application.enums.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AppRepository extends JpaRepository<Application, Long> {
    Optional<Application> findByName(String name);

    List<Application> findByCreationDate(LocalDateTime creationDate);
    @Query("SELECT count(*) from Application a")
    int countApplications();
    @Query("SELECT count(*) from Application a join a.Developers d WHERE d.email = :email")
    int countApplicationsForDeveloper(@Param("email") String email);

    @Query("SELECT count(*) FROM Application a WHERE a.state = 'ACTIVE'")
    int countAppsByActive();

    @Query("SELECT count(*) FROM Application a WHERE a.state = 'DISACTIVE'")
    int countAppsByDisactive();

    @Query("SELECT count(*) FROM Application a WHERE a.state = 'INMAINTENANCE'")
    int countAppsMaintenance();

    @Query("SELECT COUNT(a) FROM Application a JOIN a.Developers u WHERE u.email = :email AND a.state = :state")
    Integer countByStateAndUserEmail(@Param("state") State state, @Param("email") String email);


    @Query("SELECT a FROM Application a JOIN a.Developers u WHERE u.email = :email")
    Page<Application> findByUserEmail(@Param("email") String userEmail, Pageable pageable);
}
