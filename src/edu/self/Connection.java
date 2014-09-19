package edu.self;

import org.json.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class Connection implements EventAPI {
	@Override
	public boolean doGet(String json) {

		boolean result = false;

		try {
			// 连接本地数据库
			Mongo m = new Mongo();

			// 创建名为testdb的数据库
			DB db = m.getDB("testdb");

			// 获取testdb中的集合
			java.util.Set<String> cols = db.getCollectionNames();

			// 遍历testdb中的集合
			for (String s : cols) {
				System.out.println(s);
			}

			// 创建一个"testCol"的集合
			DBCollection collection = db.getCollection("testCol");

			// 初始化一个基本DB对象，最终插入数据库的就是这个DB对象
			BasicDBObject obj = new BasicDBObject();

			// 创建JSONObject
			JSONObject jsonObject = new JSONObject(json);
			obj.put("type", jsonObject.getLong("type"));
			obj.put("value", jsonObject.getString("value"));

			// 插入对象
			collection.insert(obj);

			// 查看记录
			DBObject dbobj = collection.findOne();

			// 打印插入的数据
			System.out.println(dbobj);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
}
