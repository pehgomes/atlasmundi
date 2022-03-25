package br.com.atlasmundi.atlasmundi.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Profile implements Serializable {

    @EmbeddedId
    private ProfileId profileId;

    @Column
    private String username;

    @Column
    private String password;

    private Profile() {}

    public Profile(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    public ProfileId getProfileId() {
        return profileId;
    }

    public void setProfileId(ProfileId profileId) {
        this.profileId = profileId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
