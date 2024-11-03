package com.farming.system.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.farming.system.Model.PersonalInfo;
import com.farming.system.Service.PersonalInfoService;

import java.util.Optional;

@RestController
@RequestMapping("/api/user/personalInfo")
public class PersonalInfoController {

    @Autowired
    private PersonalInfoService personalInfoService;

    @PostMapping("/create")
    public ResponseEntity<PersonalInfo> createPersonalInfo(@RequestBody PersonalInfo personalInfo) {
        PersonalInfo savedInfo = personalInfoService.createPersonalInfo(personalInfo);
        return ResponseEntity.ok(savedInfo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonalInfo> getPersonalInfo(@PathVariable Long id) {
        Optional<PersonalInfo> info = personalInfoService.getPersonalInfo(id);
        return info.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonalInfo> updatePersonalInfo(@PathVariable Long id, @RequestBody PersonalInfo personalInfoDetails) {
        Optional<PersonalInfo> updatedInfo = personalInfoService.updatePersonalInfo(id, personalInfoDetails);
        return updatedInfo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonalInfo(@PathVariable Long id) {
        boolean isDeleted = personalInfoService.deletePersonalInfo(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
