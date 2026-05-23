package com.onesignal.inAppMessages.internal;

import com.onesignal.common.DateUtils;
import com.onesignal.core.internal.time.ITime;
import com.onesignal.inAppMessages.BuildConfig;
import com.onesignal.inAppMessages.IInAppMessage;
import com.onesignal.notifications.internal.bundle.impl.NotificationBundleProcessor;
import com.onesignal.session.internal.influence.impl.InfluenceConstants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: InAppMessage.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010#\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 N2\u00020\u0001:\u0001NB\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B5\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\n\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u000eB\u0017\b\u0016\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0011B\u0015\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0012J\u000e\u00107\u001a\u0002082\u0006\u00109\u001a\u00020\bJ\u0006\u0010:\u001a\u000208J\u0013\u0010;\u001a\u00020\u00032\b\u0010<\u001a\u0004\u0018\u00010=H\u0096\u0002J\b\u0010>\u001a\u00020?H\u0016J\u000e\u0010@\u001a\u00020\u00032\u0006\u00109\u001a\u00020\bJ\u0012\u0010A\u001a\u0004\u0018\u00010 2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J6\u0010B\u001a(\u0012\n\u0012\b\u0012\u0004\u0012\u00020/0C0Cj\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020/0Cj\b\u0012\u0004\u0012\u00020/`D`D2\u0006\u0010E\u001a\u00020FH\u0002JT\u0010G\u001aF\u0012\u0004\u0012\u00020\b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0H0Hj*\u0012\u0004\u0012\u00020\b\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0Hj\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b`I`I2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u000e\u0010J\u001a\u0002082\u0006\u00109\u001a\u00020\bJ\u0006\u0010K\u001a\u00020\u0003J\u0006\u0010L\u001a\u00020\u0010J\b\u0010M\u001a\u00020\bH\u0016R\u000e\u0010\u0013\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u00152\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u0015@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010!\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0003@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010#\"\u0004\b%\u0010&R\u0011\u0010'\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b'\u0010#R\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0003@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010#R\u001a\u0010(\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010#\"\u0004\b)\u0010&R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u001e\u0010\f\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\r@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R6\u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020/0.0.2\u0012\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020/0.0.@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b1\u00102RN\u00104\u001a\u001a\u0012\u0004\u0012\u00020\b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b03032\u001e\u0010\u0014\u001a\u001a\u0012\u0004\u0012\u00020\b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0303@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b5\u00106¨\u0006O"}, d2 = {"Lcom/onesignal/inAppMessages/internal/InAppMessage;", "Lcom/onesignal/inAppMessages/IInAppMessage;", "isPreview", "", InfluenceConstants.TIME, "Lcom/onesignal/core/internal/time/ITime;", "(ZLcom/onesignal/core/internal/time/ITime;)V", InAppMessage.IAM_ID, "", "clickIds", "", "displayedInSession", "redisplayStats", "Lcom/onesignal/inAppMessages/internal/InAppMessageRedisplayStats;", "(Ljava/lang/String;Ljava/util/Set;ZLcom/onesignal/inAppMessages/internal/InAppMessageRedisplayStats;Lcom/onesignal/core/internal/time/ITime;)V", "json", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;Lcom/onesignal/core/internal/time/ITime;)V", "(Ljava/lang/String;Lcom/onesignal/core/internal/time/ITime;)V", "actionTaken", "<set-?>", "", "clickedClickIds", "getClickedClickIds", "()Ljava/util/Set;", InAppMessage.DISPLAY_DURATION, "", "getDisplayDuration", "()D", "setDisplayDuration", "(D)V", "endTime", "Ljava/util/Date;", "hasLiquid", "getHasLiquid", "()Z", "isDisplayedInSession", "setDisplayedInSession", "(Z)V", "isFinished", "isTriggerChanged", "setTriggerChanged", "getMessageId", "()Ljava/lang/String;", "getRedisplayStats", "()Lcom/onesignal/inAppMessages/internal/InAppMessageRedisplayStats;", "", "Lcom/onesignal/inAppMessages/internal/Trigger;", InAppMessage.IAM_TRIGGERS, "getTriggers", "()Ljava/util/List;", "", InAppMessage.IAM_VARIANTS, "getVariants", "()Ljava/util/Map;", "addClickId", "", "clickId", "clearClickIds", "equals", NotificationBundleProcessor.PUSH_MINIFIED_BUTTONS_LIST, "", "hashCode", "", "isClickAvailable", "parseEndTimeJson", "parseTriggerJson", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "triggersJson", "Lorg/json/JSONArray;", "parseVariants", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "removeClickId", "takeActionAsUnique", "toJSONObject", "toString", "Companion", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class InAppMessage implements IInAppMessage {
    public static final Companion Companion = new Companion(null);
    private static final String DISPLAY_DURATION = "displayDuration";
    private static final String END_TIME = "end_time";
    private static final String HAS_LIQUID = "has_liquid";
    private static final String IAM_ID = "messageId";
    private static final String IAM_REDISPLAY_STATS = "redisplay";
    private static final String IAM_TRIGGERS = "triggers";
    private static final String IAM_VARIANTS = "variants";
    private static final String ID = "id";
    private boolean actionTaken;
    private Set<String> clickedClickIds;
    private double displayDuration;
    private Date endTime;
    private boolean hasLiquid;
    private boolean isDisplayedInSession;
    private boolean isPreview;
    private boolean isTriggerChanged;
    private final String messageId;
    private InAppMessageRedisplayStats redisplayStats;
    private List<? extends List<Trigger>> triggers;
    private Map<String, ? extends Map<String, String>> variants;

    public InAppMessage(String messageId, ITime time) {
        Intrinsics.checkNotNullParameter(messageId, IAM_ID);
        Intrinsics.checkNotNullParameter(time, InfluenceConstants.TIME);
        this.messageId = messageId;
        this.variants = MapsKt.emptyMap();
        this.triggers = CollectionsKt.emptyList();
        this.clickedClickIds = new LinkedHashSet();
        this.redisplayStats = new InAppMessageRedisplayStats(time);
    }

    @Override // com.onesignal.inAppMessages.IInAppMessage
    public String getMessageId() {
        return this.messageId;
    }

    public final Map<String, Map<String, String>> getVariants() {
        return this.variants;
    }

    public final List<List<Trigger>> getTriggers() {
        return this.triggers;
    }

    public final Set<String> getClickedClickIds() {
        return this.clickedClickIds;
    }

    public final InAppMessageRedisplayStats getRedisplayStats() {
        return this.redisplayStats;
    }

    public final double getDisplayDuration() {
        return this.displayDuration;
    }

    public final void setDisplayDuration(double d) {
        this.displayDuration = d;
    }

    public final boolean isDisplayedInSession() {
        return this.isDisplayedInSession;
    }

    public final void setDisplayedInSession(boolean z) {
        this.isDisplayedInSession = z;
    }

    public final boolean isTriggerChanged() {
        return this.isTriggerChanged;
    }

    public final void setTriggerChanged(boolean z) {
        this.isTriggerChanged = z;
    }

    public final boolean isPreview() {
        return this.isPreview;
    }

    public final boolean getHasLiquid() {
        return this.hasLiquid;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public InAppMessage(boolean isPreview, ITime time) {
        this("", time);
        Intrinsics.checkNotNullParameter(time, InfluenceConstants.TIME);
        this.isPreview = isPreview;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public InAppMessage(String messageId, Set<String> set, boolean displayedInSession, InAppMessageRedisplayStats redisplayStats, ITime time) {
        this(messageId, time);
        Intrinsics.checkNotNullParameter(messageId, IAM_ID);
        Intrinsics.checkNotNullParameter(set, "clickIds");
        Intrinsics.checkNotNullParameter(redisplayStats, "redisplayStats");
        Intrinsics.checkNotNullParameter(time, InfluenceConstants.TIME);
        this.clickedClickIds = CollectionsKt.toMutableSet(set);
        this.isDisplayedInSession = displayedInSession;
        this.redisplayStats = redisplayStats;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public InAppMessage(JSONObject json, ITime time) throws JSONException {
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(time, InfluenceConstants.TIME);
        String string = json.getString("id");
        Intrinsics.checkNotNullExpressionValue(string, "json.getString(ID)");
        this(string, time);
        JSONObject jSONObject = json.getJSONObject(IAM_VARIANTS);
        Intrinsics.checkNotNullExpressionValue(jSONObject, "json.getJSONObject(IAM_VARIANTS)");
        this.variants = parseVariants(jSONObject);
        JSONArray jSONArray = json.getJSONArray(IAM_TRIGGERS);
        Intrinsics.checkNotNullExpressionValue(jSONArray, "json.getJSONArray(IAM_TRIGGERS)");
        this.triggers = parseTriggerJson(jSONArray);
        this.endTime = parseEndTimeJson(json);
        if (json.has(HAS_LIQUID)) {
            this.hasLiquid = json.getBoolean(HAS_LIQUID);
        }
        if (json.has(IAM_REDISPLAY_STATS)) {
            JSONObject jSONObject2 = json.getJSONObject(IAM_REDISPLAY_STATS);
            Intrinsics.checkNotNullExpressionValue(jSONObject2, "json.getJSONObject(IAM_REDISPLAY_STATS)");
            this.redisplayStats = new InAppMessageRedisplayStats(jSONObject2, time);
        }
    }

    private final Date parseEndTimeJson(JSONObject json) {
        try {
            String endTimeString = json.getString(END_TIME);
            Intrinsics.checkNotNullExpressionValue(endTimeString, "{\n                json.g…g(END_TIME)\n            }");
            if (Intrinsics.areEqual(endTimeString, "null")) {
                return null;
            }
            try {
                SimpleDateFormat format = DateUtils.INSTANCE.iso8601Format();
                return format.parse(endTimeString);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        } catch (JSONException e2) {
            return null;
        }
    }

    private final HashMap<String, HashMap<String, String>> parseVariants(JSONObject json) throws JSONException {
        HashMap<String, HashMap<String, String>> map = new HashMap<>();
        Iterator<String> itKeys = json.keys();
        while (itKeys.hasNext()) {
            String variantType = itKeys.next();
            JSONObject variant = json.getJSONObject(variantType);
            HashMap<String, String> map2 = new HashMap<>();
            Iterator<String> itKeys2 = variant.keys();
            while (itKeys2.hasNext()) {
                String languageType = itKeys2.next();
                Intrinsics.checkNotNullExpressionValue(languageType, "languageType");
                String string = variant.getString(languageType);
                Intrinsics.checkNotNullExpressionValue(string, "variant.getString(languageType)");
                map2.put(languageType, string);
            }
            Intrinsics.checkNotNullExpressionValue(variantType, "variantType");
            map.put(variantType, map2);
        }
        return map;
    }

    private final ArrayList<ArrayList<Trigger>> parseTriggerJson(JSONArray triggersJson) throws JSONException {
        ArrayList<ArrayList<Trigger>> arrayList = new ArrayList<>();
        int length = triggersJson.length();
        for (int i = 0; i < length; i++) {
            JSONArray ands = triggersJson.getJSONArray(i);
            ArrayList<Trigger> arrayList2 = new ArrayList<>();
            int length2 = ands.length();
            for (int j = 0; j < length2; j++) {
                JSONObject jSONObject = ands.getJSONObject(j);
                Intrinsics.checkNotNullExpressionValue(jSONObject, "ands.getJSONObject(j)");
                Trigger trigger = new Trigger(jSONObject);
                arrayList2.add(trigger);
            }
            arrayList.add(arrayList2);
        }
        return arrayList;
    }

    public final JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(IAM_ID, getMessageId());
            JSONObject variants = new JSONObject();
            for (String key : this.variants.keySet()) {
                Map<String, String> map = this.variants.get(key);
                Intrinsics.checkNotNull(map);
                Map<String, String> map2 = map;
                JSONObject converted = new JSONObject();
                for (String variantKey : map2.keySet()) {
                    converted.put(variantKey, map2.get(variantKey));
                }
                variants.put(key, converted);
            }
            jSONObject.put(IAM_VARIANTS, variants);
            jSONObject.put(DISPLAY_DURATION, this.displayDuration);
            jSONObject.put(IAM_REDISPLAY_STATS, this.redisplayStats.toJSONObject());
            JSONArray orConditions = new JSONArray();
            for (List<Trigger> list : this.triggers) {
                JSONArray andConditions = new JSONArray();
                for (Trigger trigger : list) {
                    andConditions.put(trigger.toJSONObject());
                }
                orConditions.put(andConditions);
            }
            jSONObject.put(IAM_TRIGGERS, orConditions);
            if (this.endTime != null) {
                SimpleDateFormat format = DateUtils.INSTANCE.iso8601Format();
                String endTimeString = format.format(this.endTime);
                jSONObject.put(END_TIME, endTimeString);
            }
            jSONObject.put(HAS_LIQUID, this.hasLiquid);
        } catch (JSONException exception) {
            exception.printStackTrace();
        }
        return jSONObject;
    }

    public final boolean takeActionAsUnique() {
        if (this.actionTaken) {
            return false;
        }
        this.actionTaken = true;
        return true;
    }

    public final boolean isClickAvailable(String clickId) {
        Intrinsics.checkNotNullParameter(clickId, "clickId");
        return !this.clickedClickIds.contains(clickId);
    }

    public final void clearClickIds() {
        this.clickedClickIds.clear();
    }

    public final void addClickId(String clickId) {
        Intrinsics.checkNotNullParameter(clickId, "clickId");
        this.clickedClickIds.add(clickId);
    }

    public final void removeClickId(String clickId) {
        Intrinsics.checkNotNullParameter(clickId, "clickId");
        this.clickedClickIds.remove(clickId);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OSInAppMessage{messageId='").append(getMessageId()).append("', variants=").append(this.variants).append(", triggers=").append(this.triggers).append(", clickedClickIds=").append(this.clickedClickIds).append(", redisplayStats=").append(this.redisplayStats).append(", displayDuration=").append(this.displayDuration).append(", displayedInSession=").append(this.isDisplayedInSession).append(", triggerChanged=").append(this.isTriggerChanged).append(", actionTaken=").append(this.actionTaken).append(", isPreview=").append(this.isPreview).append(", endTime=").append(this.endTime).append(", hasLiquid=");
        sb.append(this.hasLiquid).append('}');
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !Intrinsics.areEqual(getClass(), o.getClass())) {
            return false;
        }
        InAppMessage that = (InAppMessage) o;
        return Intrinsics.areEqual(getMessageId(), that.getMessageId());
    }

    public int hashCode() {
        return getMessageId().hashCode();
    }

    public final boolean isFinished() {
        if (this.endTime == null) {
            return false;
        }
        Date now = new Date();
        Date date = this.endTime;
        Intrinsics.checkNotNull(date);
        return date.before(now);
    }

    /* JADX INFO: compiled from: InAppMessage.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/onesignal/inAppMessages/internal/InAppMessage$Companion;", "", "()V", "DISPLAY_DURATION", "", "END_TIME", "HAS_LIQUID", "IAM_ID", "IAM_REDISPLAY_STATS", "IAM_TRIGGERS", "IAM_VARIANTS", "ID", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
