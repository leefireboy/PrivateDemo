/*
 * Copyright (c) 2016 libing. All rights reserved.
 */
package cn.com.tcsl.sqlgeneratorutil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <P>
 * Description:A Class to generate SqlMap
 * </p>
 * @author libing
 * @version 1.0
 * @Date 2016年6月14日下午3:31:42
 */
@SuppressWarnings("unchecked")
public class SqlGeneratorUtil {

 // mode to return sql and values
    private static final String MODE_SQL = "0";
    // mode to return values
    private static final String MODE_VALUES = "1";
    // sqlMap key for sql
    static final String KEY_FOR_SQL = "sql";
    // sqlMap key for values
    static final String KEY_FOR_VALUES = "values";

    /**
     * <P>
     * Description:generate inserts sql and values
     * </p>
     * @author libing
     * @version 1.0
     * @Date 2016年6月14日下午3:55:37
     * @param tableName tableName
     * @param datas some datas
     * @param ignoreCols cols not to insert
     * @return Map<String, Object> two K-V "sql"-"insert into..." "values"-"List<Object[]>" or null
     */
    static Map<String, Object> insertsSqlGenerator(String tableName, List<Map<String, Object>> datas,
                                                          List<String> ignoreCols) {
        if (null == tableName || "".equals(tableName)) {
            throw new RuntimeException("tableName is null or nothing when you use the method "
                    + "insertsSqlGenerator(String tableName, List<Map<String, Object>> datas, String ignoreCols)");
        }

        if (null == datas) {
            throw new RuntimeException("datas is null when you use the method "
                    + "insertsSqlGenerator(String tableName, List<Map<String, Object>> datas, String ignoreCols)");
        } else if (datas.isEmpty()) {
            return null;
        }

        Map<String, Object> sqlMap = null;
        Map<String, Object> data = null;
        Map<String, Object> aValueMap = null;
        List<Object[]> values = null;
        Object[] value = null;

        for (int i = 0; i < datas.size(); i++) {
            data = datas.get(i);

            if (i == 0) {
                sqlMap = insertSqlGenerator(MODE_SQL, tableName, data, ignoreCols);
                if (sqlMap == null)
                    return null;
            } else {
                aValueMap = insertSqlGenerator(MODE_VALUES, tableName, data, ignoreCols);
                values = (List<Object[]>) aValueMap.get("values");
                value = values.get(0);
                values = (List<Object[]>) sqlMap.get("values");
                values.add(value);
            }
        }

        return sqlMap;
    }

