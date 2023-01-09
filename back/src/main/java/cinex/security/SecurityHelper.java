package cinex.security;

import cinex.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

public class SecurityHelper {
    public static User getLoggedUser() {
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal.getClass() == Optional.of(User.class).getClass()))
            return null;

        var optional = (Optional<?>) principal;

        return (User) optional.orElse(null);
    }

    public static boolean requireRoles(List<UserRoles> roles) {
        var user = getLoggedUser();
        return user != null && roles.stream().anyMatch(x -> user.getUserRoles().contains(x));
    }
}
