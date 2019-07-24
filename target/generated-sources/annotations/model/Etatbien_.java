package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Bien;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-20T11:10:31")
@StaticMetamodel(Etatbien.class)
public class Etatbien_ { 

    public static volatile SingularAttribute<Etatbien, Date> date;
    public static volatile SingularAttribute<Etatbien, String> libelle;
    public static volatile SingularAttribute<Etatbien, Bien> idbien;
    public static volatile SingularAttribute<Etatbien, Integer> id;

}