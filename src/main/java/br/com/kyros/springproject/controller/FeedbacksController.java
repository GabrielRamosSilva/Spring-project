package br.com.kyros.springproject.controller;

import br.com.kyros.springproject.dto.FeedbackDto;
import br.com.kyros.springproject.form.FeedbackForm;
import br.com.kyros.springproject.model.Feedback;
import br.com.kyros.springproject.repository.EmployeeRepository;
import br.com.kyros.springproject.repository.FeedbackRepository;
import br.com.kyros.springproject.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/feedbacks")
public class FeedbacksController {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SkillRepository skillRepository;

    @GetMapping("/")
    public List<FeedbackDto> feedbacksList() {
        List<Feedback> feedbacks = feedbackRepository.findByOrderByRegistrationDateDesc();
        return FeedbackDto.convertToDto(feedbacks);
    }

    @GetMapping("/byEmployee")
    public List<FeedbackDto> feedbacksByEmployee(@RequestParam String adresseNumber) {
        List<Feedback> feedbacks = feedbackRepository.findByAdresseeNumber(adresseNumber);
        return FeedbackDto.convertToDto(feedbacks);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<FeedbackDto> saveFeedback(@RequestBody @Valid FeedbackForm feedbackForm, UriComponentsBuilder uriBuilder) {
        Feedback feedback = feedbackForm.convertToFeedback(employeeRepository, skillRepository);
        if (feedback.getWriter().getRegistrationNumber() == feedback.getAddressee().getLeader().getRegistrationNumber()) {
            feedbackRepository.save(feedback);
            URI uri = uriBuilder.path("/feedbacks/{id}").buildAndExpand(feedback.getId()).toUri();
            return ResponseEntity.created(uri).body(new FeedbackDto(feedback));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteFeedback(@PathVariable Long id) {
        Optional<Feedback> optional = feedbackRepository.findById(id);
        if (optional.isPresent()) {
            employeeRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
