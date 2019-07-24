package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Bien;
import model.Client;
import model.Loyer;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-20T11:10:31")
@StaticMetamodel(Location.class)
public class Location_ { 

    public static volatile SingularAttribute<Location, Boolean> occupe;
    public static volatile SingularAttribute<Location, String> etatbien;
    public static volatile ListAttribute<Location, Loyer> loyerList;
    public static volatile SingularAttribute<Location, Date> dateenregistrement;
    public static volatile SingularAttribute<Location, Bien> idbien;
    public static volatile SingularAttribute<Location, Float> montant;
    public static volatile SingularAttribute<Location, Integer> id;
    public static volatile SingularAttribute<Location, Client> idclient;
    public static volatile SingularAttribute<Location, Date> datedernierpaiem;

}