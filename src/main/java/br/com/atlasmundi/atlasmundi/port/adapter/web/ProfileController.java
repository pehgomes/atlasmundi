package br.com.atlasmundi.atlasmundi.port.adapter.web;

import br.com.atlasmundi.atlasmundi.application.command.ProfileCommand;
import br.com.atlasmundi.atlasmundi.application.profile.ProfileApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/v1/profiles")
public class ProfileController {

    private final ProfileApplicationService profileApplicationService;

    public ProfileController(ProfileApplicationService profileApplicationService) {
        this.profileApplicationService = profileApplicationService;
    }

    @GetMapping
    public ResponseEntity<Void> getTest() {

        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Void> createProfile(@RequestBody ProfileCommand command) {
        var id = profileApplicationService.createAccount(command);
        return ResponseEntity.created(URI.create("/v1/profiles/".concat(id.toString()))).build();
    }
}
