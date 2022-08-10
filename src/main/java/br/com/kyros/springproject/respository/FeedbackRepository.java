package br.com.kyros.springproject.respository;

import br.com.kyros.springproject.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    @Query("SELECT f FROM Feedback f WHERE f.addressee.registrationNumber = :registrationNumber")
    List<Feedback> findByAdresseeNumber(@Param("registrationNumber") String adresseeNumber);

    List<Feedback> findByOrderByRegistrationDateDesc();

}
