package com.stackroute.MusixAppAssignment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
@Document
@Builder

public class TrackDto {
    private static int counter = 0;

    private int id;

    private String name;

    private String artist;
    private String url;
    private String streamable;
    private int listeners;

    public TrackDto() {
        id = ++counter;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        TrackDto.counter = counter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStreamable() {
        return streamable;
    }

    public void setStreamable(String streamable) {
        this.streamable = streamable;
    }

    public int getListeners() {
        return listeners;
    }

    public void setListeners(int listeners) {
        this.listeners = listeners;
    }
}
