package es.deusto.ingenieria.sd.auctions.server.data.domain;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.*;

@Generated(value="org.datanucleus.jpa.query.JPACriteriaProcessor")
@StaticMetamodel(TrainingSession.class)
public class TrainingSession_
{
    public static volatile SingularAttribute<TrainingSession, java.lang.String> title;
    public static volatile SingularAttribute<TrainingSession, java.lang.String> sport;
    public static volatile SingularAttribute<TrainingSession, Float> distance;
    public static volatile SingularAttribute<TrainingSession, java.util.Date> startDate;
    public static volatile SingularAttribute<TrainingSession, java.time.LocalTime> startTime;
    public static volatile SingularAttribute<TrainingSession, Float> duration;
    public static volatile SingularAttribute<TrainingSession, es.deusto.ingenieria.sd.auctions.server.data.domain.User> user;
}
