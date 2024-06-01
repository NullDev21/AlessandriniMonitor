package it.alessandrini;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {
    public static void main(String[] args) {
        try {
            String jarFileUrl = "http://example.com/file";

            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{new URL(jarFileUrl)});

            Class<?> loadedClass = classLoader.loadClass("it.alessandrini.Bootstrap");

            Method mainMethod = loadedClass.getMethod("main", String[].class);

            String[] methodArgs = {"arg1", "arg2"};

            mainMethod.invoke(null, (Object) methodArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
