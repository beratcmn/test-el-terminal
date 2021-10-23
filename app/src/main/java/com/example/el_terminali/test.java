package com.example.el_terminali;

public class test {
    @com.google.gson.annotations.SerializedName("odata.metadata")
    private String _$OdataMetadata178;// FIXME check this code
    @com.google.gson.annotations.SerializedName("value")
    private java.util.List<ValueDTO> value;

    // FIXME generate failure  field _$OrdersDocumentLines100
    public static class ValueDTO {
        public static class Orders{
                @com.google.gson.annotations.SerializedName("LineNum")
                private Integer lineNum;
                @com.google.gson.annotations.SerializedName("ItemCode")
                private String ıtemCode;
                @com.google.gson.annotations.SerializedName("ItemDescription")
                private String ıtemDescription;
                @com.google.gson.annotations.SerializedName("Quantity")
                private Integer quantity;
                @com.google.gson.annotations.SerializedName("WarehouseCode")
                private String warehouseCode;
                @com.google.gson.annotations.SerializedName("UoMCode")
                private String uoMCode;
                @com.google.gson.annotations.SerializedName("UoMEntry")
                private Integer uoMEntry;

                public static Orders objectFromData(String str) {

                    return new com.google.gson.Gson().fromJson(str, Orders.class);
                }

                public static java.util.List<Orders> arrayOrdersFromData(String str) {

                    java.lang.reflect.Type listType = new com.google.gson.reflect.TypeToken<java.util.ArrayList<Orders>>() {
                    }.getType();

                    return new com.google.gson.Gson().fromJson(str, listType);
                }

                public Integer getLineNum() {
                    return lineNum;
                }

                public void setLineNum(Integer lineNum) {
                    this.lineNum = lineNum;
                }

                public String getItemCode() {
                    return ıtemCode;
                }

                public void setItemCode(String ıtemCode) {
                    this.ıtemCode = ıtemCode;
                }

                public String getItemDescription() {
                    return ıtemDescription;
                }

                public void setItemDescription(String ıtemDescription) {
                    this.ıtemDescription = ıtemDescription;
                }

                public Integer getQuantity() {
                    return quantity;
                }

                public void setQuantity(Integer quantity) {
                    this.quantity = quantity;
                }

                public String getWarehouseCode() {
                    return warehouseCode;
                }

                public void setWarehouseCode(String warehouseCode) {
                    this.warehouseCode = warehouseCode;
                }

                public String getUoMCode() {
                    return uoMCode;
                }

                public void setUoMCode(String uoMCode) {
                    this.uoMCode = uoMCode;
                }

                public Integer getUoMEntry() {
                    return uoMEntry;
                }

                public void setUoMEntry(Integer uoMEntry) {
                    this.uoMEntry = uoMEntry;
                }
    }}
}
