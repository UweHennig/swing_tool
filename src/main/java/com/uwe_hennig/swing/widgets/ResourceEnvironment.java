/**
 * @(#)ResourceEnvironment.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.swing.widgets;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * ResourceEnvironment
 * 
 * Helper for loading images 
 * TODO !
 * @author Uwe Hennig
 */
public class ResourceEnvironment {
    private static ResourceEnvironment instance = null;
    private URL                        urlBase;

    private ResourceEnvironment(URL urlBase) {
        this.urlBase = urlBase;
    }

    private ResourceEnvironment() {
    }

    private static ResourceEnvironment getInstance() {
        if (instance == null)
            initInstance();
        return instance;
    }

    private static void initInstance(URL urlBase) {
        if (instance == null) {
            instance = new ResourceEnvironment(urlBase);
        }
    }

    public static void initInstance() {
        try {
            if (instance == null) {
                Object o = new ResourceEnvironment();
                ClassLoader clsLoader = o.getClass().getClassLoader();

                URL tmpURL = clsLoader.getResource("de/uh/guibase/images/icons/24x24/ATEXT1.gif");
                String path = tmpURL.getPath();

                path = path.split("images/icons/24x24/ATEXT1.gif")[0];
                tmpURL = new URL(tmpURL.getProtocol(), tmpURL.getHost(), path);
                initInstance(tmpURL);
            }
        } catch (MalformedURLException ex) {
            System.err.println("MalformedURL in ResourceEnvironment.initInstance");
            ex.printStackTrace();
        }
    }

    public static URL getIconURL(String gifName) {
        URL tmpURL = null;

        try {
            tmpURL = new URL(getInstance().urlBase.getProtocol(), getInstance().urlBase.getHost(),
                    getInstance().urlBase.getPath() + "images/icons/24x24/" + gifName);
        } catch (MalformedURLException ex) {
            System.err.println("MalformedURL in ResourceEnvironment.getIconURL");
            ex.printStackTrace();
        }

        return tmpURL;
    }
}
