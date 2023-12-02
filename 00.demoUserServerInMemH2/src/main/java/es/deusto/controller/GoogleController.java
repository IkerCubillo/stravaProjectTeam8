package es.deusto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import es.deusto.service.GoogleServiceImpl;

import java.rmi.RemoteException;

@RestController
@RequestMapping("/google")
public class GoogleController {

    @Autowired
    private GoogleServiceImpl googleService;

    @GetMapping("/validateUser")
    public ResponseEntity<Boolean> validateUser(@RequestParam String email) {
        try {
            boolean result = googleService.googleUserValidation(email);
            return ResponseEntity.ok(result);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(false);
        }
    }

    @GetMapping("/validatePassword")
    public ResponseEntity<Boolean> validatePassword(@RequestParam String email, @RequestParam String password) {
        try {
            boolean result = googleService.googlePasswordValidation(email, password);
            return ResponseEntity.ok(result);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(false);
        }
    }
}