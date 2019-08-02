package com.stackroute.springboot.muzix.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.springboot.muzix.model.Track;
import com.stackroute.springboot.muzix.service.TrackService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TrackControllerTest {

    private MockMvc mockMvc;
    @InjectMocks
    private TrackController trackController;

    @Mock
    private TrackService trackService;



    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(trackController)
                .build();
    }

    @Test
    public void trackController() throws Exception{
        Track track= new Track(1,"track name","track content","track status");
        when(trackService.saveTrack(track)).thenReturn(track);
        mockMvc.perform(post("/track")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(track)))
            .andExpect(status().isCreated());
       verify(trackService, times(1)).saveTrack(Mockito.any(Track.class));
        verifyNoMoreInteractions(trackService);
    }

    @Test
    public void setTrackByName() throws Exception{
        List<Track> track=Arrays.asList(new Track(2,"track","content","status"));
        when(trackService.trackByName("track")).thenReturn(track);
        mockMvc.perform(get("/track/{name}", "track")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(track)))
                .andExpect(status().isOk());
        verify(trackService, times(1)).trackByName("track");
        verifyNoMoreInteractions(trackService);
    }

    @Test
    public void getAllTracks() throws Exception{
       List<Track> track= Arrays.asList(new Track(1,"track1","content1","status1"),
        new Track(2,"track2","content2","status2"));
        when(trackService.getAllTrack()).thenReturn(track);
        mockMvc.perform(get("/track").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));
//                .andExpect((ResultMatcher) jsonPath("$[0].id",is(1)))
//                .andExpect((ResultMatcher) jsonPath("$[0].name", is(2)))
//                .andExpect((ResultMatcher) jsonPath("$[1].id", is(2)))
//                .andExpect((ResultMatcher) jsonPath("$[1].username", is(2)));
        verify(trackService, times(1)).getAllTrack();
        verifyNoMoreInteractions(trackService);
    }

    @Test
    public void deleteTrackById() throws Exception{
        Track track= new Track(1,"track name","track content","track status");
        doNothing().when(trackService).deleteTrack(track.getId());
        mockMvc.perform(delete("/track/{id}",1)
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(trackService,times(1)).deleteTrack(track.getId());
        verifyNoMoreInteractions(trackService);

    }

    @Test
    public void UpdateTrackTest() throws Exception{
        Track track=new Track(1,"track name","track content","track status");
        when(trackService.updateTrack(track,1)).thenReturn(track);
        mockMvc.perform(put("/track/{id}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(track)))
                .andExpect(status().isCreated());
        verify(trackService,times(1)).updateTrack(track,1);
        verifyNoMoreInteractions(trackService);
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}