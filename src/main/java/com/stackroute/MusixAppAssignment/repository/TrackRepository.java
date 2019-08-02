package com.stackroute.MusixAppAssignment.repository;

import com.stackroute.MusixAppAssignment.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository //it is saying as this class is repository class

public interface TrackRepository extends JpaRepository<Track,Integer> {

      //query for geeting all traks by name
    @Query(value = "SELECT * FROM track where name = ?1", nativeQuery = true )
    List<Track> findTitleByName(String name);

    
    //getting tracks by searching
    @Query(value = "SELECT * FROM track where (?1 is null or name = ?1) and (?2 = 0 or id = ?2)", nativeQuery = true)
    List<Track> findTitleByName(String name,int id);
}
