import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.mediaocean.retailcounter.entity.LineItemEntity;
import com.mediaocean.retailcounter.entity.OrderEntity;
import com.mediaocean.retailcounter.model.LineItem;
import com.mediaocean.retailcounter.model.Product;
import com.mediaocean.retailcounter.service.RetailCounterService;
import com.mediaocean.retailcounter.util.MapperUtil;

public class App {
	static MapperUtil mapper = new MapperUtil();
	
	public static void main(String[] args) {
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("RetailStorePersistenceUnit");
	        EntityManager em = emf.createEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        tx.begin();

	        try {
	            em.persist(prepareOrder());
	            tx.commit();
	            
	            
	            CriteriaBuilder cb = em.getCriteriaBuilder();
	            CriteriaQuery<LineItemEntity> cqLE = cb.createQuery(LineItemEntity.class);
	            Root<LineItemEntity> rootLE = cqLE.from(LineItemEntity.class);
	            cqLE.select(rootLE);
	            List<LineItemEntity> resultList = em.createQuery(cqLE).getResultList();
	            resultList.forEach(System.out::println);
	            
	            CriteriaQuery<OrderEntity> cqOE = cb.createQuery(OrderEntity.class);
	            Root<OrderEntity> rootOE = cqOE.from(OrderEntity.class);
	            cqOE.select(rootOE);
	            List<OrderEntity> orders = em.createQuery(cqOE).getResultList();
	            orders.forEach(System.out::println);
	        } catch (Exception e) {
	        	e.printStackTrace();
	            tx.rollback();
	        } finally {
	            em.close();
	            emf.close();
	}

	}

	
	private static OrderEntity prepareOrder( ) {
		Product a = new Product("A");
    	a.setDescription("Desert Rose");
    	a.setName("Apple");
    	a.setPrice(10.24);
    	
    	
    	Product b = new Product("B");
    	b.setDescription("Tangy Dutch");
    	b.setName("Orange");
    	b.setPrice(14.00);
    	
    	List<Product> prods = new ArrayList<>();
    	prods.add(a); prods.add(b);
    	RetailCounterService rcService = new RetailCounterService();
    	rcService.createProducts(prods);
    	
    	
    	
    	LineItem i1 = new LineItem();
        i1.setItemQuantity(4);
        i1.setProductId(1);
       
        LineItem i2 = new LineItem();
        i2.setItemQuantity(5);
        i2.setProductId(2);
        
        List<LineItem> items = new ArrayList<>();
        items.add(i1);
        items.add(i2);
        
        return mapper.toOrderEntity(items);
	}
}
