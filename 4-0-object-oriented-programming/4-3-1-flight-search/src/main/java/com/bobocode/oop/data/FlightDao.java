package com.bobocode.oop.data;

import java.util.List;
import java.util.Set;

public interface FlightDao {

    boolean register(String flightNumber);

    Set<String> findAll();

   List<String> getByFlightIdContains(String query);
}
