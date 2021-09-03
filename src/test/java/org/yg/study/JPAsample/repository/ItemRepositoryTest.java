package org.yg.study.JPAsample.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.yg.study.JPAsample.entity.Item;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    // 주의
    // 아래처럼 Entity에 GeneratedValue 가 없이 PK 를 직접 지정하면 em.merger 로 Save 가 동작한다. 이렇게 되면 조회 후 insert 함으로 성능에 손해다
    @Test
    public void save() {
        Item item = new Item("A");
        itemRepository.save(item);


    }

}