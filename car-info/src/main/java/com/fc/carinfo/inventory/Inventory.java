package com.fc.carinfo.inventory;

import com.fc.carinfo.inventory.type.InventoryTypeEnum;
import lombok.Data;

@Data
public class Inventory {
    private int length;
    private int width;
    private int height;
    private InventoryTypeEnum type;
    private int capacity;
    private int current;

    // helper에 있음 !!!!!!!!!!!!!!!!!!!
//    public int getUsableCapa(){
//        return capacity - current;
//    }
}
