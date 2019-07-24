package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Location;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-20T11:10:31")
@StaticMetamodel(Loyer.class)
public class Loyer_ { 

    public static volatile SingularAttribute<Loyer, Date> dateenregistrement;
    public static volatile SingularAttribute<Loyer, Boolean> paye;
    public static volatile SingularAttribute<Loyer, Float> montant;
    public static volatile SingularAttribute<Loyer, Location> idlocation;
    public static volatile SingularAttribute<Loyer, Integer> id;

}