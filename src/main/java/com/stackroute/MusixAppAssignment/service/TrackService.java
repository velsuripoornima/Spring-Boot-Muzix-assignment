package com.stackroute.MusixAppAssignment.service;

import com.stackroute.MusixAppAssignment.exceptions.TrackNotFoundException;
import com.stackroute.MusixAppAssignment.exceptions.UserAlreadyExistException;
import com.stackroute.MusixAppAssignment.model.Track;


import java.util.List;

public interface TrackService {

    //creating methods

     Track saveTrack(Track track) throws UserAlreadyExistException;

     List<Track> getAllTrack();

     Track updateTrack(Track track, int id) throws TrackNotFoundException;

     Track deleteTrack(int id);

     List<Track> trackByName(String name);

     List<Track> searchByNameAndId(int id,String name);

}
