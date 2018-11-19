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

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REFRESH;

@Entity
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Getter
@Setter
@NamedQueries({
  @NamedQuery(
    name = Shoe.Queries.FIND_ALL_FILTERED,
    query = "select b from Shoe b where b.model like CONCAT('%',:filterParam,'%')"
  )
})
public class Shoe implements Serializable {
  @Id @GeneratedValue private Integer id;

  @Size(min = 4, max = 250)
  private String model;

  @Size(min = 1)
  @ManyToMany(cascade = {MERGE, REFRESH})
  private List<ShoesCollection> shoesCollections = new ArrayList<>();

  public Shoe(String model, List<ShoesCollection> shoesCollections) {
    this.model = model;
    this.shoesCollections = shoesCollections;
  }

  public static class Queries {
    public static final String FIND_ALL_FILTERED = "SHOE_FIND_ALL_FILTERED";
  }
}
