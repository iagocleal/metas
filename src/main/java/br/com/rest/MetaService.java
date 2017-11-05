package br.com.rest;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import br.com.entidade.Meta;
import br.com.persistencia.MetaDAO;

@Path("/meta")
public class MetaService {

	@GET
	public String testRest() {
		return "REST OK";
	}
	
	@Transactional
	@GET
	@Path("/getMetaRelacionada")
	@Produces("application/json")
	public Response getMetaRelacionada(@QueryParam("seqOrgao") Long seqOrgao) {
		MetaDAO metaDAO = new MetaDAO();
		return Response.ok(metaDAO.buscarListaMetaRelacionada(seqOrgao)).build();
	}
	
	@Transactional
	@GET
	@Path("/getLstMetas")
	@Produces("application/json")
	public Response getLstMetas() {
		MetaDAO metaDAO = new MetaDAO();
		return Response.ok(metaDAO.buscarListaMeta()).build();
	}
	
	@Transactional
	@POST
	@Path("/salvarMeta")
	public List<Meta> salvarMeta(Meta meta) {
		MetaDAO metaDAO = new MetaDAO();
		
		if(meta.getMetaRelacionada() != null && meta.getMetaRelacionada().getId().equals(0L)){
			meta.setMetaRelacionada(null);
		}
		
		if(meta.getId() != null)
		{
			metaDAO.alterar(meta);
		}
		else{
			metaDAO.salvar(meta);
		}
		
		return null;
	}
	
	@Transactional
	@GET
	@Path("/getMetaById")
	@Produces("application/json")
	public Response getMetaById(@QueryParam("idMeta") Long idMeta) {
		MetaDAO metaDAO = new MetaDAO();
		return Response.ok(metaDAO.buscarListaMetaById(idMeta)).build();
	}
	
	@Transactional
	@GET
	@Path("/excluirMeta")
	@Produces("application/json")
	public Response excluirMeta(@QueryParam("idMeta") Long idMeta) {
		MetaDAO metaDAO = new MetaDAO();
		metaDAO.excluir(idMeta);
		return Response.ok().build();
	}

}
