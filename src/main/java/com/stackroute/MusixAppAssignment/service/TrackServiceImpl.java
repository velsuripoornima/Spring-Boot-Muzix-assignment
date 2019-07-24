package com.stackroute.MusixAppAssignment.service;

import com.stackroute.MusixAppAssignment.exceptions.TrackNotFoundException;
import com.stackroute.MusixAppAssignment.exceptions.UserAlreadyExistException;
import com.stackroute.MusixAppAssignment.model.Track;
import com.stackroute.MusixAppAssignment.repository.TrackRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service  //it is a service class
public class TrackServiceImpl implements TrackService {

    //overriding all the methods from trackservice interface
    @Autowired
    TrackRepository trackRepository;

    public TrackServiceImpl(TrackRepository trackRepository) {

        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws UserAlreadyExistException {

        if(trackRepository.existsById(track.getId())){

            throw new UserAlreadyExistException("track already exist");
        }

        Track track1=trackRepository.save(track);
        return track1;
    }

    @Override
    public List<Track> getAllTrack() {

        return trackRepository.findAll();
    }

    @Override
    public Track updateTrack(Track track,int id) throws TrackNotFoundException {

       Optional<Track> track1= trackRepository.findById(id);

        if(!track1.isPresent()){

            throw new TrackNotFoundException("track not found exception");
        }

        track.setId(id);

        return  trackRepository.save(track);

    }

    @Override
    public void deleteTrack(int id) {

        trackRepository.deleteById(id);

    }

    @Override
    public List<Track> trackByName(String name) {
        List<Track> trackList= trackRepository.findTitleByName(name);
        return trackList;
    }

    @Override
    public List<Track> searchByNameAndId(int id, String name) {
        List<Track> trackList1=trackRepository.findTitleByName(name,id);
        return trackList1;
    }


}
