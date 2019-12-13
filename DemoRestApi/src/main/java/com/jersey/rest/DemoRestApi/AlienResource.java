package com.jersey.rest.DemoRestApi;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("aliens")
public class AlienResource {
	AlienRepository aliens = new AlienRepository();

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Alien> getAl() {
		System.out.println("Alien is getting started");

		return aliens.getAliens();
	}

	@GET
	@Path("alien/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Alien getAlien(@PathParam("id") int id) {
		return aliens.getAlien(id);
	}

	@POST
	@Path("alien")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Alien createAlien(Alien a1) {
		System.out.println(a1);
		aliens.create(a1);

		return a1;
	}

	@PUT
	@Path("update")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Alien updateAlien(Alien a1) {
		System.out.println(a1);
		aliens.update(a1);

		return a1;
	}

	@DELETE
	@Path("delete/{id}")
	public Alien deleteAlien(@PathParam("id") int id) {
		System.out.println(id);
		Alien a1 = aliens.getAlien(id);
		if (a1.getId() != 0) {
			aliens.delete(id);
		}

		return a1;
	}

}
