package br.com.kyros.springproject.model;

import br.com.kyros.springproject.model.enums.FeedbackType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Feedback {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate ocurrenceDate;
    private LocalDate registrationDate = LocalDate.now();
    @Enumerated(EnumType.STRING)
    private FeedbackType feedbackType;
    @ManyToOne
    private Skill skill;
    private String description;
    @ManyToOne
    private Employee writer;
    @ManyToOne
    private Employee addressee;

    public Feedback(){ }

    public Feedback(LocalDate ocurrenceDate, FeedbackType feedbackType, Skill skill, String description, Employee writerEmployee, Employee adresseeEmployee){
        this.ocurrenceDate = ocurrenceDate;
        this.feedbackType = feedbackType;
        this.skill = skill;
        this.description = description;
        this.writer = writerEmployee;
        this.addressee = adresseeEmployee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getOcurrenceDate() {
        return ocurrenceDate;
    }

    public void setOcurrenceDate(LocalDate ocurrenceDate) {
        this.ocurrenceDate = ocurrenceDate;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public FeedbackType getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(FeedbackType feedbackType) {
        this.feedbackType = feedbackType;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Employee getWriter() {
        return writer;
    }

    public void setWriter(Employee writer) {
        this.writer = writer;
    }

    public Employee getAddressee() {
        return addressee;
    }

    public void setAddressee(Employee addressee) {
        this.addressee = addressee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(id, feedback.id) && Objects.equals(ocurrenceDate, feedback.ocurrenceDate) && Objects.equals(registrationDate, feedback.registrationDate) && feedbackType == feedback.feedbackType && Objects.equals(skill, feedback.skill) && Objects.equals(description, feedback.description) && Objects.equals(writer, feedback.writer) && Objects.equals(addressee, feedback.addressee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ocurrenceDate, registrationDate, feedbackType, skill, description, writer, addressee);
    }
}
