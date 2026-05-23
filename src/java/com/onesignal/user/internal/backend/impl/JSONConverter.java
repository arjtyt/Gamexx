package com.onesignal.user.internal.backend.impl;

import com.onesignal.common.JSONObjectExtensionsKt;
import com.onesignal.core.BuildConfig;
import com.onesignal.inAppMessages.internal.display.impl.WebViewManager;
import com.onesignal.session.internal.outcomes.impl.OutcomeConstants;
import com.onesignal.user.internal.backend.CreateUserResponse;
import com.onesignal.user.internal.backend.PropertiesDeltasObject;
import com.onesignal.user.internal.backend.PropertiesObject;
import com.onesignal.user.internal.backend.PurchaseObject;
import com.onesignal.user.internal.backend.SubscriptionObject;
import com.onesignal.user.internal.backend.SubscriptionObjectType;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: JSONConverter.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rJ\u0014\u0010\u0007\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\u0010¨\u0006\u0011"}, d2 = {"Lcom/onesignal/user/internal/backend/impl/JSONConverter;", "", "()V", "convertToCreateUserResponse", "Lcom/onesignal/user/internal/backend/CreateUserResponse;", "jsonObject", "Lorg/json/JSONObject;", "convertToJSON", "propertiesDeltas", "Lcom/onesignal/user/internal/backend/PropertiesDeltasObject;", "properties", "Lcom/onesignal/user/internal/backend/PropertiesObject;", "subscription", "Lcom/onesignal/user/internal/backend/SubscriptionObject;", "Lorg/json/JSONArray;", "subscriptions", "", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class JSONConverter {
    public static final JSONConverter INSTANCE = new JSONConverter();

    private JSONConverter() {
    }

    public final CreateUserResponse convertToCreateUserResponse(JSONObject jsonObject) throws JSONException {
        LinkedHashMap destination$iv$iv;
        Map map;
        JSONObject jSONObjectSafeJSONObject;
        Map<String, Object> map2;
        Map<String, Object> map3;
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        JSONObject jSONObjectSafeJSONObject2 = JSONObjectExtensionsKt.safeJSONObject(jsonObject, "identity");
        if (jSONObjectSafeJSONObject2 == null || (map3 = JSONObjectExtensionsKt.toMap(jSONObjectSafeJSONObject2)) == null) {
            destination$iv$iv = MapsKt.emptyMap();
        } else {
            destination$iv$iv = new LinkedHashMap(MapsKt.mapCapacity(map3.size()));
            Iterable $this$associateByTo$iv$iv$iv = map3.entrySet();
            for (Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
                Map.Entry it$iv$iv = (Map.Entry) element$iv$iv$iv;
                Map.Entry it = (Map.Entry) element$iv$iv$iv;
                destination$iv$iv.put(it$iv$iv.getKey(), String.valueOf(it.getValue()));
            }
        }
        JSONObject propertiesJSON = JSONObjectExtensionsKt.safeJSONObject(jsonObject, "properties");
        if (propertiesJSON == null || (jSONObjectSafeJSONObject = JSONObjectExtensionsKt.safeJSONObject(propertiesJSON, "tags")) == null || (map2 = JSONObjectExtensionsKt.toMap(jSONObjectSafeJSONObject)) == null) {
            map = null;
        } else {
            Map destination$iv$iv2 = new LinkedHashMap(MapsKt.mapCapacity(map2.size()));
            Iterable $this$associateByTo$iv$iv$iv2 = map2.entrySet();
            for (Object element$iv$iv$iv2 : $this$associateByTo$iv$iv$iv2) {
                Map.Entry it$iv$iv2 = (Map.Entry) element$iv$iv$iv2;
                Map.Entry it2 = (Map.Entry) element$iv$iv$iv2;
                destination$iv$iv2.put(it$iv$iv2.getKey(), String.valueOf(it2.getValue()));
            }
            map = destination$iv$iv2;
        }
        PropertiesObject respProperties = new PropertiesObject(map, propertiesJSON != null ? JSONObjectExtensionsKt.safeString(propertiesJSON, "language") : null, propertiesJSON != null ? JSONObjectExtensionsKt.safeString(propertiesJSON, "timezone_id") : null, propertiesJSON != null ? JSONObjectExtensionsKt.safeString(propertiesJSON, "country") : null, propertiesJSON != null ? JSONObjectExtensionsKt.safeDouble(propertiesJSON, "lat") : null, propertiesJSON != null ? JSONObjectExtensionsKt.safeDouble(propertiesJSON, "long") : null);
        List respSubscriptions = JSONObjectExtensionsKt.expandJSONArray(jsonObject, "subscriptions", new Function1<JSONObject, SubscriptionObject>() { // from class: com.onesignal.user.internal.backend.impl.JSONConverter$convertToCreateUserResponse$respSubscriptions$1
            public final SubscriptionObject invoke(JSONObject it3) throws JSONException {
                Intrinsics.checkNotNullParameter(it3, "it");
                SubscriptionObjectType.Companion companion = SubscriptionObjectType.Companion;
                String string = it3.getString(WebViewManager.EVENT_TYPE_KEY);
                Intrinsics.checkNotNullExpressionValue(string, "it.getString(\"type\")");
                SubscriptionObjectType subscriptionType = companion.fromString(string);
                if (subscriptionType != null) {
                    return new SubscriptionObject(it3.getString(OutcomeConstants.OUTCOME_ID), subscriptionType, JSONObjectExtensionsKt.safeString(it3, "token"), JSONObjectExtensionsKt.safeBool(it3, "enabled"), JSONObjectExtensionsKt.safeInt(it3, "notification_types"), JSONObjectExtensionsKt.safeString(it3, "sdk"), JSONObjectExtensionsKt.safeString(it3, "device_model"), JSONObjectExtensionsKt.safeString(it3, "device_os"), JSONObjectExtensionsKt.safeBool(it3, "rooted"), JSONObjectExtensionsKt.safeInt(it3, "net_type"), JSONObjectExtensionsKt.safeString(it3, "carrier"), JSONObjectExtensionsKt.safeString(it3, "app_version"));
                }
                return null;
            }
        });
        return new CreateUserResponse(destination$iv$iv, respProperties, respSubscriptions);
    }

    public final JSONObject convertToJSON(PropertiesObject properties) {
        Intrinsics.checkNotNullParameter(properties, "properties");
        return JSONObjectExtensionsKt.putSafe(JSONObjectExtensionsKt.putSafe(JSONObjectExtensionsKt.putSafe(JSONObjectExtensionsKt.putSafe(JSONObjectExtensionsKt.putSafe(JSONObjectExtensionsKt.putMap(new JSONObject(), "tags", properties.getTags()), "language", properties.getLanguage()), "timezone_id", properties.getTimezoneId()), "lat", properties.getLatitude()), "long", properties.getLongitude()), "country", properties.getCountry());
    }

    public final JSONObject convertToJSON(PropertiesDeltasObject propertiesDeltas) throws JSONException {
        Intrinsics.checkNotNullParameter(propertiesDeltas, "propertiesDeltas");
        JSONObject jSONObjectPutSafe = JSONObjectExtensionsKt.putSafe(JSONObjectExtensionsKt.putSafe(new JSONObject(), "session_time", propertiesDeltas.getSessionTime()), "session_count", propertiesDeltas.getSessionCount());
        BigDecimal amountSpent = propertiesDeltas.getAmountSpent();
        return JSONObjectExtensionsKt.putJSONArray(JSONObjectExtensionsKt.putSafe(jSONObjectPutSafe, "amount_spent", amountSpent != null ? amountSpent.toString() : null), "purchases", propertiesDeltas.getPurchases(), new Function1<PurchaseObject, JSONObject>() { // from class: com.onesignal.user.internal.backend.impl.JSONConverter.convertToJSON.1
            public final JSONObject invoke(PurchaseObject it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return new JSONObject().put("sku", it.getSku()).put("iso", it.getIso()).put("amount", it.getAmount().toString());
            }
        });
    }

    public final JSONArray convertToJSON(List<SubscriptionObject> list) {
        Intrinsics.checkNotNullParameter(list, "subscriptions");
        JSONArray subscriptionsArray = new JSONArray();
        for (SubscriptionObject subscription : list) {
            subscriptionsArray.put(convertToJSON(subscription));
        }
        return subscriptionsArray;
    }

    public final JSONObject convertToJSON(SubscriptionObject subscription) throws JSONException {
        Intrinsics.checkNotNullParameter(subscription, "subscription");
        JSONObject jSONObjectPutSafe = JSONObjectExtensionsKt.putSafe(new JSONObject(), OutcomeConstants.OUTCOME_ID, subscription.getId());
        SubscriptionObjectType type = subscription.getType();
        return JSONObjectExtensionsKt.putSafe(JSONObjectExtensionsKt.putSafe(JSONObjectExtensionsKt.putSafe(JSONObjectExtensionsKt.putSafe(JSONObjectExtensionsKt.putSafe(JSONObjectExtensionsKt.putSafe(JSONObjectExtensionsKt.putSafe(JSONObjectExtensionsKt.putSafe(JSONObjectExtensionsKt.putSafe(JSONObjectExtensionsKt.putSafe(JSONObjectExtensionsKt.putSafe(jSONObjectPutSafe, WebViewManager.EVENT_TYPE_KEY, type != null ? type.getValue() : null), "token", subscription.getToken()), "enabled", subscription.getEnabled()), "notification_types", subscription.getNotificationTypes()), "sdk", subscription.getSdk()), "device_model", subscription.getDeviceModel()), "device_os", subscription.getDeviceOS()), "rooted", subscription.getRooted()), "net_type", subscription.getNetType()), "carrier", subscription.getCarrier()), "app_version", subscription.getAppVersion());
    }
}
