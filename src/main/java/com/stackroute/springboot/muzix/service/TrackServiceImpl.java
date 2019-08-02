package com.stackroute.springboot.muzix.service;

import com.stackroute.springboot.muzix.exceptions.TrackNotFoundException;
import com.stackroute.springboot.muzix.exceptions.UserAlreadyExistException;
import com.stackroute.springboot.muzix.model.Track;
import com.stackroute.springboot.muzix.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("TrackService")  //it is a service class
public class TrackServiceImpl implements TrackService , ApplicationListener<ContextRefreshedEvent> {

    //overriding all the methods from trackservice interface

    @Value("${track.1.id}")
    private int id;
    @Value("${track.1.name}")
    private String name;
    @Value("${track.1.content}")
    private String content;
    @Value("${track.1.status}")
    private String status;

    @Value("${track.2.id}")
    private int id2;
    @Value("${track.2.name}")
    private String name2;
    @Value("${track.2.content}")
    private String content2;
    @Value("${track.2.status}")
    private String status2;

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
        if(track1==null){
            throw new UserAlreadyExistException("track already exist");
        }
        return track1;
    }

    @Override
    public List<Track> getAllTrack() {
        return trackRepository.findAll();
    }

    @Override
    public Track updateTrack(Track track,int id) throws TrackNotFoundException {
        Optional<Track> track1 = trackRepository.findById(id);
        if (!track1.isPresent()) {
            throw new TrackNotFoundException("track not found exception");
        }
        track.setId(id);
        return trackRepository.save(track);
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
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        trackRepository.save(new Track(id,name,content,status));
        trackRepository.save(new Track(id2,name2,content2,status2));
    }
}
