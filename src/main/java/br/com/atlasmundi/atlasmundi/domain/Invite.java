package br.com.atlasmundi.atlasmundi.domain;

import br.com.atlasmundi.atlasmundi.domain.enumeration.InviteStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

import static javax.persistence.EnumType.STRING;

@Entity
public class Invite {

    @EmbeddedId
    private InviteId inviteId;

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

    private Invite() {}

    public Invite(InviteId inviteId,
                  Profile requester,
                  Profile receiver,
                  OffsetDateTime occurredOn,
                  InviteStatus status) {

        setInviteId(inviteId);
        setRequester(requester);
        setReceiver(receiver);
        setOccurredOn(occurredOn);
        setStatus(status);
    }

    public InviteId getInviteId() {
        return inviteId;
    }

    public void setInviteId(InviteId inviteId) {
        this.inviteId = inviteId;
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
