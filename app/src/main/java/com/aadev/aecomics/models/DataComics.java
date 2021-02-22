package com.aadev.aecomics.models;

import java.util.ArrayList;
import java.util.List;

public class DataComics {

    private float offset;
    private float limit;
    private float total;
    private float count;
    ArrayList < Comic > results = new ArrayList < Comic > ();

    public float getOffset() {
        return offset;
    }

    public void setOffset(float offset) {
        this.offset = offset;
    }

    public float getLimit() {
        return limit;
    }

    public void setLimit(float limit) {
        this.limit = limit;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getCount() {
        return count;
    }

    public void setCount(float count) {
        this.count = count;
    }

    public ArrayList<Comic> getResults() {
        return results;
    }

    public void setResults(ArrayList<Comic> results) {
        this.results = results;
    }
}
