package com.onesignal.common;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import androidx.core.view.WindowInsetsCompat;
import com.onesignal.core.BuildConfig;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DeviceUtils.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0015\u0010\t\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\f2\u000e\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/onesignal/common/DeviceUtils;", "", "()V", "MARGIN_ERROR_PX_SIZE", "", "getCarrierName", "", "appContext", "Landroid/content/Context;", "getNetType", "(Landroid/content/Context;)Ljava/lang/Integer;", "isKeyboardUp", "", "activityWeakReference", "Ljava/lang/ref/WeakReference;", "Landroid/app/Activity;", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class DeviceUtils {
    public static final DeviceUtils INSTANCE = new DeviceUtils();
    private static final int MARGIN_ERROR_PX_SIZE = ViewUtils.INSTANCE.dpToPx(24);

    private DeviceUtils() {
    }

    public final boolean isKeyboardUp(WeakReference<Activity> weakReference) {
        Intrinsics.checkNotNullParameter(weakReference, "activityWeakReference");
        DisplayMetrics metrics = new DisplayMetrics();
        Rect visibleBounds = new Rect();
        View view = null;
        if (weakReference.get() != null) {
            Activity activity = weakReference.get();
            Intrinsics.checkNotNull(activity);
            Window window = activity.getWindow();
            view = window.getDecorView();
            view.getWindowVisibleDisplayFrame(visibleBounds);
            window.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        }
        if (view == null) {
            return false;
        }
        boolean zIsVisible = false;
        if (Build.VERSION.SDK_INT >= 30) {
            WindowInsets imeInsets = view.getRootWindowInsets();
            if (imeInsets != null) {
                zIsVisible = imeInsets.isVisible(WindowInsetsCompat.Type.ime());
            }
        } else {
            int heightDiff = metrics.heightPixels - visibleBounds.bottom;
            if (heightDiff > MARGIN_ERROR_PX_SIZE) {
                zIsVisible = true;
            }
        }
        boolean isOpen = zIsVisible;
        return isOpen;
    }

    public final Integer getNetType(Context appContext) {
        int i;
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Object systemService = appContext.getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        ConnectivityManager cm = (ConnectivityManager) systemService;
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null) {
            int networkType = netInfo.getType();
            switch (networkType) {
                case 1:
                case 9:
                    i = 0;
                    break;
                default:
                    i = 1;
                    break;
            }
            return Integer.valueOf(i);
        }
        return null;
    }

    public final String getCarrierName(Context appContext) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        try {
            Object systemService = appContext.getSystemService("phone");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.telephony.TelephonyManager");
            TelephonyManager manager = (TelephonyManager) systemService;
            String carrierName = manager.getNetworkOperatorName();
            if (Intrinsics.areEqual("", carrierName)) {
                return null;
            }
            return carrierName;
        } catch (Throwable t) {
            t.printStackTrace();
            return null;
        }
    }
}
