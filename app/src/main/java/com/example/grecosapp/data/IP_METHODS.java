package com.example.grecosapp.data;

import android.content.Context;
import android.icu.lang.UScript;
import android.os.NetworkOnMainThreadException;
import android.widget.Toast;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IP_METHODS {

    double Lat = 0;
    double Lon = 0;

    static public String GET_PUBLIC_IP(Context context){
        String ip = "";
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        ip = addr.getHostAddress();
                    }
                }
            }
        } catch (Exception ex) { } // for now eat exceptions
        return ip;
    }


    public static boolean CHECK_BOT() {
        JSONObject json = GET_IP_CHECK();
        try {
            if(json.get("is-bot").equals("true"))
                return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;

    }
    
    static public Integer checkBlacklist(){
        //JSONObject json = GET_IP_BLOCKLIST_DATA();
        JSONObject json = GET_IP_CHECK();
        try {
            JSONObject blacklist = (JSONObject) json.get("is-spam-bot");
            return (Integer) blacklist.get("blocklists");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return CONTROLL_ERROR_();
    }



    static public Boolean CHECK_VPN(){
        JSONObject json = GET_IP_CHECK();
        try {
            Boolean out = (Boolean) json.get("is-tor");
            if(out)
                return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    static public Boolean CHECK_TOR(){
        JSONObject json = GET_IP_CHECK();
        try {
            Boolean out = (Boolean) json.get("is-tor");
            if(out)
                return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    static public Boolean CHECK_PROXY(){
        JSONObject json = GET_IP_CHECK();
        try {
            Boolean out = (Boolean) json.get("is-proxy");
            if(out)
                return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return CONTROLL_ERROR();
     }

    static public String CHECK_ISP(){
        JSONObject json = GET_IP_LEGIT();
        try {
            return (String) json.get("ISP");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "Not data available";
    }

    static public ArrayList<Double> GET_DAT_GEO(){
        JSONObject json = GET_IP_LEGIT();
        ArrayList<Double> toReturn = new ArrayList<Double>();
        try {
            toReturn.add((Double) json.get("Latitude"));
            toReturn.add((Double) json.get("Longitude"));

            return toReturn;
        } catch (NullPointerException | JSONException e) {
            e.printStackTrace();
        }
        return GET_IP_LEGIT_GEO();
    }

    private static JSONObject GET_IP_BLOCKLIST_DATA(){
        String IP = GET_PUBLIC_IP(null);
        try {
            HttpResponse<String> response = Unirest.get("https://tony11-blacklist-ip-v1.p.rapidapi.com/ipv4/" + IP)
                    .header("x-rapidapi-key", "API-KEY")
                    .header("x-rapidapi-host", "tony11-blacklist-ip-v1.p.rapidapi.com")
                    .asString();
            /* {
                "status":"OK"
                "content":{
                            "blacklisted":0
                           }
            } */
            try {
                JSONObject json = new JSONObject(response.getBody());
                return json;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static JSONObject GET_IP_LEGIT() {
        String IP = GET_PUBLIC_IP(null);
        try {
            String URL = "https://iprisk1.p.rapidapi.com/getipinfo/json/" + IP + "/LINK";
            HttpResponse<String> response = Unirest.get(URL)
                    .header("x-rapidapi-key", "API-KEY")
                    .header("x-rapidapi-host", "iprisk1.p.rapidapi.com")
                    .asString();
            /* {
                "IpAddress":"130.X.X.X"
                "Proxy":"no"
                "ProxyType":""
                "CountryCode":"IT"
                "CountryName":"Italy"
                "RegionName":"Sicilia"
                "CityName":"Catania"
                "Latitude":"37.50213"
                "Longitude":"15.08719"
                "AreaCode":"0095"
                "Iddcode":"39"
                "WeatherStationCode":"ITXX0017"
                "WeatherStationName":"Catania"
                "MCC":"222"
                "MNC":"10"
                "MobileCarrier":"Vodafone"
                "NetSpeed":"DSL"
                "TimeZone":"+02:00"
                "ZipCode":"95131"
                "DomainName":"vodafone.it"
                "ASN":"30722"
                "ASNOrganization":""
                "ISP":"Vodafone Italia S.p.A."
            } */
            try {
                JSONObject json = new JSONObject(response.getBody());
                return json;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static JSONObject GET_IP_CHECK(){
        String IP = GET_PUBLIC_IP(null);
        try {
            HttpResponse<String> response = Unirest.post("https://neutrinoapi-ip-blocklist.p.rapidapi.com/ip-blocklist")
                    .header("content-type", "application/x-www-form-urlencoded")
                    .header("x-rapidapi-key", "API-KEY")
                    .header("x-rapidapi-host", "neutrinoapi-ip-blocklist.p.rapidapi.com")
                    .body("ip=" + IP)
                    .asString();
            /* {
                "is-hijacked":false,
                "is-spider":false,
                "is-tor":false,
                "is-dshield":false,
                "is-vpn":false,
                "ip":"201.218.218.198",
                "is-spyware":false,
                "is-spam-bot":false,
                    "blocklists":["proxy"],
                "last-seen":1620124984,
                "is-bot":false,
                    "sensors":[{"blocklist":"proxy","description":"Open HTTP proxy","id":8}],
                "list-count":1,
                "is-proxy":true,
                "is-malware":false,
                "is-listed":true,
                "is-exploit-bot":false
            } */
            try {
                JSONObject json = new JSONObject(response.getBody());
                return json;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

    static public void WAIT_A_SECOND(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static public ArrayList<Double> GET_DATA_GEO(){
        ArrayList<Double> data = GET_IP_LEGIT_GEO();
        try {
            return data;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ArrayList<Double> GET_IP_LEGIT_GEO() {
        ArrayList<Double> a = new ArrayList<Double>();
        a.add(37.50);
        a.add(15.08);
        return a;
    }

    private static Boolean CONTROLL_ERROR() {
        return true;
    }
    private static Integer CONTROLL_ERROR_() {
        return 1;
    }
}
