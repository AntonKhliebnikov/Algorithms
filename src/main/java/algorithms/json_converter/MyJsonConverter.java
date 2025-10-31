package algorithms.json_converter;

import java.lang.reflect.Field;

public class MyJsonConverter {
    public static void main(String[] args) {
        User user = new User("Anton", 39,
                new User.Address("Ukraine", "Odessa"));

        System.out.println(toJson(user));
    }

    private static String toJson(Object object) {
        if (object == null) {
            return null;
        }

        Class<?> cl = object.getClass();
        StringBuilder json = new StringBuilder("{");
        Field[] fields = cl.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            try {
                String fieldName = field.getName();
                Object fieldValue = field.get(object);
                json.append("\"").append(fieldName).append("\":");

                if (fieldValue == null) {
                    json.append("null");
                } else if (isPrimitiveOrWrapper(field.getType()) || fieldValue instanceof String) {
                    if (fieldValue instanceof String) {
                        json.append("\"").append(fieldValue).append("\"");
                    } else {
                        json.append(fieldValue);
                    }
                } else {
                    json.append(toJson(fieldValue));
                }

                if (i < fields.length - 1) {
                    json.append(", ");
                }

            } catch (IllegalAccessException e) {
                e.fillInStackTrace();
            }
        }
        json.append("}");
        return json.toString();
    }

    private static boolean isPrimitiveOrWrapper(Class<?> type) {
        return type.isPrimitive() ||
                type == Integer.class ||
                type == Long.class ||
                type == Double.class ||
                type == Float.class ||
                type == Character.class ||
                type == Boolean.class ||
                type == Short.class ||
                type == Byte.class;
    }
}