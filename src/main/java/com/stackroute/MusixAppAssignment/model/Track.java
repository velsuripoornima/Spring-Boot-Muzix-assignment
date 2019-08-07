package com.stackroute.MusixAppAssignment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity
//this annotation is used to create a table
@Data
@Builder   
@NoArgsConstructor  //it creates constructor with out any arguments
@AllArgsConstructor   //it creates constructor with arguments
@Getter  //create geeters
@Setter  //it creates setters

//it ignores all unwanted data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Track {
    @Id  //representing id as a primary key
    @GeneratedValue(strategy=GenerationType.AUTO)  //generating automatic values 
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    private String name;

    private String artist;

    private String url;
    private String streamable;
    private int listeners;



}
