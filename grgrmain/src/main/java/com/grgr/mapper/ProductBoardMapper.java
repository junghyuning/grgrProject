package com.grgr.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.grgr.dto.ProductBoardVO;
import com.grgr.dto.ProductFile;
import com.grgr.dto.ProductUserDTO;

public interface ProductBoardMapper {

	/* 게시판 목록 */
	List<ProductBoardVO> selectProductBoardList(Map<String, Object> map);

	/* 게시판 조회 */
	ProductBoardVO selectProductBoard(int productId);

	Integer selectPrevProductId(Map<String, Object> map);

	Integer selectNextProductId(Map<String, Object> map);

	/* 게시판 등록 */
	int insertProduct(ProductBoardVO productBoard);

	/* 게시판 수정 */
	int updateProduct(ProductBoardVO productBoard);

	/* 게시판 삭제 */
	int deleteProduct(@Param("productId") int productId, @Param("uno") int uno);

	/* 조회수 증가 */
	int increaseProductViewCnt(int productId);

	/* 신고수 증가 */
	int increaseProductReportNo(int productId);

	/* 게시판 총 갯수 */
	int productBoardCount(Map<String, Object> map);

	/* 게시판 상품 정보와 회원 정보 함께 조회 */
	ProductUserDTO getBoardUserInfo(int productId);

	int blindProductBoard(Map<String, Object> map);

	/* 사진 */
	List<ProductFile> selectProductFile(int productId);

	/* 사진 추가 */
	int insertProductFile(ProductFile productFile);

	/* 사진 삭제 */
	int deleteProductFiile(int productFileNo);

}
