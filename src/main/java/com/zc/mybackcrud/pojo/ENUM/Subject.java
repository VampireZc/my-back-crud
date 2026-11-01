package com.zc.mybackcrud.pojo.ENUM;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum Subject {
    JAVA(1, "Java"),
    FRONT_END(2, "前端"),
    BIG_DATA(3, "大数据"),
    PYTHON(4, "Python"),
    GO(5, "Go"),
    EMBEDDED(6, "嵌入式");

    private final Integer value;
    private final String label;

    Subject(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    /** 统一出口：构造前端下拉框需要的 {"name":.., "value":..} */
    public static Map<String, Object> option(String name, Object value) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("name", name);
        m.put("value", value);
        return m;
    }

    /** 本枚举的全部选项，直接给前端 */
    public static List<Map<String, Object>> options() {
        return Arrays.stream(values())
                .map(s -> option(s.label, s.value))
                .toList();
    }

    /** 反查：3 -> "大数据"，未知值原样返回，方便展示 */
    public static String labelOf(Integer value) {
        return Arrays.stream(values())
                .filter(s -> s.value.equals(value))
                .map(Subject::getLabel)
                .findFirst()
                .orElse(String.valueOf(value));
    }

    /** 校验：新增/修改班级时判断 subject 是否合法 */
    public static boolean isValid(Integer value) {
        return value != null && Arrays.stream(values()).anyMatch(s -> s.value.equals(value));
    }

}
