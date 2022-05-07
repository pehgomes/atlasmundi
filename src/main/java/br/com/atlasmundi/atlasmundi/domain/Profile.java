package br.com.atlasmundi.atlasmundi.domain;

import br.com.atlasmundi.atlasmundi.application.util.Util;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
public class Profile implements Serializable {

    @EmbeddedId
    private ProfileId profileId;

    @Column
    private String login;

    @Column
    private String password;

    @Column(name = "tax_id")
    private String taxId;

    @Column(name = "name")
    private String username;

    @Column(name = "birth_date")
    private OffsetDateTime birthDate;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "receiver")
    private List<Invite> invites;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "receiver")
    private List<Friend> friends;


    private Profile() {
    }

    public Profile(ProfileId profileId,
                   String login,
                   String password,
                   String taxId,
                   String name,
                   OffsetDateTime birthDate,
                   String phoneNumber,
                   String email) {

        if (!Util.isValidTaxId(taxId))
            throw new RuntimeException("InvalidTaxId: " + taxId);

        if (!birthDate.isBefore(OffsetDateTime.now().minusYears(18)))
            throw new RuntimeException("InvalidBirthDate: " + birthDate);

        if (name == null || name.isBlank())
            throw new RuntimeException("InvalidName: " + name);

        if (login == null || login.isBlank())
            throw new RuntimeException("InvalidLogin: " + login);

        if (password == null || password.isBlank())
            throw new RuntimeException("InvalidPassword: " + password);

        setProfileId(profileId);
        setLogin(login);
        setPassword(password);
        setTaxId(taxId);
        setUsername(name);
        setBirthDate(birthDate);
        setPhoneNumber(phoneNumber);
        setEmail(email);
    }

    public ProfileId getProfileId() {
        return profileId;
    }

    public void setProfileId(ProfileId profileId) {
        this.profileId = profileId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public OffsetDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(OffsetDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Invite> getInvites() {
        return invites;
    }

    public void setInvites(List<Invite> invites) {
        this.invites = invites;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }
}
