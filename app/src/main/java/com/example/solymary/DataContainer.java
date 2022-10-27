package com.example.solymary;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.callback.Callback;

public class DataContainer  {
    private static DataContainer mData;
    private Context mContext;
    private RequestQueue requestQueue;
    private int cases[] = {0,0,0,0,0,0,0,0};
    private client aux_c;
    private prop aux_p;
    private costume aux_co;
    private rent aux_r;
    private season aux_se;
    private size aux_si;
    private product aux_pro;
    private ArrayList<String> used = new ArrayList<>();
    private ArrayList<String> values = new ArrayList<String>();
    private List<client> aux_client = new ArrayList<client>();
    private ArrayList<rent> aux_rent = new ArrayList<rent>();
    private ArrayList<product> aux_product = new ArrayList<product>();
    private ArrayList<prop> aux_prop = new ArrayList<prop>();
    private ArrayList<season> aux_season = new ArrayList<season>();
    private ArrayList<size> aux_size = new ArrayList<size>();
    private ArrayList<costume> aux_costume = new ArrayList<costume>();
    private static final String TAG ="DATACONTAINER";





    public static DataContainer get(Context context) {
        if (mData==null) {
            mData = new DataContainer(context);
        }
        return mData;
    }

    private DataContainer(Context context){
        mContext = context.getApplicationContext();
    }





    public int addUpdateClient(client client, int option, Interface_screens mClass, String cas){
        for(int i=0; i<used.size(); i++){
            if(used.get(i).compareTo(cas)==0){
                used.remove(i);
                break;
            }
        }
        values.clear();
        values.add(client.getId_client()+"");
        values.add(option+"");
        values.add(mContext.getString(R.string.client));
        values.add(client.getNames());
        values.add(client.getLastname());
        values.add(client.getAddress());
        values.add(client.getPhone());
        cud(complete(values), mClass, cas);
        switch (option){
            case 0:{
                if(aux_client.size()==0){
                    client.setId_client(1);
                    aux_client.add(client);
                }else {
                    client.setId_client(aux_client.get(aux_client.size() - 1).getId_client() + 1);
                    aux_client.add(client);
                }
                break;
            }
            case 1:{
                for(int i=0; i<aux_client.size(); i++){
                    if(aux_client.get(i).getId_client()==client.getId_client()){
                        aux_client.set(i, client);
                    }
                }
                break;
            }
            case 2:{
                for(int i=0; i<aux_client.size(); i++){
                    if(aux_client.get(i).getId_client()==client.getId_client()){
                        aux_client.remove(i);
                    }
                }
                break;
            }
        }
        return client.getId_client();
    }


    public int addUpdateRent(rent rent, int option, Interface_screens mClass, String cas){
        for(int i=0; i<used.size(); i++){
            if(used.get(i).compareTo(cas)==0){
                used.remove(i);
                break;
            }
        }
        values.clear();
        values.add(rent.id_rent+"");
        values.add(option+"");
        values.add(mContext.getString(R.string.rent));
        values.add(rent.getDay_give()+"");
        values.add(rent.getMonth_give()+"");
        values.add(rent.getYear_give()+"");
        values.add(rent.getDay_return()+"");
        values.add(rent.getMonth_return()+"");
        values.add(rent.getYear_return()+"");
        values.add(rent.getDays()+"");
        values.add(rent.getPrice()+"");
        values.add(rent.getQuantity()+"");
        values.add(rent.getId_client()+"");
        values.add(rent.getId_product()+"");
        cud(complete(values), mClass, cas);
        switch (option){
            case 0:{
                if(aux_rent.size()==0){
                    rent.setId_rent(1);
                    aux_rent.add(rent);
                }else {
                    rent.setId_rent(aux_rent.get(aux_rent.size() - 1).getId_rent() + 1);
                    aux_rent.add(rent);
                }
                break;
            }
            case 1:{
                for(int i=0; i<aux_rent.size(); i++){
                    if(aux_rent.get(i).getId_rent()==rent.getId_rent()){
                        aux_rent.set(i, rent);
                    }
                }
                break;
            }
            case 2:{
                for(int i=0; i<aux_rent.size(); i++){
                    if(aux_rent.get(i).getId_rent()==rent.getId_rent()){
                        aux_rent.remove(i);
                    }
                }
                break;
            }
        }
        return rent.getId_rent();
    }

