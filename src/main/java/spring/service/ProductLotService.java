package spring.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import spring.dto.ProductDTO;
import spring.dto.ProductLotDTO;
import spring.model.ProductLotBean;

public class ProductLotService {

	@Autowired
	ModelMapper mapper;

	public List<ProductLotDTO> getAll() {
		Connection con = ConnectionClass.getConnection();
		List<ProductLotDTO> products = new ArrayList<>();
		try {
			PreparedStatement ps = con.prepareStatement(
					"select p.name,quantity,price,uom,lot_no,expired,c.name category from product p inner join lot l on p.id=l.name_id inner join category c on c.id=p.category_id");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductLotDTO product = new ProductLotDTO();
				product.setCategory(rs.getString("category"));
				product.setExpired(rs.getString("expired"));
				product.setLot(rs.getString("lot_no"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
				product.setQuantity(rs.getInt("quantity"));
				product.setUom(rs.getString("uom"));
				products.add(product);
			}
			con.close();
		} catch (SQLException e) {
			System.out.println("Get ProductLot : " + e.getMessage());
		}
		return products;
	}

	public int insertOne(ProductLotDTO dto) {
		Connection con = ConnectionClass.getConnection();
		ProductService pService = new ProductService();
		boolean dbRs = pService.checkName(dto.getName());
		int result = 0;
		if (dbRs) {
			try {
				PreparedStatement ps = con.prepareStatement(
						"INSERT INTO lot (lot_no, name_id, quantity, uom, expired, price) VALUES (?,(select id from product where name=?),?,?,?,?)");
				ps.setString(1, dto.getLot());
				ps.setString(2, dto.getName());
				ps.setInt(3, dto.getQuantity());
				ps.setString(4, dto.getUom());
				ps.setString(5, dto.getExpired());
				ps.setDouble(6, dto.getPrice());
				result = ps.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Insert Product Lot : " + e.getMessage());
			}
		} else {
			try {
				ProductDTO productDto = new ProductDTO();
				productDto.setName(dto.getName());
				productDto.setCategory(dto.getCategory());
				pService.insertOne(productDto);
				PreparedStatement ps1 = con.prepareStatement(
						"INSERT INTO lot (lot_no, name_id, quantity, uom, expired, price) VALUES (?,(select id from product where name=?),?,?,?,?)");
				con.setAutoCommit(false);
				ps1.setString(1, dto.getLot());
				ps1.setString(2, dto.getName());
				ps1.setInt(3, dto.getQuantity());
				ps1.setString(4, dto.getUom());
				ps1.setString(5, dto.getExpired());
				ps1.setDouble(6, dto.getPrice());
				ps1.executeUpdate();
				con.commit();
			} catch (SQLException e) {
				System.out.println("Insert Product Lot : " + e.getMessage());
			}
		}

		return result;
	}

	public void updateOne(ProductLotDTO dto) {
		Connection con=ConnectionClass.getConnection();
		try {
				checkToAdd(dto);
				PreparedStatement ps = con.prepareStatement("UPDATE lot SET name_id=(select id from product where name=?), quantity=?,uom =?, expired=?, category_id=?, price=? WHERE (lot_no = ?)");
				ps.setString(1, dto.getName());
				ps.setDouble(2, dto.getQuantity());
				ps.setString(3, dto.getUom());
				ps.setString(4, dto.getExpired());
				ps.setString(5, dto.getCategory());
				ps.setDouble(6, dto.getPrice());
				ps.setString(7, dto.getLot());
				int result = ps.executeUpdate();
				con.close();
			}catch(

	SQLException e)
	{
		System.out.println("Update Product Lot : " + e.getMessage());
	}
	}

	private void checkToAdd(ProductLotDTO dto) {
		Connection con=ConnectionClass.getConnection();
		try {
			PreparedStatement ps=con.prepareStatement("select * from lot where expired=?,name=?,price=?,category=?,uom=?");
		} catch (SQLException e) {
			System.out.println("Checking to add : "+ e.getMessage());
		}
	}

	public ProductLotDTO getByLot(String name) {
		Connection con = ConnectionClass.getConnection();
		ProductLotDTO product = null;
		try {
			PreparedStatement ps = con.prepareStatement("select * from lot where lot_no=?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product = new ProductLotDTO();
				product.setLot(rs.getString("lot_no"));
				product.setQuantity(rs.getInt("quantity"));
			}
			con.close();
		} catch (SQLException e) {
			System.out.println("Get ProductLot : " + e.getMessage());
		}
		return product;
	}

	public ProductLotDTO getOne(String lot) {
		Connection con = ConnectionClass.getConnection();
		ProductLotDTO product = null;
		try {
			PreparedStatement ps = con.prepareStatement(
					"select p.name,quantity,price,uom,lot_no,expired,c.name category from product p inner join lot l on p.id=l.name_id inner join category c on c.id=l.category_id where lot_no=?");
			ps.setString(1, lot);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product = new ProductLotDTO();
				product.setCategory(rs.getString("category"));
				product.setExpired(rs.getString("expired"));
				product.setLot(rs.getString("lot_no"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
				product.setQuantity(rs.getInt("quantity"));
				product.setUom(rs.getString("uom"));
			}
			con.close();
		} catch (SQLException e) {
			System.out.println("Get Lot : " + e.getMessage());
		}

		return product;
	}
	
	public List<ProductLotDTO> getOneById(String id) {
		Connection con = ConnectionClass.getConnection();
		List<ProductLotDTO> products = new ArrayList<>();
		
		try {
			PreparedStatement ps = con.prepareStatement(
					"select p.name,quantity,price,uom,lot_no,expired,c.name category from product p inner join lot l on p.id=l.name_id inner join category c on c.id=l.category_id where name_id=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductLotDTO product = new ProductLotDTO();
				product.setCategory(rs.getString("category"));
				product.setExpired(rs.getString("expired"));
				product.setLot(rs.getString("lot_no"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
				product.setQuantity(rs.getInt("quantity"));
				product.setUom(rs.getString("uom"));
				products.add(product);
			}
			con.close();
		} catch (SQLException e) {
			System.out.println("Get Lot : " + e.getMessage());
		}

		return products;
	}

}
