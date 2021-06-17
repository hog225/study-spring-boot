package com.fc.carinfo;

import com.fc.carinfo.inventory.Helper.InventoryHelper;
import com.fc.carinfo.inventory.Inventory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InventoryHelperTests {
    @Test
    void InventoryHelper_getUsableCapa(){
        InventoryHelper inventoryHelper = new InventoryHelper();
        Inventory inventory = new Inventory();
        inventory.setCapacity(10);
        inventory.setCurrent(5);
        int useable = inventoryHelper.getUsableCapa(inventory);

        Assertions.assertEquals(5, useable);
    }
}