    public int addUpdateProduct(product product, int option, Interface_screens mClass, String cas){
        for(int i=0; i<used.size(); i++){
            if(used.get(i).compareTo(cas)==0){
                used.remove(i);
                break;
            }
        }
        values.clear();
        values.add(product.id_product+"");
        values.add(option+"");
        values.add(mContext.getString(R.string.product));
        values.add(product.getStock_status());
        values.add(product.getId_size()+"");
        cud(complete(values), mClass, cas);
        switch (option){
            case 0:{
                if(aux_product.size()==0){
                    product.setId_product(1);
                    aux_product.add(product);
                }else {
                    product.setId_product(aux_product.get(aux_product.size() - 1).getId_product() + 1);
                    aux_product.add(product);
                }
                break;
            }
            case 1:{
                for(int i=0; i<aux_product.size(); i++){
                    if(aux_product.get(i).getId_product()==product.getId_product()){
                        aux_product.set(i, product);
                    }
                }
                break;
            }
            case 2:{
                for(int i=0; i<aux_product.size(); i++){
                    if(aux_product.get(i).getId_product()==product.getId_product()){
                        aux_product.remove(i);
                    }
                }
                break;
            }
        }
        return product.getId_product();
    }

    public int addUpdateCostume(costume costume, int option, Interface_screens mClass, String cas){
        for(int i=0; i<used.size(); i++){
            if(used.get(i).compareTo(cas)==0){
                used.remove(i);
                break;
            }
        }
        values.clear();
        values.add(costume.id_costume+"");
        values.add(option+"");
        values.add(mContext.getString(R.string.costume));
        values.add(costume.getName());
        values.add(costume.getRented()+"");
        values.add(costume.getId_season()+"");
        values.add(costume.getId_product()+"");
        cud(complete(values), mClass, cas);
        switch (option){
            case 0:{
                if(aux_costume.size()==0){
                    costume.setId_costume(1);
                    aux_costume.add(costume);
                }else {
                    costume.setId_costume(aux_costume.get(aux_costume.size() - 1).getId_costume() + 1);
                    aux_costume.add(costume);
                }
                break;
            }
            case 1:{
                for(int i=0; i<aux_costume.size(); i++){
                    if(aux_costume.get(i).getId_costume()==costume.getId_costume()){
                        aux_costume.set(i, costume);
                    }
                }
                break;
            }
            case 2:{
                for(int i=0; i<aux_costume.size(); i++){
                    if(aux_costume.get(i).getId_costume()==costume.getId_costume()){
                        aux_costume.remove(i);
                    }
                }
                break;
            }
        }
        return costume.getId_costume();
    }

    public int addUpdateProp(prop prop, int option, Interface_screens mClass, String cas){
        for(int i=0; i<used.size(); i++){
            if(used.get(i).compareTo(cas)==0){
                used.remove(i);
                break;
            }
        }
        values.clear();
        values.add(prop.id_prop+"");
        values.add(option+"");
        values.add(mContext.getString(R.string.prop));
        values.add(prop.getName());
        values.add(prop.getColor());
        values.add(prop.getQuantity_available()+"");
        values.add(prop.getQuantity_rented()+"");
        values.add(prop.getRented()+"");
        values.add(prop.getId_product()+"");
        cud(complete(values), mClass, cas);
        switch (option){
            case 0:{
                if(aux_prop.size()==0){
                    prop.setId_prop(1);
                    aux_prop.add(prop);
                }else {
                    prop.setId_prop(aux_prop.get(aux_prop.size() - 1).getId_prop() + 1);
                    aux_prop.add(prop);
                }
                break;
            }
            case 1:{
                for(int i=0; i<aux_prop.size(); i++){
                    if(aux_prop.get(i).getId_prop()==prop.getId_prop()){
                        aux_prop.set(i, prop);
                    }
                }
                break;
            }
            case 2:{
                for(int i=0; i<aux_prop.size(); i++){
                    if(aux_prop.get(i).getId_prop()==prop.getId_prop()){
                        aux_prop.remove(i);
                    }
                }
                break;
            }
        }
        return prop.getId_prop();
    }

