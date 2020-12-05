package com.product.model;

import java.util.List;

public interface ProductInterface {

	public boolean insert(ProductVO product);
	public boolean update(ProductVO product);
	public boolean delete(String p_id);
	public List<ProductVO> findByProductName(String p_name);					// �j�M��
	public List<ProductVO> findByProductName(String p_name, String pt_id);		// �j�M��
	public List<ProductVO> findByStatus(String p_status);						// �f�d��
	public List<ProductVO> getAll();
}
