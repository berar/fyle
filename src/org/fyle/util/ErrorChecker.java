
package org.fyle.util;

import org.fyle.util.authenticate.AuthenticityState;
import org.fyle.util.impl.Constants;

public interface ErrorChecker {
    AuthenticityState checkErrors(Constants.ErrorType errorType);
    boolean check(String target, int min, int max, String pattern, String[] messages);
    boolean checkFormat(String target, String pattern);
    boolean checkMin(String target, int min);
    boolean checkMax(String target, int max);
}
