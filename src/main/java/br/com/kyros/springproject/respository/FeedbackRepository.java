package br.com.kyros.springproject.respository;

import br.com.kyros.springproject.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {



}
