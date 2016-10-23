package webapp.server;

import webapp.server.WebServer.Config;

public interface WithWebServerFeature {

    default public void parentCalled() {}
    
    public Config getConfig();

}
