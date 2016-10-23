package webapp.server;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by dssb on 01/10/16.
 */
@Path("/")
public class EntryPoint {
	
	public static class Data {
		
		private String name;
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
	}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Data test() {
    	Data data = new Data();
    	data.setName("The name.");
        return data;
    }
}