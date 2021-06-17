package com.fc.carinfo.inventory.Helper;

import com.fc.carinfo.inventory.Inventory;

public class InventoryHelper {
    public int getUsableCapa(Inventory inventory){
        return inventory.getCapacity() - inventory.getCurrent();
    }
}
