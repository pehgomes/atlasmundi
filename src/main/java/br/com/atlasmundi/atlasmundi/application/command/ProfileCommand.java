package br.com.atlasmundi.atlasmundi.application.command;

import com.sun.istack.NotNull;
import lombok.*;

import java.time.OffsetDateTime;


@Value
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
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
