package com.productPicture.model;

import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import com.data.database;

public class ProductPictureJDBCDAO implements ProductPictureInterface{


	
	public final String INSERT = "INSERT INTO ProductPicture (PP_ID,PP_PICTURE,P_ID) VALUES ('PP' || LPAD(ProductPicture_SEQ.NEXTVAL, 5, 0), ?, ?)";
	public final String UPDATE = "UPDATE ProductPicture SET PP_PICTURE=? WHERE PP_ID=?";
	public final String DELETE = "DELETE FROM ProductPicture WHERE PP_ID=?";
	public final String GETONE = "SELECT * FROM ProductPicture WHERE PP_ID=?";
	public final String GETALL = "SELECT * FROM ProductPicture ORDER BY PP_ID";
	
	
	public static void main(String[] args) {
		ProductPictureInterface pt = new ProductPictureJDBCDAO();
//		// 新增
		ProductPictureVO ppVOInsert = new ProductPictureVO();
		ppVOInsert.setPp_picture(null);
		ppVOInsert.setP_id("P00013");
		pt.insert(ppVOInsert);
		
		
		// 更新
//		ProductPictureVO ppVOUpdate = new ProductPictureVO();
//		ppVOUpdate.setPP_ID("PT00012");
//		ppVOUpdate.setPt_platform("PlayStationn");
//		ppVOUpdate.setPt_kind("主機");
//		pt.update(ppVOUpdate);
		
		// 刪除
//		pt.delete("PT00021");
		
		
//		// 取一
//		ProductPictureVO ppVOGetOne = pt.findByPrimaryKey("PP00007");
//		String id = ppVOGetOne.getPp_id();
//		byte[] img = ppVOGetOne.getPp_picture();
//		String pid = ppVOGetOne.getP_id();
//		
//		System.out.println("id:" + id);
//		System.out.println("file path" + img1.getAbsolutePath());
//		System.out.println("kind:" + pid);
//		
//		// 取全部
//		List<ProductPictureVO> list = pt.getAll();
//		for(ProductPictureVO ppVO : list) {
//			String id = ppVO.getPP_ID();
//			String platform = ppVO.getPt_platform();
//			String kind = ppVO.getPt_kind();
//			System.out.println("id:" + id);
//			System.out.println("platform:" + platform);
//			System.out.println("kind:" + kind);
//			System.out.println("===============================");
//		}
		
	}
	
	@Override
	public void insert(ProductPictureVO productPicture) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(database.DRIVER);
			con = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD);
			ps = con.prepareStatement(INSERT);
			
			ps.setBytes(1, productPicture.getPp_picture());
			ps.setString(2, productPicture.getP_id());
			
			int line = ps.executeUpdate();
			System.out.println(line);
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(con != null) {
				con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}

	@Override
	public void update(ProductPictureVO productPicture) {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(database.DRIVER);
			con = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD);
			ps = con.prepareStatement(UPDATE);
			
			ps.setBytes(1, productPicture.getPp_picture());	
			ps.setString(2, productPicture.getPp_id());
			
			int w = ps.executeUpdate();
			System.out.println(w);
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(con != null) {
					con.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(String pp_id) {

		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(database.DRIVER);
			con = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD);
			ps = con.prepareStatement(DELETE);
			
			ps.setString(1, pp_id);
			
			ps.executeUpdate();
			
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(con != null) {
					con.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public ProductPictureVO findByPrimaryKey(String pp_id) {
		ProductPictureVO productPicture = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName(database.DRIVER);
			con = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD);
			ps = con.prepareStatement(GETONE);
			ps.setString(1, pp_id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				productPicture = new ProductPictureVO();
				productPicture.setPp_id(rs.getString("pp_id"));
				productPicture.setPp_picture(rs.getBytes("pp_picture"));
				productPicture.setP_id(rs.getString("p_id"));
			}
			
			
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(ps != null) {
					ps.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(con != null) {
					con.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return productPicture;
	}

	@Override
	public List<ProductPictureVO> getAll() {
		List<ProductPictureVO> listppVO = new LinkedList<ProductPictureVO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName(database.DRIVER);
			con = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD);
			ps = con.prepareStatement(GETALL);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ProductPictureVO ppVO = new ProductPictureVO();
				ppVO.setPp_id(rs.getString("pp_id"));
				ppVO.setPp_picture(rs.getBytes("pp_picture"));
				ppVO.setP_id(rs.getString("p_id"));
				listppVO.add(ppVO);
			}
			
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(ps != null) {
					ps.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(con != null) {
					con.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return listppVO;
	}


}
