package org.hprtech.main;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class Main {

    public static void main(String[] args) {
        Quarkus.run(MainQuarkus.class, args);
    }

    public static class MainQuarkus implements QuarkusApplication{

        @Override
        public int run(String... args) throws Exception {
            //write your logic here
            System.out.println(">>>>> Application Started ");
            Quarkus.waitForExit();
            return 0;
        }
    }
}
