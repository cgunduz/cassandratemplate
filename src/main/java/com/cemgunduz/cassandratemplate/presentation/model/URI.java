package com.cemgunduz.cassandratemplate.presentation.model;

/**
 * Created by cem.gunduz on 22.06.2015.
 */
public enum URI {

    HOMEPAGE("index");

    private String address;

    private URI(String address)
    {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString()
    {
        return address;
    }
}
