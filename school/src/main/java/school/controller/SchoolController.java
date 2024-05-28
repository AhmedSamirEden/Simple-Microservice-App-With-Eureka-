package school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import school.feign.FeignClientSchool;
import school.mapper.SchoolMapper;
import school.model.dto.SchoolDto;
import school.model.entity.School;
import school.service.SchoolService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @GetMapping("/schools")
    List<School> getAllSchools() {
        return schoolService.getAllSchools().stream().map(schoolDto -> SchoolMapper.INSTANCE.dtoToSchool(schoolDto)).collect(Collectors.toList());
    }

    @GetMapping("/school/{id}")
    public Optional<School> getSchoolById(@PathVariable Long id) {
        return schoolService.getSchool(id).map(schoolDto -> SchoolMapper.INSTANCE.dtoToSchool(schoolDto));
    }

    @PutMapping("/school/{id}")
    public ResponseEntity<?> updateSchool(@PathVariable Long id, @RequestBody SchoolDto schoolDto, BindingResult result) {
        if (result.hasErrors()) {
            HashMap errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        } else {
            return ResponseEntity.ok(schoolService.updateSchool(id, schoolDto));
        }
    }

    @GetMapping("/school/students/names")
    public List<String> getAllNames() {
        return schoolService.getAllNames();
    }

}
