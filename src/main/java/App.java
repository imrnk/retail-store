import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.mediaocean.retailcounter.entity.OrderEntity;
import com.mediaocean.retailcounter.model.LineItem;
import com.mediaocean.retailcounter.model.Product;
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
	        } catch (Exception e) {
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
    	a.setDescription("Tangy Dutch");
    	a.setName("Orange");
    	a.setPrice(14.00);
    	
    	LineItem i1 = new LineItem();
        i1.setItemQuantity(4);
        i1.setProductId(a.getProductId());
       
        LineItem i2 = new LineItem();
        i2.setItemQuantity(5);
        i2.setProductId(b.getProductId());
        
        List<LineItem> items = new ArrayList<>();
        items.add(i1);
        items.add(i2);
        
        return mapper.toOrderEntity(items);
	}
}
