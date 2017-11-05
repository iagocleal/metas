package br.com.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.entidade.Setor;

public class SetorDAO {

	public List<Setor> buscarListaSetor(long seqOrgao) {
		EntityManager entityManager = null;
		entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("FROM Setor s WHERE s.orgao.id = :seqOrgao");
		
		Query q = entityManager.createQuery(sb.toString());
		
		q.setParameter("seqOrgao", seqOrgao);

		List<Setor> lstSetor = q.getResultList();
		
		if(lstSetor != null && !lstSetor.isEmpty())
		{
			return lstSetor;
		}

		return null;
	}

}
