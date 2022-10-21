package cinema.model;

public class Password {
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Password() {
    }

    @Override
    public String toString() {
        return password;
    }
}
