package com.example.el_terminali;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cookie;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ServiceLayer {
    public Activity context;
    public boolean loggedIn;

    OkHttpClient mainClient;
    ActiveOrder activeOrders;
    ActiveOrderDetail activeOrderDetails;


    public void Login() throws IOException {
        Handler handler = new Handler();
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    final SharedPreferences preferences = context.getSharedPreferences("LoginParams", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();

                    mainClient = UnsafeHttpClient();

                    String url = preferences.getString("URL", "") + "/b1s/v1/Login";
                    final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

                    String _loginParams = "{\"CompanyDB\":\"PROD_EFAT_TEST\",\"UserName\":\"" + preferences.getString("UserName", "") + "\",\"Password\":\"" + preferences.getString("Password", "") + "\"}";

                    RequestBody formBody = RequestBody.create(JSON, _loginParams);
                    Request request = new Request.Builder().url(url).post(formBody).build();
                    Response response = mainClient.newCall(request).execute();
                    String responseBody = response.body().string();

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Cookie cookie_b1sid = Cookie.parse(response.request().url(), response.headers("Set-Cookie").get(0));
                                Cookie cookie_routeid = Cookie.parse(response.request().url(), response.headers("Set-Cookie").get(1));

                                editor.putString("CookieB1SID", cookie_b1sid.value()); //B1SESSIONID
                                editor.putString("CookieRID", cookie_routeid.value()); //ROUTEID
                                editor.commit();

                                if (response.code() == 200) {
                                    loggedIn = true;
                                }
                            } catch (Exception ignored) {
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    public void GetActiveOrders() {
        //List<ActiveOrder.ActiveOrderObject> activeOrders = new ArrayList<ActiveOrder.ActiveOrderObject>();

        Handler handler = new Handler();
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    final SharedPreferences preferences = context.getSharedPreferences("LoginParams", Context.MODE_PRIVATE);
                    OkHttpClient client = UnsafeHttpClient();
                    String url = preferences.getString("URL", "") + "/b1s/v1/Orders()?$select=DocEntry,DocNum,DocDate,DocDueDate,CardCode,CardName,DocTotal&$filter=DocumentStatus eq 'O'";
                    Request request = new Request.Builder().url(url).addHeader("Cookie", "B1SESSION=" + preferences.getString("CookieB1SID", "")).addHeader("Cookie", "ROUTEID=" + preferences.getString("CookieRID", "")).build();

                    try (Response response = client.newCall(request).execute()) {
                        String responseBody = response.body().string();

                        Gson gson = new Gson();
                        activeOrders = gson.fromJson(responseBody, ActiveOrder.class);

                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (context.getClass() == DeliveryActivity.class) {
                                    DeliveryActivity.AdapterActiveOrders adaptorumuz = new DeliveryActivity.AdapterActiveOrders(context, activeOrders.value);
                                    DeliveryActivity currentDeliveryActivity = (DeliveryActivity) context;
                                    if (activeOrders.value.size() > 0) {
                                        currentDeliveryActivity.listView_ActiveOrders.setAdapter(adaptorumuz);
                                        currentDeliveryActivity.progressBarDelivery.setVisibility(View.INVISIBLE);
                                    } else {
                                        currentDeliveryActivity.progressBarDelivery.setVisibility(View.VISIBLE);
                                    }
                                }
                            } catch (Exception ignore) {
                            }
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    public void GetActiveOrderDetails(ActiveOrder.ActiveOrderObject _docOBJ) {
        ActiveOrderDetailActivity newAOD = new ActiveOrderDetailActivity();

        Handler handler = new Handler();
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    final SharedPreferences preferences = context.getSharedPreferences("LoginParams", Context.MODE_PRIVATE);
                    OkHttpClient client = UnsafeHttpClient();
                    String url = preferences.getString("URL", "") + "/b1s/v1/$crossjoin(Orders,Orders/DocumentLines)?$expand=Orders/DocumentLines($select=LineNum,ItemCode,ItemDescription,Quantity,WarehouseCode,UoMCode,UoMEntry)&$filter= Orders/DocEntry  eq Orders/DocumentLines/DocEntry and  Orders/DocEntry eq " + (int) _docOBJ.getDocEntry();
                    Request request = new Request.Builder().url(url).addHeader("Cookie", "B1SESSION=" + preferences.getString("CookieB1SID", "")).addHeader("Cookie", "ROUTEID=" + preferences.getString("CookieRID", "")).build();

                    try (Response response = client.newCall(request).execute()) {

                        String responseBody = response.body().string();

                        responseBody = responseBody.replace("Orders/DocumentLines", "OrdersDocumentLines");
                        responseBody = responseBody.replace("odata.metadata", "odatametadata");
                        Gson gson = new Gson();
                        activeOrderDetails = gson.fromJson(responseBody, ActiveOrderDetail.class);
                     test   tt = gson.fromJson(responseBody, test.class);
                        List<ActiveOrderDetailObject> activeOrderDetailObjects = new ArrayList<ActiveOrderDetailObject>();

                        for (int i = 0; i < activeOrderDetails.value.size(); i++) {
                            LinkedTreeMap<Object, Object> t = (LinkedTreeMap<Object, Object>) activeOrderDetails.value.get(i);
                            ActiveOrderDetailObject a = new ActiveOrderDetailObject();
                            a.setLineNum((Float) t.get("LineNum"));
                            a.setItemCode((String) t.get("ItemCode"));
                            a.setItemDescription((String) t.get("ItemDescription"));
                            a.setQuantity((Float) t.get("Quantity"));
                            a.setInputQuantity(0);
                            a.setWarehouseCode((String) t.get("WarehouseCode"));
                            a.setUoMCode((String) t.get("UoMCode"));
                            a.setUoMEntry((Float) t.get("UoMEntry"));

                            activeOrderDetailObjects.add(a);
                        }
                        activeOrderDetailObjects.get(1);
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Database database = new Database(context);
                                //database.PassActiveOrders(activeOrderDetails.value, _docOBJ);

                            } catch (Exception ignore) {
                                Log.i("BERAT", ignore.toString());
                            }
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    private static OkHttpClient UnsafeHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain,
                                                       String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain,
                                                       String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            return new OkHttpClient.Builder()
                    .sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0])
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    }).cookieJar(new MyCookieJar()).build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
