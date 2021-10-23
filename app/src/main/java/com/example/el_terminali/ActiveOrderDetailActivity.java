package com.example.el_terminali;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class ActiveOrderDetailActivity extends AppCompatActivity {

    ListView listViewActiveOrderDetails;
    ProgressBar progressBarAOD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_order_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        listViewActiveOrderDetails = findViewById(R.id.listViewActiveOrderDetails);
        progressBarAOD = findViewById(R.id.progressBarAOD);
    }

    public static class AdapterSelectedOrder extends BaseAdapter {

        private LayoutInflater mInflater;
        private List<ActiveOrderDetailObject> mKisiListesi;

        public AdapterSelectedOrder(Activity activity, List<ActiveOrderDetailObject> kisiler) {
            mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mKisiListesi = kisiler;
        }

        @Override
        public int getCount() {
            return mKisiListesi.size();
        }

        @Override
        public ActiveOrderDetailObject getItem(int position) {
            return mKisiListesi.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View item;
            item = mInflater.inflate(R.layout.item_active_order_detail, null);

            TextView _belgeNo = (TextView) item.findViewById(R.id.itemCodeAOD);
            TextView _desc = (TextView) item.findViewById(R.id.itemDescAOD);
            TextView _warehouse = (TextView) item.findViewById(R.id.quantitiyAOD);
            TextView _quantity = (TextView) item.findViewById(R.id.wareHouseCodeAOD);

            ActiveOrderDetailObject kisi = mKisiListesi.get(position);

            _belgeNo.setText("Item Code: " + kisi.getItemCode());
            _desc.setText("" + kisi.getItemDescription());

            if (kisi.getInputQuantity() > (int) kisi.getInputQuantity()) {
                if (kisi.getQuantity() > (int) kisi.getQuantity()) {
                    _warehouse.setText("Quantity: " + kisi.getInputQuantity() + " / " + kisi.getQuantity());
                } else {
                    _warehouse.setText("Quantity: " + kisi.getInputQuantity() + " / " + (int) kisi.getQuantity());
                }
            } else {


                if (kisi.getQuantity() > (int) kisi.getQuantity()) {
                    _warehouse.setText("Adet: " + (int) kisi.getInputQuantity() + " / " + kisi.getQuantity());
                } else {
                    _warehouse.setText("Adet: " + (int) kisi.getInputQuantity() + " / " + (int) kisi.getQuantity());
                }
            }
            _quantity.setText("Warehouse: " + kisi.getWarehouseCode());

            if (kisi.getInputQuantity() > kisi.getQuantity()) {
                item.setBackgroundColor(Color.argb(120, 255, 0, 0));
            } else if (kisi.getInputQuantity() == kisi.getQuantity()) {
                item.setBackgroundColor(Color.argb(120, 0, 255, 0));
            } else if (kisi.getInputQuantity() < kisi.getQuantity()) {
                if (kisi.getInputQuantity() == 0) {
                    item.setBackgroundColor(Color.argb(255, 255, 255, 255));
                } else {
                    item.setBackgroundColor(Color.argb(120, 255, 255, 0));
                }
            }

            return item;
        }


    }

}