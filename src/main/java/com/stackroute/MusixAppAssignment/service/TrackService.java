package com.stackroute.MusixAppAssignment.service;

import com.stackroute.MusixAppAssignment.exceptions.TrackNotFoundException;
import com.stackroute.MusixAppAssignment.exceptions.UserAlreadyExistException;
import com.stackroute.MusixAppAssignment.model.Track;


import java.util.List;

public interface TrackService {

    //creating methods

    public Track saveTrack(Track track) throws UserAlreadyExistException;

    public List<Track> getAllTrack();

    public Track updateTrack(Track track, int id) throws TrackNotFoundException;

    public Track deleteTrack(int id);


}
