package algorithms.json_converter;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class MyJsonConverter {
    public static void main(String[] args) {
        User user = new User("Anton", 39,
                new User.Address("Ukraine", "Odessa"),
                Sex.MALE,
                new BigDecimal("10000.00"),
                new String[]{"Basketball", "Reading"},
                List.of(7, 22, 3),
                Map.of("telegram", "@anton_dev",
                        "instagram", "https://instagram.com/anton"));


        System.out.println(toJson(user));
    }

    private static String toJson(Object object) {
        if (object == null) {
            return "null";
        } else if (object instanceof String) {
            return "\"" + object + "\"";
        } else if (object instanceof BigDecimal) {
            return object.toString();
        } else if (object instanceof Number) {
            return String.valueOf(object);
        } else if (object instanceof Boolean) {
            return (Boolean) object ? "true" : "false";
        } else if (object.getClass().isEnum()) {
            return "\"" + ((Enum<?>) object).name() + "\"";
        } else if (object.getClass().isArray()) {
            return writeJsonArray(object);
        } else if (object instanceof Collection<?> collection) {
            return writeJsonCollection(collection);
        } else if (object instanceof Map<?, ?> map) {
            return writeJsonMap(map);
        } else {
            return writeJsonPojo(object);
        }
    }

    private static String writeJsonPojo(Object object) {
        Class<?> clazz = object.getClass();
        StringBuilder json = new StringBuilder("{");
        Field[] fields = clazz.getDeclaredFields();
        boolean isFirst = true;
        for (Field field : fields) {
            int modifiers = field.getModifiers();
            if (Modifier.isStatic(modifiers) ||
                    Modifier.isTransient(modifiers) ||
                    field.isSynthetic()) {
                continue;
            }

            field.setAccessible(true);
            String fieldName = field.getName();
            try {
                if (!isFirst) {
                    json.append(", ");
                }
                Object fieldValue = field.get(object);
                json.append("\"").append(fieldName).append("\":");
                json.append(toJson(fieldValue));
                isFirst = false;

            } catch (IllegalAccessException e) {
                if (!isFirst) {
                    json.append(", ");
                }
                json.append("\"").append(field.getName()).append("\":null");
                isFirst = false;
            }
        }
        json.append("}");
        return json.toString();
    }

    private static String writeJsonMap(Map<?, ?> map) {
        StringBuilder jsonMap = new StringBuilder("{");
        boolean isFirst = true;
        for (Map.Entry<?, ?> pair : map.entrySet()) {
            Object key = pair.getKey();
            Object value = pair.getValue();

            String keyString;
            if (key == null) {
                keyString = "null";
            } else {
                keyString = String.valueOf(key);
            }

            if (!isFirst) {
                jsonMap.append(", ");
            }

            jsonMap.append("\"").append(keyString).append("\":");
            jsonMap.append(toJson(value));
            isFirst = false;
        }

        jsonMap.append("}");
        return jsonMap.toString();
    }

    private static String writeJsonCollection(Collection<?> collection) {
        StringBuilder jsonCollection = new StringBuilder("[");
        boolean isFirst = true;
        for (Object element : collection) {
            if (!isFirst) {
                jsonCollection.append(", ");
            }
            jsonCollection.append(toJson(element));
            isFirst = false;
        }
        jsonCollection.append("]");
        return jsonCollection.toString();
    }

    private static String writeJsonArray(Object object) {
        StringBuilder array = new StringBuilder("[");
        int length = Array.getLength(object);
        for (int i = 0; i < length; i++) {
            if (i > 0) array.append(", ");
            Object element = Array.get(object, i);
            array.append(toJson(element));
        }
        array.append("]");
        return array.toString();
    }
}