    /**
     * <P>
     * Description:generate insert sql and values or just values
     * </p>
     * @author libing
     * @version 1.0
     * @Date 2016年6月14日下午9:39:48
     * @param mode mode==0:return sql and values mode==1:return values
     * @param tableName tableName
     * @param data one data
     * @param ignoreCols cols not to insert
     * @return Map<String, Object> mode=1:one K-V "values"-"List<Object[]>"
     *      mode=0:two K-V "sql"-"insert into..." "values"-"List<Object[]>" or null
     */
    static Map<String, Object> insertSqlGenerator(String mode, String tableName, Map<String, Object> data,
                                                         List<String> ignoreCols) {
        if (!(MODE_SQL.equals(mode) || MODE_VALUES.equals(mode))) {
            throw new RuntimeException("the mode is not be used now.you must choose \"0\" or \"1\"");
        }

        if (null == tableName || "".equals(tableName)) {
            throw new RuntimeException("tableName is null or nothing when you use the method "
                    + "insertSqlGenerator(String tableName, Map<String, Object> data, String ignoreCols)");
        }

        if (null == data || data.size() < 1) {
            return null;
        }

        Map<String, Object> sqlMap = null;
        StringBuffer sqlInsert = null;
        StringBuffer sqlValues = null;
        List<Object[]> values = null;
        Object[] value = null;

        if (MODE_SQL.equals(mode)) {
            // insert into tableName(col1,col2,...,coln) endwith a " "
            sqlInsert = new StringBuffer();
            // values(val1,val2,...,valn)
            sqlValues = new StringBuffer();

            append(sqlInsert, "insert into ", tableName, "(");
            append(sqlValues, "values(");

            Set<Map.Entry<String, Object>> es = data.entrySet();
            value = new Object[es.size() - (ignoreCols == null ? 0 : ignoreCols.size())];

            int i = 0;
            for (Map.Entry<String, Object> e : es) {
                String col = e.getKey();

                if (ignoreCols != null && !ignoreCols.isEmpty()) {
                    if (ignoreCols.contains(col))
                        continue;
                }

                if (i != 0) {
                    append(sqlInsert, ",");
                    append(sqlValues, ",");
                }

                append(sqlInsert, col);
                append(sqlValues, "?");

                value[i] = e.getValue();

                i++;
            }

            values = new ArrayList<Object[]>();
            values.add(value);

            // 多个空格 后面是 values
            append(sqlInsert, ") ");
            append(sqlValues, ")");

            sqlMap = new HashMap<String, Object>();
            sqlMap.put(KEY_FOR_SQL, sqlInsert.append(sqlValues.toString()).toString());
            sqlMap.put(KEY_FOR_VALUES, values);

            return sqlMap;
        }

        if (MODE_VALUES.equals(mode)) {
            Set<Map.Entry<String, Object>> es = data.entrySet();
            value = new Object[es.size() - (ignoreCols == null ? 0 : ignoreCols.size())];

            int i = 0;
            for (Map.Entry<String, Object> e : es) {
                String col = e.getKey();

                if (ignoreCols != null && !ignoreCols.isEmpty()) {
                    if (ignoreCols.contains(col))
                        continue;
                }

                value[i] = e.getValue();

                i++;
            }

            values = new ArrayList<Object[]>();
            values.add(value);

            sqlMap = new HashMap<String, Object>();
            sqlMap.put(KEY_FOR_VALUES, values);

            return sqlMap;
        }

        return null;
    }

    /**
     * <P>
     * Description:generate updates sql and values
     * </p>
     * @author libing
     * @version 1.0
     * @Date 2016年6月15日下午5:12:41
     * @param tableName tableName
     * @param datas some datas
     * @param idCols list for where params
     * @param ignoreCols cols not to update
     * @return Map<String, Object> two K-V "sql"-"update ..." "values"-"List<Object[]>" or null
     */
    static Map<String, Object> updatesSqlGenerator(String tableName, List<Map<String, Object>> datas,
                                                          List<String> idCols,  List<String> ignoreCols) {
        if (null == tableName || "".equals(tableName)) {
            throw new RuntimeException("tableName is null or nothing when you use the method "
                    + "updatesSqlGenerator(String, List<Map<String, Object>>, String, String)");
        }

        if (null == datas) {
            throw new RuntimeException("datas is null when you use the method "
                    + "updatesSqlGenerator(String, List<Map<String, Object>>, String, String)");
        } else if (datas.isEmpty()) {
            return null;
        }

        if (null == idCols || idCols.size() < 1) {
            throw new RuntimeException("idCols is null or nothing! when you update a table with some data"
                    + " you'd have to provide the params after \"where\" keyword.");
        }

        Map<String, Object> sqlMap = null;
        Map<String, Object> data = null;
        Map<String, Object> aValueMap = null;
        List<Object[]> values = null;
        Object[] value = null;

        for (int i = 0; i < datas.size(); i++) {
            data = datas.get(i);

            if (i == 0) {
                sqlMap = updateSqlGenerator(MODE_SQL, tableName, data, idCols, ignoreCols);
                if (sqlMap == null)
                    return null;
            } else {
                aValueMap = updateSqlGenerator(MODE_VALUES, tableName, data, idCols, ignoreCols);
                values = (List<Object[]>) aValueMap.get("values");
                value = values.get(0);
                values = (List<Object[]>) sqlMap.get("values");
                values.add(value);
            }
        }

        return sqlMap;
    }

