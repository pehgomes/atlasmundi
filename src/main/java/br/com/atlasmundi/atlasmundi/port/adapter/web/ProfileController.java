package br.com.atlasmundi.atlasmundi.port.adapter.web;

import br.com.atlasmundi.atlasmundi.application.command.ProfileCommand;
import br.com.atlasmundi.atlasmundi.application.profile.ProfileApplicationService;
import br.com.atlasmundi.atlasmundi.domain.InviteId;
import br.com.atlasmundi.atlasmundi.domain.ProfileId;
import br.com.atlasmundi.atlasmundi.domain.enumeration.InviteStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @ApiOperation(value = "Return profile detail.")
    public ResponseEntity<ProfileData> getProfileDetail(@PathVariable ProfileId profileId) {
        var profileData = profileApplicationService.getProfileDetail(profileId);
        return ResponseEntity.ok(profileData);
    }

    @PutMapping("/{profileId}")
    @ApiOperation(value = "Update Profile data.")
    public ResponseEntity<Void> updateProfile(@PathVariable ProfileId profileId,
                                              @RequestBody @Valid @ApiParam ProfileCommand command) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{profileId}/invites/{inviteId}/accept")
    @ApiOperation(value = "Accept a invite.",
            produces = "application/json", code = 201)
    public ResponseEntity<Void> acceptInvite(@PathVariable ProfileId profileId, @PathVariable InviteId inviteId) {
        var id = profileApplicationService.deferInvite(profileId, inviteId, InviteStatus.ACCEPTED);
        return ResponseEntity.created(URI.create("/v1/profiles/".concat(profileId.uuid().toString())
                .concat("/invites/").concat(id.uuid().toString()).concat("/accept"))).build();
    }

    @PostMapping("/{profileId}/invites/{inviteId}/refuse")
    @ApiOperation(value = "Refuse a invite.",
            produces = "application/json", code = 201)
    public ResponseEntity<Void> refuseInvite(@PathVariable ProfileId profileId, @PathVariable InviteId inviteId) {
        var id = profileApplicationService.deferInvite(profileId, inviteId, InviteStatus.ACCEPTED);
        return ResponseEntity.created(URI.create("/v1/profiles/".concat(profileId.uuid().toString())
                .concat("/invites/").concat(id.uuid().toString()).concat("/refuse"))).build();
    }

    @PutMapping("/{profileId}/location")
    @ApiOperation(value = "Update profile with last location.",
            produces = "application/json")
    public ResponseEntity<Void> setLocation(
            @PathVariable ProfileId profileId,
            @RequestBody @Valid @ApiParam ProfileCommand.ProfileCurrentLocation command) {
            profileApplicationService.setLastLocation(profileId, command);
        return ResponseEntity.ok().build();
    }

}