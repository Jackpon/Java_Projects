package DBC;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;//导入驱动，在目录lib里面
import com.mysql.jdbc.PreparedStatement;

import DBC.DBConfig;

public class DBHelper implements DBConfig{
	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;
	private static PreparedStatement preparedStatement = null;
	//创建连接
	public static Connection getConnection() {
		try {
			Class.forName(driver);
			connection=(Connection) DriverManager.getConnection(url, user,passwd);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	//创建用于执行sql语句的Statement
	public static Statement getStatement() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		statement=getConnection().createStatement();
		return statement;
	}
	//创建用于执行sql语句的PreparedStatement，相较于Statement其安全性更高且有操纵SQL更灵活
		public static PreparedStatement getPreparedStatement(String sql) throws ClassNotFoundException, SQLException {
			Class.forName(driver);
			preparedStatement=(PreparedStatement)getConnection().prepareStatement(sql);
			return preparedStatement;
		}

	
/*数据库的增删改查接口 */

	//数据库更新(增删改)接口（非灵活型）
	public static void update(String sql) throws ClassNotFoundException {
		try {
			getStatement().executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//数据库更新(增删改)接口,可以动态操作(string)
	public static void update(String sql, String a[]) throws ClassNotFoundException {
		try {
			preparedStatement =(PreparedStatement) getPreparedStatement(sql);
			for(int i =0 ; i<a.length; ++i)
				preparedStatement.setString(i+1, a[i]);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//数据库更新(增删改)接口,可以动态操作(int)
		public static void update(String sql, int a[]) throws ClassNotFoundException {
			try {
				preparedStatement =(PreparedStatement) getPreparedStatement(sql);
				for(int i =0 ; i<a.length; ++i)
					preparedStatement.setInt(i+1, a[i]);
				
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	//数据库查询接口（非灵活型）
	public static ResultSet query(String sql) throws ClassNotFoundException {
		try {
			resultSet=getStatement().executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	//数据库查询接口,可以动态操作(String)
	public static ResultSet query(String sql,String a[]) throws ClassNotFoundException {
		try {
			preparedStatement =(PreparedStatement) getPreparedStatement(sql);
			for(int i =0 ; i<a.length; ++i)
				preparedStatement.setString(i+1, a[i]);
			
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	//数据库查询接口,可以动态操作(int)
	public static ResultSet query(String sql,int a[]) throws ClassNotFoundException {
		try {
			preparedStatement =(PreparedStatement) getPreparedStatement(sql);
			for(int i =0 ; i<a.length; ++i)
				preparedStatement.setInt(i+1, a[i]);
			
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	//每次调用update和query都要记得调用close来关闭
	public static void close() {
		try {
			if(resultSet != null)
				resultSet.close();
			if (statement != null) 
				statement.close();
			if(preparedStatement != null)
				preparedStatement.close();
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
/*其他扩展接口写在下面*/
	
	
	
}
