package br.com.atlasmundi.atlasmundi.application.exception;


import lombok.*;

@Value
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@ToString
class ConstraintParam {

    private static final long serialVersionUID = -2579282952706340257L;

    private String name;
    private Object value;

}
