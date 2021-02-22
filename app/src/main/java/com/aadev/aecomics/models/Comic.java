package com.aadev.aecomics.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Comic implements Serializable {

    public String id;
    public String title;
    public String description;
    public ArrayList< Prices > prices = new ArrayList < Prices > ();
    public ArrayList< Images > images = new ArrayList < Images > ();
    public Thumbnail thumbnail;
    public boolean rare;
}
