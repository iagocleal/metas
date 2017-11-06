package br.com.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.entidade.Orgao;

public class OrgaoDAO {

	@SuppressWarnings("unchecked")
	public List<Orgao> buscarListaOrgao() {
		EntityManager entityManager = null;
		entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		Query q = entityManager.createQuery("SELECT o FROM Orgao o ORDER BY o.descricao");

		List<Orgao> lstOrgao = q.getResultList();
		
		if(lstOrgao != null && !lstOrgao.isEmpty())
		{
			return lstOrgao;
		}

		return null;
	}
	
	public void salvar(Orgao orgao){
		EntityManager entityManager = null;
		try {
			entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

			entityManager.getTransaction().begin();
			entityManager.persist(orgao);
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			if (entityManager != null) {
				System.out.println("Transaction is being rolled back.");
			}
		} finally {
			if(entityManager != null){
				entityManager.close();
			}
		}
	}

}
