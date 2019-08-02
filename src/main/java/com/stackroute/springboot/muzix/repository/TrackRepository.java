package com.stackroute.springboot.muzix.repository;

import com.stackroute.springboot.muzix.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //it is saying as this class is repository class

public interface TrackRepository extends JpaRepository<Track,Integer> {


    @Query(value = "SELECT * FROM Track  where name = ?1", nativeQuery = true )
    List<Track> findTitleByName(String name);
}
