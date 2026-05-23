package com.onesignal.core.internal.config;

import com.onesignal.common.modeling.Model;
import com.onesignal.core.BuildConfig;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ConfigModel.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\r\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005R$\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00078F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR$\u0010\r\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00078F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\fR$\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00078F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0011\u0010\n\"\u0004\b\u0012\u0010\fR$\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0006\u001a\u00020\u00138F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R$\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0006\u001a\u00020\u00138F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0018\u0010\u0015\"\u0004\b\u0019\u0010\u0017R$\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u0006\u001a\u00020\u00138F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001a\u0010\u0015\"\u0004\b\u001b\u0010\u0017R$\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00078F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001d\u0010\n\"\u0004\b\u001e\u0010\f¨\u0006 "}, d2 = {"Lcom/onesignal/core/internal/config/InfluenceConfigModel;", "Lcom/onesignal/common/modeling/Model;", "parentModel", "parentProperty", "", "(Lcom/onesignal/common/modeling/Model;Ljava/lang/String;)V", "value", "", "iamLimit", "getIamLimit", "()I", "setIamLimit", "(I)V", "indirectIAMAttributionWindow", "getIndirectIAMAttributionWindow", "setIndirectIAMAttributionWindow", "indirectNotificationAttributionWindow", "getIndirectNotificationAttributionWindow", "setIndirectNotificationAttributionWindow", "", "isDirectEnabled", "()Z", "setDirectEnabled", "(Z)V", "isIndirectEnabled", "setIndirectEnabled", "isUnattributedEnabled", "setUnattributedEnabled", "notificationLimit", "getNotificationLimit", "setNotificationLimit", "Companion", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class InfluenceConfigModel extends Model {
    public static final Companion Companion = new Companion(null);
    public static final int DEFAULT_INDIRECT_ATTRIBUTION_WINDOW = 1440;
    public static final int DEFAULT_NOTIFICATION_LIMIT = 10;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InfluenceConfigModel(Model parentModel, String parentProperty) {
        super(parentModel, parentProperty);
        Intrinsics.checkNotNullParameter(parentModel, "parentModel");
        Intrinsics.checkNotNullParameter(parentProperty, "parentProperty");
    }

    public final int getIndirectNotificationAttributionWindow() {
        return getIntProperty("indirectNotificationAttributionWindow", new Function0<Integer>() { // from class: com.onesignal.core.internal.config.InfluenceConfigModel$indirectNotificationAttributionWindow$2
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Integer m39invoke() {
                return Integer.valueOf(InfluenceConfigModel.DEFAULT_INDIRECT_ATTRIBUTION_WINDOW);
            }
        });
    }

    public final void setIndirectNotificationAttributionWindow(int value) throws Throwable {
        Model.setIntProperty$default(this, "indirectNotificationAttributionWindow", value, null, false, 12, null);
    }

    public final int getNotificationLimit() {
        return getIntProperty("notificationLimit", new Function0<Integer>() { // from class: com.onesignal.core.internal.config.InfluenceConfigModel$notificationLimit$2
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Integer m43invoke() {
                return 10;
            }
        });
    }

    public final void setNotificationLimit(int value) throws Throwable {
        Model.setIntProperty$default(this, "notificationLimit", value, null, false, 12, null);
    }

    public final int getIndirectIAMAttributionWindow() {
        return getIntProperty("indirectIAMAttributionWindow", new Function0<Integer>() { // from class: com.onesignal.core.internal.config.InfluenceConfigModel$indirectIAMAttributionWindow$2
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Integer m38invoke() {
                return Integer.valueOf(InfluenceConfigModel.DEFAULT_INDIRECT_ATTRIBUTION_WINDOW);
            }
        });
    }

    public final void setIndirectIAMAttributionWindow(int value) throws Throwable {
        Model.setIntProperty$default(this, "indirectIAMAttributionWindow", value, null, false, 12, null);
    }

    public final int getIamLimit() {
        return getIntProperty("iamLimit", new Function0<Integer>() { // from class: com.onesignal.core.internal.config.InfluenceConfigModel$iamLimit$2
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Integer m37invoke() {
                return 10;
            }
        });
    }

    public final void setIamLimit(int value) throws Throwable {
        Model.setIntProperty$default(this, "iamLimit", value, null, false, 12, null);
    }

    public final boolean isDirectEnabled() {
        return getBooleanProperty("isDirectEnabled", new Function0<Boolean>() { // from class: com.onesignal.core.internal.config.InfluenceConfigModel.isDirectEnabled.2
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Boolean m40invoke() {
                return false;
            }
        });
    }

    public final void setDirectEnabled(boolean value) throws Throwable {
        Model.setBooleanProperty$default(this, "isDirectEnabled", value, null, false, 12, null);
    }

    public final boolean isIndirectEnabled() {
        return getBooleanProperty("isIndirectEnabled", new Function0<Boolean>() { // from class: com.onesignal.core.internal.config.InfluenceConfigModel.isIndirectEnabled.2
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Boolean m41invoke() {
                return false;
            }
        });
    }

    public final void setIndirectEnabled(boolean value) throws Throwable {
        Model.setBooleanProperty$default(this, "isIndirectEnabled", value, null, false, 12, null);
    }

    public final boolean isUnattributedEnabled() {
        return getBooleanProperty("isUnattributedEnabled", new Function0<Boolean>() { // from class: com.onesignal.core.internal.config.InfluenceConfigModel.isUnattributedEnabled.2
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Boolean m42invoke() {
                return false;
            }
        });
    }

    public final void setUnattributedEnabled(boolean value) throws Throwable {
        Model.setBooleanProperty$default(this, "isUnattributedEnabled", value, null, false, 12, null);
    }

    /* JADX INFO: compiled from: ConfigModel.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/onesignal/core/internal/config/InfluenceConfigModel$Companion;", "", "()V", "DEFAULT_INDIRECT_ATTRIBUTION_WINDOW", "", "DEFAULT_NOTIFICATION_LIMIT", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
