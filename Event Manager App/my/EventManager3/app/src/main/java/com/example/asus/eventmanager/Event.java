package com.example.asus.eventmanager;

public class Event {

    private String event_name;
    private String event_des;
    private String image;




    public Event(){


    }



    public  Event(String event_name,String event_des,String image)
    {

       this.event_name=event_name;
       this.event_des=event_des;
       this.image=image;



    }


    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_des() {
        return event_des;
    }

    public void setEvent_des(String event_des) {
        this.event_des = event_des;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }





}
