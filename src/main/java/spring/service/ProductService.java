package spring.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import spring.dto.ProductDTO;
import spring.dto.ProductLotDTO;

public class ProductService {
	
	public List<ProductDTO> getAll() {
		Connection con=ConnectionClass.getConnection();
		List<ProductDTO> products = new ArrayList<>();
		try {
			PreparedStatement ps=con.prepareStatement("select * from product ");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				ProductDTO product = new ProductDTO();
				product.setName(rs.getString("name"));
				product.setId(rs.getInt("id")+"");
				product.setCategory(rs.getInt("category_id")+"");
				products.add(product);
			}
			con.close();
		} catch (SQLException e) {
			System.out.println("Get All : "+ e.getMessage());
		}
		
		return products;
	}

//	public ProductDTO getOne(Integer id) {
//		Connection con=ConnectionClass.getConnection();
//		ProductDTO product=null;
//		try {
//			PreparedStatement ps = con.prepareStatement("select p.id, p.name, quantity, price, c.name category from product p inner join category c on p.category_id=c.id where p.deleted=0 and p.id=?");
//			ps.setInt(1, id);
//			ResultSet rs = ps.executeQuery();
//			while(rs.next())
//			{
//				product=new ProductDTO();
//				product.setId(rs.getInt("id")+"");
//				product.setName(rs.getString("name"));
//				product.setQuantity(rs.getInt("quantity"));
//				product.setPrice(rs.getDouble("price"));
//				product.setCategory(rs.getString("category"));
//			}
//			
//			con.close();
//		} catch (SQLException e) {
//			System.out.println("Get One : "+ e.getMessage());
//		}
//		
//		return product;
//	}
//
//	public int updateOne(ProductDTO dto) {
//		Connection con=ConnectionClass.getConnection();
//		int result = 0;
//		try {
//			PreparedStatement ps = con.prepareStatement("update product set name=?,quantity=?,price=?,category_id=(select id from category where name=?) where id=?");
//			ps.setString(1,dto.getName());
//			ps.setInt(2, dto.getQuantity());
//			ps.setDouble(3, dto.getPrice());
//			ps.setString(4, dto.getCategory());
//			ps.setInt(5, Integer.valueOf(dto.getId()));
//			result = ps.executeUpdate();
//			con.close();
//		} catch (SQLException e) {
//			System.out.println("Update One : "+ e.getMessage());
//		}
//		return result;
//	}
//
//	public int deleteOne(Integer id) {
//		Connection con=ConnectionClass.getConnection();
//		int result=0;
//		try {
//			PreparedStatement ps = con.prepareStatement("update product set deleted=1 where id=?");
//			ps.setInt(1, id);
//			result=ps.executeUpdate();
//			con.close();
//		} catch (SQLException e) {
//			System.out.println("Delete One : "+ e.getMessage());
//		}
//		
//		return result;
//	}
//
	public void insertOne(ProductDTO dto) {
		Connection con=ConnectionClass.getConnection();
		int result = 0;
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO product (name, category_id) VALUES (?, ?)");
			ps.setString(1, dto.getName());
			ps.setInt(2, Integer.valueOf(dto.getCategory()));
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Insert One Product : "+ e.getMessage());
		}
	}


	public boolean checkName(String name) {
		Connection con=ConnectionClass.getConnection();
		boolean result = false;
		try {
			PreparedStatement ps = con.prepareStatement("select * from product where name=?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				result = true;
			}
			con.close();
		} catch (SQLException e) {
			System.out.println("Check Name : "+ e.getMessage());
		}
		return result;
		
	}
	
}
