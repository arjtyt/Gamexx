package com.onesignal.user.internal.backend;

import com.onesignal.core.BuildConfig;
import com.onesignal.core.internal.device.IDeviceService;
import com.onesignal.inAppMessages.internal.display.impl.WebViewManager;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SubscriptionObjectType.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\b\u0086\u0001\u0018\u0000 \u00142\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0014B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013¨\u0006\u0015"}, d2 = {"Lcom/onesignal/user/internal/backend/SubscriptionObjectType;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "IOS_PUSH", "ANDROID_PUSH", "FIREOS_PUSH", "CHROME_EXTENSION", "CHROME_PUSH", "WINDOWS_PUSH", "SAFARI_PUSH", "SAFARI_PUSH_LEGACY", "FIREFOX_PUSH", "MACOS_PUSH", "EMAIL", "HUAWEI_PUSH", "SMS", "Companion", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public enum SubscriptionObjectType {
    IOS_PUSH("iOSPush"),
    ANDROID_PUSH("AndroidPush"),
    FIREOS_PUSH("FireOSPush"),
    CHROME_EXTENSION("ChromeExtensionPush"),
    CHROME_PUSH("ChromePush"),
    WINDOWS_PUSH("WindowsPush"),
    SAFARI_PUSH("SafariPush"),
    SAFARI_PUSH_LEGACY("SafariLegacyPush"),
    FIREFOX_PUSH("FirefoxPush"),
    MACOS_PUSH("macOSPush"),
    EMAIL("Email"),
    HUAWEI_PUSH("HuaweiPush"),
    SMS("SMS");

    public static final Companion Companion = new Companion(null);
    private final String value;

    SubscriptionObjectType(String value) {
        this.value = value;
    }

    public final String getValue() {
        return this.value;
    }

    /* JADX INFO: compiled from: SubscriptionObjectType.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/onesignal/user/internal/backend/SubscriptionObjectType$Companion;", "", "()V", "fromDeviceType", "Lcom/onesignal/user/internal/backend/SubscriptionObjectType;", WebViewManager.EVENT_TYPE_KEY, "Lcom/onesignal/core/internal/device/IDeviceService$DeviceType;", "fromString", "", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {

        /* JADX INFO: compiled from: SubscriptionObjectType.kt */
        @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[IDeviceService.DeviceType.values().length];
                iArr[IDeviceService.DeviceType.Android.ordinal()] = 1;
                iArr[IDeviceService.DeviceType.Fire.ordinal()] = 2;
                iArr[IDeviceService.DeviceType.Huawei.ordinal()] = 3;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
        public final SubscriptionObjectType fromDeviceType(IDeviceService.DeviceType type) throws NoWhenBranchMatchedException {
            Intrinsics.checkNotNullParameter(type, WebViewManager.EVENT_TYPE_KEY);
            switch (WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
                case 1:
                    return SubscriptionObjectType.ANDROID_PUSH;
                case 2:
                    return SubscriptionObjectType.FIREOS_PUSH;
                case 3:
                    return SubscriptionObjectType.HUAWEI_PUSH;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }

        public final SubscriptionObjectType fromString(String type) {
            Intrinsics.checkNotNullParameter(type, WebViewManager.EVENT_TYPE_KEY);
            for (SubscriptionObjectType subscriptionObjectType : SubscriptionObjectType.values()) {
                if (StringsKt.equals(subscriptionObjectType.getValue(), type, true)) {
                    return subscriptionObjectType;
                }
            }
            return null;
        }
    }
}
