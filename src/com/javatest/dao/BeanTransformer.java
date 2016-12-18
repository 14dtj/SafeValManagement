package com.javatest.dao;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public class BeanTransformer {
	public static void transform2Model(Object model, ResultSetMetaData rsmd, ResultSet rs) {
		Class<?> cla = model.getClass();
		Field[] fields = cla.getDeclaredFields();
		try {
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				Field field = fields[i];
				field.setAccessible(true);
				if (field.getName().equals("reportList")) {
					continue;
				}
				String columnName = rsmd.getColumnName(i + 1);
				String value = rs.getString(columnName).trim();
				try {
					field.set(model, value);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		} catch (SecurityException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static void parseBody2Model(HttpServletRequest request, Object model) {
		Class<?> cla = model.getClass();
		Field[] fields = cla.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			String value = request.getParameter(field.getName());
			try {
				field.set(model, value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
}
