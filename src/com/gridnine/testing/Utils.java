package com.gridnine.testing;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;



public class Utils {
    static String allFlight(){
        return "------------All flights------------\n" +
                FlightBuilder.createFlights()
                        .stream()
                        .map(Objects::toString)
                        .collect(Collectors.joining("\n"));
    }

    static String afterThisMoment(){
        return "--------Аfter this moment----------\n" +
                FlightBuilder.createFlights()
                        .stream()
                        .filter((Flight p) ->
                            p.getSegments()
                                    .stream()
                                    .allMatch(segment -> segment.getDepartureDate().isBefore(segment.getArrivalDate())))
                        .map(Objects::toString)
                        .collect(Collectors.joining("\n"));
    }

    static String moreTwoHoursOnGround() {
        StringBuilder stringBuilder = new StringBuilder("-----More two hours on ground------\n");
        for (Flight f : FlightBuilder.createFlights()) {
            long groundTime = 0;
            List<Segment> segments = f.getSegments();
            for (int i = 1; i < segments.size(); i++) {
                groundTime += ChronoUnit.HOURS.between(segments.get(i -1).getArrivalDate(), segments.get(i).getDepartureDate());
            }
            if(groundTime < 2){
                stringBuilder.append(f.toString()).append("\n");
            }
        }
        return stringBuilder.toString();
    }
}
