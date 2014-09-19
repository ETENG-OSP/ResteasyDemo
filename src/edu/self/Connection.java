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
			// ���ӱ������ݿ�
			Mongo m = new Mongo();

			// ������Ϊtestdb�����ݿ�
			DB db = m.getDB("testdb");

			// ��ȡtestdb�еļ���
			java.util.Set<String> cols = db.getCollectionNames();

			// ����testdb�еļ���
			for (String s : cols) {
				System.out.println(s);
			}

			// ����һ��"testCol"�ļ���
			DBCollection collection = db.getCollection("testCol");

			// ��ʼ��һ������DB�������ղ������ݿ�ľ������DB����
			BasicDBObject obj = new BasicDBObject();

			// ����JSONObject
			JSONObject jsonObject = new JSONObject(json);
			obj.put("type", jsonObject.getLong("type"));
			obj.put("value", jsonObject.getString("value"));

			// �������
			collection.insert(obj);

			// �鿴��¼
			DBObject dbobj = collection.findOne();

			// ��ӡ���������
			System.out.println(dbobj);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
}
