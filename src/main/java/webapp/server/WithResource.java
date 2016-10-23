package webapp.server;

import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ResourceHandler;

import webapp.server.WebServer.Config;

public interface WithResource extends WithWebServerFeature {
	
    default public ResourceWebContextFrom resource(String theWebContext) {
    	parentCalled();
    	Config config = getConfig();
    	return new ResourceWebContextFrom(config, theWebContext);
    }
	
	public static class ResourceWebContextFrom extends WebServer {
		
		private String webContext;
		
		ResourceWebContextFrom(Config theConfig, String theWebContext) {
			super(theConfig);
			webContext = theWebContext;
		}
		
		public WebServer from(String theDirName) {
	    	ResourceHandler resourceHandler= new ResourceHandler();
	        resourceHandler.setResourceBase(theDirName);
	        ContextHandler resourcesContextHandler= new ContextHandler(webContext);
	        resourcesContextHandler.setHandler(resourceHandler);
	        
	        config.handleList.add(resourcesContextHandler);
	        return this;
		}
		
	}

}
