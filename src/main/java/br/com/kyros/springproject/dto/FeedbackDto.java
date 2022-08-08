package br.com.kyros.springproject.dto;

import br.com.kyros.springproject.model.Employee;
import br.com.kyros.springproject.model.Feedback;
import br.com.kyros.springproject.model.Skill;
import br.com.kyros.springproject.model.enums.FeedbackType;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class FeedbackDto {

    private Long id;
    private LocalDate ocurrenceDate;
    private LocalDate registrationDate = LocalDate.now();
    private FeedbackType feedbackType;
    private Skill skill;
    private String description;
    private Employee writer;
    private Employee addressee;

    public FeedbackDto(Feedback feedback) {
        this.id = feedback.getId();
        this.ocurrenceDate = feedback.getOcurrenceDate();
        this.registrationDate = feedback.getRegistrationDate();
        this.feedbackType = feedback.getFeedbackType();
        this.skill = feedback.getSkill();
        this.description = feedback.getDescription();
        this.writer = feedback.getWriter();
        this.addressee = feedback.getAddressee();
    }

    public static List<FeedbackDto> convertToDto(List<Feedback> feedbacks) {
        return feedbacks.stream().map(FeedbackDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public LocalDate getOcurrenceDate() {
        return ocurrenceDate;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public FeedbackType getFeedbackType() {
        return feedbackType;
    }

    public Skill getSkill() {
        return skill;
    }

    public String getDescription() {
        return description;
    }

    public Employee getWriter() {
        return writer;
    }

    public Employee getAddressee() {
        return addressee;
    }
}
