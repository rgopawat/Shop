package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import dao.ShopInformationDao;
import model.ShopInformation;

@Repository
public class ShopInformationDaoImpl implements ShopInformationDao {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void create(ShopInformation shopInformation) {
		entityManager.persist(shopInformation);

	}

	@Override
	public void update(ShopInformation shopInformation) {
		entityManager.merge(shopInformation);

	}

	@Override
	public ShopInformation getShopInformationByName(String shopName) {
		ShopInformation retObj = entityManager.find(ShopInformation.class, shopName);

		return retObj;
	}

	@Override
	public void delete(String shopName) {
		ShopInformation shopInformation = getShopInformationByName(shopName);
		if (shopInformation != null) {
			entityManager.remove(shopInformation);
		}

	}

	@Override
	public List<ShopInformation> getShopInformationByLatiLongi(String latitude, String longitude) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ShopInformation> q = cb.createQuery(ShopInformation.class);
		Root<ShopInformation> c = q.from(ShopInformation.class);
		ParameterExpression<String> peLong = cb.parameter(String.class);
		ParameterExpression<String> peLati = cb.parameter(String.class);
		q.select(c).where(cb.equal(c.get("latitude"), peLati), cb.equal(c.get("longitude"), peLong));
		TypedQuery<ShopInformation> tq = entityManager.createQuery(q);

		tq.setParameter(peLong, longitude);
		tq.setParameter(peLati, latitude);

		List<ShopInformation> results = tq.getResultList();

		return results;
	}

	@Override
	public List<ShopInformation> getAllShops() {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ShopInformation> cr = cb.createQuery(ShopInformation.class);
		cr.select(cr.from(ShopInformation.class));

		List<ShopInformation> results = entityManager.createQuery(cr).getResultList();
		return results;
	}

}
