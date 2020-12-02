package com.productPicture.model;

import java.util.List;

public interface productPictureInterface {

	public boolean insert(ProductPictureVO productPicture);
	public boolean update(ProductPictureVO productPicture);
	public boolean delete(String pp_id);
	public ProductPictureVO findByPrimaryKey(String pp_id);
	public List<ProductPictureVO> gatAll();
}
