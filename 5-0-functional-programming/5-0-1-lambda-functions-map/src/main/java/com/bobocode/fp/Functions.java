package com.bobocode.fp;

import java.util.function.Function;

/**
 * An util class that provides a factory method for creating an instance of a {@link FunctionMap} filled with a list
 * of functions.
 * <p>
 * TODO: implement a method and verify it by running {@link FunctionsTest}
 * <p>
 * TODO: if you find this exercise valuable and you want to get more like it, <a href="https://www.patreon.com/bobocode"> 
 *     please support us on Patreon</a>
 *
 * @author Taras Boychuk
 */
public class Functions {
    private Functions() {
    }

    /**
     * A static factory method that creates an integer function map with basic functions:
     * - abs (absolute value)
     * - sgn (signum function)
     * - increment
     * - decrement
     * - square
     *
     * @return an instance of {@link FunctionMap} that contains all listed functions
     */
    public static FunctionMap<Integer, Integer> intFunctionMap() {
        FunctionMap<Integer, Integer> intFunctionMap = new FunctionMap<>();

        Function<Integer, Integer> abs = Math::abs;
        Function<Integer, Integer> sgn = x -> Double.valueOf(Math.signum((double) x)).intValue();
        Function<Integer, Integer> increment = x -> ++x;
        Function<Integer, Integer> decrement = x -> --x;
        Function<Integer, Integer> square = x -> x * x;
        intFunctionMap.addFunction("abs", abs);
        intFunctionMap.addFunction("sgn", sgn);
        intFunctionMap.addFunction("increment", increment);
        intFunctionMap.addFunction("decrement", decrement);
        intFunctionMap.addFunction("square", square);

        return intFunctionMap;
    }
}
