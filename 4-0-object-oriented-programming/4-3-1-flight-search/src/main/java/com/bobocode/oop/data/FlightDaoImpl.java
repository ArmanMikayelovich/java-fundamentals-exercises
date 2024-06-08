package com.bobocode.oop.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * {@link FlightDaoImpl} represents a Data Access Object (DAO) for flights. The implementation is simplified, so it just
 * uses {@link HashSet} to store flight numbers.
 * <p>
 * todo: 1. Implement a method {@link FlightDaoImpl#register(String)} that store new flight number into the set
 * todo: 2. Implement a method {@link FlightDaoImpl#findAll()} that returns a set of all flight numbers
 */
public class FlightDaoImpl implements FlightDao {
    private Set<String> flights = new HashSet<>();

    /**
     * Stores a new flight number
     *
     * @param flightNumber a flight number to store
     * @return {@code true} if a flight number was stored, {@code false} otherwise
     */
    public boolean register(String flightNumber) {
        return flights.add(flightNumber);
    }

    /**
     * Returns all stored flight numbers
     *
     * @return a set of flight numbers
     */
    public Set<String> findAll() {
        return flights;
    }

    @Override
    public List<String> getByFlightIdContains(String query) {
        return flights.stream().filter(flight -> flight.contains(query)).toList();
    }
}
