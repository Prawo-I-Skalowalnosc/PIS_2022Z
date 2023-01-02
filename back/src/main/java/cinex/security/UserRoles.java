package cinex.security;

public enum UserRoles {
    ADMIN("admin"),
    USER("user");

    UserRoles(String name) {
        this.name = name;
    }
    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
