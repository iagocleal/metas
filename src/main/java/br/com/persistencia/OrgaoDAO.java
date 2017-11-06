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

}
