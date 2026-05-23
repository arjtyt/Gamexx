package com.onesignal.common;

import com.onesignal.core.BuildConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: JSONObjectExtensions.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000`\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0000\u001aC\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052#\u0010\u0006\u001a\u001f\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\b\u0012\b\b\u0004\u0012\u0004\b\b(\t\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0007\u001a5\u0010\n\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052!\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\b\u0012\b\b\u0004\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u000b0\u0007\u001aM\u0010\f\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u00012#\u0010\u000e\u001a\u001f\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\u0004\u0012\u0004\b\b(\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0007\u001a5\u0010\u0010\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052!\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\b\u0012\b\b\u0004\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u000b0\u0007\u001a*\u0010\u0012\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0016\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0018\u00010\u0014\u001a \u0010\u0012\u001a\u00020\u0003*\u00020\u00032\u0014\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0014\u001a\u001c\u0010\u0016\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0017\u001a\u0004\u0018\u00010\u0015\u001a\u0019\u0010\u0018\u001a\u0004\u0018\u00010\u0019*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u001a\u001a\u0019\u0010\u001b\u001a\u0004\u0018\u00010\u001c*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u001d\u001a\u0019\u0010\u001e\u001a\u0004\u0018\u00010\u001f*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010 \u001a\u0014\u0010!\u001a\u0004\u0018\u00010\u0003*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0019\u0010\"\u001a\u0004\u0018\u00010#*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010$\u001a\u0014\u0010%\u001a\u0004\u0018\u00010\u0005*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0014\u0010&\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0018\u00010\u0001*\u00020'\u001a\u0018\u0010(\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00150)*\u00020\u0003¨\u0006*"}, d2 = {"expandJSONArray", "", "T", "Lorg/json/JSONObject;", "name", "", "into", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "childObject", "expandJSONObject", "", "putJSONArray", "list", "create", "item", "putJSONObject", "expand", "putMap", "map", "", "", "putSafe", "value", "safeBool", "", "(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/Boolean;", "safeDouble", "", "(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/Double;", "safeInt", "", "(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/Integer;", "safeJSONObject", "safeLong", "", "(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/Long;", "safeString", "toList", "Lorg/json/JSONArray;", "toMap", "", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class JSONObjectExtensionsKt {
    public static final Integer safeInt(JSONObject $this$safeInt, String name) {
        Intrinsics.checkNotNullParameter($this$safeInt, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        if ($this$safeInt.has(name)) {
            return Integer.valueOf($this$safeInt.getInt(name));
        }
        return null;
    }

    public static final Long safeLong(JSONObject $this$safeLong, String name) {
        Intrinsics.checkNotNullParameter($this$safeLong, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        if ($this$safeLong.has(name)) {
            return Long.valueOf($this$safeLong.getLong(name));
        }
        return null;
    }

    public static final Double safeDouble(JSONObject $this$safeDouble, String name) {
        Intrinsics.checkNotNullParameter($this$safeDouble, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        if ($this$safeDouble.has(name)) {
            return Double.valueOf($this$safeDouble.getDouble(name));
        }
        return null;
    }

    public static final Boolean safeBool(JSONObject $this$safeBool, String name) {
        Intrinsics.checkNotNullParameter($this$safeBool, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        if ($this$safeBool.has(name)) {
            return Boolean.valueOf($this$safeBool.getBoolean(name));
        }
        return null;
    }

    public static final String safeString(JSONObject $this$safeString, String name) {
        Intrinsics.checkNotNullParameter($this$safeString, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        if ($this$safeString.has(name)) {
            return $this$safeString.getString(name);
        }
        return null;
    }

    public static final JSONObject safeJSONObject(JSONObject $this$safeJSONObject, String name) {
        Intrinsics.checkNotNullParameter($this$safeJSONObject, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        if ($this$safeJSONObject.has(name)) {
            return $this$safeJSONObject.getJSONObject(name);
        }
        return null;
    }

    public static final Map<String, Object> toMap(JSONObject $this$toMap) throws JSONException {
        Object list;
        Intrinsics.checkNotNullParameter($this$toMap, "<this>");
        Map results = new HashMap();
        Iterator<String> itKeys = $this$toMap.keys();
        Intrinsics.checkNotNullExpressionValue(itKeys, "this.keys()");
        while (itKeys.hasNext()) {
            String key = itKeys.next();
            Object value = $this$toMap.get(key);
            Intrinsics.checkNotNullExpressionValue(key, "key");
            if (JSONObject.NULL.equals(value)) {
                list = null;
            } else if (value instanceof JSONObject) {
                list = toMap((JSONObject) value);
            } else if (value instanceof JSONArray) {
                list = toList((JSONArray) value);
            } else {
                list = value;
            }
            results.put(key, list);
        }
        return results;
    }

    public static final List<Object> toList(JSONArray $this$toList) {
        Intrinsics.checkNotNullParameter($this$toList, "<this>");
        int size = $this$toList.length();
        List results = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            Object element = $this$toList.opt(i);
            if (JSONObject.NULL.equals(element)) {
                results.add(null);
            } else if (element instanceof JSONArray) {
                results.add(toList((JSONArray) element));
            } else if (element instanceof JSONObject) {
                results.add(toMap((JSONObject) element));
            } else {
                results.add(element);
            }
        }
        return results;
    }

    public static final void expandJSONObject(JSONObject $this$expandJSONObject, String name, Function1<? super JSONObject, Unit> function1) throws JSONException {
        Intrinsics.checkNotNullParameter($this$expandJSONObject, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(function1, "into");
        if ($this$expandJSONObject.has(name)) {
            JSONObject jSONObject = $this$expandJSONObject.getJSONObject(name);
            Intrinsics.checkNotNullExpressionValue(jSONObject, "this.getJSONObject(name)");
            function1.invoke(jSONObject);
        }
    }

    public static final <T> List<T> expandJSONArray(JSONObject $this$expandJSONArray, String name, Function1<? super JSONObject, ? extends T> function1) throws JSONException {
        Intrinsics.checkNotNullParameter($this$expandJSONArray, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(function1, "into");
        List listToRet = new ArrayList();
        if ($this$expandJSONArray.has(name)) {
            JSONArray jsonArray = $this$expandJSONArray.getJSONArray(name);
            int length = jsonArray.length();
            for (int index = 0; index < length; index++) {
                JSONObject itemJSONObject = jsonArray.getJSONObject(index);
                Intrinsics.checkNotNullExpressionValue(itemJSONObject, "itemJSONObject");
                Object item = function1.invoke(itemJSONObject);
                if (item != null) {
                    listToRet.add(item);
                }
            }
        }
        return listToRet;
    }

    public static final JSONObject putMap(JSONObject $this$putMap, Map<String, ? extends Object> map) throws JSONException {
        Intrinsics.checkNotNullParameter($this$putMap, "<this>");
        Intrinsics.checkNotNullParameter(map, "map");
        for (Map.Entry<String, ? extends Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value == null) {
                value = JSONObject.NULL;
            }
            $this$putMap.put(key, value);
        }
        return $this$putMap;
    }

    public static final JSONObject putMap(JSONObject $this$putMap, String name, final Map<String, ? extends Object> map) throws JSONException {
        Intrinsics.checkNotNullParameter($this$putMap, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        if (map != null) {
            putJSONObject($this$putMap, name, new Function1<JSONObject, Unit>() { // from class: com.onesignal.common.JSONObjectExtensionsKt.putMap.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object p1) throws JSONException {
                    invoke((JSONObject) p1);
                    return Unit.INSTANCE;
                }

                public final void invoke(JSONObject it) throws JSONException {
                    Intrinsics.checkNotNullParameter(it, "it");
                    JSONObjectExtensionsKt.putMap(it, map);
                }
            });
        }
        return $this$putMap;
    }

    public static final JSONObject putJSONObject(JSONObject $this$putJSONObject, String name, Function1<? super JSONObject, Unit> function1) throws JSONException {
        Intrinsics.checkNotNullParameter($this$putJSONObject, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(function1, "expand");
        JSONObject childJSONObject = new JSONObject();
        function1.invoke(childJSONObject);
        $this$putJSONObject.put(name, childJSONObject);
        return $this$putJSONObject;
    }

    public static final <T> JSONObject putJSONArray(JSONObject $this$putJSONArray, String name, List<? extends T> list, Function1<? super T, ? extends JSONObject> function1) throws JSONException {
        Intrinsics.checkNotNullParameter($this$putJSONArray, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(function1, "create");
        if (list != null) {
            JSONArray jsonArray = new JSONArray();
            List<? extends T> $this$forEach$iv = list;
            for (Object element$iv : $this$forEach$iv) {
                JSONObject item = (JSONObject) function1.invoke(element$iv);
                if (item != null) {
                    jsonArray.put(item);
                }
            }
            $this$putJSONArray.put(name, jsonArray);
        }
        return $this$putJSONArray;
    }

    public static final JSONObject putSafe(JSONObject $this$putSafe, String name, Object value) throws JSONException {
        Intrinsics.checkNotNullParameter($this$putSafe, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        if (value != null) {
            $this$putSafe.put(name, value);
        }
        return $this$putSafe;
    }
}
