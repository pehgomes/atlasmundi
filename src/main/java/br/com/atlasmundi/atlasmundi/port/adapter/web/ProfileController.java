package br.com.atlasmundi.atlasmundi.port.adapter.web;

import br.com.atlasmundi.atlasmundi.application.command.ProfileCommand;
import br.com.atlasmundi.atlasmundi.application.profile.ProfileApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/v1/profiles")
public class ProfileController {

    private final ProfileApplicationService profileApplicationService;

    public ProfileController(ProfileApplicationService profileApplicationService) {
        this.profileApplicationService = profileApplicationService;
    }

    @GetMapping("/accounts")
    public ResponseEntity<Void> logged() {
        return new ResponseEntity("logged", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createProfile(@RequestBody @Valid ProfileCommand command) {
        var id = profileApplicationService.createAccount(command);
        return ResponseEntity.created(URI.create("/v1/profiles/".concat(id.toString()))).build();
    }
}
