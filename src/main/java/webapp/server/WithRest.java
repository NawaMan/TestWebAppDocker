package webapp.server;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import webapp.server.WebServer.Config;

public interface WithRest extends WithWebServerFeature {
    
    default public RestEndPoints rest(String theWebContext) {
    	parentCalled();
    	Config config = getConfig();
    	return new RestEndPoints(config, theWebContext);
    }
	
	public class RestEndPoints extends WebServer {

		private String webContext;
		private List<Class<?>> endPointClasses = new ArrayList<>();
		
		RestEndPoints(Config theConfig, String theWebContext) {
			super(theConfig);
			webContext = theWebContext;
		}
		
		public RestEndPoints include(Class<?> endPointClass) {
			endPointClasses.add(endPointClass);
	        return this;
		}
		
		public void parentCalled() {
	        ServletContextHandler restHandle = new ServletContextHandler(ServletContextHandler.SESSIONS);
	        restHandle.setContextPath(webContext);
	        ServletHolder jerseyServlet = restHandle.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
	        jerseyServlet.setInitOrder(0);
	        String classNameArray = endPointClasses.stream().map(Class::getCanonicalName).collect(Collectors.joining(";"));
			jerseyServlet.setInitParameter("jersey.config.server.provider.classnames", classNameArray);
	        
			config.handleList.add(restHandle);
		}
		
	}

}
