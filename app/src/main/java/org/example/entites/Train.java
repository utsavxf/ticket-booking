package org.example.entites;

import java.sql.Time;
import java.util.List;
import java.util.Map;

public class Train {
    private String trainId;
    private String trainNo;
    private List<List<Integer>>seats;
    private Map<String,Time>stationTimes; //kis station par kis samay pahuchegi
    private List<String>stations; //all the stations which it will pass through
}
