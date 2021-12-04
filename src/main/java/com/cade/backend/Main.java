package com.cade.backend;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

/**
 * @see <a href="https://quarkus.io/guides/lifecycle#the-main-method">
 *     https://quarkus.io/guides/lifecycle#the-main-method
 * </a>
 */
@QuarkusMain
public class Main {
    public static void main(String[] args) {
        Quarkus.run(args);
    }
}
