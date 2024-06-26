package spring.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LotService {

	public String getLog()
	{
		Connection con=ConnectionClass.getConnection();
		String str = null;
		try {
			PreparedStatement ps = con.prepareStatement("select lot_no from lot order by lot_no desc limit 1");
			ResultSet rs = ps.executeQuery();
			if(rs != null)
			{
				while(rs.next())
				{
					str = rs.getString("lot_no");
				}
			}
		} catch (SQLException e) {
			System.out.println("Get Lot No : "+ e.getMessage());
		}
		return str;
	}
	
}
