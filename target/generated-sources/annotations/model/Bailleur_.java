package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Bien;
import model.Moyenpaiement;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-20T11:10:31")
@StaticMetamodel(Bailleur.class)
public class Bailleur_ { 

    public static volatile SingularAttribute<Bailleur, Moyenpaiement> modepaiement;
    public static volatile ListAttribute<Bailleur, Bien> bienList;
    public static volatile SingularAttribute<Bailleur, Integer> id;
    public static volatile SingularAttribute<Bailleur, String> nom;
    public static volatile SingularAttribute<Bailleur, Integer> cni;

}