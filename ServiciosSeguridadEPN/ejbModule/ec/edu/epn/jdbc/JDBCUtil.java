package ec.edu.epn.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUtil {
  
	public static void close(Connection obj){
		try {
			if (obj!=null) {
				obj.close();
			}
		}catch(Exception e){}
	}
	
	public static void close(PreparedStatement obj){
		try {
			if (obj!=null) {
				obj.close();
			}
		}catch(Exception e){}
	}

	public static void close(ResultSet obj){
		try {
			if (obj!=null) {
				obj.close();
			}
		}catch(Exception e){}
	}
}
