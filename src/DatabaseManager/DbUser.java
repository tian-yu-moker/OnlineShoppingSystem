package DatabaseManager;// STUBBED FILE

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// this is the class through which all Database calls go
public class DbUser extends DbBasic
{
	private Statement statement = null;
	private ResultSet rs = null;
	private String name;
	private PreparedStatement ps = null;


	public DbUser(String dbName, String username)
	{
		super(dbName);
		this.name = username;
	}

	public DbUser( String dbName )
	{
		super( dbName );
	}

	public int getMoney()
	{
		String sql = "select Money FROM HeroInfo WHERE UserId = '" + name + "'";
		int money = 0;
		try {
			statement = DbBasic.con.createStatement();
			rs = statement.executeQuery(sql);
			while(rs.next())
			{
				if(!this.rs.getString("Money").equals(null))
					money = Integer.parseInt(this.rs.getString("Money"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return money;
	}

	public void updateMoney(int money)
	{
		String sql = "UPDATE HeroInfo SET Money = " + money + " WHERE UserId = '" + name + "'";
		try {
			ps = DbBasic.con.prepareStatement(sql);
			ps.executeUpdate();
			DbBasic.con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//Update the pack
	//LJY Use, operation = "Use"
	public void updatePack(int type, int num, String operation)
	{
		if(operation.equals("Add"))
		{
			String sql = "SELECT Type FROM PackInfo WHERE UserId = '" + name + "' AND Type = " + type;
			try {
				statement = DbBasic.con.createStatement();
				rs = statement.executeQuery(sql);
				boolean isExists = false;
				while(rs.next())
					if(!this.rs.getString("Type").equals(null))
						isExists = true;
				//If exists in the package
				if(isExists)
				{
					String mySql = "SELECT Number FROM PackInfo WHERE UserId = '" + name + "' AND Type = " + type;
					statement = DbBasic.con.createStatement();
					rs = statement.executeQuery(mySql);
					int number = Integer.parseInt(this.rs.getString("Number"));
					number += num;
					mySql = "UPDATE PackInfo SET Number = " + number + " WHERE UserId = '" + name + "' AND Type = " + type;
					ps = DbBasic.con.prepareStatement(mySql);
					ps.executeUpdate();
					DbBasic.con.commit();
				}
				else if(!isExists)
				{
					String mySql = "INSERT INTO PackInfo VALUES ('" + name + "', " + type + ", " + num + ")";
					ps = DbBasic.con.prepareStatement(mySql);
					ps.executeUpdate();
					DbBasic.con.commit();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(operation.equals("Use"))
		{
			String sql = "SELECT Number FROM PackInfo WHERE UserId = '" + name + "' AND Type = " + type;
			try {
				statement = DbBasic.con.createStatement();
				rs = statement.executeQuery(sql);
				String myNum = "Null";
				while(rs.next())
					myNum = this.rs.getString("Number");
				int number = 0;
				if(!myNum.equals("Null"))
					number = Integer.parseInt(myNum);

				if(number == 1)
				{
					String mySql = "DELETE FROM PackInfo WHERE UserId = '" + name + "' AND Type = " + type;
					ps = DbBasic.con.prepareStatement(mySql);
					ps.executeUpdate();
					DbBasic.con.commit();
				}
				else if(number > 1)
				{
					number = number - 1;
					String mySql = "UPDATE PackInfo SET Number = " + number + " WHERE UserId = '" + name + "' AND Type = " + type;
					ps = DbBasic.con.prepareStatement(mySql);
					ps.executeUpdate();
					DbBasic.con.commit();
				}
				else if(number <= 0)
				{
					String mySql = "DELETE FROM PackInfo WHERE UserId = '" + name + "' AND Type = " + type;
					ps = DbBasic.con.prepareStatement(mySql);
					ps.executeUpdate();
					DbBasic.con.commit();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	//Get all the items info in the package
	//If no items, return "empty"
	//LJY use
	public String getPackInfo()
	{
		String content = "";
		String sql = "SELECT * FROM PackInfo WHERE UserId = '" + name + "'";
		try {
			statement = DbBasic.con.createStatement();
			rs = statement.executeQuery(sql);
			while(rs.next())
				content = content + rs.getString("Type") + "," + rs.getString("Number") + "\n";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(content.equals(""))
			content = "empty";
		return content;
	}

	//Weapons update
	//Operation Add / Drop
	public void updateEquip(int type, int number, int blank, String operation)
	{
		if(operation.equals("Add"))
		{
			String sql = "INSERT INTO EquipInfo VALUES ('" + name + "', "+ type + ", " + number + ", " + blank + ")";
			try {
				ps = DbBasic.con.prepareStatement(sql);
				ps.executeUpdate();
				DbBasic.con.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(operation.equals("Drop"))
		{
			String sql = "DELETE FROM EquipInfo WHERE UserId = '" + name + "' AND Type = " + type + " AND Number = " + number + " AND Blank = " + blank;
			try {
				ps = DbBasic.con.prepareStatement(sql);
				ps.executeUpdate();
				DbBasic.con.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList getEquipInfo()
	{
		String sql = "SELECT Type, Number, Blank FROM EquipInfo WHERE UserId = '" + name + "'";
		ArrayList type = new ArrayList();
		ArrayList number = new ArrayList();
		ArrayList blank = new ArrayList();
		ArrayList all = new ArrayList();
		try {
			statement = DbBasic.con.createStatement();
			rs = statement.executeQuery(sql);
			while(rs.next())
			{
				type.add(Integer.parseInt(this.rs.getString("Type")));
				number.add(Integer.parseInt(this.rs.getString("Number")));
				blank.add(Integer.parseInt(this.rs.getString("Blank")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		all.add(type);
		all.add(number);
		all.add(blank);
		return all;
	}
	
	public ArrayList getHeroInfo() {
		String content = "";
			String sql = "SELECT Level,Blood,Attack,MP,Speed,Exp FROM HeroInfo WHERE UserId = '" + name + "'";
			ArrayList all = new ArrayList();
			try { 
				statement = DbBasic.con.createStatement();
				rs = statement.executeQuery(sql);
				while(rs.next())
				{
					all.add(Integer.parseInt (rs.getString("Level")));
					all.add(Float.parseFloat (rs.getString("Blood")));
					all.add(Float.parseFloat (rs.getString("Attack")));
					all.add(Float.parseFloat (rs.getString("MP")));
					all.add(Float.parseFloat(rs.getString("Speed")));
					all.add(Integer.parseInt (rs.getString("Exp")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return all;
		}
		
		public void updateHero(int level, float blood, float attack, float mP, float speed, int exp)
		{
			String sql = "UPDATE HeroInfo SET Level = " + level + "," + "Blood=" + blood + "," + "Attack=" + attack +  "," +"MP=" + mP 
					+  "," +"Speed=" + speed +  "," +"Exp=" + exp +  " WHERE UserId = '" + name + "'";
			try {
				ps = DbBasic.con.prepareStatement(sql);
				ps.executeUpdate();
				DbBasic.con.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	//Updata the user information
	public void setUserInfor1(String UserId, String Password)
	{
		String sql = "INSERT INTO HeroInfo(UserId, Password, Money) VALUES ('" + UserId + "', '"+ Password + "', "  + 20 +")";
		try {
			ps = DbBasic.con.prepareStatement(sql);
			ps.executeUpdate();
			DbBasic.con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void setUserInfor2(String heroType, String u)
	{
		//WHERE UserId = '" + name + "'"
		//String sql = "INSERT INTO HeroInfo(HeroType) VALUES('" + heroType +"') WHERE UserId = '" + u + "'";
		int level = 0;
		float blood = 0;
		float attack = 0;
		float MP = 0;
		float speed = 0;
		int exp = 0;

		if(heroType.equals("Lancer"))
		{
			level = 1;
			blood = (float) 150.0;
			attack = (float) 20.0;
			MP = (float) 100.0;
			speed = (float) 70.0;
		}
		else if(heroType.equals("Saber"))
		{
			level = 1;
			blood = (float) 150.0;
			attack = (float) 25.0;
			MP = (float) 100.0;
			speed = (float) 70.0;

		}
		else if(heroType.equals("Knight"))
		{
			level = 1;
			blood = (float) 170.0;
			attack = (float) 15.0;
			MP = (float) 80.0;
			speed = (float) 70.0;

		}
		String mySql = "UPDATE HeroInfo SET HeroType = '" + heroType + "',"+ "Level = " + level + "," + "Blood=" + blood + "," + "Attack=" + attack +  "," +"MP=" + MP
				+  "," +"Speed=" + speed +  "," +"Exp=" + exp +  " WHERE UserId = '" + u + "'";
		//String sql = "UPDATE HeroInfo SET HeroType = ? AND Level = ? AND Blood = ? AND Attack  = ? AND MP = ?" +
		//		" AND Speed = ? AND Exp = ? WHERE UserId = ?";
		try {
			ps =con.prepareStatement(mySql);// DbBasic.

			/*ps.setString(1, heroType);
			ps.setInt(2, level);
			ps.setFloat(3, blood);
			ps.setFloat(4, attack);
			ps.setFloat(5, MP);
			ps.setFloat(6, speed);
			ps.setInt(7, exp);
			ps.setString(8, u);*/
			ps.executeUpdate();
			DbBasic.con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int judgeUserId(String id)
	{//String n
		int exist=0;
		String sql = "select count(UserId) as c FROM HeroInfo WHERE UserId = '" + id +"'";
		//结果为 1，则说明记录存在；结果为 0，则说明记录不存在。
		try {
			statement = DbBasic.con.createStatement();
			rs = statement.executeQuery(sql);//ResultSet
			while(rs.next())
			{
				exist =Integer.parseInt(rs.getString("c"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exist;//rs
	}

	public String getPassword(String nabd)
	{
		String Password = "";
		String sql = "select Password FROM HeroInfo WHERE UserId = '" + nabd + "'";//
		try {
			statement = DbBasic.con.createStatement();
			rs = statement.executeQuery(sql);
			while(rs.next()) {
				Password = rs.getString("Password");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Password;
	}

	public int judgeHeroType(String id)
	{//String n
		int exist=0;
		String sql = "select count(HeroType) as c FROM HeroInfo WHERE UserId = '" + id +"'";
		//结果为 1，则说明记录存在；结果为 0，则说明记录不存在。
		try {
			statement = DbBasic.con.createStatement();
			rs = statement.executeQuery(sql);//ResultSet
			while(rs.next())
			{
				exist =Integer.parseInt(rs.getString("c"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exist;//rs
	}

	public String getHeroType(String user)
	{
		String heroType = "";
		String sql = "select HeroType FROM HeroInfo WHERE UserId = '" + user + "'";
		try {
			statement = DbBasic.con.createStatement();
			rs = statement.executeQuery(sql);
			while(rs.next()) {
				heroType = rs.getString("HeroType");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return heroType;
	}

	public void addMark(String reUserName) {
			String sql = "INSERT INTO Mark VALUES ('" + reUserName + "', " + 0 + ", " + 0 + ", " + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + ")";
			try {
				ps = DbBasic.con.prepareStatement(sql);
				ps.executeUpdate();
				DbBasic.con.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}

	}

	public void updateMark(int level, int star)
	{
		String sql = "UPDATE Mark SET level" + level + " = "+ star + " WHERE UserId = '" + name + "'";
		try {
			ps = DbBasic.con.prepareStatement(sql);
			ps.executeUpdate();
			DbBasic.con.commit();
		} catch (SQLException e) {
				e.printStackTrace();
		}
	}

	public void calculateTotalStar()
	{
		int sum=0;
		String sql = "SELECT * FROM Mark WHERE UserId = '" + name + "'";
		try {
			ps = DbBasic.con.prepareStatement(sql);
			rs=ps.executeQuery();
			ResultSetMetaData md = rs.getMetaData();
			int columnCount = md.getColumnCount();
			while(rs.next()) {
				for(int i =2; i <= columnCount-1; i++) {
					sum = sum+rs.getInt(i);
					//System.out.println(sum);
				}
			}
			String sql2 = "UPDATE Mark SET Sum = "+ sum + " WHERE UserId = '" + name + "'";
			ps = DbBasic.con.prepareStatement(sql2);
			ps.executeUpdate();
			DbBasic.con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public int getLevelRecord(String userId)
	{
		int level = 1;
		String sql = "SELECT CurLevel FROM LevelRecord WHERE UserId = '" + userId + "'";
		try {
			statement = DbBasic.con.createStatement();
			rs = statement.executeQuery(sql);
			String levelNum = "empty";
			while(rs.next())
				levelNum = rs.getString("CurLevel");
			if(!levelNum.equals("empty"))
				level = Integer.parseInt(levelNum);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return level;
	}

	public void updateLevelRecord(String userId, int level)
	{
		String sql = "UPDATE LevelRecord SET CurLevel = " + level +  " WHERE UserId = '" + userId + "'";
		String mySql = "SELECT CurLevel FROM LevelRecord WHERE userId = '" + userId + "'";
		try {
			statement = DbBasic.con.createStatement();
			rs = statement.executeQuery(mySql);
			String levelNum = "empty";
			while(rs.next())
				levelNum = rs.getString("CurLevel");
			System.out.println(levelNum);
			if(!levelNum.equals("empty"))
			{
				ps = DbBasic.con.prepareStatement(sql);
				ps.executeUpdate();
				DbBasic.con.commit();
			}
			else if(levelNum.equals("empty"))
			{
				String insertSql = "INSERT INTO LevelRecord VALUES('" + userId + "', " + level + ")";
				ps = DbBasic.con.prepareStatement(insertSql);
				ps.executeUpdate();
				DbBasic.con.commit();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List getEndInfo()
	{
		List info = new ArrayList();
		String user = "";
		String hero = "";
		String sum = "";
		String sql = "SELECT Mark.UserId, HeroInfo.HeroType, Mark.Sum FROM Mark, HeroInfo WHERE Mark.UserId = HeroInfo.UserId ORDER BY Mark.Sum DESC LIMIT 10";
		try {
			statement = DbBasic.con.createStatement();
			rs = statement.executeQuery(sql);
			//boolean dec = false;
			while(rs.next())
			{
				user += rs.getString("UserId") + "\n";
				hero += rs.getString("HeroType") + "\n";
				sum += rs.getString("Sum") + "\n";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		info.add(user);
		info.add(hero);
		info.add(sum);
		return info;
	}

	public String getOrder()
	{
		String str="";
		String sql = "SELECT UserId,Sum FROM Mark ORDER BY Sum DESC";
		try {
			statement = DbBasic.con.createStatement();
			rs = statement.executeQuery(sql);
			while(rs.next()) {
				String str1 = rs.getString("UserId");
				String str2 = rs.getString("Sum");
				str=str+str1+" "+str2+"\r\n";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return str;
	}

	public int getSum(String UserId)
	{
		int sum = 0;
		String sql = "SELECT Sum FROM Mark WHERE UserId = '" + UserId + "'";
		try {
			statement = DbBasic.con.createStatement();
			rs = statement.executeQuery(sql);
			while(rs.next())
				sum = rs.getInt("Sum");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sum;
	}
}
