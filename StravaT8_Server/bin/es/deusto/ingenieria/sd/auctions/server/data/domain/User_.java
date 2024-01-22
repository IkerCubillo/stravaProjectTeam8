package es.deusto.ingenieria.sd.auctions.server.data.domain;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.*;

@Generated(value="org.datanucleus.jpa.query.JPACriteriaProcessor")
@StaticMetamodel(User.class)
public class User_
{
    public static volatile SingularAttribute<User, java.lang.String> email;
    public static volatile SingularAttribute<User, java.lang.String> name;
    public static volatile SingularAttribute<User, java.lang.String> account;
    public static volatile SingularAttribute<User, java.util.Date> birthDate;
    public static volatile SingularAttribute<User, Float> weight;
    public static volatile SingularAttribute<User, Float> height;
    public static volatile SingularAttribute<User, Integer> mBPM;
    public static volatile SingularAttribute<User, Integer> bpm;
    public static volatile SetAttribute<User, es.deusto.ingenieria.sd.auctions.server.data.domain.TrainingSession> userSessions;
    public static volatile SetAttribute<User, es.deusto.ingenieria.sd.auctions.server.data.domain.Challenge> userActiveChallenges;
}
