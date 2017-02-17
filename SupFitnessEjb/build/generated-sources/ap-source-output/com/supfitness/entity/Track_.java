package com.supfitness.entity;

import com.supfitness.entity.Race;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-03-11T22:14:49")
@StaticMetamodel(Track.class)
public class Track_ { 

    public static volatile SingularAttribute<Track, Race> raceId;
    public static volatile SingularAttribute<Track, Long> distance;
    public static volatile SingularAttribute<Track, Long> latitude;
    public static volatile SingularAttribute<Track, Integer> id;
    public static volatile SingularAttribute<Track, Long> speed;
    public static volatile SingularAttribute<Track, Long> longitude;

}