package com.productType.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.data.database;

public class ProductTypeDAOJDBC implements ProductTypeInterface{


	
	public final String INSERT = "INSERT INTO PRODUCTTYPE (PT_ID,PT_PLATFORM,PT_KIND) VALUES ('PT' || LPAD(PRODUCTTYPE_SEQ.NEXTVAL, 5, 0), ?, ?)";
	public final String UPDATE = "UPDATE PRODUCTTYPE SET PT_PLATFORM=?, PT_KIND=? WHERE PT_ID=?";
	public final String DELETE = "DELETE FROM PRODUCTTYPE WHERE PT_ID=?";
	public final String GETONE = "SELECT * FROM PRODUCTTYPE WHERE PT_ID=?";
	public final String GETALL = "SELECT * FROM PRODUCTTYPE ODER BY PT_ID";
	
	
	public static void main(String[] args) {
		ProductTypeVO pvo = new ProductTypeVO();
		ProductTypeInterface pt = new ProductTypeDAOJDBC();
		pvo.setPt_platform("PlaaayStationn");
		pvo.setPt_kind("sssssssssssssssssss");
		pt.insert(pvo);
	}
	
	@Override
	public void insert(ProductTypeVO productType) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(database.DRIVER);
			con = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD);
			ps = con.prepareStatement(INSERT);
			
			ps.setString(1, productType.getPt_platform());
			ps.setString(2, productType.getPt_kind());
			
			int line = ps.executeUpdate();
			System.out.println(line);
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
	}

	@Override
	public void update(ProductTypeVO productType) {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(database.DRIVER);
			con = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD);
			ps = con.prepareStatement(UPDATE);
			
			ps.setString(1, productType.getPt_platform());
			ps.setString(2, productType.getPt_kind());
			ps.setString(3, productType.getPt_id());
			
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
	public void delete(String p_id) {

		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(database.DRIVER);
			con = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD);
			ps = con.prepareStatement(DELETE);
			
			ps.setString(1, p_id);
			
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
	public ProductTypeVO findByPrimaryKey(String p_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductTypeVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
