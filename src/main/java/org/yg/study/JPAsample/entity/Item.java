package org.yg.study.JPAsample.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Persistence;

@Entity
@NoArgsConstructor
public class Item extends BaseEntity implements Persistable<String>  {

    @Id
    private String id;

    @Override
    public String getId(){
        return id;
    }

    @Override
    public boolean isNew(){
        return createdDate == null;
    }

    public Item(String id) {
        this.id = id;
    }
}
