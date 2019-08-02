package com.stackroute.springboot.muzix.service;

import com.stackroute.springboot.muzix.exceptions.UserAlreadyExistException;
import com.stackroute.springboot.muzix.model.Track;
import com.stackroute.springboot.muzix.repository.TrackRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class TrackServiceImplTest {

    Track track;
    //Create a mock for UserRepository
    @Mock
    TrackRepository trackRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
     TrackServiceImpl trackService;
    List<Track> list= null;


    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        track=new Track(1,"track","track content","track status");

    }

    @Test
    public void saveUserTestSuccess() throws UserAlreadyExistException {

        when(trackRepository.save((Track)any())).thenReturn(track);
        Track savedUser = trackService.saveTrack(track);
        Assert.assertEquals(track,savedUser);

        //verify here verifies that userRepository save method is only called once
        verify(trackRepository,times(1)).save(track);

    }


    @Test(expected = UserAlreadyExistException.class)
    public void saveUserTestFailure() throws UserAlreadyExistException {
        when(trackRepository.save((Track) any())).thenReturn(null);
        Track savedUser = trackService.saveTrack(track);
        System.out.println("savedUser" + savedUser);

    }

    @Test
    public void getAllTracksTest() throws Exception{
        List<Track> trackList= Arrays.asList(new Track(1,"tack name","track content","track status") ,
                new Track(2,"track name1","track content","track status1"));
        when(trackRepository.findAll()).thenReturn(trackList);
        List<Track> trackList1=trackService.getAllTrack();
        Assert.assertEquals(trackList,trackList1);
        verify(trackRepository,times(1)).findAll();
    }

    @Test
    public void updateTrackTest() throws Exception{
        Track track1=new Track(2,"track name1","track content","track status1");
        Optional<Track> optional=Optional.of(track1);
        when(trackRepository.save(track1)).thenReturn(track1);
        when(trackRepository.findById(track1.getId())).thenReturn(optional);
        Track track2=trackService.updateTrack(track1,2);
        Assert.assertEquals(track1,track2);
        verify(trackRepository,times(1)).save(track2);
    }
}