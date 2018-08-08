package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeGenTest {

    public static final char UNDERLINE = '_';

    public static void main(String[] args) {
        System.out.println("start");

        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/wx_mall?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=utf8";
        String user = "root";
        String password = "root";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            if (!conn.isClosed()) {
                Statement statement = conn.createStatement();
                String sql = "select COLUMN_NAME,COLUMN_COMMENT ,DATA_TYPE from information_schema.COLUMNS where table_name = 'goods_properties'";
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    String columnComment = rs.getString("COLUMN_COMMENT");
                    String dataType = rs.getString("DATA_TYPE");

                    String columnName = rs.getString("COLUMN_NAME");
                    String camelColumn = underlineToCamel(columnName);

//                    System.out.println(getEntity(camelColumn, columnComment, dataType));
//                    System.out.println("" + columnName+",");
//					  System.out.println("<result property=\""+camelColumn+ "\" column=\""+columnName+"\"/>");
                    System.out.println("<if test=\"vo." + camelColumn + " != null\"> a." + columnName + " = #{vo." + camelColumn + "},</if>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getEntity(String camelColumn, String columnComment, String dataType) {
        String type = "";

        switch (dataType) {
            case "int":
                type = "Integer";
                break;
            case "varchar":
                type = "String";
                break;
            case "float":
                type = "Double";
                break;
            case "tinyint":
                type = "Integer";
                break;
            case "datetime":
                type = "Date";
                break;
            case "timestamp":
                type = "Date";
                break;
        }
        return "private " + type + " " + camelColumn + "; //" + columnComment;
    }

    /**
     * 将下划线转换为峰陀
     *
     * @param param
     * @return
     */
    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        StringBuilder sb = new StringBuilder(param);
        Matcher mc = Pattern.compile("_").matcher(param);
        int i = 0;
        while (mc.find()) {
            int position = mc.end() - (i++);
            sb.replace(position - 1, position + 1, sb.substring(position, position + 1).toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将峰陀转换为下划线
     *
     * @param param
     * @return
     */
    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
