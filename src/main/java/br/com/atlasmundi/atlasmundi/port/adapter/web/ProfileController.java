package br.com.atlasmundi.atlasmundi.port.adapter.web;

import br.com.atlasmundi.atlasmundi.application.command.ProfileCommand;
import br.com.atlasmundi.atlasmundi.application.profile.ProfileApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/v1/profiles")
@Api(tags = "Profile Controller", value = "ProfileCommand", description = "Controller for Profiles")
public class ProfileController {

    private final ProfileApplicationService profileApplicationService;

    public ProfileController(ProfileApplicationService profileApplicationService) {
        this.profileApplicationService = profileApplicationService;
    }

    @PostMapping
    @ApiOperation(value = "Create a profile using profile command.",
            produces = "application/json", code = 201)
    public ResponseEntity<Void> createProfile(@RequestBody @Valid @ApiParam ProfileCommand command) {
        var id = profileApplicationService.createAccount(command);
        return ResponseEntity.created(URI.create("/v1/profiles/".concat(id.toString()))).build();
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<Object> getProfileDetail() {
        return new ResponseEntity("unavailable ...", HttpStatus.OK);
    }
}
