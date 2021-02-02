package com.example.demo.login.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;

@Repository("UserDaoNamedJdbcImpl")
public class UserDaoNamedJdbcImpl implements UserDao
{
	
	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	// Userテーブルの件数を取得
	@Override
	public int count()
	{
		// SQL文
		String sql = "SELECT COUNT(*) FROM m_user";
		
		// パラメータを生成
		SqlParameterSource params = new MapSqlParameterSource();
		
		// 全件取得してカウント
		return jdbc.queryForObject(sql, params, Integer.class);
	}
	
	// Userテーブルに1件データをインサート
	@Override
	public int insertOne(User user)
	{
		// SQL文にキー名を指定
		String sql = "INSERT INTO m_user(user_id,"
				+ " password,"
				+ " user_name,"
				+ " birthday,"
				+ " age,"
				+ " marriage,"
				+ " role)"
				+ " VALUES(:userId,"
				+ " :password,"
				+ " :userName,"
				+ " :birthday,"
				+ " :age,"
				+ " :marriage,"
				+ " :role)";
		
		// パラメータの設定
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("userId", user.getUserId())
				.addValue("password", user.getPassword())
				.addValue("userName", user.getUserName())
				.addValue("birthday", user.getBirthday())
				.addValue("age", user.getAge())
				.addValue("marriage", user.isMarriage())
				.addValue("role", user.getRole());
		
		// SQLの実行
		return jdbc.update(sql, params);
	}
	
	// Userテーブルのデータを1件取得
	@Override
	public User selectOne(String userId)
	{
		// SQL文
		String sql = "SELECT * FROM m_user WHERE user_id = :userId";
		
		// パラメータの設定
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("userId", userId);
		
		// SQLの実行
		Map<String, Object> map = jdbc.queryForMap(sql, params);
		
		// 結果返却用のインスタンスを生成
		User user = new User();
		
		// 取得データをインスタンスにセット
		user.setUserId((String)map.get("user_id"));
		user.setPassword((String)map.get("password"));
		user.setUserName((String)map.get("user_name"));
		user.setBirthday((Date)map.get("birthday"));
		user.setAge((Integer)map.get("age"));
		user.setMarriage((Boolean)map.get("marriage"));
		user.setRole((String)map.get("role"));
		
		return user;
	}
	
	//Userテーブルの全データを取得
	@Override
	public List<User> selectMany()
	{
		// SQL文
	    String sql = "SELECT * FROM m_user";
	    
	    // パラメータの設定
	    SqlParameterSource params = new MapSqlParameterSource();
	    
	    // SQLの実行
	    List<Map<String, Object>> getList = jdbc.queryForList(sql, params);
	    
	    // 結果返却用のList
	    List<User> userList = new ArrayList<>();
	    
	    // 取得データ分loop
	    for (Map<String, Object> map: getList) {
	    	// Userインスタンスの生成
	    	User user = new User();
	    	
	    	// Userインスタンスに取得したデータをセットする
	    	user.setUserId((String)map.get("user_id"));
	    	user.setPassword((String)map.get("password"));
	    	user.setUserName((String)map.get("user_name"));
	    	user.setBirthday((Date)map.get("birthday"));
	    	user.setAge((Integer)map.get("age"));
	    	user.setMarriage((Boolean)map.get("marriage"));
	    	user.setRole((String)map.get("role"));
	    	
	    	// Listに追加
	    	userList.add(user);
	    }
	    
	    return userList;
	}
	
	// Userテーブル1件更新
	@Override
	public int updateOne(User user)
	{
		// SQL文
		String sql = "UPDATE M_USER"
				+ " SET"
				+ " password = :password,"
				+ " user_name = :userName,"
				+ " birthday = :birthday,"
				+ " age = :age,"
				+ " marriage = :marriage"
				+ " WHERE user_id = :userId";
		
		// パラメータ
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("userId", user.getUserId())
				.addValue("password", user.getPassword())
				.addValue("userName", user.getUserName())
				.addValue("birthday", user.getBirthday())
				.addValue("age", user.getAge())
				.addValue("marriage", user.isMarriage());
		
		// SQLの実行
		return jdbc.update(sql, params);
	}
	
	// Userテーブルを1件削除
	@Override
	public int deleteOne(String userId)
	{
		// SQL文
		String sql = "DELETE FROM m_user WHERE user_id = :userId";
		
		// パラメータ
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("userId", userId);
		
		// SQLの実行
		int rowNumber = jdbc.update(sql, params);
		
		return rowNumber;
	}
	
	// SQL取得結果をサーバーにCSVで保存する
	@Override
	public void userCsvOut()
	{
		// USERテーブルのデータを全件取得
		String sql = "SELECT * FROM m_user";
		
		// ResultSetExtractorの生成
		UserRowCallbackHandler handler = new UserRowCallbackHandler();
		
		// クエリの実行とCSV出力
		jdbc.query(sql, handler);
	}
	

}
