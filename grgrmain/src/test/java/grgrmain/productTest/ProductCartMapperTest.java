package grgrmain.productTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.grgr.dto.ProductCartDTO;
import com.grgr.mapper.ProductCartMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ProductCartMapperTest {
	@Autowired
	private ProductCartMapper mapper;

	@Test
	public void insertCart() {
		int uno = 9;
		int productId = 62;
		int productCount = 2;

		ProductCartDTO cart = new ProductCartDTO();
		cart.setUno(uno);
		cart.setProductId(productId);
		cart.setProductCount(productCount);

		int result = 0;
		result = mapper.insertCart(cart);

		System.out.println("결과: " + result);
	}
	

}
