package com.onesignal.core.internal.preferences;

import com.onesignal.core.BuildConfig;
import kotlin.Metadata;

/* JADX INFO: compiled from: IPreferencesService.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/onesignal/core/internal/preferences/PreferencePlayerPurchasesKeys;", "", "()V", "PREFS_EXISTING_PURCHASES", "", "PREFS_PURCHASE_TOKENS", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class PreferencePlayerPurchasesKeys {
    public static final PreferencePlayerPurchasesKeys INSTANCE = new PreferencePlayerPurchasesKeys();
    public static final String PREFS_EXISTING_PURCHASES = "ExistingPurchases";
    public static final String PREFS_PURCHASE_TOKENS = "purchaseTokens";

    private PreferencePlayerPurchasesKeys() {
    }
}
