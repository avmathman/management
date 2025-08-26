package com.invent.management.api.controllers.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* Passes authenticated user token to sign-out.
*/
@Getter
@Setter
@NoArgsConstructor
public class SignOutDto {

    /**
    * Authenticated user token.
    */
    String token;
}
