package com.fc.carinfo;

import com.fc.carinfo.inventory.Inventory;
import com.fc.carinfo.inventory.type.InventoryTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InventoryAppTest {
    @Test
    void 인벤토리_사이즈_테스트(){
        Inventory inventory = new Inventory();

        inventory.setLength(10);
        inventory.setWidth(10);
        inventory.setHeight(10);

        Assertions.assertEquals(10, inventory.getLength());
        Assertions.assertEquals(10, inventory.getWidth());
        Assertions.assertEquals(10, inventory.getHeight());
    }

    @Test
    void 인벤토리_타입_테스트(){
        Inventory inventory = new Inventory();

        inventory.setType(InventoryTypeEnum.COLD);

        Assertions.assertEquals(InventoryTypeEnum.COLD, inventory.getType());


    }

    @Test
    void 인벤토리_총_저장수량_타입_테스트(){
        Inventory inventory = new Inventory();
        inventory.setCapacity(10);


        Assertions.assertEquals(10, inventory.getCapacity());


    }

    @Test
    void 인벤토리_현제_저장수량_타입_테스트(){
        Inventory inventory = new Inventory();
        inventory.setCurrent(10);


        Assertions.assertEquals(10, inventory.getCurrent());
    }

//    @Test
//    void 사용가능한_저장공간(){
//        Inventory inventory = new Inventory();
//        inventory.setCurrent(10);
//        inventory.setCapacity(10);
//        inventory.setCurrent(5);
//        Assertions.assertEquals(5, inventory.getUsableCapa());
//
//    }
}
