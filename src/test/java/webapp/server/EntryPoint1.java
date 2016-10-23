package webapp.server;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by dssb on 01/10/16.
 */
@Path("/resource1")
public class EntryPoint1 {
	
	public static class Data {
		
		private String name;
		
		private String value;
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
		
		
	}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Data test() {
    	Data data = new Data();
    	data.setName("The name.");
    	data.setValue("The value.");
        return data;
    }
}