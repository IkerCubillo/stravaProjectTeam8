package es.deusto.ingenieria.sd.auctions.server.data.domain;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.*;

@Generated(value="org.datanucleus.jpa.query.JPACriteriaProcessor")
@StaticMetamodel(Challenge.class)
public class Challenge_
{
    public static volatile SingularAttribute<Challenge, java.lang.String> name;
    public static volatile SingularAttribute<Challenge, java.util.Date> start;
    public static volatile SingularAttribute<Challenge, java.util.Date> end;
    public static volatile SingularAttribute<Challenge, Float> metric;
    public static volatile SingularAttribute<Challenge, java.lang.String> sportType;
    public static volatile SingularAttribute<Challenge, es.deusto.ingenieria.sd.auctions.server.data.domain.User> user;
}
