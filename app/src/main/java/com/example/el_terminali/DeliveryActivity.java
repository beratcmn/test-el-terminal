package com.example.el_terminali;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class DeliveryActivity extends AppCompatActivity {

    ServiceLayer serviceLayer = new ServiceLayer();
    ListView listView_ActiveOrders;
    ProgressBar progressBarDelivery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        serviceLayer.context = this;
        listView_ActiveOrders = findViewById(R.id.listViewActiveOrders);
        progressBarDelivery = findViewById(R.id.deliveryProgressBar);

        serviceLayer.GetActiveOrders();

        listView_ActiveOrders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                serviceLayer.GetActiveOrderDetails(serviceLayer.activeOrders.value.get(i));
            }
        });
    }

    public static class AdapterActiveOrders extends BaseAdapter {

        private LayoutInflater mInflater;
        private List<ActiveOrder.ActiveOrderObject> mKisiListesi;

        public AdapterActiveOrders(Activity activity, List<ActiveOrder.ActiveOrderObject> kisiler) {
            mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mKisiListesi = kisiler;
        }

        @Override
        public int getCount() {
            return mKisiListesi.size();
        }

        @Override
        public ActiveOrder.ActiveOrderObject getItem(int position) {
            return mKisiListesi.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View satirView;
            satirView = mInflater.inflate(R.layout.item_active_order, null);


            TextView _belgeNo = (TextView) satirView.findViewById(R.id.belgeNo);
            TextView _tarih = (TextView) satirView.findViewById(R.id.tarih);
            TextView _cardCode = (TextView) satirView.findViewById(R.id.cardCode);
            TextView _cardName = (TextView) satirView.findViewById(R.id.cardName);

            ActiveOrder.ActiveOrderObject kisi = mKisiListesi.get(position);

            _belgeNo.setText("Doc. Number: " + kisi.getDocNum());
            _tarih.setText(kisi.getDocDate());
            _cardCode.setText("Cardcode: " + kisi.getCardCode());
            _cardName.setText(kisi.getCardName());
            return satirView;
        }


    }

    public void ShowAOD(Intent aodIntent) {
        startActivity(aodIntent);
    }
}