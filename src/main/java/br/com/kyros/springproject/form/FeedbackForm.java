package br.com.kyros.springproject.form;

import br.com.kyros.springproject.model.Employee;
import br.com.kyros.springproject.model.Feedback;
import br.com.kyros.springproject.model.Skill;
import br.com.kyros.springproject.model.enums.FeedbackType;
import br.com.kyros.springproject.repository.EmployeeRepository;
import br.com.kyros.springproject.repository.FeedbackRepository;
import br.com.kyros.springproject.repository.SkillRepository;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Optional;

public class FeedbackForm {

    @DateTimeFormat
    private LocalDate ocurrenceDate;
    private FeedbackType feedbackType;
    @NotNull @NotEmpty
    private String skillName;
    @NotNull @NotEmpty
    private String description;
    @NotNull @NotEmpty
    private String writerRegistrationNumber;
    @NotNull @NotEmpty
    private String addresseeRegistrationNumber;

    public FeedbackForm(LocalDate ocurrenceDate, FeedbackType feedbackType, String skillName, String description, String writerRegistrationNumber, String addresseeRegistrationNumber) {
        this.ocurrenceDate = ocurrenceDate;
        this.feedbackType = feedbackType;
        this.skillName = skillName;
        this.description = description;
        this.writerRegistrationNumber = writerRegistrationNumber;
        this.addresseeRegistrationNumber = addresseeRegistrationNumber;
    }

    public Feedback convertToFeedback(EmployeeRepository employeeRepository, SkillRepository skillRepository) {
        Optional<Employee> writerEmployee = employeeRepository.findOneEmployeeByRegistrationNumber(writerRegistrationNumber);
        Optional<Employee> adresseeEmployee = employeeRepository.findOneEmployeeByRegistrationNumber(addresseeRegistrationNumber);
        Skill skill = skillRepository.findBySkillName(skillName);
        return new Feedback(ocurrenceDate, feedbackType, skill, description, writerEmployee.get(), adresseeEmployee.get());
    }

    public Feedback update(Long id, FeedbackRepository feedbackRepository, SkillRepository skillRepository, EmployeeRepository employeeRepository) {
        Feedback feedback = feedbackRepository.getOne(id);
        feedback.setOcurrenceDate(this.ocurrenceDate);
        feedback.setFeedbackType(this.feedbackType);
        feedback.setSkill(skillRepository.findBySkillName(this.skillName));
        feedback.setDescription(this.description);
        feedback.setWriter(employeeRepository.findOneEmployeeByRegistrationNumber(this.writerRegistrationNumber).get());
        feedback.setAddressee(employeeRepository.findOneEmployeeByRegistrationNumber(this.addresseeRegistrationNumber).get());
        return feedback;
    }

    public LocalDate getOcurrenceDate() {
        return ocurrenceDate;
    }

    public void setOcurrenceDate(LocalDate ocurrenceDate) {
        this.ocurrenceDate = ocurrenceDate;
    }

    public FeedbackType getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(FeedbackType feedbackType) {
        this.feedbackType = feedbackType;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWriterRegistrationNumber() {
        return writerRegistrationNumber;
    }

    public void setWriterRegistrationNumber(String writerRegistrationNumber) {
        this.writerRegistrationNumber = writerRegistrationNumber;
    }

    public String getAddresseeRegistrationNumber() {
        return addresseeRegistrationNumber;
    }

    public void setAddresseeRegistrationNumber(String addresseeRegistrationNumber) {
        this.addresseeRegistrationNumber = addresseeRegistrationNumber;
    }
}
