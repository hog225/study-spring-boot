package org.yg.practice.flyway.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.yg.practice.flyway.models.*;

@Repository
public interface BookDao extends CrudRepository<Book, Long>{
    

    
}