package webapp.server;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;

public class WebServer implements WithResource, WithHandle, WithServlet, WithRest {
	
	private static final Handler[] EMPTY_HANDLE_ARRAY = new Handler[0];
    
	protected final Config config;
	
    public static class Config {
		int portNumber = 8080;
		List<Handler> handleList = new ArrayList<>();
    } 
    
    public WebServer() {
    	config = new Config();
    } 
    
    WebServer(Config theConfig) {
    	config = theConfig;
    }
    
    public Config getConfig() {
    	return config;
    }
    
	public WebServer listenTo(int thePortNumber) {
    	parentCalled();
    	config.portNumber = thePortNumber;
    	return this;
    }
	
	public void start() throws Exception {
    	parentCalled();
        
        HandlerList handleList = new HandlerList();
        Handler[] handlers = config.handleList.toArray(EMPTY_HANDLE_ARRAY);
        handleList.setHandlers(handlers);
        
        Server server = new Server(config.portNumber);
        server.setHandler(handleList);
        try {
            server.start();
            server.join();
        } finally {
            server.destroy();
        }
	}

}
