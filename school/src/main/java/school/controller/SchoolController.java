package school.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;
import school.model.dto.SchoolDto;
import school.service.SchoolService;

import java.util.HashMap;

@RestController
public class SchoolController {
    @Autowired
    SchoolService schoolService;

    @PostMapping("/school")
    public ResponseEntity<?> addSchool(@Valid @RequestBody SchoolDto schoolDto, BindingResult result) {
        if (result.hasErrors()) {
            HashMap<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        } else {
            return ResponseEntity.ok(schoolService.addSchool(schoolDto));
        }
    }
}
