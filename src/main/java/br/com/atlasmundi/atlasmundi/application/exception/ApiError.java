package br.com.atlasmundi.atlasmundi.application.exception;

import lombok.*;

import java.util.List;

@Value
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@ToString
public class ApiError {
    private List<Error> errors;

    @Value
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
    @ToString
    public static class Error {

        private String property;
        private Object value;
        private String message;
        private Constraint constraint;

        @Value
        @AllArgsConstructor
        @NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
        @ToString
        public static class Constraint {

            private String name;
            private List<Param> params;

            @Value
            @AllArgsConstructor
            @NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
            @ToString
            public static class Param {

                private String name;
                private Object value;

            }

        }

    }

}
