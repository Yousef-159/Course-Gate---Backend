package org.jo.studentback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/admins")
public class adminController {

    @Autowired
    private adminRepository repository;


    @GetMapping
    public List<admin> getAllAdmins() {
        return repository.findAll();
    }



    @PostMapping
    public ResponseEntity<?> addAdmin(@RequestBody admin admin) {
        try {
            admin saved = repository.save(admin);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody admin loginRequest) {
        admin found = repository.findByCode(loginRequest.getCode());

        if (found != null && found.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.ok("Login successful: " + found.getName());
        } else {
            return ResponseEntity.status(401).body("Invalid code or password");
        }
    }
}
