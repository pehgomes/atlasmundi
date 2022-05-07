package br.com.atlasmundi.atlasmundi.port.adapter.web;

import br.com.atlasmundi.atlasmundi.application.command.FriendCommand;
import br.com.atlasmundi.atlasmundi.application.friend.FriendApplicationService;
import br.com.atlasmundi.atlasmundi.application.profile.ProfileApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/v1/invites")
@Api(tags = "Invite Controller")
public class InviteController {

    private final ProfileApplicationService profileApplicationService;
    private final FriendApplicationService friendApplicationService;

    public InviteController(ProfileApplicationService profileApplicationService,
                            FriendApplicationService friendApplicationService) {
        this.profileApplicationService = profileApplicationService;
        this.friendApplicationService = friendApplicationService;
    }

    @PostMapping
    @ApiOperation(value = "Invite a friend.", produces = "application/json", code = 201)
    public ResponseEntity<Void> createProfile(@RequestBody FriendCommand friendCommand) {
        var invite = friendApplicationService.inviteAFriend(friendCommand);
        return ResponseEntity.created(URI.create("/v1/invites/".concat(invite.uuid().toString()))).build();
    }

}