    /**
     * <P>
     * Description:generate update sql and values or just values
     * </p>
     * @author libing
     * @version 1.0
     * @Date 2016年6月15日下午5:12:35
     * @param mode mode==0:return sql and values mode==1:return values
     * @param tableName tableName
     * @param data one data
     * @param idCols list for where params
     * @param ignoreCols cols not to update
     * @return Map<String, Object> mode=1:one K-V "values"-"List<Object[]>"
     *      mode=0:two K-V "sql"-"update ..." "values"-"List<Object[]>" or null
     */
    static Map<String, Object> updateSqlGenerator(String mode, String tableName, Map<String, Object> data,
                                                         List<String> idCols, List<String> ignoreCols) {
        if (!(MODE_SQL.equals(mode) || MODE_VALUES.equals(mode))) {
            throw new RuntimeException("the mode is not be used now.you must choose \"0\" or \"1\" when you use the "
                    + "method updateSqlGenerator(String, String, Map<String, Object>, String, String)");
        }

        if (null == tableName || "".equals(tableName)) {
            throw new RuntimeException("tableName is null or nothing when you use the method "
                    + "updateSqlGenerator(String, String, Map<String, Object>, String, String)");
        }

        if (null == data || data.size() < 1) {
            return null;
        }

        if (null == idCols || idCols.size() < 1) {
            throw new RuntimeException("idCols is null or nothing! when you update a table with some data"
                    + " you'd have to provide the params after \"where\" keyword.");
        }

        if (idCols.size() + ignoreCols.size() == data.size()) {
            return null;
        }

        Map<String, Object> sqlMap = null;
        StringBuffer sqlUpdate = null;
        List<Object[]> values = null;
        Object[] value = null;

        if (MODE_SQL.equals(mode)) {
            // update tableName set ...
            sqlUpdate = new StringBuffer();
            append(sqlUpdate, "update ", tableName, " set ");

            Set<Map.Entry<String, Object>> es = data.entrySet();
            value = new Object[es.size() - (ignoreCols == null ? 0 : ignoreCols.size())];

            int i = 0;
            for (Map.Entry<String, Object> e : es) {
                String col = e.getKey();

                if (idCols != null && !idCols.isEmpty()) {
                    if (idCols.contains(col))
                        continue;
                }

                if (ignoreCols != null && !ignoreCols.isEmpty()) {
                    if (ignoreCols.contains(col))
                        continue;
                }

                if (i != 0)
                    sqlUpdate.append(",");

                sqlUpdate.append(col).append("=?");

                value[i] = e.getValue();

                i++;
            }

            sqlUpdate.append(" where ");

            int j = 0;
            for (String id : idCols) {
                if (j != 0)
                    sqlUpdate.append(" and ");

                sqlUpdate.append(id).append("=?");

                value[i + j] = data.get(id);

                j++;
            }

            values = new ArrayList<Object[]>();
            values.add(value);

            sqlMap = new HashMap<String, Object>();
            sqlMap.put(KEY_FOR_SQL, sqlUpdate.toString());
            sqlMap.put(KEY_FOR_VALUES, values);

            return sqlMap;
        }

        if (MODE_VALUES.equals(mode)) {
            Set<Map.Entry<String, Object>> es = data.entrySet();
            value = new Object[es.size() - (ignoreCols == null ? 0 : ignoreCols.size())];

            int i = 0;
            for (Map.Entry<String, Object> e : es) {
                String col = e.getKey();

                if (idCols != null && !idCols.isEmpty()) {
                    if (idCols.contains(col))
                        continue;
                }

                if (ignoreCols != null && !ignoreCols.isEmpty()) {
                    if (ignoreCols.contains(col))
                        continue;
                }

                value[i] = e.getValue();

                i++;
            }

            values = new ArrayList<Object[]>();
            values.add(value);

            sqlMap = new HashMap<String, Object>();
            sqlMap.put(KEY_FOR_VALUES, values);

            return sqlMap;
        }

        return null;
    }

