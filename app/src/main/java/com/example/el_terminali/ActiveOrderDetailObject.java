package com.example.el_terminali;

public class ActiveOrderDetailObject {
    private float LineNum;
    private String ItemCode;
    private String ItemDescription;
    private float Quantity;
    private float InputQuantity;
    private String WarehouseCode;
    private String UoMCode;
    private float UoMEntry;

    // Getter Methods

    public float getLineNum() {
        return LineNum;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public String getItemDescription() {
        return ItemDescription;
    }

    public float getQuantity() {
        return Quantity;
    }

    public float getInputQuantity() {
        return InputQuantity;
    }

    public String getWarehouseCode() {
        return WarehouseCode;
    }

    public String getUoMCode() {
        return UoMCode;
    }

    public float getUoMEntry() {
        return UoMEntry;
    }

    // Setter Methods

    public void setLineNum(float LineNum) {
        this.LineNum = LineNum;
    }

    public void setItemCode(String ItemCode) {
        this.ItemCode = ItemCode;
    }

    public void setItemDescription(String ItemDescription) {
        this.ItemDescription = ItemDescription;
    }

    public void setQuantity(float Quantity) {
        this.Quantity = Quantity;
    }

    public void setInputQuantity(float InputQuantity) {
        this.InputQuantity = InputQuantity;
    }

    public void setWarehouseCode(String WarehouseCode) {
        this.WarehouseCode = WarehouseCode;
    }

    public void setUoMCode(String UoMCode) {
        this.UoMCode = UoMCode;
    }

    public void setUoMEntry(float UoMEntry) {
        this.UoMEntry = UoMEntry;
    }
}
