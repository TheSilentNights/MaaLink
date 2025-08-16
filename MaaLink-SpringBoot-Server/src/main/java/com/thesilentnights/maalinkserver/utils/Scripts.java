package com.thesilentnights.maalinkserver.utils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Scripts {
    public static double eval(String expr) throws ScriptException {
        // 拿到 JavaScript 引擎（Rhino/Nashorn/Graal 均可）
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
        // 直接 eval 得到 Object，再转成 Number
        Object obj = engine.eval(expr);
        if (obj instanceof Number) {
            return ((Number) obj).doubleValue();
        }
        throw new ScriptException("结果不是数值: " + obj);
    }
}