    /**
     * <P>
     * Description:query sql and params for sub table
     * </p>
     * @author libing
     * @version 1.0
     * @Date 2016年6月18日下午4:40:42
     * @param tableName tableName for subTable
     * @param associateCols cols relationship between mainTable and subTable
     *          eg:"id,main_id,name,main_name"
     * @param datas datas from mainTable will be used to the query conditions
     *          eg:list[0]=Map("id"-1,"name"-"a") list[1]=Map("id"-2,"name"-"b")
     * @return
     */
    static Map<String, Object> associateSqlGenerator(String tableName, String associateCols, List<Map<String, Object>> datas){
        Map<String, Object> sqlMap = associateWhereSqlGenerator(associateCols, datas);

        // get sql part after Where
        String sqlWhere = (String) sqlMap.get(SqlGeneratorUtil.KEY_FOR_SQL);

        sqlMap.put(SqlGeneratorUtil.KEY_FOR_SQL, "select * from " + tableName + " where " + sqlWhere);

        return sqlMap;
    }

    /**
     * <P>
     * Description:query sqlWhere and params for sub table
     * </p>
     * @author libing
     * @version 1.0
     * @Date 2016年6月18日下午12:09:06
     * @param associateCols cols relationship between mainTable and subTable
     *          eg:"id,main_id,name,main_name"
     * @param datas datas from mainTable will be used to the query conditions
     *          eg:list[0]=Map("id"-1,"name"-"a") list[1]=Map("id"-2,"name"-"b")
     * @return Map<String, Object>
     *          two K-V:"sql"-"sqlAfterWhere" "values"-Object[]
     */
    static Map<String, Object> associateWhereSqlGenerator(String associateCols, List<Map<String, Object>> datas) {
        Map<String, Object> sqlMap = null;
        StringBuffer sqlWhere = null;
        Object[] values = null;

        String[] associateColsArr = associateCols.split(",");

        // num of where condition
        int cols = associateColsArr.length / 2;

        values = new Object[datas.get(0).size()];

        if (cols == 1) {
            sqlWhere = new StringBuffer();
            values = new Object[datas.size()];

            // "subCol in (..."
            SqlGeneratorUtil.append(sqlWhere, associateColsArr[1], " in (");

            for (int i = 0; i < datas.size(); i++) {
                if (i > 0)
                    sqlWhere.append(",");

                sqlWhere.append("?");

                values[i] = datas.get(i).get(associateColsArr[0]);
            }

            sqlWhere.append(")");
        } else {
            values = new Object[datas.size() * cols];

            sqlWhere = new StringBuffer();
//            sqlWhere.append("(");

            int valueIdx = 0;
            for (int i = 0; i < datas.size(); i++) {
                if (i > 0)
                    sqlWhere.append(" or ");

                for (int j = 0; j < cols; j++) {
                    if (j == 0)
                        sqlWhere.append("(");

                    if (j > 0)
                        sqlWhere.append(" and ");

                    sqlWhere.append(associateColsArr[j * 2 + 1]).append("=?");

                    if (j == cols - 1)
                        sqlWhere.append(")");

                    values[valueIdx++] = datas.get(i).get(associateColsArr[j * 2]);
                }
            }

//            sqlWhere.append(")");
        }

        sqlMap = new HashMap<String, Object>();
        sqlMap.put(SqlGeneratorUtil.KEY_FOR_SQL, sqlWhere.toString());
        sqlMap.put(SqlGeneratorUtil.KEY_FOR_VALUES, values);

        return sqlMap;
    }

