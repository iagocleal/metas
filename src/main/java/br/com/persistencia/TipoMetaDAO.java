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
	
	public void salvar(TipoMeta tipoMeta){
		EntityManager entityManager = null;
		try {
			entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

			entityManager.getTransaction().begin();
			entityManager.persist(tipoMeta);
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
