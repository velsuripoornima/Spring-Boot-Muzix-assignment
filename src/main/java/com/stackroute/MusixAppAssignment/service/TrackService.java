package com.stackroute.MusixAppAssignment.service;

import com.stackroute.MusixAppAssignment.exceptions.TrackNotFoundException;
import com.stackroute.MusixAppAssignment.exceptions.UserAlreadyExistException;
import com.stackroute.MusixAppAssignment.model.Track;


import java.util.List;

public interface TrackService {

    //creating methods

    public Track saveTrack(Track track) throws UserAlreadyExistException;

    public List<com.stackroute.MusixAppAssignment.model.Track> getAllTrack();

    public Track updateTrack(Track track, int id) throws TrackNotFoundException;

    public void deleteTrack(int id);

    public List<Track> trackByName(String name);

    public List<Track> searchByNameAndId(int id,String name);

}
