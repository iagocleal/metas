package br.com.rest;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import br.com.persistencia.OrgaoDAO;
import br.com.persistencia.SetorDAO;

@Path("/setor")
public class SetorService {

	@GET
	public String testRest() {
		return "REST OK";
	}
	
	@Transactional
	@GET
	@Path("/getSetor")
	@Produces("application/json")
	public Response getSetor(@QueryParam("seqOrgao") Long seqOrgao) {
		SetorDAO setorDAO = new SetorDAO();
		return Response.ok(setorDAO.buscarListaSetor(seqOrgao)).build();
	}

}
