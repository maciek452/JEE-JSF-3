package pl.gda.pg.eti.kask.javaee.jsf.business.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Getter
@Setter
@NamedQueries({
  @NamedQuery(name = Brand.Queries.FIND_ALL, query = "select a from Brand a"),
})
public class Brand implements Serializable {
  @Id @GeneratedValue private Integer id;

  @Size(min = 4, max = 250)
  private String name;

  @OneToMany(cascade = ALL, mappedBy = "brand")
  private List<Shoe> shoes = new ArrayList<>();

  public Brand(String name) {
    this.name = name;
  }

  public static class Queries {
    public static final String FIND_ALL = "BRANDS_FIND_ALL";
  }
}
