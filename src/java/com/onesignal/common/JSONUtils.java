package com.onesignal.common;

import android.os.Bundle;
import com.onesignal.core.BuildConfig;
import com.onesignal.debug.internal.logging.Logging;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: JSONUtils.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u001a\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\fJ\u0010\u0010\u000e\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000f\u001a\u00020\u0004J\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u00112\u0006\u0010\u0012\u001a\u00020\u0006J\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u00142\u0006\u0010\u0015\u001a\u00020\fJ\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0017\u001a\u00020\u0001J\u000e\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0006J\u0010\u0010\u001a\u001a\u00020\f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/onesignal/common/JSONUtils;", "", "()V", "EXTERNAL_USER_ID", "", "bundleAsJSONObject", "Lorg/json/JSONObject;", "bundle", "Landroid/os/Bundle;", "compareJSONArrays", "", "jsonArray1", "Lorg/json/JSONArray;", "jsonArray2", "jsonStringToBundle", "data", "newStringMapFromJSONObject", "", "jsonObject", "newStringSetFromJSONArray", "", "jsonArray", "normalizeType", "object", "toUnescapedEUIDString", "json", "wrapInJsonArray", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class JSONUtils {
    public static final String EXTERNAL_USER_ID = "external_user_id";
    public static final JSONUtils INSTANCE = new JSONUtils();

    private JSONUtils() {
    }

    public final JSONArray wrapInJsonArray(JSONObject jsonObject) {
        JSONArray jSONArrayPut = new JSONArray().put(jsonObject);
        Intrinsics.checkNotNullExpressionValue(jSONArrayPut, "JSONArray().put(jsonObject)");
        return jSONArrayPut;
    }

    public final JSONObject bundleAsJSONObject(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        JSONObject json = new JSONObject();
        for (String key : bundle.keySet()) {
            try {
                json.put(key, bundle.get(key));
            } catch (JSONException e) {
                Logging.error("bundleAsJSONObject error for key: " + key, e);
            }
        }
        return json;
    }

    public final Bundle jsonStringToBundle(String data) {
        Intrinsics.checkNotNullParameter(data, "data");
        try {
            JSONObject jsonObject = new JSONObject(data);
            Bundle bundle = new Bundle();
            Iterator<String> itKeys = jsonObject.keys();
            Intrinsics.checkNotNullExpressionValue(itKeys, "jsonObject.keys()");
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlin.String");
                String key = next;
                String value = jsonObject.getString(key);
                bundle.putString(key, value);
            }
            return bundle;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public final Map<String, String> newStringMapFromJSONObject(JSONObject jsonObject) {
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        Iterator<String> itKeys = jsonObject.keys();
        Intrinsics.checkNotNullExpressionValue(itKeys, "jsonObject.keys()");
        Map result = new LinkedHashMap();
        while (itKeys.hasNext()) {
            String key = itKeys.next();
            try {
                Object value = jsonObject.opt(key);
                if ((value instanceof JSONArray) || (value instanceof JSONObject)) {
                    Logging.error$default("Omitting key '" + key + "'! sendTags DO NOT supported nested values!", null, 2, null);
                } else if (jsonObject.isNull(key) || Intrinsics.areEqual("", value)) {
                    result.put(key, "");
                } else {
                    result.put(key, value.toString());
                }
            } catch (Throwable th) {
            }
        }
        return result;
    }

    public final Set<String> newStringSetFromJSONArray(JSONArray jsonArray) throws JSONException {
        Intrinsics.checkNotNullParameter(jsonArray, "jsonArray");
        Set stringSet = new LinkedHashSet();
        int length = jsonArray.length();
        for (int i = 0; i < length; i++) {
            String string = jsonArray.getString(i);
            Intrinsics.checkNotNullExpressionValue(string, "jsonArray.getString(i)");
            stringSet.add(string);
        }
        return stringSet;
    }

    public final String toUnescapedEUIDString(JSONObject json) {
        String matched;
        Intrinsics.checkNotNullParameter(json, "json");
        String strJsonBody = json.toString();
        Intrinsics.checkNotNullExpressionValue(strJsonBody, "json.toString()");
        if (json.has(EXTERNAL_USER_ID)) {
            Pattern eidPattern = Pattern.compile("(?<=\"external_user_id\":\").*?(?=\")");
            Matcher eidMatcher = eidPattern.matcher(strJsonBody);
            if (eidMatcher.find() && (matched = eidMatcher.group(0)) != null) {
                String unescapedEID = StringsKt.replace$default(matched, "\\/", "/", false, 4, (Object) null);
                String strJsonBody2 = eidMatcher.replaceAll(Matcher.quoteReplacement(unescapedEID));
                Intrinsics.checkNotNullExpressionValue(strJsonBody2, "eidMatcher.replaceAll(unescapedEID)");
                return strJsonBody2;
            }
            return strJsonBody;
        }
        return strJsonBody;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0049, code lost:
    
        r2 = r2 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean compareJSONArrays(org.json.JSONArray r10, org.json.JSONArray r11) {
        /*
            r9 = this;
            r0 = 1
            if (r10 != 0) goto L6
            if (r11 != 0) goto L6
            return r0
        L6:
            r1 = 0
            if (r10 == 0) goto L53
            if (r11 != 0) goto Lc
            goto L53
        Lc:
            int r2 = r10.length()
            int r3 = r11.length()
            if (r2 == r3) goto L17
            return r1
        L17:
            r2 = 0
            int r3 = r10.length()     // Catch: org.json.JSONException -> L4e
        L1d:
            if (r2 >= r3) goto L4d
            r4 = 0
            int r5 = r11.length()     // Catch: org.json.JSONException -> L4e
        L24:
            if (r4 >= r5) goto L4c
            java.lang.Object r6 = r10.get(r2)     // Catch: org.json.JSONException -> L4e
            java.lang.String r7 = "jsonArray1[i]"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)     // Catch: org.json.JSONException -> L4e
            java.lang.Object r6 = r9.normalizeType(r6)     // Catch: org.json.JSONException -> L4e
            java.lang.Object r7 = r11.get(r4)     // Catch: org.json.JSONException -> L4e
            java.lang.String r8 = "jsonArray2[j]"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r8)     // Catch: org.json.JSONException -> L4e
            java.lang.Object r7 = r9.normalizeType(r7)     // Catch: org.json.JSONException -> L4e
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r7)     // Catch: org.json.JSONException -> L4e
            if (r8 != 0) goto L49
            int r4 = r4 + 1
            goto L24
        L49:
            int r2 = r2 + 1
            goto L1d
        L4c:
            return r1
        L4d:
            return r0
        L4e:
            r0 = move-exception
            r0.printStackTrace()
            return r1
        L53:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.common.JSONUtils.compareJSONArrays(org.json.JSONArray, org.json.JSONArray):boolean");
    }

    public final Object normalizeType(Object object) {
        Intrinsics.checkNotNullParameter(object, "object");
        Class<?> cls = object.getClass();
        if (Intrinsics.areEqual(cls, Integer.TYPE)) {
            int it = ((Integer) object).intValue();
            return Long.valueOf(it);
        }
        if (Intrinsics.areEqual(cls, Float.TYPE)) {
            float it2 = ((Float) object).floatValue();
            return Double.valueOf(it2);
        }
        return object;
    }
}
