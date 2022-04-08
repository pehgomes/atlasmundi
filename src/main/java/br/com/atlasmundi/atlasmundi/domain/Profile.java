package br.com.atlasmundi.atlasmundi.domain;

import br.com.atlasmundi.atlasmundi.application.util.Util;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.time.OffsetDateTime;

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

    @Column
    private String name;

    @Column(name = "birth_date")
    private OffsetDateTime birthDate;

    @Column(name = "phone_number")
    private String phoneNumber;


    private Profile() {
    }

    public Profile(ProfileId profileId,
                   String login,
                   String password,
                   String taxId,
                   String name,
                   OffsetDateTime birthDate,
                   String phoneNumber) {

        if (!Util.isValidTaxId(taxId))
            throw new RuntimeException("InvalidTaxId: " + taxId);

        if (!birthDate.isBefore(OffsetDateTime.now().minusYears(18)))
            throw new RuntimeException("InvalidBirthDate: " + birthDate);

//        if (!Util.isValidPhoneNumber(phoneNumber))
//            throw new RuntimeException("InvalidPhoneNumber: " + phoneNumber);

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
        setName(name);
        setBirthDate(birthDate);
        setPhoneNumber(phoneNumber);
    }

    public ProfileId uuid() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
