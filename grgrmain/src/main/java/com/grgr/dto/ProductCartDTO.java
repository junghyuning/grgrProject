/* product_cart 테이블 생성
CREATE TABLE product_cart (
    product_cart_no INT PRIMARY KEY,
    uno INT,
    product_id INT,
    product_count INT DEFAULT 1,
    CONSTRAINT fk_userinfo FOREIGN KEY (uno) REFERENCES userinfo (uno),
    CONSTRAINT fk_product_board FOREIGN KEY (product_id) REFERENCES product_board (product_id)
);
*/
package com.grgr.dto;

import java.util.List;

import lombok.Data;

//0923 - cartList 필드 추가(주문페이지에서 사용)
@Data
public class ProductCartDTO {
	private int productCartNo; // 장바구니 등록 번호
	private int uno; // 회원번호
	private int productId; // 상품번호
	private int productCount; // 상품수량

	private String productTitle; // 상품명
	private int productPrice; // 가격

	private List<ProductCartDTO> cartList;//주문페이지 사용
}
