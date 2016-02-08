package com.rideable.server.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Message implements Serializable {

    public List<String> registration_ids;
    public Map<String,String> data;

    public void addRegId(String regId){
        if(registration_ids == null)
            registration_ids = new LinkedList<String>();
	        registration_ids.add(regId);
    }

    public void createData(String name, String message, String adId){
        if(data == null){
            data = new HashMap<String,String>();
        }
        data.put("adID", adId);
        data.put("name", name);
        data.put("message", message);
    }	

}
