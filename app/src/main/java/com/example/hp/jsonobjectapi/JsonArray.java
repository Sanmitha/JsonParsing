package com.example.hp.jsonobjectapi;

/**
 * Created by hp on 09-12-2017.
 */

public class JsonArray {
    String name;
    int id;
    String email;
    public void JsonArray(String name,int id, String email)
    {
this.name=name;
        this.id=id;
        this.email=email;
    }
    public String getName()
    {
        return name;
    }
    public int getId()
    {
        return id;
    }
    public String getEmail()
    {
        return email;
    }
}
