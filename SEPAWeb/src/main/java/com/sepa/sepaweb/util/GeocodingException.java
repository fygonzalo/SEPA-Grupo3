package com.sepa.sepaweb.util;

public class GeocodingException extends Exception {

    private int errorCode;
    private String errorDesc;
    private GeocodingCode geocodingCode;

    public GeocodingException(GeocodingCode geocodingCode) {
        super(geocodingCode.toString());
        this.errorCode = geocodingCode.getNumber();
        this.errorDesc = geocodingCode.getDescription();
        this.geocodingCode = geocodingCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public GeocodingCode getGeocodingCode() {
        return geocodingCode;
    }

}
