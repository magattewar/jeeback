package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Bailleur;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-20T11:10:31")
@StaticMetamodel(Moyenpaiement.class)
public class Moyenpaiement_ { 

    public static volatile SingularAttribute<Moyenpaiement, String> libelle;
    public static volatile SingularAttribute<Moyenpaiement, Integer> id;
    public static volatile ListAttribute<Moyenpaiement, Bailleur> bailleurList;

}