package com.stackroute.MusixAppAssignment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


//ignoring all unwanted data from tracks
@JsonIgnoreProperties(ignoreUnknown = true)
public class Results {

    private Trackmatches trackmatches;

    //creating getters and setters
    public Trackmatches getTrackmatches() {
        return trackmatches;
    }

    
    public void setTrackmatches(Trackmatches trackmatches) {
        this.trackmatches = trackmatches;
    }
}
