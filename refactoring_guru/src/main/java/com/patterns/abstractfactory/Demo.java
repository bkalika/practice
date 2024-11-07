package com.patterns.abstractfactory;

public class Demo {
    private static Application configureApplication() {
        Application application;
        GUIFactory guiFactory;
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("windows")) {
            guiFactory = new WindowsFactory();
        } else {
            guiFactory = new MacOSFactory();
        }
        application = new Application(guiFactory);
        return application;
    }

    public static void main(String[] args) {
        Application application = configureApplication();
        application.paint();
    }
}
