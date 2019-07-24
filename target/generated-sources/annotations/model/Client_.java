package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Location;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-20T11:10:31")
@StaticMetamodel(Client.class)
public class Client_ { 

    public static volatile ListAttribute<Client, Location> locationList;
    public static volatile SingularAttribute<Client, String> telephone;
    public static volatile SingularAttribute<Client, Integer> id;
    public static volatile SingularAttribute<Client, String> nom;
    public static volatile SingularAttribute<Client, String> cni;

}