package pl.gda.pg.eti.kask.javaee.jsf.business.entities.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Shoe;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@Getter
@Setter
@NamedQueries({
        @NamedQuery(
                name = User.Queries.FIND_ALL,
                query = "select u from User u"
        )
})
public class User implements Serializable {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Integer id;

  @Column(nullable = false, unique = true)
  private String login;

  private String password;

  @ElementCollection
  private List<Role> roles = new ArrayList<>();

  public User(final String login, final String password, final List<Role> roles) {
    this.login = login;
    this.password = password;
    this.roles = roles;
  }

  public static class Queries {
    public static final String FIND_ALL = "USER_FIND_ALL";
  }
}
