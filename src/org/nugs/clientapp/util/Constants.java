/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nugs.clientapp.util;

import java.util.Random;

/**
 *
 * @author Korisnik
 */
public interface Constants {
    public static final String RGX_DETECT_ANY_SPACE = ".*\\s+.*";
    public static final String RGX_DETECT_NAME_COMMAND_CORRECT = "(?i)^\\/name \\w+";
    public static final String RGX_DETECT_NAME_COMMAND_ANY = "^/name .*$"; 
    public static final Random RANDOM = new Random(System.nanoTime());
}
