package webapp.server;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.handler.ContextHandler;

import webapp.server.WebServer.Config;

public interface WithHandle extends WithWebServerFeature {
    
    default public HandleBy handle(String theWebContext) {
    	parentCalled();
    	Config config = getConfig();
    	return new HandleBy(config, theWebContext);
    }
	
	public static class HandleBy extends WebServer {
		
		private String webContext;
		
		HandleBy(Config theConfig, String theWebContext) {
			super(theConfig);
			webContext = theWebContext;
		}
		
		public WebServer by(Handler theHandle) {
	        ContextHandler contextHandler= new ContextHandler(webContext);
	        contextHandler.setHandler(theHandle);
	        
	        config.handleList.add(contextHandler);
	        return this;
		}
		
		public WebServer by(Class<? extends Handler> theHandleClass) {
	        try {
				Handler handle = theHandleClass.newInstance();
		        this.by(handle);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
	        return this;
		}
		
	}

}
