package com.stackroute.MusixAppAssignment.service;

import com.stackroute.MusixAppAssignment.model.Track;
import com.stackroute.MusixAppAssignment.model.TrackDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TrackMapper {
//creating the all the methods
    TrackDto trackToTrackDto(Track track);
    Track trackDtoToTrack(TrackDto trackDto);
    List<Track> trackDtoListToTrackList(List<TrackDto> trackDtoList);
}
