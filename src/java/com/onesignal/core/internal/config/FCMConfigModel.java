package com.onesignal.core.internal.config;

import com.onesignal.common.modeling.Model;
import com.onesignal.core.BuildConfig;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ConfigModel.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005R(\u0010\u0007\u001a\u0004\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00048F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR(\u0010\f\u001a\u0004\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00048F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000bR(\u0010\u000f\u001a\u0004\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00048F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006\u0012"}, d2 = {"Lcom/onesignal/core/internal/config/FCMConfigModel;", "Lcom/onesignal/common/modeling/Model;", "parentModel", "parentProperty", "", "(Lcom/onesignal/common/modeling/Model;Ljava/lang/String;)V", "value", "apiKey", "getApiKey", "()Ljava/lang/String;", "setApiKey", "(Ljava/lang/String;)V", "appId", "getAppId", "setAppId", "projectId", "getProjectId", "setProjectId", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class FCMConfigModel extends Model {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FCMConfigModel(Model parentModel, String parentProperty) {
        super(parentModel, parentProperty);
        Intrinsics.checkNotNullParameter(parentModel, "parentModel");
        Intrinsics.checkNotNullParameter(parentProperty, "parentProperty");
    }

    public final String getProjectId() {
        return getOptStringProperty("projectId", new Function0<String>() { // from class: com.onesignal.core.internal.config.FCMConfigModel$projectId$2
            public final String invoke() {
                return null;
            }
        });
    }

    public final void setProjectId(String value) throws Throwable {
        Model.setOptStringProperty$default(this, "projectId", value, null, false, 12, null);
    }

    public final String getAppId() {
        return getOptStringProperty("appId", new Function0<String>() { // from class: com.onesignal.core.internal.config.FCMConfigModel$appId$2
            public final String invoke() {
                return null;
            }
        });
    }

    public final void setAppId(String value) throws Throwable {
        Model.setOptStringProperty$default(this, "appId", value, null, false, 12, null);
    }

    public final String getApiKey() {
        return getOptStringProperty("apiKey", new Function0<String>() { // from class: com.onesignal.core.internal.config.FCMConfigModel$apiKey$2
            public final String invoke() {
                return null;
            }
        });
    }

    public final void setApiKey(String value) throws Throwable {
        Model.setOptStringProperty$default(this, "apiKey", value, null, false, 12, null);
    }
}
