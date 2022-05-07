package br.com.atlasmundi.atlasmundi.port.adapter.web;

import br.com.atlasmundi.atlasmundi.domain.FriendId;
import br.com.atlasmundi.atlasmundi.domain.InviteId;
import br.com.atlasmundi.atlasmundi.domain.ProfileId;
import br.com.atlasmundi.atlasmundi.domain.enumeration.InviteStatus;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@Value
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class ProfileData {

    ProfileId profileId;
    String login;
    String taxId;
    String name;
    OffsetDateTime birthDate;
    String phoneNumber;
    List<FriendData> friends;
    List<InviteData> invites;

    @Value
    @ToString
    @EqualsAndHashCode
    @AllArgsConstructor
    @NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
    public static class FriendData {
        FriendId friendId;
        String name;
        Boolean online;
    }

    @Value
    @ToString
    @EqualsAndHashCode
    @AllArgsConstructor
    @NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
    public static class InviteData {
        InviteId inviteId;
        String requesterName;
        InviteStatus status;
    }
}
