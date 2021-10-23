package com.example.el_terminali;

import java.util.ArrayList;

public class ActiveOrder {
    private String odatametadata;
    ArrayList<ActiveOrderObject> value = new ArrayList<ActiveOrderObject>();


    public String getOdatametadata() {
        return odatametadata;
    }

    public void setOdatametadata(String odatametadata) {
        this.odatametadata = odatametadata;
    }

    public static class ActiveOrderObject {
        private float DocEntry;
        private float DocNum;
        private String DocDate;
        private String DocDueDate;
        private String CardCode;
        private String CardName;
        private float DocTotal;

        public float getDocEntry() {
            return DocEntry;
        }

        public float getDocNum() {
            return DocNum;
        }

        public String getDocDate() {
            return DocDate;
        }

        public String getDocDueDate() {
            return DocDueDate;
        }

        public String getCardCode() {
            return CardCode;
        }

        public String getCardName() {
            return CardName;
        }

        public float getDocTotal() {
            return DocTotal;
        }

        // Setter Methods

        public void setDocEntry(float DocEntry) {
            this.DocEntry = DocEntry;
        }

        public void setDocNum(float DocNum) {
            this.DocNum = DocNum;
        }

        public void setDocDate(String DocDate) {
            this.DocDate = DocDate;
        }

        public void setDocDueDate(String DocDueDate) {
            this.DocDueDate = DocDueDate;
        }

        public void setCardCode(String CardCode) {
            this.CardCode = CardCode;
        }

        public void setCardName(String CardName) {
            this.CardName = CardName;
        }

        public void setDocTotal(float DocTotal) {
            this.DocTotal = DocTotal;
        }
    }
}
