package br.com.atlasmundi.atlasmundi.application.command;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;


@Value
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class ProfileCommand {

    @NotNull
    String taxId;
    @NotNull
    String name;
    @NotNull
    String login;
    @NotNull
    String password;
    @NotNull
    OffsetDateTime birthDate;
    @NotNull
    String phoneNumber;
}
