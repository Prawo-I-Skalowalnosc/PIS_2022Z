package cinex.security;

public enum UserRoles {
    ADMIN("admin"),
    USER("user"),
    MODERATOR("moderator");

    UserRoles(String name) {
        this.name = name;
    }
    private final String name;

    @Override
    public String toString() {
        return name;
    }

    static UserRoles newUserRole(String name) {
        return UserRoles.valueOf(name);
    }
}
