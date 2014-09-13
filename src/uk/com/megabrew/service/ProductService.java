package uk.com.megabrew.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import uk.com.megabrew.model.Product;

@WebService
@Stateless
public class ProductService {

	@PersistenceContext
	private EntityManager em;
	
	public List<Product> listProduct() {
		return em.createQuery("select p from Product p", Product.class).getResultList();
	}
	
	public void persistProduct(Product product) {
		
		if (product.getId() == 0) {
			em.persist(product);
		} else {
			em.merge(product);
		}
		
	}
	
	public void die(Product product) {
		
		if (product.getId() != 0) 
			em.remove(product);
		
	}
	
	public Product findById(long id) {
		return em.find(Product.class, id);
	}
	
}
