package cinex.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.codec.binary.Hex;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToMany(mappedBy = "rater")
    private List<MovieRating> ratings;

    private String username;
    private Character role;
    private String email;
    private Date join_date;
    private String hash;
    private String salt;

    public User(String username, byte[] hash, String email, byte[] salt) {
        this.username = username;
        this.email = email;
        this.join_date = new Date();
        this.role = 'U';    //TODO: placeholder, zmienić jak będą zaimplementowane role (U-user)
        this.hash = Hex.encodeHexString(hash);
        this.salt = Hex.encodeHexString(salt);
    }

    public String getPassword() {
        return hash;
    }

    public String getSalt() {
        return salt;
    }

}
