package com.product.model;

import java.util.List;

public interface ProductInterface {

	public boolean insert(ProductVO product);
	public boolean update(ProductVO product);
	public boolean delete(String p_id);
	public ProductVO findByPrimeryKey(String p_id);
	public List<ProductVO> getAll();
}
