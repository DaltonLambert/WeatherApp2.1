package com.dalton.WeatherApp.model;

public class Forecast {

    private int high;
    private int low;
    public Forecast(int high, int low) {
        this.high = high;
        this.low = low;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "high=" + high +
                ", low=" + low +
                '}';
    }


}
