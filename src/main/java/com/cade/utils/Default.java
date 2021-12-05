package com.cade.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @see <a href="https://stackoverflow.com/a/64658200/10807183">
 *     https://stackoverflow.com/a/64658200/10807183
 *     </a>
 */
@Target(ElementType.CONSTRUCTOR)
@Retention(RetentionPolicy.CLASS)
public @interface Default {

}
