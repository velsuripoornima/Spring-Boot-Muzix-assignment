package com.stackroute.MusixAppAssignment.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
//this annotation is used to create a table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Track {
    @Id  //representing id as a primary key
    @Value("1")
    private int id;

    @Value("poornima")
    private String name;

    @Value("omreio")
    private String content;

    @Value("nmoireu")
    private String status;



}
