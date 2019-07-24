package com.stackroute.MusixAppAssignment.controller;

import com.stackroute.MusixAppAssignment.model.Track;
import com.stackroute.MusixAppAssignment.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws Exception{
        ResponseEntity responseEntity;
        trackService.saveTrack(track);
        responseEntity=new ResponseEntity<String>("row added successfully", HttpStatus.CREATED);
        return responseEntity;
//        try{
//            //if trackservice having any values it save to track
//            trackService.saveTrack(track);
//            responseEntity=new ResponseEntity<String>("row added successfully", HttpStatus.CREATED);
//            return responseEntity;
//        }
//        catch (UserAlreadyExistException e){
//
//            //otherwise it will rise an exception
//            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
//            return responseEntity;
//        }
    }

    @GetMapping("track")
    public ResponseEntity<?> getAllTrack(@RequestBody Track track){

        //getting all tracks
        return new ResponseEntity<List<Track>>(trackService.getAllTrack(),HttpStatus.OK);

    }

    @PutMapping("/track/{id}")
    public ResponseEntity<?> getTrack(@RequestBody Track track,@PathVariable("id") int id) throws Exception {

        trackService.updateTrack(track, id);
        return new ResponseEntity<String>("updated successfully",HttpStatus.CREATED);
//        try {
//            trackService.updateTrack(track, id);
//            return new ResponseEntity<String>("updated successfully",HttpStatus.CREATED);
//        }
//        catch (TrackNotFoundException e){
//            return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
//        }

    }

    @DeleteMapping("/track/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable int id){
        trackService.deleteTrack(id);
        return new ResponseEntity<String>("deleted successfully",HttpStatus.OK);
    }


    @GetMapping("/track/{name}")
    public ResponseEntity<?> trackByName(@PathVariable String name){

        return new ResponseEntity<>( trackService.trackByName(name),HttpStatus.OK);
    }

    @GetMapping("/track/{id}/{name}")
    public ResponseEntity<?> searchByName(@PathVariable int id,@PathVariable String name){
       return new ResponseEntity<>(trackService.searchByNameAndId(id,name),HttpStatus.OK);

    }
}
