package webapp.server;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

import javax.servlet.http.HttpServlet;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import webapp.server.WebServer.Config;

public interface WithServlet extends WithWebServerFeature {
    
    default public ServeBy serve(String theWebContext) {
    	parentCalled();
    	Config config = getConfig();
    	return new ServeBy(config, theWebContext);
    }
	
	public class ServeBy extends WebServer {

		private String webContext;
		private Map<String, Supplier<HttpServlet>> servletSources = new LinkedHashMap<>();
		
		ServeBy(Config theConfig, String theWebContext) {
			super(theConfig);
			webContext = theWebContext;
		}
		
		public ServeBy by(String path, Class<? extends HttpServlet> servletClass) {
			servletSources.put(path, ()->{
				try {
					return servletClass.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			});
	        return this;
		}
		
		public ServeBy by(String path, HttpServlet servlet) {
			servletSources.put(path, ()->servlet);
	        return this;
		}
		
		public void parentCalled() {
	        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
	        servletContextHandler.setContextPath(webContext);
	        servletSources.entrySet().stream().forEach(entry->{
	        	String path = entry.getKey();
	        	HttpServlet servlet = entry.getValue().get();
		        servletContextHandler.addServlet(new ServletHolder(servlet), path);
	        });
	        
			config.handleList.add(servletContextHandler);
		}
		
	}

}
