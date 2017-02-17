package com.supfitness.entity;

import com.supfitness.entity.Track;
import com.supfitness.entity.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-03-11T22:14:49")
@StaticMetamodel(Race.class)
public class Race_ { 

    public static volatile SingularAttribute<Race, Date> date;
    public static volatile SingularAttribute<Race, Integer> id;
    public static volatile SingularAttribute<Race, User> userId;
    public static volatile CollectionAttribute<Race, Track> trackCollection;

}