    public int addSize(size size, Interface_screens mClass, String cas){
        for(int i=0; i<used.size(); i++){
            if(used.get(i).compareTo(cas)==0){
                used.remove(i);
                break;
            }
        }
        values.clear();
        values.add(size.getId_size()+"");
        values.add("0");
        values.add(mContext.getString(R.string.size));
        values.add(size.getDimension()+"");
        values.add(size.getUnit());
        cud(complete(values), mClass, cas);
        if(aux_size.size()==0){
            size.setId_size(1);
            aux_size.add(size);
        }else {
            size.setId_size(aux_size.get(aux_size.size() - 1).getId_size() + 1);
            aux_size.add(size);
        }
        return size.getId_size();
    }

    public int addSeason(season season, Interface_screens mClass, String cas){
        for(int i=0; i<used.size(); i++){
            if(used.get(i).compareTo(cas)==0){
                used.remove(i);
                break;
            }
        }
        values.clear();
        values.add(season.id_season+"");
        values.add("0");
        values.add(mContext.getString(R.string.season));
        values.add(season.getName());
        cud(complete(values), mClass, cas);
        if(aux_season.size()==0){
            season.setId_season(1);
            aux_season.add(season);
        }else {
            season.setId_season(aux_season.get(aux_season.size() - 1).getId_season() + 1);
            aux_season.add(season);
        }
        return season.getId_season();
    }



    public void deleteData(int id, String table, Interface_screens mClass, String cas){
        for(int i=0; i<used.size(); i++){
            if(used.get(i).compareTo(cas)==0){
                used.remove(i);
                break;
            }
        }
        values.clear();
        values.add(id+"");
        values.add("2");
        values.add(table);
        cud(complete(values), mClass, cas);
        switch (table){
            case "rent":{
                for(int i=0; i<aux_rent.size(); i++){
                    if(aux_rent.get(i).getId_rent()==id){
                        aux_rent.remove(i);
                        break;
                    }
                }
                break;
            }
        }
    }



    public client getClient(int id){
        for(int i=0; i<aux_client.size(); i++){
            if(aux_client.get(i).getId_client()==id){
                return aux_client.get(i);
            }
        }
        return null;
    }

    public rent getRent(int id){
        for(int i=0; i<aux_rent.size(); i++){
            if(aux_rent.get(i).getId_rent()==id){
                return aux_rent.get(i);
            }
        }
        return null;
    }
    public product getProduct(int id){
        for(int i=0; i<aux_product.size(); i++){
            if(aux_product.get(i).getId_product()==id){
                return aux_product.get(i);
            }
        }
        return null;
    }

    public costume getCostume(int id){
        for(int i=0; i<aux_costume.size(); i++){
            if(aux_costume.get(i).getId_costume()==id){
                return aux_costume.get(i);
            }
        }
        return null;
    }

    public prop getProp(int id){
        for(int i=0; i<aux_prop.size(); i++){
            if(aux_prop.get(i).getId_prop()==id){
                return aux_prop.get(i);
            }
        }
        return null;
    }

    public season getSeason(int id){
        for(int i=0; i<aux_season.size(); i++){
            if(aux_season.get(i).getId_season()==id){
                return aux_season.get(i);
            }
        }
        return null;
    }

    public size getSize(int id){
        for(int i=0; i<aux_size.size(); i++){
            if(aux_size.get(i).getId_size()==id){
                return aux_size.get(i);
            }
        }
        return null;
    }




