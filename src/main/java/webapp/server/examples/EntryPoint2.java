package webapp.server.examples;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by dssb on 01/10/16.
 */
@Path("/resource2")
public class EntryPoint2 {
	
	public static class Data {
		
		private String name;
		
		private int time;
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getTime() {
			return time;
		}

		public void setTime(int time) {
			this.time = time;
		}
		
		
	}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Data test() {
    	Data data = new Data();
    	data.setName("The name.");
    	data.setTime(42);
        return data;
    }
}
