package com.montbazon.gg_shark;

public class Deal {

    public Deal(String n,String p,String i,String s,String note)
    {
        name = n;
        price = p;
        imageURL =i;
        storeid = s;
        this.note = note;
    }

    public String name;
    public String price;
    public String imageURL;
    public String storeid;
    public String note;

}
