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
public class FriendId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "friend_id", columnDefinition = "uuid")
    private UUID id;

    @JsonCreator
    public FriendId(String id) {
        this.id = UUID.fromString(id);
    }

    @SuppressWarnings("unused")
    private FriendId() {
    }

    @JsonValue
    public String id() {
        return id.toString();
    }

    public UUID uuid() {
        return id;
    }

}
