package ui;

import javafx.application.Platform;

public class Setup {

    private static boolean isPlattformSetUp = false;

    public static void start_up_javaFX_plattform(){

        if(!isPlattformSetUp){
            Platform.startup(() -> {
            });
            isPlattformSetUp = true;
        }
    }
}
