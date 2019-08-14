package com.stackroute.MusixAppAssignment.service;

import ch.qos.logback.classic.Logger;
import com.stackroute.MusixAppAssignment.exceptions.TrackNotFoundException;
import com.stackroute.MusixAppAssignment.exceptions.UserAlreadyExistException;
import com.stackroute.MusixAppAssignment.model.Track;
import com.stackroute.MusixAppAssignment.model.TrackDto;
import com.stackroute.MusixAppAssignment.repository.TrackRepository;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service  //it is a service class
public class TrackServiceImpl implements TrackService {

    //overriding all the methods from trackservice interface

    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    //logger data getting from logger factory

    TrackMapper trackMapper;

    TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository,TrackMapper trackMapper) {

        this.trackRepository = trackRepository;
        this.trackMapper=trackMapper;
    }

    @Override
    @CacheEvict(value = "track",beforeInvocation = true)
    public Track saveTrack(Track track) throws UserAlreadyExistException {

//        if(trackRepository.existsById(track.getId())){
//
//            throw new UserAlreadyExistException("track already exist");
//        }
        //converting the track to trackDto
        TrackDto track2= trackMapper.trackToTrackDto(track);
        //saving trackDto into repository
        TrackDto track1=trackRepository.save(track2);
        //getting data and converting into trackDto to track
        Track saveTrack=trackMapper.trackDtoToTrack(track1);
        logger.info(track1.getName());
        //logging information
        logger.info("track Dto"+ saveTrack.getName());
        //handling null pointer exception
        if(track1==null){
            throw new UserAlreadyExistException("track is null");
        }
        //return
        return saveTrack;
    }

    @Override
    @Cacheable("track") //handling cache
    @CacheEvict(value = "track",beforeInvocation = true) //reloading the cache
    public List<Track> getAllTrack() {
        try {
            //method operation after 3000millis
            Thread.sleep(3000);
            //logging information
            logger.info("Entered into get all method in trackServiceImpl");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        //getting all tracks from database
        List<TrackDto> trackDto=trackRepository.findAll();
        //converting all tracks trackDto to track
        List<Track> trackList=trackMapper.trackDtoListToTrackList(trackDto);
        //return trackList
        return trackList;
    }

    @Override
    @CacheEvict(value = "track",beforeInvocation = true)  //reloading the cache
    public Track updateTrack(Track track,int id) throws TrackNotFoundException {
        //geeting the track with optional class
       Optional<TrackDto> track1dto= trackRepository.findById(id);
       //converting the trackdto to track
       Track track2=trackMapper.trackDtoToTrack(track1dto.get());

        TrackDto trackDto=trackMapper.trackToTrackDto(track2);
        if(!track1dto.isPresent()){

            throw new TrackNotFoundException("track not found exception");
        }

//        track.setId(id);
        //again converting the track to trackDto
        TrackDto trackDto1=trackRepository.save(trackDto);
        Track track1=trackMapper.trackDtoToTrack(trackDto1);
        return  track1;
    }

    @Override
    @CacheEvict(value = "track",beforeInvocation = true)
    public Track deleteTrack(int id) {
        //delete track using the track by id
        Optional<TrackDto> track1= trackRepository.findById(id);
        Track track=trackMapper.trackDtoToTrack(track1.get());
        trackRepository.deleteById(id);
        return track;
    }

}
