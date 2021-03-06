package webapp.server.examples;

import webapp.server.WebServer;

public class Main {

    public static void main(String[] args) throws Exception {
    	new WebServer()
    		.listenTo(8080)
    		.resource("/")
    			.from("build/resources/main/web")
    		.rest("/rest")
    			.include(EntryPoint.class)
    			.include(EntryPoint1.class)
    			.include(EntryPoint2.class)
    		.handle("/handle")
    			.by(HelloHandler.class)
    		.serve("/servlet")
    			.by("/moon",   new HelloServlet("Hello moon!"))
    			.by("/planet", new HelloServlet("Hello planet earth!"))
    			.by("/",       new HelloServlet())
    		.start();
    }

}
