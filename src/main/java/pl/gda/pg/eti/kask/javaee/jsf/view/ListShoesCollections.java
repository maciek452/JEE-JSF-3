package pl.gda.pg.eti.kask.javaee.jsf.view;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.services.ShoesCollectionsService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ShoesCollection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;

@Named
@RequestScoped
public class ListShoesCollections implements Serializable {

  @Inject private ShoesCollectionsService shoesCollectionsService;

  private Collection<ShoesCollection> shoesCollections;

  public Collection<ShoesCollection> getShoesCollections() {
    return shoesCollections != null
        ? shoesCollections
        : (shoesCollections = shoesCollectionsService.findAllShoesCollections());
  }

  public String removeShoesCollection(ShoesCollection shoesCollection) {
    shoesCollectionsService.removeShoesCollection(shoesCollection);
    return "list_shoes_collections?faces-redirect=true";
  }
}
