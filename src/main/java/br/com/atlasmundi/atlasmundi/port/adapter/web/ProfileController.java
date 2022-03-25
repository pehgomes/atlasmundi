package br.com.atlasmundi.atlasmundi.port.adapter.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/profiles")
public class ProfileController {

    @GetMapping
    public ResponseEntity<Void> getTeste() {

        return ResponseEntity.ok().build();
    }
}