    public void triggerGetsQuery(String table, Interface_screens mClass, String cas){
        query(table, "", mClass, cas);
        return;
    }

    List<client> getClients(){ return aux_client; }

    List<rent> getRents(){ return aux_rent; }

    List<product> getProducts(){ return aux_product; }

    List<costume> getCostumes(){ return aux_costume; }

    List<prop> getProps(){ return aux_prop; }

    List<season> getSeasons(){ return aux_season; }

    List<size> getSizes(){ return aux_size; }






    public void query(final String table, String where, final Interface_screens mClass, final String cas){
        String URL = "http://192.168.15.1:80/Solymary/query.php?query=SELECT * FROM "+ table + " " + where;
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                boolean make = true;
                for(int i=0; i<cases.length; i++){
                    if(Integer.parseInt(cas.trim())==cases[i]){
                        make = false;
                    }
                }
                if(make) {
                    for (int i = 0; i < cases.length; i++) {
                        if (cases[i] == 0) {
                            cases[i] = Integer.parseInt(cas.trim());
                            break;
                        }
                    }
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            jsonObject = response.getJSONObject(i);
                            switch (table) {
                                case "client": {
                                    client aux = new client();
                                    aux.setId_client(Integer.parseInt(jsonObject.getString("id_client").trim()));
                                    aux.setNames(jsonObject.getString("names"));
                                    aux.setLastname(jsonObject.getString("lastname"));
                                    aux.setAddress(jsonObject.getString("address"));
                                    aux.setPhone(jsonObject.getString("phone"));
                                    aux_client.add(aux);
                                    break;
                                }
                                case "rent": {
                                    rent aux = new rent();
                                    aux.setId_rent(Integer.parseInt(jsonObject.getString("id_rent").trim()));
                                    aux.setDay_give(Integer.parseInt(jsonObject.getString("day_give").trim()));
                                    aux.setMonth_give(Integer.parseInt(jsonObject.getString("month_give").trim()));
                                    aux.setYear_give(Integer.parseInt(jsonObject.getString("year_give").trim()));
                                    aux.setDay_return(Integer.parseInt(jsonObject.getString("day_return").trim()));
                                    aux.setMonth_return(Integer.parseInt(jsonObject.getString("month_return").trim()));
                                    aux.setYear_return(Integer.parseInt(jsonObject.getString("year_return").trim()));
                                    aux.setDays(Integer.parseInt(jsonObject.getString("days").trim()));
                                    aux.setPrice(Integer.parseInt(jsonObject.getString("price").trim()));
                                    aux.setQuantity(Integer.parseInt(jsonObject.getString("quantity").trim()));
                                    aux.setId_client(Integer.parseInt(jsonObject.getString("id_client").trim()));
                                    aux.setId_product(Integer.parseInt(jsonObject.getString("id_product").trim()));
                                    aux_rent.add(aux);
                                    break;
                                }
                                case "product": {
                                    product aux = new product();
                                    aux.setId_product(Integer.parseInt(jsonObject.getString("id_product").trim()));
                                    aux.setStock_status(jsonObject.getString("stock_status"));
                                    aux.setId_size(Integer.parseInt(jsonObject.getString("id_size").trim()));
                                    aux_product.add(aux);
                                    break;
                                }
                                case "costume": {
                                    costume aux = new costume();
                                    aux.setId_costume(Integer.parseInt(jsonObject.getString("id_costume").trim()));
                                    aux.setName(jsonObject.getString("name"));
                                    aux.setRented(Integer.parseInt(jsonObject.getString("rented").trim()));
                                    aux.setId_season(Integer.parseInt(jsonObject.getString("id_season").trim()));
                                    aux.setId_product(Integer.parseInt(jsonObject.getString("id_product").trim()));
                                    aux_costume.add(aux);
                                    break;
                                }
                                case "prop": {
                                    prop aux = new prop();
                                    aux.setId_prop(Integer.parseInt(jsonObject.getString("id_prop").trim()));
                                    aux.setName(jsonObject.getString("name"));
                                    aux.setColor(jsonObject.getString("color"));
                                    aux.setQuantity_available(Integer.parseInt(jsonObject.getString("quantity_available").trim()));
                                    aux.setQuantity_rented(Integer.parseInt(jsonObject.getString("quantity_rented").trim()));
                                    aux.setRented(Integer.parseInt(jsonObject.getString("rented").trim()));
                                    aux.setId_product(Integer.parseInt(jsonObject.getString("id_product").trim()));
                                    aux_prop.add(aux);
                                    break;
                                }
                                case "size": {
                                    size aux = new size();
                                    aux.setId_size(Integer.parseInt(jsonObject.getString("id_size").trim()));
                                    aux.setUnit(jsonObject.getString("unit"));
                                    aux.setDimension(Integer.parseInt(jsonObject.getString("dimension").trim()));
                                    aux_size.add(aux);
                                    break;
                                }
                                case "season": {
                                    season aux = new season();
                                    aux.setId_season(Integer.parseInt(jsonObject.getString("id_season").trim()));
                                    aux.setName(jsonObject.getString("name"));
                                    aux_season.add(aux);
                                    break;
                                }
                            }
                            mClass.callback(cas);
                        } catch (JSONException e) {
                            Log.e(TAG, e.toString());
                            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mClass.callback(cas);
            }
        });
        jsonArrayRequest.setRetryPolicy( new DefaultRetryPolicy(0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue= Volley.newRequestQueue(mContext);
        requestQueue.add(jsonArrayRequest);
    }




    public void cud(final ArrayList<String> values, final Interface_screens mClass, final String cas){
        String URL = "http://192.168.15.1:80/Solymary/cud.php";
        StringRequest postRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                boolean make = true;
                for(int i=0; i<used.size(); i++){
                    if(used.get(i).compareTo(cas)==0){
                        make = false;
                    }
                }
                if(make){
                    used.add(cas);
                    mClass.callback(cas);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters=new HashMap<String, String>();
                parameters.put("id", values.get(0));
                parameters.put("option", values.get(1));
                parameters.put("table", values.get(2));
                parameters.put("value1", values.get(3));
                parameters.put("value2", values.get(4));
                parameters.put("value3", values.get(5));
                parameters.put("value4", values.get(6));
                parameters.put("value5", values.get(7));
                parameters.put("value6", values.get(8));
                parameters.put("value7", values.get(9));
                parameters.put("value8", values.get(10));
                parameters.put("value9", values.get(11));
                parameters.put("value10", values.get(12));
                parameters.put("value11", values.get(13));
                return parameters;
            }
        };
        /*postRequest.setRetryPolicy( new DefaultRetryPolicy(0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));*/
        requestQueue= Volley.newRequestQueue(mContext);
        requestQueue.add(postRequest);
    }





    ArrayList<String> complete (ArrayList<String> values){
        for(int i=values.size(); i<14; i++){
            values.add(" ");
        }
        for(int i=0; i<values.size(); i++){
            if(values.get(i)==null){
                values.set(i, " ");
            }
        }
        return values;
    }



    public client getAux_c() { return aux_c; }
    public prop getAux_p() { return aux_p; }
    public costume getAux_co() { return aux_co; }
    public rent getAux_r() { return aux_r; }
    public void setAux_c(client aux_c) { this.aux_c = aux_c; }
    public void setAux_p(prop aux_p) { this.aux_p = aux_p; }
    public void setAux_co(costume aux_co) { this.aux_co = aux_co; }
    public void setAux_r(rent aux_r) { this.aux_r = aux_r; }
    public season getAux_se() { return aux_se; }
    public size getAux_si() { return aux_si; }
    public product getAux_pro() { return aux_pro; }
    public void setAux_se(season aux_se) { this.aux_se = aux_se; }
    public void setAux_si(size aux_si) { this.aux_si = aux_si; }
    public void setAux_pro(product aux_pro) { this.aux_pro = aux_pro; }
}
