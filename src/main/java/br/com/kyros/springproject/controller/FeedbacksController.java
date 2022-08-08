package br.com.kyros.springproject.controller;

import br.com.kyros.springproject.dto.FeedbackDto;
import br.com.kyros.springproject.form.FeedbackForm;
import br.com.kyros.springproject.model.Employee;
import br.com.kyros.springproject.model.Feedback;
import br.com.kyros.springproject.respository.EmployeeRepository;
import br.com.kyros.springproject.respository.FeedbackRepository;
import br.com.kyros.springproject.respository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
        List<Feedback> feedbacks = feedbackRepository.findAll();
        return FeedbackDto.convertToDto(feedbacks);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<FeedbackDto> saveFeedback(@RequestBody @Valid FeedbackForm feedbackForm, UriComponentsBuilder uriBuilder) {
        Feedback feedback = feedbackForm.convertToFeedback(employeeRepository, skillRepository);
        feedbackRepository.save(feedback);
        URI uri = uriBuilder.path("/feedbacks/{id}").buildAndExpand(feedback.getId()).toUri();
        return ResponseEntity.created(uri).body(new FeedbackDto(feedback));
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<FeedbackDto> updateFeedback(@PathVariable Long id, @RequestBody @Valid FeedbackForm form) {
        Optional<Feedback> optional = feedbackRepository.findById(id);
        if (optional.isPresent()) {
            Feedback feedback = form.update(id, feedbackRepository, skillRepository, employeeRepository);
            return ResponseEntity.ok(new FeedbackDto(feedback));
        }
        return ResponseEntity.notFound().build();
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
