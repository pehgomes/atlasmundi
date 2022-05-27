package br.com.atlasmundi.atlasmundi.domain.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LocationHelper {

    public static BigDecimal getDistanceBetween(double lat1, double lon1, double lat2, double lon2) {
        var distance = distance(lat1, lon1, lat2, lon2, "K");
        return new BigDecimal(distance).setScale(3, RoundingMode.DOWN);
    }

    private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        } else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            if (unit.equals("K")) {
                dist = dist * 1.609344;
            } else if (unit.equals("N")) {
                dist = dist * 0.8684;
            }
            return (dist);
        }
    }
}
