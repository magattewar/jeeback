package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Bien;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-20T11:10:31")
@StaticMetamodel(Creditbailleur.class)
public class Creditbailleur_ { 

    public static volatile SingularAttribute<Creditbailleur, Date> date;
    public static volatile SingularAttribute<Creditbailleur, Bien> idbien;
    public static volatile SingularAttribute<Creditbailleur, Boolean> paye;
    public static volatile SingularAttribute<Creditbailleur, Float> montant;
    public static volatile SingularAttribute<Creditbailleur, Integer> id;

}