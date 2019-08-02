package com.stackroute.springboot.muzix.service;

import com.stackroute.springboot.muzix.exceptions.TrackNotFoundException;
import com.stackroute.springboot.muzix.exceptions.UserAlreadyExistException;
import com.stackroute.springboot.muzix.model.Track;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface TrackService {

    //creating methods

    public Track saveTrack(Track track) throws UserAlreadyExistException;

    public List<Track> getAllTrack();

    public Track updateTrack(Track track,int id) throws TrackNotFoundException;

    public void deleteTrack(int id);

    public List<Track> trackByName(String name);


}
