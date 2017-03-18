package com.betterclever.aparoksha.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Organizer {
    
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("contact")
    @Expose
    private String contact;
    
    /**
     * No args constructor for use in serialization
     *
     */
    public Organizer() {
    }
    
    /**
     *
     * @param phone
     * @param name
     */
    public Organizer(String name, String phone) {
        super();
        this.name = name;
        this.contact = phone;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getContact() {
        return contact;
    }
    
    public void setContact(String contact) {
        this.contact = contact;
    }
    
}