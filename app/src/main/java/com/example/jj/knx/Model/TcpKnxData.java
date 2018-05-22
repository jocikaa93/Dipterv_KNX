package com.example.jj.knx.Model;

import com.google.gson.annotations.SerializedName;

public class TcpKnxData {

    @SerializedName("idetifier")
    private String identifier;
    @SerializedName("destination_address")
    private String destinationAddress;
    @SerializedName("value")
    private Integer value;

    public TcpKnxData(String identifier, String destinationAddress, Integer value) {
        this.identifier = identifier;
        this.destinationAddress = destinationAddress;
        this.value = value;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public Integer getValue() {
        return value;
    }
}
