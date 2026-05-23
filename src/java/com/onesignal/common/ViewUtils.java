package com.onesignal.common;

import android.R;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.view.DisplayCutout;
import android.view.View;
import android.view.WindowInsets;
import com.onesignal.core.BuildConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: ViewUtils.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0002J\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\f\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0003J\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\tH\u0002J\u000e\u0010\u0011\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t¨\u0006\u0012"}, d2 = {"Lcom/onesignal/common/ViewUtils;", "", "()V", "dpToPx", "", "dp", "getCutoutAndStatusBarInsets", "", "activity", "Landroid/app/Activity;", "getDisplaySizeY", "getFullbleedWindowWidth", "getWindowHeight", "getWindowHeightAPI23Plus", "getWindowHeightLollipop", "getWindowVisibleDisplayFrame", "Landroid/graphics/Rect;", "getWindowWidth", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class ViewUtils {
    public static final ViewUtils INSTANCE = new ViewUtils();

    private ViewUtils() {
    }

    public final int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public final int getWindowHeight(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        return getWindowHeightAPI23Plus(activity);
    }

    private final int getDisplaySizeY(Activity activity) {
        Point point = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(point);
        return point.y;
    }

    private final int getWindowHeightAPI23Plus(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(decorView, "activity.window.decorView");
        WindowInsets windowInsets = decorView.getRootWindowInsets();
        return windowInsets == null ? decorView.getHeight() : (decorView.getHeight() - windowInsets.getStableInsetBottom()) - windowInsets.getStableInsetTop();
    }

    private final int getWindowHeightLollipop(Activity activity) {
        if (activity.getResources().getConfiguration().orientation == 2) {
            return getWindowVisibleDisplayFrame(activity).height();
        }
        return getDisplaySizeY(activity);
    }

    private final Rect getWindowVisibleDisplayFrame(Activity activity) {
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect;
    }

    public final int[] getCutoutAndStatusBarInsets(Activity activity) {
        DisplayCutout cutout;
        Intrinsics.checkNotNullParameter(activity, "activity");
        Rect frame = getWindowVisibleDisplayFrame(activity);
        View contentView = activity.getWindow().findViewById(R.id.content);
        float rightInset = 0.0f;
        float leftInset = 0.0f;
        float topInset = (frame.top - contentView.getTop()) / Resources.getSystem().getDisplayMetrics().density;
        float bottomInset = (contentView.getBottom() - frame.bottom) / Resources.getSystem().getDisplayMetrics().density;
        if (Build.VERSION.SDK_INT == 29 && (cutout = activity.getWindowManager().getDefaultDisplay().getCutout()) != null) {
            rightInset = cutout.getSafeInsetRight() / Resources.getSystem().getDisplayMetrics().density;
            leftInset = cutout.getSafeInsetLeft() / Resources.getSystem().getDisplayMetrics().density;
        }
        return new int[]{MathKt.roundToInt(topInset), MathKt.roundToInt(bottomInset), MathKt.roundToInt(rightInset), MathKt.roundToInt(leftInset)};
    }

    public final int getFullbleedWindowWidth(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        View decorView = activity.getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(decorView, "activity.window.decorView");
        return decorView.getWidth();
    }

    public final int getWindowWidth(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        return getWindowVisibleDisplayFrame(activity).width();
    }
}
