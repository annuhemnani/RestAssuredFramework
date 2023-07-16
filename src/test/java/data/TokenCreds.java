package data;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenCreds {
    private String username;
    private String password;

}
