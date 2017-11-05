package br.com.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.entidade.Meta;
import br.com.entidade.Orgao;
import br.com.entidade.Setor;

public class MetaDAO {

	public List<Meta> buscarListaMeta() {
		EntityManager entityManager = null;
		entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT NEW br.com.entidade.Meta(m.id, m.descricao, o.id, o.descricao, mr.id, mr.descricao, "
				+ " tm.id, tm.descricao, s.id, s.descricao) "
				+ "FROM Meta m "
				+ "INNER JOIN m.orgao o "
				+ "INNER JOIN m.tipoMeta tm "
				+ "INNER JOIN m.setor s "
				+ "LEFT JOIN m.metaRelacionada mr "
				+ "ORDER BY o.descricao");
		
		Query q = entityManager.createQuery(sb.toString());

		List<Meta> lstMeta = q.getResultList();
		
		if(lstMeta != null && !lstMeta.isEmpty())
		{
			return lstMeta;
		}

		return null;
	}
	
	public List<Meta> buscarListaMetaRelacionada(long seqOrgao) {
		EntityManager entityManager = null;
		entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("FROM Meta m WHERE m.orgao.id = :seqOrgao");
		
		Query q = entityManager.createQuery(sb.toString());
		
		q.setParameter("seqOrgao", seqOrgao);

		List<Meta> lstMeta = q.getResultList();
		
		if(lstMeta != null && !lstMeta.isEmpty())
		{
			return lstMeta;
		}

		return new ArrayList<Meta>();
	}
	
	public List<Meta> buscarListaMetaById(Long idMeta) {
		EntityManager entityManager = null;
		entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT NEW br.com.entidade.Meta(m.id, m.descricao, o.id, o.descricao, mr.id, mr.descricao, "
				+ " tm.id, tm.descricao, s.id, s.descricao) "
				+ "FROM Meta m "
				+ "INNER JOIN m.orgao o "
				+ "INNER JOIN m.tipoMeta tm "
				+ "INNER JOIN m.setor s "
				+ "LEFT JOIN m.metaRelacionada mr "
				+ "WHERE m.id = :idMeta ");
		
		Query q = entityManager.createQuery(sb.toString());
		
		q.setParameter("idMeta", idMeta);

		List<Meta> lstMeta = q.getResultList();
		
		if(lstMeta != null && !lstMeta.isEmpty())
		{
			return lstMeta;
		}

		return null;
	}
	
	public void salvar(Meta meta){
		EntityManager entityManager = null;
		try {
			entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

			entityManager.getTransaction().begin();
			entityManager.persist(meta);
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			if (entityManager != null) {
				System.out.println("Transaction is being rolled back.");
			}
		} finally {
			entityManager.close();
		}
	}
	
	public void alterar(Meta meta){
		EntityManager entityManager = null;
		try {
			entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

			entityManager.getTransaction().begin();
			entityManager.merge(meta);
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			if (entityManager != null) {
				System.out.println("Transaction is being rolled back.");
			}
		} finally {
			entityManager.close();
		}
	}
	
	public void excluir(Long idMeta){
		EntityManager entityManager = null;
		try {
			entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

			entityManager.getTransaction().begin();		
			Meta metaRemover = entityManager.find(Meta.class, idMeta);			
			entityManager.remove(metaRemover);
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			if (entityManager != null) {
				System.out.println("Transaction is being rolled back.");
			}
		} finally {
			entityManager.close();
		}
	}
}
