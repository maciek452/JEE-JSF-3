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
  @NamedQuery(name = ShoesCollection.Queries.FIND_ALL, query = "select a from ShoesCollection a"),
})
public class ShoesCollection implements Serializable {
  @Id @GeneratedValue private Integer id;

  @Size(min = 4, max = 250)
  private String name;

  @Size(min = 4, max = 250)
  private String season;

  @ManyToMany(cascade = ALL, mappedBy = "shoesCollections")
  private List<Shoe> shoes = new ArrayList<>();

  public ShoesCollection(String name, String season) {
    this.name = name;
    this.season = season;
  }

  public static class Queries {
    public static final String FIND_ALL = "SHOES_COLLECTION_FIND_ALL";
  }
}
