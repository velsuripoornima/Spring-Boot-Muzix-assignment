package com.stackroute.MusixAppAssignment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Results {

    private Trackmatches trackmatches;

    public Trackmatches getTrackmatches() {
        return trackmatches;
    }

    public void setTrackmatches(Trackmatches trackmatches) {
        this.trackmatches = trackmatches;
    }
}