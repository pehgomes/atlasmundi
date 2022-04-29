package br.com.atlasmundi.atlasmundi.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@EqualsAndHashCode
@ToString
@Embeddable
@Access(AccessType.FIELD)
public class InviteId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "invite_id", columnDefinition = "uuid")
    private UUID id;

    @JsonCreator
    public InviteId(String id) {
        this.id = UUID.fromString(id);
    }

    @SuppressWarnings("unused")
    private InviteId() {
    }

    @JsonValue
    public String id() {
        return id.toString();
    }

    public UUID uuid() {
        return id;
    }

}
