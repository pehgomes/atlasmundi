package br.com.atlasmundi.atlasmundi.application.command;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "taxId. like cpf BR", example = "93143129093")
    String taxId;

    @NotNull
    @ApiModelProperty(value = "account name", example = "Goku")
    String name;

    @NotNull
    @ApiModelProperty(value = "login, used to auth", example = "goku123")
    String login;

    @NotNull
    @ApiModelProperty(value = "password, used to auth", example = "rs&%^8LqH#KX")
    String password;

    @NotNull
    @ApiModelProperty(value = "birthdate, in localDateTime", example = "1999-01-25T00:00:00.000-03:00")
    OffsetDateTime birthDate;

    @NotNull
    @ApiModelProperty(value = "e-mail", example = "fulano@gmail.com")
    String email;

    @NotNull
    @ApiModelProperty(value = "account phone number", example = "85999998821", dataType = "")
    String phoneNumber;


}