    /**
     * <P>
     * Description:link strs
     * </p>
     * @author libing
     * @version 1.0
     * @Date 2016年6月14日下午4:25:24
     * @param str
     * @param strs
     */
    static void append(StringBuffer str, String... strs) {
        if (str != null) {
            for (String s : strs) {
                str.append(s == null ? "" : s);
            }
        }
    }

    public static void main(String[] args) {
        // Test insertSqlGenerator
//        Map<String, Object> data = new HashMap<String, Object>();
//        data.put("id", 1);
//        data.put("name", "Lee");
//
//        List<String> ignoreCols = new ArrayList<String>();
//        ignoreCols.add("name");
////        ignoreCols = null;
//
//        Map<String, Object> sqlMap = insertSqlGenerator("0", "emp", data, ignoreCols);
//        if (sqlMap != null) {
//            String sql = (String) sqlMap.get("sql");
//            System.out.println(sql);
//            List<Object[]> values = (List<Object[]>) sqlMap.get("values");
//            System.out.println("values size is : " + values.size());
//            System.out.println("one value size is : " + values.get(0).length);
//            System.out.println(sqlMap);
//        } else {
//            System.out.println(sqlMap);
//        }


        // Test insertsSqlGenerator
//        Map<String, Object> data = new HashMap<String, Object>();
//        data.put("id", 1);
//        data.put("name", "Lee");
//        List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
//        datas.add(data);
//        datas.add(data);
//        datas.add(data);
//        System.out.println(datas.size());
//
//        List<String> ignoreCols = new ArrayList<String>();
////        ignoreCols.add("name");
//        Map<String, Object> sqlMap = insertsSqlGenerator("emp", datas, ignoreCols);
//        System.out.println(sqlMap);


        // Test updateSqlGenerator
//        Map<String, Object> data = new HashMap<String, Object>();
//        data.put("id", 1);
//        data.put("name", "Lee");
//        data.put("age", 24);
//        data.put("height", 177);
//
//        List<String> idCols = new ArrayList<String>();
//        idCols.add("id");
//
//        List<String> ignoreCols = new ArrayList<String>();
////        ignoreCols.add("name");
//
//        Map<String, Object> sqlMap = updateSqlGenerator("0", "emp", data, idCols, ignoreCols);
//        if (sqlMap != null) {
//            String sql = (String) sqlMap.get("sql");
//            System.out.println(sql);
//            List<Object[]> values = (List<Object[]>) sqlMap.get("values");
//            System.out.println("values size is : " + values.size());
//            System.out.println("one value size is : " + values.get(0).length);
//            System.out.println(sqlMap);
//        } else {
//            System.out.println(sqlMap);
//        }

        // Test updatesSqlGenerator
//        Map<String, Object> data = new HashMap<String, Object>();
//        data.put("id", 1);
//        data.put("name", "Lee");
//        data.put("age", 24);
//        data.put("height", 177);
//        List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
//        datas.add(data);
//        datas.add(data);
//        datas.add(data);
//        System.out.println(datas.size());
//
//        List<String> idCols = new ArrayList<String>();
//        idCols.add("id");
//        List<String> ignoreCols = new ArrayList<String>();
//        ignoreCols.add("name");
//        ignoreCols.add("age");
//        Map<String, Object> sqlMap = updatesSqlGenerator("emp", datas, idCols, ignoreCols);
//        System.out.println(sqlMap);


        // Test append
//        StringBuffer strBuf = new StringBuffer();
//        append(strBuf, "123", null);
//        System.out.print(strBuf.toString());


        List<Map<String, Object>> mainList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1234);
        map.put("name", "djfalkfjdlsakjf;dls");
        mainList.add(map);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", 0000);
        map1.put("name", "iiii");
        mainList.add(map1);
        Map<String, Object> sqlMap = associateWhereSqlGenerator("id,main_id", mainList);
        System.out.println(sqlMap.get(KEY_FOR_SQL));

        Object[] values = (Object[]) sqlMap.get(KEY_FOR_VALUES);
        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i]);
        }

    }

}
