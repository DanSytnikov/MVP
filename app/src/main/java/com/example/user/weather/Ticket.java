package com.example.user.weather;

public class Ticket {
    String wDate;
    Double wTemp;
    Double wPressure;
    Integer wHumidity;
    String wDescr;
    double wWind;
    String wIcon;

    Ticket (String _wDate, Double _wTemp, Double _wPressure, Integer _wHumidity, String _wDescr, double _wWind, String _wIcon){
        wDate = _wDate;
        wTemp = _wTemp;
        wPressure = _wPressure;
        wHumidity =_wHumidity;
        wDescr = _wDescr;
        wWind = _wWind;
        wIcon = _wIcon;
    }
}
