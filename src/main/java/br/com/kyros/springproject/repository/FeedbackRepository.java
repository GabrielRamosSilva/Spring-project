package br.com.kyros.springproject.repository;

import br.com.kyros.springproject.model.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    @Query("SELECT f FROM Feedback f WHERE f.addressee.registrationNumber = :registrationNumber AND f.ocurrenceDate > :fromTheLastYear")
    Page<Feedback> findByAdresseeNumber(@Param("registrationNumber") String adresseeNumber, @Param("fromTheLastYear") LocalDate fromTheLastYear, Pageable pageable);

    Page<Feedback> findByOrderByRegistrationDateDesc(Pageable pageable);

}
