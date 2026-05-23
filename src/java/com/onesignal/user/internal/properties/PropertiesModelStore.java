package com.onesignal.user.internal.properties;

import com.onesignal.common.modeling.SimpleModelStore;
import com.onesignal.common.modeling.SingletonModelStore;
import com.onesignal.core.BuildConfig;
import com.onesignal.core.internal.preferences.IPreferencesService;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PropertiesModelStore.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/2.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/onesignal/user/internal/properties/PropertiesModelStore;", "Lcom/onesignal/common/modeling/SingletonModelStore;", "Lcom/onesignal/user/internal/properties/PropertiesModel;", "prefs", "Lcom/onesignal/core/internal/preferences/IPreferencesService;", "(Lcom/onesignal/core/internal/preferences/IPreferencesService;)V", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public class PropertiesModelStore extends SingletonModelStore<PropertiesModel> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PropertiesModelStore(IPreferencesService prefs) {
        super(new SimpleModelStore(new Function0<PropertiesModel>() { // from class: com.onesignal.user.internal.properties.PropertiesModelStore.1
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final PropertiesModel m94invoke() {
                return new PropertiesModel();
            }
        }, "properties", prefs));
        Intrinsics.checkNotNullParameter(prefs, "prefs");
    }
}
