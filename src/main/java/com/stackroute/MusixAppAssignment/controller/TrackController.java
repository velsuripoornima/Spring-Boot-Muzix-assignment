package com.stackroute.MusixAppAssignment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.MusixAppAssignment.model.Root;
import com.stackroute.MusixAppAssignment.model.Track;
import com.stackroute.MusixAppAssignment.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TrackController {

    //using the class trackservice
    @Autowired
    TrackService trackService;

    //create constructor for trackservice
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws Exception {
        ResponseEntity responseEntity;
        trackService.saveTrack(track);
        responseEntity = new ResponseEntity<String>("row added successfully", HttpStatus.CREATED);
        return responseEntity;

    }

    @GetMapping("track")
    public ResponseEntity<?> getAllTrack(@RequestBody Track track) {

        //getting all tracks
        return new ResponseEntity<List<Track>>(trackService.getAllTrack(), HttpStatus.OK);

    }

    //get track by track,id and updated
    @PutMapping("/track/{id}")
    public ResponseEntity<?> getTrack(@RequestBody Track track, @PathVariable("id") int id) throws Exception {

        trackService.updateTrack(track, id);
        return new ResponseEntity<String>("updated successfully", HttpStatus.CREATED);
    }

    //delete track by id
    @DeleteMapping("/track/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable int id) {
        trackService.deleteTrack(id);
        return new ResponseEntity<String>("deleted successfully", HttpStatus.OK);
    }

    //getting tracks by name
    @GetMapping("/track/{name}")
    public ResponseEntity<?> trackByName(@PathVariable String name) {

        return new ResponseEntity<>(trackService.trackByName(name), HttpStatus.OK);
    }

    //getting tracks by searching name and id
    @GetMapping("/track/{id}/{name}")
    public ResponseEntity<?> searchByName(@PathVariable int id, @PathVariable String name) {
        return new ResponseEntity<>(trackService.searchByNameAndId(id, name), HttpStatus.OK);

    }

    //getting all the the tracks in the last.fm tird party api
    @GetMapping("listOfTrack")
    public ResponseEntity<?> getListOfTrackFromLastFm(@RequestParam String url) throws Exception {

        RestTemplate restTemplate = new RestTemplate();
        String string = restTemplate.getForObject(url,String.class);
        System.out.println(string);
        //convert jsonobject to java object
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