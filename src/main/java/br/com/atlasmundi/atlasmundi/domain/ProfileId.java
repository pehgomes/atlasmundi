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
public class ProfileId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "profile_id", columnDefinition = "uuid")
    private UUID id;

    @JsonCreator
    public ProfileId(String id) {
        this.id = UUID.fromString(id);
    }

    @SuppressWarnings("unused")
    private ProfileId() {
    }

    @JsonValue
    public String id() {
        return id.toString();
    }

    public UUID uuid() {
        return id;
    }

}
