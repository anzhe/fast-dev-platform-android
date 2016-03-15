package fast.dev.platform.android.database;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import fast.dev.platform.android.bean.DictData;

public class CaseTypeDBService {

	private DBOpenHelper dbHelper;

	public CaseTypeDBService(Context context) {
		dbHelper = new DBOpenHelper(context);
	}

	public void close() {
		if (dbHelper != null) {
			dbHelper.close();
		}
	}

	/**
	 * 批量新增
	 * 
	 * @param list
	 */
	public void batchInsert(List<DictData> list) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		long a = System.currentTimeMillis();
		db.beginTransaction();

		for (DictData dictData : list) {
			ContentValues values = new ContentValues();
			values.put("ID", dictData.getID());
			values.put("UP_ID", dictData.getUP_ID());
			values.put("DICT_NAME", dictData.getDICT_NAME());
			values.put("DICT_LEVEL", dictData.getDICT_LEVEL());
			db.insert("case_type_dict", null, values);
		}

		db.setTransactionSuccessful();
		db.endTransaction();
		long b = System.currentTimeMillis();
		Log.e("CaseTypeDBService-batchInsert-耗时->", String.valueOf(b - a));
	}
	
	public void save(String sql, Object o) throws Exception {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		sql = sql.toLowerCase();
		String insert = null, values = null, excSql = null;
		String[] valuesParam = null, insertParm = null;
		String regex = "values\\s*(.*)";
		Matcher m = Pattern.compile(regex).matcher(sql);
		while (m.find()) {
			if (!"".equals(m.group())) {
				values = m.group();
				valuesParam = values.replace("values", "").replace(" ", "").replace("(", "").replace(")", "")
						.split(",");
			}
		}
		insert = sql.replaceFirst(regex, "");
		String st = insert.substring(insert.indexOf("(") + 1, insert.indexOf(")"));
		insertParm = st.replace(" ", "").split(",");
		if (valuesParam == null || insertParm == null) {
			throw new Exception("sql values 错误");
		}
		int opc = valuesParam.length;
		Object[] objects = new Object[opc];
		StringBuilder sb = new StringBuilder("values (");
		Class c = o.getClass();
		Method[] ms = c.getMethods();
		for (int i = 0;; i++) {
			objects[i] = null;
			String getName = valuesParam[i];
			if (getName == null || getName.equals("?")) {
				getName = insertParm[i];
			}
			for (int j = 0; j < ms.length; j++) {
				if (ms[j].getParameterTypes().length > 0) {
					continue;
				}
				if (ms[j].getName().replace("get", "").toLowerCase().equals(valuesParam[i].toLowerCase())) {
					objects[i] = ms[j].invoke(o);
				}
			}
			if (i == opc - 1) {
				sb.append("?)");
				break;
			} else {
				sb.append("?,");
			}
		}
		excSql = insert + sb.toString();
		db.execSQL(excSql, objects);
		if (db.isOpen()) {
			db.close();
		}
	}

	public <T> List<T> queryViaLevel(Class<T> ot, String level) {
		List<T> list = new ArrayList<T>();
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cs = db.query("case_type_dict", new String[] { "ID,UP_ID ,DICT_NAME,DICT_LEVEL" }, "DICT_LEVEL=?",
				new String[] { level }, null, null, null);
		try {
			while (cs.moveToNext()) {
				T o = ot.newInstance();
				Class c = o.getClass();
				Method[] ms = c.getMethods();
				for (int i = 0; i < cs.getColumnCount(); i++) {
					String cn = cs.getColumnName(i);
					for (int j = 0; j < ms.length; j++) {
						if (ms[j].getParameterTypes().length != 1) {
							continue;
						}
						if (ms[j].getName().toLowerCase().equals(("set" + cn).toLowerCase())) {
							String mt = ms[j].getParameterTypes()[0].getName();
							try {
								if (mt.equals("java.lang.String")) {
									ms[j].invoke(o, cs.getString(i));
								}
								if (mt.equals("java.lang.Integer")) {
									ms[j].invoke(o, cs.getInt(i));
								}
								if (mt.equals("int")) {
									ms[j].invoke(o, cs.getInt(i));
								}
								if (mt.equals("java.lang.Double")) {
									ms[j].invoke(o, cs.getDouble(i));
								}
								if (mt.equals("double")) {
									ms[j].invoke(o, cs.getInt(i));
								}
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								e.printStackTrace();
							}
						}
					}
				}
				list.add(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cs.close();
			db.close();
		}
		return list;
	}
	
	public <T> List<T> query(Class<T> ot, String upid) {
		List<T> list = new ArrayList<T>();
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cs = db.query("case_type_dict", new String[] { "ID,UP_ID,DICT_NAME,DICT_LEVEL" }, "UP_ID=?",
				new String[] { upid }, null, null, null);
		// Cursor cs = db.rawQuery(sql, new String[] {});
		try {
			while (cs.moveToNext()) {
				T o = ot.newInstance();
				Class c = o.getClass();
				Method[] ms = c.getMethods();
				for (int i = 0; i < cs.getColumnCount(); i++) {
					String cn = cs.getColumnName(i);
					for (int j = 0; j < ms.length; j++) {
						if (ms[j].getParameterTypes().length != 1) {
							continue;
						}
						if (ms[j].getName().toLowerCase().equals(("set" + cn).toLowerCase())) {
							String mt = ms[j].getParameterTypes()[0].getName();
							try {
								if (mt.equals("java.lang.String")) {
									ms[j].invoke(o, cs.getString(i));
								}
								if (mt.equals("java.lang.Integer")) {
									ms[j].invoke(o, cs.getInt(i));
								}
								if (mt.equals("int")) {
									ms[j].invoke(o, cs.getInt(i));
								}
								if (mt.equals("java.lang.Double")) {
									ms[j].invoke(o, cs.getDouble(i));
								}
								if (mt.equals("double")) {
									ms[j].invoke(o, cs.getInt(i));
								}
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								e.printStackTrace();
							}
						}
					}
				}
				list.add(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cs.close();
			db.close();
		}
		return list;
	}

	public void detele() {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.delete("case_type_dict", null, null);
	}

	public int queryCaseTypeIdByName(String caseTypeName) {
		int caseTypeId = 0;
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query("case_type_dict", new String[] { "ID" }, "DICT_NAME=?", new String[] { caseTypeName }, null, null, null);
		if (cursor.moveToNext()) {
			caseTypeId = cursor.getInt(cursor.getColumnIndex("ID"));
		}
		return caseTypeId;
	}

	public <T> List<T> queryChildrenViaParentId(Class<T> ot, String UP_ID) {
		List<T> list = new ArrayList<T>();
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cs = db.query("case_type_dict", new String[] { "ID,UP_ID,DICT_NAME,DICT_LEVEL" }, "UP_ID=?",
				new String[] { UP_ID }, null, null, null);
		try {
			while (cs.moveToNext()) {
				T o = ot.newInstance();
				Class c = o.getClass();
				Method[] ms = c.getMethods();
				for (int i = 0; i < cs.getColumnCount(); i++) {
					String cn = cs.getColumnName(i);
					for (int j = 0; j < ms.length; j++) {
						if (ms[j].getParameterTypes().length != 1) {
							continue;
						}
						if (ms[j].getName().toLowerCase().equals(("set" + cn).toLowerCase())) {
							String mt = ms[j].getParameterTypes()[0].getName();
							try {
								if (mt.equals("java.lang.String")) {
									ms[j].invoke(o, cs.getString(i));
								}
								if (mt.equals("java.lang.Integer")) {
									ms[j].invoke(o, cs.getInt(i));
								}
								if (mt.equals("int")) {
									ms[j].invoke(o, cs.getInt(i));
								}
								if (mt.equals("java.lang.Double")) {
									ms[j].invoke(o, cs.getDouble(i));
								}
								if (mt.equals("double")) {
									ms[j].invoke(o, cs.getInt(i));
								}
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								e.printStackTrace();
							}
						}
					}
				}
				list.add(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cs.close();
			db.close();
		}
		return list;
	}
	
	/**
	 * 查询所有案件类型，key为父类型，value为子类型集合
	 * 
	 * @return
	 */
	public Map<String, List<String>> queryAllCaseTypesInMap() {
		Map<String, List<String>> caseTypes = new LinkedHashMap<String, List<String>>();
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cs = db.query("case_type_dict", new String[] { "ID,DICT_NAME" }, "DICT_LEVEL=?", new String[] { "1" },
				null, null, "ID ASC");
		Cursor cs1 = null;
		List<String> subCaseTypes = null;
		int UP_ID;
		while (cs.moveToNext()) {
			UP_ID = cs.getInt(cs.getColumnIndex("ID"));
			cs1 = db.query("case_type_dict", new String[] { "DICT_NAME" }, "UP_ID=?", new String[] { UP_ID + "" }, null,
					null, null);
			subCaseTypes = new ArrayList<String>();
			while (cs1.moveToNext()) {
				subCaseTypes.add(cs1.getString(cs1.getColumnIndex("DICT_NAME")));
			}
			caseTypes.put(cs.getString(cs.getColumnIndex("DICT_NAME")), subCaseTypes);
		}
		cs1.close();
		cs.close();
		db.close();
		return caseTypes;
	}
	
}
