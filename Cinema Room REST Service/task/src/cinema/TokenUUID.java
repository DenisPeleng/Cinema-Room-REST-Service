package cinema;

import java.util.UUID;

public class TokenUUID {

    private String token;

    public TokenUUID() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static String generateUUIDToken() {
        return UUID.randomUUID().toString();
    }
}
