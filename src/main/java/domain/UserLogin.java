package domain;

public record UserLogin(String type, String id, String password) {
    public UserLogin {
        if (type == null || id == null || password == null) {
            throw new IllegalArgumentException("Login credentials cannot be null.");
        }
    }
}
