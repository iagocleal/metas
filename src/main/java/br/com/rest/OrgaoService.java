package br.com.rest;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.com.persistencia.OrgaoDAO;

@Path("/orgao")
public class OrgaoService {

	@GET
	public String testRest() {
		return "REST OK";
	}
	
	@Transactional
	@GET
	@Path("/orgao")
	@Produces("application/json")
	public Response getOrgao() {		
		OrgaoDAO orgaoDAO = new OrgaoDAO();
		return Response.ok(orgaoDAO.buscarListaOrgao()).build();
	}

}
