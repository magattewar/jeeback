package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Bailleur;
import model.Creditbailleur;
import model.Etatbien;
import model.Location;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-20T11:10:31")
@StaticMetamodel(Bien.class)
public class Bien_ { 

    public static volatile SingularAttribute<Bien, Float> montantvoulu;
    public static volatile SingularAttribute<Bien, Boolean> disponibilite;
    public static volatile SingularAttribute<Bien, Bailleur> idbailleur;
    public static volatile SingularAttribute<Bien, String> description;
    public static volatile SingularAttribute<Bien, String> photo;
    public static volatile ListAttribute<Bien, Etatbien> etatbienList;
    public static volatile ListAttribute<Bien, Location> locationList;
    public static volatile SingularAttribute<Bien, Float> prixlocation;
    public static volatile SingularAttribute<Bien, Date> dateenregistrement;
    public static volatile SingularAttribute<Bien, String> adresse;
    public static volatile ListAttribute<Bien, Creditbailleur> creditbailleurList;
    public static volatile SingularAttribute<Bien, Integer> id;
    public static volatile SingularAttribute<Bien, Date> datedernierpaiement;

}