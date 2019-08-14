package com.stackroute.MusixAppAssignment.controller;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.MusixAppAssignment.model.Root;
import com.stackroute.MusixAppAssignment.model.Track;
import com.stackroute.MusixAppAssignment.service.TrackService;
import org.apache.tomcat.util.json.JSONParser;
import org.hibernate.validator.internal.util.privilegedactions.GetMethod;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.spring.web.json.Json;


import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.repository.init.ResourceReader.Type.JSON;

@CrossOrigin(origins = "*")
@RestController
public class TrackController {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    //using the class trackservice
    @Autowired
    private TrackService trackService;

    //create constructor for trackservice
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    //handler method for post values
    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws Exception {
        ResponseEntity responseEntity;
        logger.info("Entered into saveTrack method in TrackController");
        Track track1=trackService.saveTrack(track);
        responseEntity = new ResponseEntity<Track>(track1, HttpStatus.CREATED);
        return responseEntity;
    }

    //getting all the tracks  from the track
    @GetMapping("track")
    public ResponseEntity<?> getAllTrack() {
        logger.info("Entered into getAllTrack methond in TrackController");
        //getting all tracks
        return new ResponseEntity<List<Track>>(trackService.getAllTrack(), HttpStatus.OK);

    }

    //getiing track by id and update entire track
    @PutMapping("/track/{id}")
    public ResponseEntity<?> getTrack(@RequestBody Track track, @PathVariable("id") int id) throws Exception {
        logger.info("Entered into updateTrack method in Trackcontroller");
        trackService.updateTrack(track, id);
        return new ResponseEntity<String>("updated successfully", HttpStatus.CREATED);

    }


    //delete tracks based on id
    @DeleteMapping("/track/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable int id) {
        logger.info("Entered into deleteTrack method in TrackController");
        Track track1=trackService.deleteTrack(id);
        return new ResponseEntity<Track>(track1, HttpStatus.OK);
    }

    //getting all the tracks by using last.fm api
    @GetMapping("listOfTrack")
    public ResponseEntity<?> getListOfTrackFromLastFm(@RequestParam String url) throws Exception {


        RestTemplate restTemplate = new RestTemplate();
        String string = restTemplate.getForObject(url,String.class);
        System.out.println(string);
        //converting json object to java object
        ObjectMapper objectMapper = new ObjectMapper();
        Root root = objectMapper.readValue(string, Root.class);
        List<Track> trackList = root.getResults().getTrackmatches().getTrack();
        System.out.println(trackList);
        List<Track> savedTrackList = new ArrayList<>();
        for (Track user:trackList) {
            Track user1 = trackService.saveTrack(user);
            savedTrackList.add(user1);
        }
        return new ResponseEntity<>(savedTrackList,HttpStatus.OK);
    }
}