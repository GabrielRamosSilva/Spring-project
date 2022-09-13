package br.com.kyros.springproject.service;

import br.com.kyros.springproject.model.Feedback;
import org.springframework.data.domain.Page;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

public class FeedbackService {

    private EntityManager em;

    public List<Feedback> findFeedbacksFromTheLastYear() {
        LocalDate fromTheLastYear = LocalDate.now().minusYears(1);
        return em.createQuery("SELECT f FROM Feedback WHERE f.ocurrenceDate > :fromTheLastYear", Feedback.class)
                .setParameter("fromTheLastYear", fromTheLastYear)
                .getResultList();
    }

}
