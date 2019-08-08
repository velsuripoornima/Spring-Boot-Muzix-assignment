package com.stackroute.MusixAppAssignment.repository;

import com.stackroute.MusixAppAssignment.model.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TrackRepositoryTest {

   @Autowired
  private TrackRepository trackRepository;
   private  Track track;

   @Before
   public void setUp()
   {
       track = new Track();
       track.setId(10);
       track.setName("John");
       track.setId(101);
       track.setName("John");


   }

   @After
   public void tearDown(){

       trackRepository.deleteAll();
   }


   @Test
   public void testGetAllUser(){
       //Track u = new Track(1,"Johny","Jenny","Johny12","iwygedu",123);
       Track u1 = new Track(2,"Harry","Jenny","Harry12","abc",123);
       //trackRepository.save(u);
       trackRepository.save(u1);

       List<Track> list = trackRepository.findAll();
       Assert.assertEquals(u1,list.get(1));


   }

}
