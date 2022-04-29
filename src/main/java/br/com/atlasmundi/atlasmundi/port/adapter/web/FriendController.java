package br.com.atlasmundi.atlasmundi.port.adapter.web;

import br.com.atlasmundi.atlasmundi.application.profile.ProfileApplicationService;
import br.com.atlasmundi.atlasmundi.domain.ProfileId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/v1/friends")
@Api(tags = "Friend Controller")
public class FriendController {

    private final ProfileApplicationService profileApplicationService;

    public FriendController(ProfileApplicationService profileApplicationService) {
        this.profileApplicationService = profileApplicationService;
    }

    @PostMapping("/{friendId}")
    @ApiOperation(value = "Invite a friend.",
            produces = "application/json", code = 201)
    public ResponseEntity<Void> createProfile(@PathVariable ProfileId profileId) {

        return ResponseEntity.created(URI.create("/v1/friend/".concat(""))).build();
    }

}
