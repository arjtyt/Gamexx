package com.onesignal.common.modeling;

import com.onesignal.common.events.EventProducer;
import com.onesignal.common.events.IEventNotifier;
import com.onesignal.core.BuildConfig;
import com.onesignal.session.internal.outcomes.impl.OutcomeConstants;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: Model.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0002\b)\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0000\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u001e\u0010\u0018\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u001cH\u0014J\u001a\u0010\u001d\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u001fH\u0014J\"\u0010 \u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\u00052\u0010\b\u0002\u0010\"\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010#H\u0004J\"\u0010$\u001a\u00020%2\u0006\u0010!\u001a\u00020\u00052\u0010\b\u0002\u0010\"\u001a\n\u0012\u0004\u0012\u00020%\u0018\u00010#H\u0004J\"\u0010&\u001a\u00020\u000f2\u0006\u0010!\u001a\u00020\u00052\u0010\b\u0002\u0010\"\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010#H\u0004J\"\u0010'\u001a\u00020(2\u0006\u0010!\u001a\u00020\u00052\u0010\b\u0002\u0010\"\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010#H\u0004J(\u0010)\u001a\u0002H*\"\u0010\b\u0000\u0010*\u0018\u0001*\b\u0012\u0004\u0012\u0002H*0+2\u0006\u0010!\u001a\u00020\u0005H\u0084\b¢\u0006\u0002\u0010,J\"\u0010-\u001a\u00020.2\u0006\u0010!\u001a\u00020\u00052\u0010\b\u0002\u0010\"\u001a\n\u0012\u0004\u0012\u00020.\u0018\u00010#H\u0004J\"\u0010/\u001a\u0002002\u0006\u0010!\u001a\u00020\u00052\u0010\b\u0002\u0010\"\u001a\n\u0012\u0004\u0012\u000200\u0018\u00010#H\u0004J4\u00101\u001a\b\u0012\u0004\u0012\u0002H*0\u0019\"\u0004\b\u0000\u0010*2\u0006\u0010!\u001a\u00020\u00052\u0016\b\u0002\u0010\"\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H*0\u0019\u0018\u00010#H\u0004J\"\u00102\u001a\u0002032\u0006\u0010!\u001a\u00020\u00052\u0010\b\u0002\u0010\"\u001a\n\u0012\u0004\u0012\u000203\u0018\u00010#H\u0004J4\u00104\u001a\b\u0012\u0004\u0012\u0002H*05\"\u0004\b\u0000\u0010*2\u0006\u0010!\u001a\u00020\u00052\u0016\b\u0002\u0010\"\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H*05\u0018\u00010#H\u0004J&\u00106\u001a\u0004\u0018\u00010\u000b2\u0006\u0010!\u001a\u00020\u00052\u0012\b\u0002\u0010\"\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0018\u00010#H\u0004J&\u00107\u001a\u0004\u0018\u00010%2\u0006\u0010!\u001a\u00020\u00052\u0012\b\u0002\u0010\"\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010%\u0018\u00010#H\u0004J+\u00108\u001a\u0004\u0018\u00010\u000f2\u0006\u0010!\u001a\u00020\u00052\u0012\b\u0002\u0010\"\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0018\u00010#H\u0004¢\u0006\u0002\u00109J+\u0010:\u001a\u0004\u0018\u00010(2\u0006\u0010!\u001a\u00020\u00052\u0012\b\u0002\u0010\"\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010(\u0018\u00010#H\u0004¢\u0006\u0002\u0010;J*\u0010<\u001a\u0004\u0018\u0001H*\"\u0010\b\u0000\u0010*\u0018\u0001*\b\u0012\u0004\u0012\u0002H*0+2\u0006\u0010!\u001a\u00020\u0005H\u0084\b¢\u0006\u0002\u0010,J+\u0010=\u001a\u0004\u0018\u00010.2\u0006\u0010!\u001a\u00020\u00052\u0012\b\u0002\u0010\"\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010.\u0018\u00010#H\u0004¢\u0006\u0002\u0010>J+\u0010?\u001a\u0004\u0018\u0001002\u0006\u0010!\u001a\u00020\u00052\u0012\b\u0002\u0010\"\u001a\f\u0012\u0006\u0012\u0004\u0018\u000100\u0018\u00010#H\u0004¢\u0006\u0002\u0010@J8\u0010A\u001a\n\u0012\u0004\u0012\u0002H*\u0018\u00010\u0019\"\u0004\b\u0000\u0010*2\u0006\u0010!\u001a\u00020\u00052\u0018\b\u0002\u0010\"\u001a\u0012\u0012\f\u0012\n\u0012\u0004\u0012\u0002H*\u0018\u00010\u0019\u0018\u00010#H\u0004J+\u0010B\u001a\u0004\u0018\u0001032\u0006\u0010!\u001a\u00020\u00052\u0012\b\u0002\u0010\"\u001a\f\u0012\u0006\u0012\u0004\u0018\u000103\u0018\u00010#H\u0004¢\u0006\u0002\u0010CJ8\u0010D\u001a\n\u0012\u0004\u0012\u0002H*\u0018\u000105\"\u0004\b\u0000\u0010*2\u0006\u0010!\u001a\u00020\u00052\u0018\b\u0002\u0010\"\u001a\u0012\u0012\f\u0012\n\u0012\u0004\u0012\u0002H*\u0018\u000105\u0018\u00010#H\u0004J&\u0010E\u001a\u0004\u0018\u00010\u00052\u0006\u0010!\u001a\u00020\u00052\u0012\b\u0002\u0010\"\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010#H\u0004J\"\u0010F\u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u00052\u0010\b\u0002\u0010\"\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010#H\u0004J\u000e\u0010G\u001a\u00020\u000f2\u0006\u0010!\u001a\u00020\u0005J\u000e\u0010H\u001a\u00020I2\u0006\u0010\u001e\u001a\u00020\u001fJ\u0018\u0010J\u001a\u00020I2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0006\u0010K\u001a\u00020\u0000J4\u0010L\u001a\u00020I2\u0006\u0010M\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u00052\u0006\u0010N\u001a\u00020\u00052\b\u0010O\u001a\u0004\u0018\u00010\u000b2\b\u0010P\u001a\u0004\u0018\u00010\u000bH\u0002J*\u0010Q\u001a\u00020I2\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u000b2\b\b\u0002\u0010N\u001a\u00020\u00052\b\b\u0002\u0010R\u001a\u00020\u000fJ*\u0010S\u001a\u00020I2\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020%2\b\b\u0002\u0010N\u001a\u00020\u00052\b\b\u0002\u0010R\u001a\u00020\u000fJ*\u0010T\u001a\u00020I2\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u000f2\b\b\u0002\u0010N\u001a\u00020\u00052\b\b\u0002\u0010R\u001a\u00020\u000fJ*\u0010U\u001a\u00020I2\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020(2\b\b\u0002\u0010N\u001a\u00020\u00052\b\b\u0002\u0010R\u001a\u00020\u000fJD\u0010V\u001a\u00020I\"\u0010\b\u0000\u0010*\u0018\u0001*\b\u0012\u0004\u0012\u0002H*0+2\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u0002H*2\b\b\u0002\u0010N\u001a\u00020\u00052\b\b\u0002\u0010R\u001a\u00020\u000fH\u0086\b¢\u0006\u0002\u0010WJ*\u0010X\u001a\u00020I2\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020.2\b\b\u0002\u0010N\u001a\u00020\u00052\b\b\u0002\u0010R\u001a\u00020\u000fJ*\u0010Y\u001a\u00020I2\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u0002002\b\b\u0002\u0010N\u001a\u00020\u00052\b\b\u0002\u0010R\u001a\u00020\u000fJ6\u0010Z\u001a\u00020I\"\u0004\b\u0000\u0010*2\u0006\u0010!\u001a\u00020\u00052\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H*0\u00192\b\b\u0002\u0010N\u001a\u00020\u00052\b\b\u0002\u0010R\u001a\u00020\u000fJ*\u0010[\u001a\u00020I2\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u0002032\b\b\u0002\u0010N\u001a\u00020\u00052\b\b\u0002\u0010R\u001a\u00020\u000fJ6\u0010\\\u001a\u00020I\"\u0004\b\u0000\u0010*2\u0006\u0010!\u001a\u00020\u00052\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H*052\b\b\u0002\u0010N\u001a\u00020\u00052\b\b\u0002\u0010R\u001a\u00020\u000fJ,\u0010]\u001a\u00020I2\u0006\u0010!\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010N\u001a\u00020\u00052\b\b\u0002\u0010R\u001a\u00020\u000fJ,\u0010^\u001a\u00020I2\u0006\u0010!\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010%2\b\b\u0002\u0010N\u001a\u00020\u00052\b\b\u0002\u0010R\u001a\u00020\u000fJ1\u0010_\u001a\u00020I2\u0006\u0010!\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010N\u001a\u00020\u00052\b\b\u0002\u0010R\u001a\u00020\u000f¢\u0006\u0002\u0010`J1\u0010a\u001a\u00020I2\u0006\u0010!\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010(2\b\b\u0002\u0010N\u001a\u00020\u00052\b\b\u0002\u0010R\u001a\u00020\u000f¢\u0006\u0002\u0010bJF\u0010c\u001a\u00020I\"\u0010\b\u0000\u0010*\u0018\u0001*\b\u0012\u0004\u0012\u0002H*0+2\u0006\u0010!\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u0001H*2\b\b\u0002\u0010N\u001a\u00020\u00052\b\b\u0002\u0010R\u001a\u00020\u000fH\u0086\b¢\u0006\u0002\u0010WJ1\u0010d\u001a\u00020I2\u0006\u0010!\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010.2\b\b\u0002\u0010N\u001a\u00020\u00052\b\b\u0002\u0010R\u001a\u00020\u000f¢\u0006\u0002\u0010eJ1\u0010f\u001a\u00020I2\u0006\u0010!\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u0001002\b\b\u0002\u0010N\u001a\u00020\u00052\b\b\u0002\u0010R\u001a\u00020\u000f¢\u0006\u0002\u0010gJ8\u0010h\u001a\u00020I\"\u0004\b\u0000\u0010*2\u0006\u0010!\u001a\u00020\u00052\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u0002H*\u0018\u00010\u00192\b\b\u0002\u0010N\u001a\u00020\u00052\b\b\u0002\u0010R\u001a\u00020\u000fJ1\u0010i\u001a\u00020I2\u0006\u0010!\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u0001032\b\b\u0002\u0010N\u001a\u00020\u00052\b\b\u0002\u0010R\u001a\u00020\u000f¢\u0006\u0002\u0010jJ8\u0010k\u001a\u00020I\"\u0004\b\u0000\u0010*2\u0006\u0010!\u001a\u00020\u00052\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u0002H*\u0018\u0001052\b\b\u0002\u0010N\u001a\u00020\u00052\b\b\u0002\u0010R\u001a\u00020\u000fJ,\u0010l\u001a\u00020I2\u0006\u0010!\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010N\u001a\u00020\u00052\b\b\u0002\u0010R\u001a\u00020\u000fJ*\u0010m\u001a\u00020I2\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00052\b\b\u0002\u0010N\u001a\u00020\u00052\b\b\u0002\u0010R\u001a\u00020\u000fJ\u0010\u0010n\u001a\u00020I2\u0006\u0010o\u001a\u00020\u0002H\u0016J\u0006\u0010p\u001a\u00020\u001fJ\u0010\u0010q\u001a\u00020I2\u0006\u0010o\u001a\u00020\u0002H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R$\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00058F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006r"}, d2 = {"Lcom/onesignal/common/modeling/Model;", "Lcom/onesignal/common/events/IEventNotifier;", "Lcom/onesignal/common/modeling/IModelChangedHandler;", "_parentModel", "_parentProperty", "", "(Lcom/onesignal/common/modeling/Model;Ljava/lang/String;)V", "changeNotifier", "Lcom/onesignal/common/events/EventProducer;", "data", "", "", "getData", "()Ljava/util/Map;", "hasSubscribers", "", "getHasSubscribers", "()Z", "value", OutcomeConstants.OUTCOME_ID, "getId", "()Ljava/lang/String;", "setId", "(Ljava/lang/String;)V", "createListForProperty", "", "property", "jsonArray", "Lorg/json/JSONArray;", "createModelForProperty", "jsonObject", "Lorg/json/JSONObject;", "getAnyProperty", "name", "create", "Lkotlin/Function0;", "getBigDecimalProperty", "Ljava/math/BigDecimal;", "getBooleanProperty", "getDoubleProperty", "", "getEnumProperty", "T", "", "(Ljava/lang/String;)Ljava/lang/Enum;", "getFloatProperty", "", "getIntProperty", "", "getListProperty", "getLongProperty", "", "getMapModelProperty", "Lcom/onesignal/common/modeling/MapModel;", "getOptAnyProperty", "getOptBigDecimalProperty", "getOptBooleanProperty", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;)Ljava/lang/Boolean;", "getOptDoubleProperty", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;)Ljava/lang/Double;", "getOptEnumProperty", "getOptFloatProperty", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;)Ljava/lang/Float;", "getOptIntProperty", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;)Ljava/lang/Integer;", "getOptListProperty", "getOptLongProperty", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;)Ljava/lang/Long;", "getOptMapModelProperty", "getOptStringProperty", "getStringProperty", "hasProperty", "initializeFromJson", "", "initializeFromModel", "model", "notifyChanged", "path", "tag", "oldValue", "newValue", "setAnyProperty", "forceChange", "setBigDecimalProperty", "setBooleanProperty", "setDoubleProperty", "setEnumProperty", "(Ljava/lang/String;Ljava/lang/Enum;Ljava/lang/String;Z)V", "setFloatProperty", "setIntProperty", "setListProperty", "setLongProperty", "setMapModelProperty", "setOptAnyProperty", "setOptBigDecimalProperty", "setOptBooleanProperty", "(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Z)V", "setOptDoubleProperty", "(Ljava/lang/String;Ljava/lang/Double;Ljava/lang/String;Z)V", "setOptEnumProperty", "setOptFloatProperty", "(Ljava/lang/String;Ljava/lang/Float;Ljava/lang/String;Z)V", "setOptIntProperty", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Z)V", "setOptListProperty", "setOptLongProperty", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Z)V", "setOptMapModelProperty", "setOptStringProperty", "setStringProperty", "subscribe", "handler", "toJSON", "unsubscribe", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public class Model implements IEventNotifier<IModelChangedHandler> {
    private Model _parentModel;
    private final String _parentProperty;
    private final EventProducer<IModelChangedHandler> changeNotifier;
    private final Map<String, Object> data;

    /* JADX WARN: Multi-variable type inference failed */
    public Model() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public Model(Model _parentModel, String _parentProperty) throws Exception {
        this._parentModel = _parentModel;
        this._parentProperty = _parentProperty;
        Map<String, Object> mapSynchronizedMap = Collections.synchronizedMap(new LinkedHashMap());
        Intrinsics.checkNotNullExpressionValue(mapSynchronizedMap, "synchronizedMap(mutableMapOf())");
        this.data = mapSynchronizedMap;
        this.changeNotifier = new EventProducer<>();
        if (this._parentModel != null && this._parentProperty == null) {
            throw new Exception("If parent model is set, parent property must also be set.");
        }
        if (this._parentModel != null || this._parentProperty == null) {
        } else {
            throw new Exception("If parent property is set, parent model must also be set.");
        }
    }

    public /* synthetic */ Model(Model model, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : model, (i & 2) != 0 ? null : str);
    }

    public final String getId() {
        return getStringProperty$default(this, OutcomeConstants.OUTCOME_ID, null, 2, null);
    }

    public final void setId(String value) throws Throwable {
        Intrinsics.checkNotNullParameter(value, "value");
        setStringProperty$default(this, OutcomeConstants.OUTCOME_ID, value, null, false, 12, null);
    }

    protected final Map<String, Object> getData() {
        return this.data;
    }

    /* JADX WARN: Removed duplicated region for block: B:116:0x00b1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00b2 A[Catch: all -> 0x01b8, LOOP:1: B:19:0x006e->B:33:0x00b2, LOOP_END, TryCatch #0 {, blocks: (B:5:0x000d, B:6:0x001b, B:8:0x0021, B:10:0x002f, B:12:0x003d, B:13:0x0043, B:15:0x0047, B:17:0x0055, B:18:0x005b, B:20:0x0070, B:22:0x0083, B:24:0x0089, B:35:0x00b7, B:37:0x00bc, B:39:0x00c2, B:44:0x00d4, B:45:0x00e8, B:50:0x00fa, B:51:0x010e, B:56:0x0120, B:57:0x0135, B:62:0x0147, B:63:0x015b, B:68:0x016d, B:69:0x0181, B:74:0x0193, B:75:0x01a3, B:72:0x018a, B:66:0x0165, B:60:0x013f, B:54:0x0118, B:48:0x00f2, B:42:0x00cc, B:33:0x00b2, B:76:0x01b3), top: B:82:0x000d }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void initializeFromJson(org.json.JSONObject r18) {
        /*
            Method dump skipped, instruction units count: 443
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.common.modeling.Model.initializeFromJson(org.json.JSONObject):void");
    }

    public final void initializeFromModel(String id, Model model) {
        Intrinsics.checkNotNullParameter(model, "model");
        Map<? extends String, ? extends Object> mapSynchronizedMap = Collections.synchronizedMap(new LinkedHashMap());
        for (Map.Entry<String, Object> entry : model.data.entrySet()) {
            if (entry.getValue() instanceof Model) {
                Object value = entry.getValue();
                Intrinsics.checkNotNull(value, "null cannot be cast to non-null type com.onesignal.common.modeling.Model");
                Model childModel = (Model) value;
                childModel._parentModel = this;
                Intrinsics.checkNotNullExpressionValue(mapSynchronizedMap, "newData");
                mapSynchronizedMap.put(entry.getKey(), childModel);
            } else {
                Intrinsics.checkNotNullExpressionValue(mapSynchronizedMap, "newData");
                mapSynchronizedMap.put(entry.getKey(), entry.getValue());
            }
        }
        if (id != null) {
            Intrinsics.checkNotNullExpressionValue(mapSynchronizedMap, "newData");
            mapSynchronizedMap.put(OutcomeConstants.OUTCOME_ID, id);
        }
        synchronized (this.data) {
            this.data.clear();
            Map<String, Object> map = this.data;
            Intrinsics.checkNotNullExpressionValue(mapSynchronizedMap, "newData");
            map.putAll(mapSynchronizedMap);
            Unit unit = Unit.INSTANCE;
        }
    }

    protected Model createModelForProperty(String property, JSONObject jsonObject) {
        Intrinsics.checkNotNullParameter(property, "property");
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        return null;
    }

    protected List<?> createListForProperty(String property, JSONArray jsonArray) {
        Intrinsics.checkNotNullParameter(property, "property");
        Intrinsics.checkNotNullParameter(jsonArray, "jsonArray");
        return null;
    }

    public static /* synthetic */ void setEnumProperty$default(Model $this, String name, Enum value, String tag, boolean forceChange, int i, Object obj) throws Throwable {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setEnumProperty");
        }
        if ((i & 4) != 0) {
            tag = ModelChangeTags.NORMAL;
        }
        if ((i & 8) != 0) {
            forceChange = false;
        }
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(tag, "tag");
        $this.setOptAnyProperty(name, value.toString(), tag, forceChange);
    }

    public final /* synthetic */ <T extends Enum<T>> void setEnumProperty(String name, T t, String tag, boolean forceChange) throws Throwable {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(t, "value");
        Intrinsics.checkNotNullParameter(tag, "tag");
        setOptAnyProperty(name, t.toString(), tag, forceChange);
    }

    public static /* synthetic */ void setMapModelProperty$default(Model model, String str, MapModel mapModel, String str2, boolean z, int i, Object obj) throws Throwable {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setMapModelProperty");
        }
        if ((i & 4) != 0) {
            str2 = ModelChangeTags.NORMAL;
        }
        if ((i & 8) != 0) {
            z = false;
        }
        model.setMapModelProperty(str, mapModel, str2, z);
    }

    public final <T> void setMapModelProperty(String name, MapModel<T> mapModel, String tag, boolean forceChange) throws Throwable {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(mapModel, "value");
        Intrinsics.checkNotNullParameter(tag, "tag");
        setOptMapModelProperty(name, mapModel, tag, forceChange);
    }

    public static /* synthetic */ void setListProperty$default(Model model, String str, List list, String str2, boolean z, int i, Object obj) throws Throwable {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setListProperty");
        }
        if ((i & 4) != 0) {
            str2 = ModelChangeTags.NORMAL;
        }
        if ((i & 8) != 0) {
            z = false;
        }
        model.setListProperty(str, list, str2, z);
    }

    public final <T> void setListProperty(String name, List<? extends T> list, String tag, boolean forceChange) throws Throwable {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(list, "value");
        Intrinsics.checkNotNullParameter(tag, "tag");
        setOptListProperty(name, list, tag, forceChange);
    }

    public static /* synthetic */ void setStringProperty$default(Model model, String str, String str2, String str3, boolean z, int i, Object obj) throws Throwable {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setStringProperty");
        }
        if ((i & 4) != 0) {
            str3 = ModelChangeTags.NORMAL;
        }
        if ((i & 8) != 0) {
            z = false;
        }
        model.setStringProperty(str, str2, str3, z);
    }

    public final void setStringProperty(String name, String value, String tag, boolean forceChange) throws Throwable {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(tag, "tag");
        setOptStringProperty(name, value, tag, forceChange);
    }

    public static /* synthetic */ void setBooleanProperty$default(Model model, String str, boolean z, String str2, boolean z2, int i, Object obj) throws Throwable {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setBooleanProperty");
        }
        if ((i & 4) != 0) {
            str2 = ModelChangeTags.NORMAL;
        }
        if ((i & 8) != 0) {
            z2 = false;
        }
        model.setBooleanProperty(str, z, str2, z2);
    }

    public final void setBooleanProperty(String name, boolean value, String tag, boolean forceChange) throws Throwable {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(tag, "tag");
        setOptBooleanProperty(name, Boolean.valueOf(value), tag, forceChange);
    }

    public static /* synthetic */ void setLongProperty$default(Model model, String str, long j, String str2, boolean z, int i, Object obj) throws Throwable {
        String str3;
        boolean z2;
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setLongProperty");
        }
        if ((i & 4) == 0) {
            str3 = str2;
        } else {
            str3 = ModelChangeTags.NORMAL;
        }
        if ((i & 8) == 0) {
            z2 = z;
        } else {
            z2 = false;
        }
        model.setLongProperty(str, j, str3, z2);
    }

    public final void setLongProperty(String name, long value, String tag, boolean forceChange) throws Throwable {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(tag, "tag");
        setOptLongProperty(name, Long.valueOf(value), tag, forceChange);
    }

    public static /* synthetic */ void setDoubleProperty$default(Model model, String str, double d, String str2, boolean z, int i, Object obj) throws Throwable {
        String str3;
        boolean z2;
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setDoubleProperty");
        }
        if ((i & 4) == 0) {
            str3 = str2;
        } else {
            str3 = ModelChangeTags.NORMAL;
        }
        if ((i & 8) == 0) {
            z2 = z;
        } else {
            z2 = false;
        }
        model.setDoubleProperty(str, d, str3, z2);
    }

    public final void setDoubleProperty(String name, double value, String tag, boolean forceChange) throws Throwable {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(tag, "tag");
        setOptDoubleProperty(name, Double.valueOf(value), tag, forceChange);
    }

    public static /* synthetic */ void setFloatProperty$default(Model model, String str, float f, String str2, boolean z, int i, Object obj) throws Throwable {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setFloatProperty");
        }
        if ((i & 4) != 0) {
            str2 = ModelChangeTags.NORMAL;
        }
        if ((i & 8) != 0) {
            z = false;
        }
        model.setFloatProperty(str, f, str2, z);
    }

    public final void setFloatProperty(String name, float value, String tag, boolean forceChange) throws Throwable {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(tag, "tag");
        setOptFloatProperty(name, Float.valueOf(value), tag, forceChange);
    }

    public static /* synthetic */ void setIntProperty$default(Model model, String str, int i, String str2, boolean z, int i2, Object obj) throws Throwable {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setIntProperty");
        }
        if ((i2 & 4) != 0) {
            str2 = ModelChangeTags.NORMAL;
        }
        if ((i2 & 8) != 0) {
            z = false;
        }
        model.setIntProperty(str, i, str2, z);
    }

    public final void setIntProperty(String name, int value, String tag, boolean forceChange) throws Throwable {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(tag, "tag");
        setOptIntProperty(name, Integer.valueOf(value), tag, forceChange);
    }

    public static /* synthetic */ void setBigDecimalProperty$default(Model model, String str, BigDecimal bigDecimal, String str2, boolean z, int i, Object obj) throws Throwable {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setBigDecimalProperty");
        }
        if ((i & 4) != 0) {
            str2 = ModelChangeTags.NORMAL;
        }
        if ((i & 8) != 0) {
            z = false;
        }
        model.setBigDecimalProperty(str, bigDecimal, str2, z);
    }

    public final void setBigDecimalProperty(String name, BigDecimal value, String tag, boolean forceChange) throws Throwable {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(tag, "tag");
        setOptBigDecimalProperty(name, value, tag, forceChange);
    }

    public static /* synthetic */ void setAnyProperty$default(Model model, String str, Object obj, String str2, boolean z, int i, Object obj2) throws Throwable {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setAnyProperty");
        }
        if ((i & 4) != 0) {
            str2 = ModelChangeTags.NORMAL;
        }
        if ((i & 8) != 0) {
            z = false;
        }
        model.setAnyProperty(str, obj, str2, z);
    }

    public final void setAnyProperty(String name, Object value, String tag, boolean forceChange) throws Throwable {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(tag, "tag");
        setOptAnyProperty(name, value, tag, forceChange);
    }

    public static /* synthetic */ void setOptEnumProperty$default(Model $this, String name, Enum value, String tag, boolean forceChange, int i, Object obj) throws Throwable {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setOptEnumProperty");
        }
        if ((i & 4) != 0) {
            tag = ModelChangeTags.NORMAL;
        }
        if ((i & 8) != 0) {
            forceChange = false;
        }
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(tag, "tag");
        $this.setOptAnyProperty(name, value != null ? value.toString() : null, tag, forceChange);
    }

    public final /* synthetic */ <T extends Enum<T>> void setOptEnumProperty(String name, T t, String tag, boolean forceChange) throws Throwable {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(tag, "tag");
        setOptAnyProperty(name, t != null ? t.toString() : null, tag, forceChange);
    }

    public static /* synthetic */ void setOptMapModelProperty$default(Model model, String str, MapModel mapModel, String str2, boolean z, int i, Object obj) throws Throwable {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setOptMapModelProperty");
        }
        if ((i & 4) != 0) {
            str2 = ModelChangeTags.NORMAL;
        }
        if ((i & 8) != 0) {
            z = false;
        }
        model.setOptMapModelProperty(str, mapModel, str2, z);
    }

    public final <T> void setOptMapModelProperty(String name, MapModel<T> mapModel, String tag, boolean forceChange) throws Throwable {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(tag, "tag");
        setOptAnyProperty(name, mapModel, tag, forceChange);
    }

    public static /* synthetic */ void setOptListProperty$default(Model model, String str, List list, String str2, boolean z, int i, Object obj) throws Throwable {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setOptListProperty");
        }
        if ((i & 4) != 0) {
            str2 = ModelChangeTags.NORMAL;
        }
        if ((i & 8) != 0) {
            z = false;
        }
        model.setOptListProperty(str, list, str2, z);
    }

    public final <T> void setOptListProperty(String name, List<? extends T> list, String tag, boolean forceChange) throws Throwable {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(tag, "tag");
        setOptAnyProperty(name, list, tag, forceChange);
    }

    public static /* synthetic */ void setOptStringProperty$default(Model model, String str, String str2, String str3, boolean z, int i, Object obj) throws Throwable {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setOptStringProperty");
        }
        if ((i & 4) != 0) {
            str3 = ModelChangeTags.NORMAL;
        }
        if ((i & 8) != 0) {
            z = false;
        }
        model.setOptStringProperty(str, str2, str3, z);
    }

    public final void setOptStringProperty(String name, String value, String tag, boolean forceChange) throws Throwable {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(tag, "tag");
        setOptAnyProperty(name, value, tag, forceChange);
    }

    public static /* synthetic */ void setOptBooleanProperty$default(Model model, String str, Boolean bool, String str2, boolean z, int i, Object obj) throws Throwable {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setOptBooleanProperty");
        }
        if ((i & 4) != 0) {
            str2 = ModelChangeTags.NORMAL;
        }
        if ((i & 8) != 0) {
            z = false;
        }
        model.setOptBooleanProperty(str, bool, str2, z);
    }

    public final void setOptBooleanProperty(String name, Boolean value, String tag, boolean forceChange) throws Throwable {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(tag, "tag");
        setOptAnyProperty(name, value, tag, forceChange);
    }

    public static /* synthetic */ void setOptLongProperty$default(Model model, String str, Long l, String str2, boolean z, int i, Object obj) throws Throwable {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setOptLongProperty");
        }
        if ((i & 4) != 0) {
            str2 = ModelChangeTags.NORMAL;
        }
        if ((i & 8) != 0) {
            z = false;
        }
        model.setOptLongProperty(str, l, str2, z);
    }

    public final void setOptLongProperty(String name, Long value, String tag, boolean forceChange) throws Throwable {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(tag, "tag");
        setOptAnyProperty(name, value, tag, forceChange);
    }

    public static /* synthetic */ void setOptDoubleProperty$default(Model model, String str, Double d, String str2, boolean z, int i, Object obj) throws Throwable {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setOptDoubleProperty");
        }
        if ((i & 4) != 0) {
            str2 = ModelChangeTags.NORMAL;
        }
        if ((i & 8) != 0) {
            z = false;
        }
        model.setOptDoubleProperty(str, d, str2, z);
    }

    public final void setOptDoubleProperty(String name, Double value, String tag, boolean forceChange) throws Throwable {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(tag, "tag");
        setOptAnyProperty(name, value, tag, forceChange);
    }

    public static /* synthetic */ void setOptFloatProperty$default(Model model, String str, Float f, String str2, boolean z, int i, Object obj) throws Throwable {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setOptFloatProperty");
        }
        if ((i & 4) != 0) {
            str2 = ModelChangeTags.NORMAL;
        }
        if ((i & 8) != 0) {
            z = false;
        }
        model.setOptFloatProperty(str, f, str2, z);
    }

    public final void setOptFloatProperty(String name, Float value, String tag, boolean forceChange) throws Throwable {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(tag, "tag");
        setOptAnyProperty(name, value, tag, forceChange);
    }

    public static /* synthetic */ void setOptIntProperty$default(Model model, String str, Integer num, String str2, boolean z, int i, Object obj) throws Throwable {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setOptIntProperty");
        }
        if ((i & 4) != 0) {
            str2 = ModelChangeTags.NORMAL;
        }
        if ((i & 8) != 0) {
            z = false;
        }
        model.setOptIntProperty(str, num, str2, z);
    }

    public final void setOptIntProperty(String name, Integer value, String tag, boolean forceChange) throws Throwable {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(tag, "tag");
        setOptAnyProperty(name, value, tag, forceChange);
    }

    public static /* synthetic */ void setOptBigDecimalProperty$default(Model model, String str, BigDecimal bigDecimal, String str2, boolean z, int i, Object obj) throws Throwable {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setOptBigDecimalProperty");
        }
        if ((i & 4) != 0) {
            str2 = ModelChangeTags.NORMAL;
        }
        if ((i & 8) != 0) {
            z = false;
        }
        model.setOptBigDecimalProperty(str, bigDecimal, str2, z);
    }

    public final void setOptBigDecimalProperty(String name, BigDecimal value, String tag, boolean forceChange) throws Throwable {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(tag, "tag");
        setOptAnyProperty(name, value != null ? value.toString() : null, tag, forceChange);
    }

    public static /* synthetic */ void setOptAnyProperty$default(Model model, String str, Object obj, String str2, boolean z, int i, Object obj2) throws Throwable {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setOptAnyProperty");
        }
        if ((i & 4) != 0) {
            str2 = ModelChangeTags.NORMAL;
        }
        if ((i & 8) != 0) {
            z = false;
        }
        model.setOptAnyProperty(str, obj, str2, z);
    }

    public final void setOptAnyProperty(String name, Object value, String tag, boolean forceChange) throws Throwable {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(tag, "tag");
        Object oldValue = this.data.get(name);
        synchronized (this.data) {
            try {
                if (Intrinsics.areEqual(oldValue, value) && !forceChange) {
                    return;
                }
                try {
                    if (value == null) {
                        if (this.data.containsKey(name)) {
                            this.data.remove(name);
                        }
                        Unit unit = Unit.INSTANCE;
                        notifyChanged(name, name, tag, oldValue, value);
                        return;
                    }
                    this.data.put(name, value);
                    Unit unit2 = Unit.INSTANCE;
                    notifyChanged(name, name, tag, oldValue, value);
                    return;
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
            throw th;
        }
    }

    public final boolean hasProperty(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.data.containsKey(name);
    }

    protected final /* synthetic */ <T extends Enum<T>> T getEnumProperty(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        T t = null;
        Object optAnyProperty$default = getOptAnyProperty$default(this, str, null, 2, null);
        if (optAnyProperty$default != null) {
            Intrinsics.reifiedOperationMarker(3, "T");
            if (optAnyProperty$default instanceof Enum) {
                t = (T) optAnyProperty$default;
            } else if (optAnyProperty$default instanceof String) {
                Intrinsics.reifiedOperationMarker(5, "T");
                t = (T) Enum.valueOf(null, (String) optAnyProperty$default);
            } else {
                Intrinsics.reifiedOperationMarker(1, "T");
                t = (T) optAnyProperty$default;
            }
        }
        Intrinsics.reifiedOperationMarker(1, "T");
        return t;
    }

    public static /* synthetic */ MapModel getMapModelProperty$default(Model model, String str, Function0 function0, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getMapModelProperty");
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        return model.getMapModelProperty(str, function0);
    }

    protected final <T> MapModel<T> getMapModelProperty(String name, Function0<? extends MapModel<T>> function0) {
        Intrinsics.checkNotNullParameter(name, "name");
        MapModel<T> optMapModelProperty = getOptMapModelProperty(name, function0);
        Intrinsics.checkNotNull(optMapModelProperty, "null cannot be cast to non-null type com.onesignal.common.modeling.MapModel<T of com.onesignal.common.modeling.Model.getMapModelProperty>");
        return optMapModelProperty;
    }

    public static /* synthetic */ List getListProperty$default(Model model, String str, Function0 function0, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getListProperty");
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        return model.getListProperty(str, function0);
    }

    protected final <T> List<T> getListProperty(String name, Function0<? extends List<? extends T>> function0) {
        Intrinsics.checkNotNullParameter(name, "name");
        List<T> optListProperty = getOptListProperty(name, function0);
        Intrinsics.checkNotNull(optListProperty, "null cannot be cast to non-null type kotlin.collections.List<T of com.onesignal.common.modeling.Model.getListProperty>");
        return optListProperty;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ String getStringProperty$default(Model model, String str, Function0 function0, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getStringProperty");
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        return model.getStringProperty(str, function0);
    }

    protected final String getStringProperty(String name, Function0<String> function0) {
        Intrinsics.checkNotNullParameter(name, "name");
        String optStringProperty = getOptStringProperty(name, function0);
        Intrinsics.checkNotNull(optStringProperty, "null cannot be cast to non-null type kotlin.String");
        return optStringProperty;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ boolean getBooleanProperty$default(Model model, String str, Function0 function0, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getBooleanProperty");
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        return model.getBooleanProperty(str, function0);
    }

    protected final boolean getBooleanProperty(String name, Function0<Boolean> function0) {
        Intrinsics.checkNotNullParameter(name, "name");
        Boolean optBooleanProperty = getOptBooleanProperty(name, function0);
        Intrinsics.checkNotNull(optBooleanProperty, "null cannot be cast to non-null type kotlin.Boolean");
        return optBooleanProperty.booleanValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ long getLongProperty$default(Model model, String str, Function0 function0, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getLongProperty");
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        return model.getLongProperty(str, function0);
    }

    protected final long getLongProperty(String name, Function0<Long> function0) {
        Intrinsics.checkNotNullParameter(name, "name");
        Long optLongProperty = getOptLongProperty(name, function0);
        Intrinsics.checkNotNull(optLongProperty, "null cannot be cast to non-null type kotlin.Long");
        return optLongProperty.longValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ double getDoubleProperty$default(Model model, String str, Function0 function0, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getDoubleProperty");
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        return model.getDoubleProperty(str, function0);
    }

    protected final double getDoubleProperty(String name, Function0<Double> function0) {
        Intrinsics.checkNotNullParameter(name, "name");
        Double optDoubleProperty = getOptDoubleProperty(name, function0);
        Intrinsics.checkNotNull(optDoubleProperty, "null cannot be cast to non-null type kotlin.Double");
        return optDoubleProperty.doubleValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ float getFloatProperty$default(Model model, String str, Function0 function0, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getFloatProperty");
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        return model.getFloatProperty(str, function0);
    }

    protected final float getFloatProperty(String name, Function0<Float> function0) {
        Intrinsics.checkNotNullParameter(name, "name");
        Float optFloatProperty = getOptFloatProperty(name, function0);
        Intrinsics.checkNotNull(optFloatProperty, "null cannot be cast to non-null type kotlin.Float");
        return optFloatProperty.floatValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ int getIntProperty$default(Model model, String str, Function0 function0, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getIntProperty");
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        return model.getIntProperty(str, function0);
    }

    protected final int getIntProperty(String name, Function0<Integer> function0) {
        Intrinsics.checkNotNullParameter(name, "name");
        Integer optIntProperty = getOptIntProperty(name, function0);
        Intrinsics.checkNotNull(optIntProperty, "null cannot be cast to non-null type kotlin.Int");
        return optIntProperty.intValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ BigDecimal getBigDecimalProperty$default(Model model, String str, Function0 function0, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getBigDecimalProperty");
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        return model.getBigDecimalProperty(str, function0);
    }

    protected final BigDecimal getBigDecimalProperty(String name, Function0<? extends BigDecimal> function0) {
        Intrinsics.checkNotNullParameter(name, "name");
        BigDecimal optBigDecimalProperty = getOptBigDecimalProperty(name, function0);
        Intrinsics.checkNotNull(optBigDecimalProperty, "null cannot be cast to non-null type java.math.BigDecimal");
        return optBigDecimalProperty;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object getAnyProperty$default(Model model, String str, Function0 function0, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getAnyProperty");
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        return model.getAnyProperty(str, function0);
    }

    protected final Object getAnyProperty(String name, Function0<? extends Object> function0) {
        Intrinsics.checkNotNullParameter(name, "name");
        Object optAnyProperty = getOptAnyProperty(name, function0);
        Intrinsics.checkNotNull(optAnyProperty, "null cannot be cast to non-null type kotlin.Any");
        return optAnyProperty;
    }

    protected final /* synthetic */ <T extends Enum<T>> T getOptEnumProperty(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        Object optAnyProperty$default = getOptAnyProperty$default(this, str, null, 2, null);
        if (optAnyProperty$default == null) {
            return null;
        }
        Intrinsics.reifiedOperationMarker(3, "T");
        if (optAnyProperty$default instanceof Enum) {
            return (T) optAnyProperty$default;
        }
        if (optAnyProperty$default instanceof String) {
            Intrinsics.reifiedOperationMarker(5, "T");
            return (T) Enum.valueOf(null, (String) optAnyProperty$default);
        }
        Intrinsics.reifiedOperationMarker(1, "T");
        return (T) optAnyProperty$default;
    }

    public static /* synthetic */ MapModel getOptMapModelProperty$default(Model model, String str, Function0 function0, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getOptMapModelProperty");
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        return model.getOptMapModelProperty(str, function0);
    }

    protected final <T> MapModel<T> getOptMapModelProperty(String name, Function0<? extends MapModel<T>> function0) {
        Intrinsics.checkNotNullParameter(name, "name");
        return (MapModel) getOptAnyProperty(name, function0);
    }

    public static /* synthetic */ List getOptListProperty$default(Model model, String str, Function0 function0, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getOptListProperty");
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        return model.getOptListProperty(str, function0);
    }

    protected final <T> List<T> getOptListProperty(String name, Function0<? extends List<? extends T>> function0) {
        Intrinsics.checkNotNullParameter(name, "name");
        return (List) getOptAnyProperty(name, function0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ String getOptStringProperty$default(Model model, String str, Function0 function0, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getOptStringProperty");
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        return model.getOptStringProperty(str, function0);
    }

    protected final String getOptStringProperty(String name, Function0<String> function0) {
        Intrinsics.checkNotNullParameter(name, "name");
        return (String) getOptAnyProperty(name, function0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Boolean getOptBooleanProperty$default(Model model, String str, Function0 function0, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getOptBooleanProperty");
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        return model.getOptBooleanProperty(str, function0);
    }

    protected final Boolean getOptBooleanProperty(String name, Function0<Boolean> function0) {
        Intrinsics.checkNotNullParameter(name, "name");
        return (Boolean) getOptAnyProperty(name, function0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Long getOptLongProperty$default(Model model, String str, Function0 function0, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getOptLongProperty");
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        return model.getOptLongProperty(str, function0);
    }

    protected final Long getOptLongProperty(String name, Function0<Long> function0) {
        Intrinsics.checkNotNullParameter(name, "name");
        Object value = getOptAnyProperty(name, function0);
        if (value == null) {
            return null;
        }
        return value instanceof Long ? (Long) value : value instanceof Integer ? Long.valueOf(((Number) value).intValue()) : value instanceof Float ? Long.valueOf((long) ((Number) value).floatValue()) : value instanceof Double ? Long.valueOf((long) ((Number) value).doubleValue()) : (Long) value;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Float getOptFloatProperty$default(Model model, String str, Function0 function0, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getOptFloatProperty");
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        return model.getOptFloatProperty(str, function0);
    }

    protected final Float getOptFloatProperty(String name, Function0<Float> function0) {
        Intrinsics.checkNotNullParameter(name, "name");
        Object value = getOptAnyProperty(name, function0);
        if (value == null) {
            return null;
        }
        return value instanceof Float ? (Float) value : value instanceof Double ? Float.valueOf((float) ((Number) value).doubleValue()) : value instanceof Integer ? Float.valueOf(((Number) value).intValue()) : value instanceof Long ? Float.valueOf(((Number) value).longValue()) : (Float) value;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Double getOptDoubleProperty$default(Model model, String str, Function0 function0, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getOptDoubleProperty");
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        return model.getOptDoubleProperty(str, function0);
    }

    protected final Double getOptDoubleProperty(String name, Function0<Double> function0) {
        Intrinsics.checkNotNullParameter(name, "name");
        Object value = getOptAnyProperty(name, function0);
        if (value == null) {
            return null;
        }
        return value instanceof Double ? (Double) value : value instanceof Float ? Double.valueOf(((Number) value).floatValue()) : value instanceof Integer ? Double.valueOf(((Number) value).intValue()) : value instanceof Long ? Double.valueOf(((Number) value).longValue()) : (Double) value;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Integer getOptIntProperty$default(Model model, String str, Function0 function0, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getOptIntProperty");
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        return model.getOptIntProperty(str, function0);
    }

    protected final Integer getOptIntProperty(String name, Function0<Integer> function0) {
        Intrinsics.checkNotNullParameter(name, "name");
        Object value = getOptAnyProperty(name, function0);
        if (value == null) {
            return null;
        }
        return value instanceof Integer ? (Integer) value : value instanceof Long ? Integer.valueOf((int) ((Number) value).longValue()) : value instanceof Float ? Integer.valueOf((int) ((Number) value).floatValue()) : value instanceof Double ? Integer.valueOf((int) ((Number) value).doubleValue()) : (Integer) value;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ BigDecimal getOptBigDecimalProperty$default(Model model, String str, Function0 function0, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getOptBigDecimalProperty");
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        return model.getOptBigDecimalProperty(str, function0);
    }

    protected final BigDecimal getOptBigDecimalProperty(String name, Function0<? extends BigDecimal> function0) {
        Intrinsics.checkNotNullParameter(name, "name");
        Object value = getOptAnyProperty(name, function0);
        if (value == null) {
            return null;
        }
        return value instanceof Integer ? new BigDecimal(((Number) value).intValue()) : value instanceof Long ? new BigDecimal(((Number) value).longValue()) : value instanceof Float ? new BigDecimal(((Number) value).floatValue()) : value instanceof Double ? new BigDecimal(((Number) value).doubleValue()) : value instanceof String ? new BigDecimal((String) value) : (BigDecimal) value;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object getOptAnyProperty$default(Model model, String str, Function0 function0, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getOptAnyProperty");
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        return model.getOptAnyProperty(str, function0);
    }

    protected final Object getOptAnyProperty(String name, Function0<? extends Object> function0) {
        Object defaultValue;
        Intrinsics.checkNotNullParameter(name, "name");
        synchronized (this.data) {
            if (this.data.containsKey(name) || function0 == null) {
                defaultValue = this.data.get(name);
            } else {
                defaultValue = function0.invoke();
                this.data.put(name, defaultValue);
            }
        }
        return defaultValue;
    }

    private final void notifyChanged(String path, String property, final String tag, Object oldValue, Object newValue) {
        final ModelChangedArgs changeArgs = new ModelChangedArgs(this, path, property, oldValue, newValue);
        this.changeNotifier.fire(new Function1<IModelChangedHandler, Unit>() { // from class: com.onesignal.common.modeling.Model.notifyChanged.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                invoke((IModelChangedHandler) p1);
                return Unit.INSTANCE;
            }

            public final void invoke(IModelChangedHandler it) {
                Intrinsics.checkNotNullParameter(it, "it");
                it.onChanged(changeArgs, tag);
            }
        });
        if (this._parentModel != null) {
            String parentPath = this._parentProperty + '.' + path;
            Model model = this._parentModel;
            Intrinsics.checkNotNull(model);
            model.notifyChanged(parentPath, property, tag, oldValue, newValue);
        }
    }

    public final JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        synchronized (this.data) {
            for (Map.Entry<String, Object> entry : this.data.entrySet()) {
                Object value = entry.getValue();
                if (value instanceof Model) {
                    jsonObject.put(entry.getKey(), ((Model) value).toJSON());
                } else if (value instanceof List) {
                    JSONArray jsonArray = new JSONArray();
                    for (Object arrayItem : (List) value) {
                        if (arrayItem instanceof Model) {
                            jsonArray.put(((Model) arrayItem).toJSON());
                        } else {
                            jsonArray.put(arrayItem);
                        }
                    }
                    jsonObject.put(entry.getKey(), jsonArray);
                } else {
                    jsonObject.put(entry.getKey(), value);
                }
            }
            Unit unit = Unit.INSTANCE;
        }
        return jsonObject;
    }

    @Override // com.onesignal.common.events.IEventNotifier
    public void subscribe(IModelChangedHandler handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.changeNotifier.subscribe(handler);
    }

    @Override // com.onesignal.common.events.IEventNotifier
    public void unsubscribe(IModelChangedHandler handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.changeNotifier.unsubscribe(handler);
    }

    @Override // com.onesignal.common.events.IEventNotifier
    public boolean getHasSubscribers() {
        return this.changeNotifier.getHasSubscribers();
    }
}
