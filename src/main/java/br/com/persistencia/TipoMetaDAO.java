package br.com.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.entidade.TipoMeta;

public class TipoMetaDAO {

	public List<TipoMeta> buscarListaTipoMeta() {
		EntityManager entityManager = null;
		entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		Query q = entityManager.createQuery("SELECT tm From TipoMeta tm ORDER BY tm.descricao");

		List<TipoMeta> lstTipoMeta = q.getResultList();
		
		if(lstTipoMeta != null && !lstTipoMeta.isEmpty())
		{
			return lstTipoMeta;
		}

		return null;
	}

}
