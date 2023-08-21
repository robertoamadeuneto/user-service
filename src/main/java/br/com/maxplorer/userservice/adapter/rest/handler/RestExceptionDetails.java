package br.com.maxplorer.userservice.adapter.rest.handler;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Data
public class RestExceptionDetails {

    private List<Detail> details;

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor
    @Data
    static class Detail {

        private String property;
        private Object value;
        private String message;
        private Constraint constraint;

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        @AllArgsConstructor
        @Data
        static class Constraint {

            private String name;
        }
    }
}
