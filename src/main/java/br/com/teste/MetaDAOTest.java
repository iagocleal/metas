package br.com.teste;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.com.entidade.Meta;
import br.com.entidade.Orgao;
import br.com.entidade.Setor;
import br.com.entidade.TipoMeta;
import br.com.persistencia.MetaDAO;
import br.com.persistencia.OrgaoDAO;
import br.com.persistencia.SetorDAO;
import br.com.persistencia.TipoMetaDAO;

public class MetaDAOTest {

	private Orgao orgao;
	
	private Setor setor;
	
	private TipoMeta tipoMeta;
	
	private Meta meta;
	
	@Before
	public void setUp() throws Exception {
		orgao = new Orgao();
		orgao.setDescricao("Orgao Teste");
		
		setor = new Setor();
		setor.setDescricao("Setor Teste");
		
		tipoMeta = new TipoMeta();
		tipoMeta.setDescricao("Tipo Meta Teste");
		
		meta = new Meta();
		meta.setDescricao("Meta Teste");
	}

	@Test
	public void testSalvarOrgao() {
		System.out.println("Incluindo um Orgao no sistema");
		
		OrgaoDAO dao = new OrgaoDAO();
		dao.salvar(orgao);
		
		System.out.println("Orgao incluido com sucesso");
	}
	
	@Test
	public void testSalvarSetor() {
		System.out.println("Incluindo um Setor no sistema");
		
		SetorDAO dao = new SetorDAO();
		orgao.setId(1L);
		setor.setOrgao(orgao);
		dao.salvar(setor);
		
		System.out.println("Setor incluido com sucesso");
	}
	
	@Test
	public void testSalvarTipoMeta() {
		System.out.println("Incluindo um Tipo Meta no sistema");
		
		TipoMetaDAO dao = new TipoMetaDAO();
		dao.salvar(tipoMeta);
		
		System.out.println("Tipo Meta incluido com sucesso");
	}
	
	@Test
	public void testSalvar() {
		System.out.println("Incluindo uma meta no sistema");
		
		MetaDAO dao = new MetaDAO();
		orgao.setId(1L);
		setor.setId(1L);
		tipoMeta.setId(1L);
		meta.setOrgao(orgao);
		meta.setSetor(setor);
		meta.setTipoMeta(tipoMeta);
		
		dao.salvar(meta);
		
		System.out.println("Meta incluida com sucesso");
	}
	
	@Test
	public void testBuscarListaMetaById()
	{
		MetaDAO dao = new MetaDAO();
		List<Meta> meta = dao.buscarListaMetaById(1L);
		
		Assert.assertEquals(meta.iterator().next().getDescricao(), "Meta Teste");
	
	}

	@Test
	public void testAlterar() {
		System.out.println("Alterando uma meta no sistema");
		
		MetaDAO dao = new MetaDAO();
		orgao.setId(1L);
		setor.setId(1L);
		tipoMeta.setId(1L);
		meta.setOrgao(orgao);
		meta.setSetor(setor);
		meta.setTipoMeta(tipoMeta);
		
		Meta metaRelacionada = new Meta();
		metaRelacionada.setId(1L);
		
		meta.setMetaRelacionada(metaRelacionada);
		
		dao.alterar(meta);
		
		System.out.println("Meta Alterada com sucesso");
	}

	@Test
	public void testExcluir() {
		System.out.println("Excluindo uma meta no sistema");
		
		MetaDAO dao = new MetaDAO();		
		dao.excluir(1L);
		
		System.out.println("Meta excluida com sucesso");
	}
	
	@Test
	public void testBuscarListaMetaByIdNull()
	{
		MetaDAO dao = new MetaDAO();
		List<Meta> meta = dao.buscarListaMetaById(1L);
		
		Assert.assertNull(meta.iterator().next());
	
	}

}
