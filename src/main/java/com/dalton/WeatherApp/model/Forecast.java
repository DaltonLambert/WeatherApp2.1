package com.dalton.WeatherApp.model;

public class Forecast {

    private double high;
    private double low;
    private String icon;
    public Forecast(double high, double low, String icon) {
        this.high = high;
        this.low = low;
        this.icon =icon;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "high=" + high +
                ", low=" + low +
                '}';
    }


}
