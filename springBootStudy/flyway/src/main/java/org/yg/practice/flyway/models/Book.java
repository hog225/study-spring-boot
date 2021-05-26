package org.yg.practice.flyway.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data; 
import java.util.*;

@Entity
@Data
public class Book {
    @Id
    @Column
    private Long id;

    @Column
    private String name;
    
    @Column
    private String author;

}