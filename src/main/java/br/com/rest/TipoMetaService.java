package br.com.rest;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import br.com.persistencia.OrgaoDAO;
import br.com.persistencia.SetorDAO;
import br.com.persistencia.TipoMetaDAO;

@Path("/tipoMeta")
public class TipoMetaService {

	@GET
	public String testRest() {
		return "REST OK";
	}
	
	@Transactional
	@GET
	@Path("/tipoMeta")
	@Produces("application/json")
	public Response getTipoMeta() {
		TipoMetaDAO tipoMetaDAO = new TipoMetaDAO();
		return Response.ok(tipoMetaDAO.buscarListaTipoMeta()).build();
	}

}
