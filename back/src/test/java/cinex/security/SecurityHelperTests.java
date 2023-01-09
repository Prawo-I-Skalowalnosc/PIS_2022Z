package cinex.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

import static cinex.GlobalTestValues.getUser;
import static cinex.GlobalTestValues.username;
import static cinex.security.SecurityHelper.getLoggedUser;
import static org.junit.jupiter.api.Assertions.*;

public class SecurityHelperTests {
    @Test
    public void testGetLoggedUser_Null() {
        var optUser = Optional.empty();
        var token = "tokentoken";
        var grantedAuthorityList = List.of(new SimpleGrantedAuthority(UserRoles.USER.toString()));

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                optUser, token, grantedAuthorityList);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        assertNull(getLoggedUser());
    }

    @Test
    public void testGetLoggedUser_Ok() {
        var optUser = Optional.of(getUser());
        var token = "tokentoken";
        var grantedAuthorityList = List.of(new SimpleGrantedAuthority(UserRoles.ADMIN.toString()));

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                optUser, token, grantedAuthorityList);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        assertNotNull(getLoggedUser());
        assertEquals(username, getLoggedUser().getUsername());
        assertEquals(getUser().getSalt(), getLoggedUser().getSalt());
        assertEquals(optUser.get().getEmail(), getLoggedUser().getEmail());
        assertEquals(getUser().getHash(), getLoggedUser().getHash());
        assertEquals(getUser().getRatings().size(), getLoggedUser().getRatings().size());
        assertEquals(getUser().getRole(), getLoggedUser().getRole());
        assertEquals(getUser().getJoin_date().getTime(), getLoggedUser().getJoin_date().getTime(), 1000000);
    }
}
