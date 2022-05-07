package br.com.atlasmundi.atlasmundi.application.command;

import br.com.atlasmundi.atlasmundi.domain.ProfileId;
import lombok.*;

import javax.validation.constraints.NotNull;

@Value
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class FriendCommand {
    @NotNull
    ProfileId from;
    @NotNull
    ProfileId to;
}
