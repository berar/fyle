/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nugs.clientapp.util.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Korisnik
 */
public class DelimiterInputParserImpl {
    
    private DelimiterInputParserImpl(){
        throw new AssertionError();
    }
    
    public static List<String> parseInput(String message) {
        String[] tokens = message.split(" ");
        List<String> result = new ArrayList<>(tokens.length);
        result.addAll(Arrays.asList(tokens));
        return result;

    }

    public static String removeFirst(String s) {
        return s.substring(1);
    }
}
