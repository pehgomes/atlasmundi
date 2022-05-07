package br.com.atlasmundi.atlasmundi.domain;

import br.com.atlasmundi.atlasmundi.domain.enumeration.InviteStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

import static javax.persistence.EnumType.STRING;

@Entity
public class Friend {

    @EmbeddedId
    private FriendId friendId;

    @ManyToOne
    @JoinColumn(name = "requester_id")
    private Profile requester;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Profile receiver;

    @Column(name = "occurred_on")
    private OffsetDateTime occurredOn;

    @Column(name = "status", length = 50)
    @Enumerated(STRING)
    @NotNull
    private InviteStatus status;

    private Friend() {}

    public Friend(FriendId friendId,
                  Profile requester,
                  Profile receiver,
                  OffsetDateTime occurredOn,
                  InviteStatus status) {

        setFriendId(friendId);
        setRequester(requester);
        setReceiver(receiver);
        setOccurredOn(occurredOn);
        setStatus(status);
    }

    public FriendId getFriendId() {
        return friendId;
    }

    public void setFriendId(FriendId friendId) {
        this.friendId = friendId;
    }

    public Profile getRequester() {
        return requester;
    }

    public void setRequester(Profile requester) {
        this.requester = requester;
    }

    public Profile getReceiver() {
        return receiver;
    }

    public void setReceiver(Profile receiver) {
        this.receiver = receiver;
    }

    public OffsetDateTime getOccurredOn() {
        return occurredOn;
    }

    public void setOccurredOn(OffsetDateTime occurredOn) {
        this.occurredOn = occurredOn;
    }

    public InviteStatus getStatus() {
        return status;
    }

    public void setStatus(InviteStatus status) {
        this.status = status;
    }
}
