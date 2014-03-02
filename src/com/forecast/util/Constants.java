package com.forecast.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Constants {

	public static final String key = "6zqY56WOY40lMzWkQSwFq0HDeGo5vN82";
	public static final String DATE_FORMAT = "EEE";
	public static final String DAY_FORMAT = "dd/MM HH";
	public static final String METER = "m";
	
	public static final String MARIANO = "Mariano";
	public static final String DIVA = "Diva";
	public static final String BIOLOGIA = "Biologia";
	public static final String LAPEPITA = "La pepita";
	
	public static final String ARPOADOR = "Arpoador";
	public static final String PEPINO = "Pepino";
	public static final String MARESIAS = "Maresias";
	public static final String UBATUBA = "Ubatuba";
	
	public static final List<String> argentinaList = Arrays.asList(MARIANO, DIVA, LAPEPITA, BIOLOGIA);
	public static final List<String> brasilList = Arrays.asList(UBATUBA, PEPINO, MARESIAS, ARPOADOR);
	
	
	public static HashMap<Integer, List<String>> mapSpinner = new HashMap<Integer, List<String>>(){{
	        put(0, argentinaList);
	        put(1, brasilList);
	    }};	
	
	public static HashMap<String, Integer> mapAll = new HashMap<String, Integer>(){{
        put(UBATUBA, 3870);
        put(PEPINO, 502);
        put(MARESIAS, 434);
        put(ARPOADOR, 1277);
        

        put(MARIANO, 2709);
        put(DIVA, 2710);
        put(LAPEPITA, 2707);
        put(BIOLOGIA, 2704);
    }};	    	
}
