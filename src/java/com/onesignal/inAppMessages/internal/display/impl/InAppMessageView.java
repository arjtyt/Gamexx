package com.onesignal.inAppMessages.internal.display.impl;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.webkit.WebView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import androidx.cardview.widget.CardView;
import androidx.core.widget.PopupWindowCompat;
import com.onesignal.common.AndroidUtils;
import com.onesignal.common.ViewUtils;
import com.onesignal.common.threading.ThreadUtilsKt;
import com.onesignal.common.threading.Waiter;
import com.onesignal.debug.internal.logging.Logging;
import com.onesignal.inAppMessages.BuildConfig;
import com.onesignal.inAppMessages.internal.InAppMessageContent;
import com.onesignal.inAppMessages.internal.display.impl.DraggableRelativeLayout;
import com.onesignal.inAppMessages.internal.display.impl.WebViewManager;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: InAppMessageView.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000¤\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0000\u0018\u0000 l2\u00020\u0001:\u0002lmB'\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\u0019\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010/J2\u00100\u001a\u0002012\u0006\u0010-\u001a\u00020.2\u0006\u00102\u001a\u00020\u00142\u0006\u00103\u001a\u00020\u00142\u0006\u00104\u001a\u00020\u00142\b\u00105\u001a\u0004\u0018\u000106H\u0002J \u00107\u001a\u00020,2\u0006\u00108\u001a\u00020.2\u0006\u00109\u001a\u00020\u00142\u0006\u0010:\u001a\u00020;H\u0002J*\u0010<\u001a\u00020,2\u0006\u00108\u001a\u00020.2\u0006\u0010-\u001a\u00020.2\u0006\u0010:\u001a\u00020;2\b\u0010=\u001a\u0004\u0018\u000106H\u0002J \u0010>\u001a\u00020,2\u0006\u0010?\u001a\u00020\u00102\u0006\u00108\u001a\u00020.2\u0006\u0010-\u001a\u00020.H\u0002J \u0010@\u001a\u00020,2\u0006\u00108\u001a\u00020.2\u0006\u00109\u001a\u00020\u00142\u0006\u0010:\u001a\u00020;H\u0002J\u0011\u0010A\u001a\u00020,H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010BJ\b\u0010C\u001a\u00020,H\u0002J\u0010\u0010D\u001a\u00020;2\u0006\u0010E\u001a\u00020FH\u0002J\u0010\u0010G\u001a\u00020F2\u0006\u0010H\u001a\u00020IH\u0002J \u0010J\u001a\u00020K2\u0006\u0010$\u001a\u00020\u00142\u0006\u0010?\u001a\u00020\u00102\u0006\u0010L\u001a\u00020\u0007H\u0002J\b\u0010M\u001a\u00020NH\u0002J\u0010\u0010O\u001a\u00020,2\u0006\u0010&\u001a\u00020'H\u0002J\u0019\u0010P\u001a\u00020,2\u0006\u0010\u000b\u001a\u00020\fH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010QJ\b\u0010R\u001a\u00020,H\u0002J\u0011\u0010S\u001a\u00020,H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010BJ\u0011\u0010T\u001a\u00020,H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010BJ\u0010\u0010U\u001a\u00020\u00072\u0006\u0010H\u001a\u00020IH\u0002J\b\u0010V\u001a\u00020\u0014H\u0002J\u0006\u0010W\u001a\u00020,J\u0010\u0010X\u001a\u00020,2\u0006\u0010Y\u001a\u00020\u0005H\u0002J\u0010\u0010Z\u001a\u00020,2\b\u0010\"\u001a\u0004\u0018\u00010#J\"\u0010[\u001a\u00020,2\u0006\u0010H\u001a\u00020I2\b\u0010\\\u001a\u0004\u0018\u00010N2\u0006\u0010]\u001a\u00020KH\u0002J\u0010\u0010^\u001a\u00020,2\u0006\u0010H\u001a\u00020IH\u0002J\u000e\u0010_\u001a\u00020,2\u0006\u0010\u0002\u001a\u00020\u0003J3\u0010`\u001a\u00020,2\u0006\u0010?\u001a\u00020\u00102\u0006\u0010\\\u001a\u00020N2\b\u0010a\u001a\u0004\u0018\u00010N2\u0006\u0010b\u001a\u00020KH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010cJ\u001b\u0010d\u001a\u00020,2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010QJ\u0019\u0010e\u001a\u00020,2\u0006\u0010f\u001a\u00020\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010QJ\u0011\u0010g\u001a\u00020,H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010BJ\b\u0010h\u001a\u00020iH\u0016J\u0019\u0010j\u001a\u00020,2\u0006\u0010$\u001a\u00020\u0014H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010kR\u000e\u0010\n\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00148BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0007@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0014X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006n"}, d2 = {"Lcom/onesignal/inAppMessages/internal/display/impl/InAppMessageView;", "", "webView", "Landroid/webkit/WebView;", "messageContent", "Lcom/onesignal/inAppMessages/internal/InAppMessageContent;", "disableDragDismiss", "", "hideGrayOverlay", "(Landroid/webkit/WebView;Lcom/onesignal/inAppMessages/internal/InAppMessageContent;ZZ)V", "cancelDismissTimer", "currentActivity", "Landroid/app/Activity;", "displayDuration", "", "displayPosition", "Lcom/onesignal/inAppMessages/internal/display/impl/WebViewManager$Position;", "getDisplayPosition", "()Lcom/onesignal/inAppMessages/internal/display/impl/WebViewManager$Position;", "displayYSize", "", "getDisplayYSize", "()I", "draggableRelativeLayout", "Lcom/onesignal/inAppMessages/internal/display/impl/DraggableRelativeLayout;", "hasBackground", "isDismissTimerSet", "<set-?>", "isDragging", "()Z", "marginPxSizeBottom", "marginPxSizeLeft", "marginPxSizeRight", "marginPxSizeTop", "messageController", "Lcom/onesignal/inAppMessages/internal/display/impl/InAppMessageView$InAppMessageViewListener;", "pageHeight", "pageWidth", "parentRelativeLayout", "Landroid/widget/RelativeLayout;", "popupWindow", "Landroid/widget/PopupWindow;", "shouldDismissWhenActive", "animateAndDismissLayout", "", "backgroundView", "Landroid/view/View;", "(Landroid/view/View;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animateBackgroundColor", "Landroid/animation/ValueAnimator;", "duration", "startColor", "endColor", "animCallback", "Landroid/animation/Animator$AnimatorListener;", "animateBottom", "messageView", "height", "cardViewAnimCallback", "Landroid/view/animation/Animation$AnimationListener;", "animateCenter", "backgroundAnimCallback", "animateInAppMessage", WebViewManager.IAM_DISPLAY_LOCATION_KEY, "animateTop", "checkIfShouldDismiss", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cleanupViewsAfterDismiss", "createAnimationListener", "messageViewCardView", "Landroidx/cardview/widget/CardView;", "createCardView", "context", "Landroid/content/Context;", "createDraggableLayoutParams", "Lcom/onesignal/inAppMessages/internal/display/impl/DraggableRelativeLayout$Params;", "disableDragging", "createParentRelativeLayoutParams", "Landroid/widget/RelativeLayout$LayoutParams;", "createPopupWindow", "delayShowUntilAvailable", "(Landroid/app/Activity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dereferenceViews", "dismissAndAwaitNextMessage", "finishAfterDelay", "getHideDropShadow", "getOverlayColor", "removeAllViews", "setMarginsFromContent", "content", "setMessageController", "setUpDraggableLayout", "relativeLayoutParams", "draggableParams", "setUpParentRelativeLayout", "setWebView", "showDraggableView", "draggableRelativeLayoutParams", "webViewLayoutParams", "(Lcom/onesignal/inAppMessages/internal/display/impl/WebViewManager$Position;Landroid/widget/RelativeLayout$LayoutParams;Landroid/widget/RelativeLayout$LayoutParams;Lcom/onesignal/inAppMessages/internal/display/impl/DraggableRelativeLayout$Params;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "showInAppMessageView", "showView", "activity", "startDismissTimerIfNeeded", "toString", "", "updateHeight", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "InAppMessageViewListener", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class InAppMessageView {
    private static final int ACTIVITY_BACKGROUND_COLOR_EMPTY = 0;
    private static final int ACTIVITY_FINISH_AFTER_DISMISS_DELAY_MS = 600;
    private static final int ACTIVITY_INIT_DELAY = 200;
    private static final int IN_APP_BACKGROUND_ANIMATION_DURATION_MS = 400;
    private static final int IN_APP_BANNER_ANIMATION_DURATION_MS = 1000;
    private static final int IN_APP_CENTER_ANIMATION_DURATION_MS = 1000;
    private static final String IN_APP_MESSAGE_CARD_VIEW_TAG = "IN_APP_MESSAGE_CARD_VIEW_TAG";
    private boolean cancelDismissTimer;
    private Activity currentActivity;
    private final boolean disableDragDismiss;
    private final double displayDuration;
    private final WebViewManager.Position displayPosition;
    private DraggableRelativeLayout draggableRelativeLayout;
    private final boolean hasBackground;
    private final boolean hideGrayOverlay;
    private boolean isDismissTimerSet;
    private boolean isDragging;
    private int marginPxSizeBottom;
    private int marginPxSizeLeft;
    private int marginPxSizeRight;
    private int marginPxSizeTop;
    private final InAppMessageContent messageContent;
    private InAppMessageViewListener messageController;
    private int pageHeight;
    private final int pageWidth;
    private RelativeLayout parentRelativeLayout;
    private PopupWindow popupWindow;
    private boolean shouldDismissWhenActive;
    private WebView webView;
    public static final Companion Companion = new Companion(null);
    private static final int ACTIVITY_BACKGROUND_COLOR_FULL = Color.parseColor("#BB000000");
    private static final int DRAG_THRESHOLD_PX_SIZE = ViewUtils.INSTANCE.dpToPx(4);

    /* JADX INFO: compiled from: InAppMessageView.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&¨\u0006\u0006"}, d2 = {"Lcom/onesignal/inAppMessages/internal/display/impl/InAppMessageView$InAppMessageViewListener;", "", "onMessageWasDismissed", "", "onMessageWasDisplayed", "onMessageWillDismiss", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
    public interface InAppMessageViewListener {
        void onMessageWasDismissed();

        void onMessageWasDisplayed();

        void onMessageWillDismiss();
    }

    /* JADX INFO: compiled from: InAppMessageView.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[WebViewManager.Position.values().length];
            iArr[WebViewManager.Position.TOP_BANNER.ordinal()] = 1;
            iArr[WebViewManager.Position.BOTTOM_BANNER.ordinal()] = 2;
            iArr[WebViewManager.Position.CENTER_MODAL.ordinal()] = 3;
            iArr[WebViewManager.Position.FULL_SCREEN.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.display.impl.InAppMessageView$delayShowUntilAvailable$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppMessageView.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.display.impl.InAppMessageView", f = "InAppMessageView.kt", i = {1, 1}, l = {440, 444, 445}, m = "delayShowUntilAvailable", n = {"this", "currentActivity"}, s = {"L$0", "L$1"})
    static final class C00611 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C00611(Continuation<? super C00611> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InAppMessageView.this.delayShowUntilAvailable(null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.display.impl.InAppMessageView$startDismissTimerIfNeeded$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppMessageView.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.display.impl.InAppMessageView", f = "InAppMessageView.kt", i = {0, 1}, l = {418, 429}, m = "startDismissTimerIfNeeded", n = {"this", "this"}, s = {"L$0", "L$0"})
    static final class C00641 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C00641(Continuation<? super C00641> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InAppMessageView.this.startDismissTimerIfNeeded((Continuation) this);
        }
    }

    public InAppMessageView(WebView webView, InAppMessageContent messageContent, boolean disableDragDismiss, boolean hideGrayOverlay) {
        double dDoubleValue;
        Intrinsics.checkNotNullParameter(messageContent, "messageContent");
        this.webView = webView;
        this.messageContent = messageContent;
        this.disableDragDismiss = disableDragDismiss;
        this.hideGrayOverlay = hideGrayOverlay;
        this.pageWidth = -1;
        this.pageHeight = this.messageContent.getPageHeight();
        this.marginPxSizeLeft = ViewUtils.INSTANCE.dpToPx(24);
        this.marginPxSizeRight = ViewUtils.INSTANCE.dpToPx(24);
        this.marginPxSizeTop = ViewUtils.INSTANCE.dpToPx(24);
        this.marginPxSizeBottom = ViewUtils.INSTANCE.dpToPx(24);
        WebViewManager.Position displayLocation = this.messageContent.getDisplayLocation();
        Intrinsics.checkNotNull(displayLocation);
        this.displayPosition = displayLocation;
        if (this.messageContent.getDisplayDuration() == null) {
            dDoubleValue = 0.0d;
        } else {
            Double displayDuration = this.messageContent.getDisplayDuration();
            Intrinsics.checkNotNull(displayDuration);
            dDoubleValue = displayDuration.doubleValue();
        }
        this.displayDuration = dDoubleValue;
        this.hasBackground = !this.displayPosition.isBanner();
        setMarginsFromContent(this.messageContent);
    }

    public final WebViewManager.Position getDisplayPosition() {
        return this.displayPosition;
    }

    public final boolean isDragging() {
        return this.isDragging;
    }

    private final void setMarginsFromContent(InAppMessageContent content) {
        this.marginPxSizeTop = content.getUseHeightMargin() ? ViewUtils.INSTANCE.dpToPx(24) : 0;
        this.marginPxSizeBottom = content.getUseHeightMargin() ? ViewUtils.INSTANCE.dpToPx(24) : 0;
        this.marginPxSizeLeft = content.getUseWidthMargin() ? ViewUtils.INSTANCE.dpToPx(24) : 0;
        this.marginPxSizeRight = content.getUseWidthMargin() ? ViewUtils.INSTANCE.dpToPx(24) : 0;
    }

    public final void setWebView(WebView webView) {
        Intrinsics.checkNotNullParameter(webView, "webView");
        this.webView = webView;
        WebView webView2 = this.webView;
        if (webView2 != null) {
            webView2.setBackgroundColor(0);
        }
    }

    public final void setMessageController(InAppMessageViewListener messageController) {
        this.messageController = messageController;
    }

    public final Object showView(Activity activity, Continuation<? super Unit> continuation) {
        Object objDelayShowUntilAvailable = delayShowUntilAvailable(activity, continuation);
        return objDelayShowUntilAvailable == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDelayShowUntilAvailable : Unit.INSTANCE;
    }

    public final Object checkIfShouldDismiss(Continuation<? super Unit> continuation) {
        if (!this.shouldDismissWhenActive) {
            return Unit.INSTANCE;
        }
        this.shouldDismissWhenActive = false;
        Object objFinishAfterDelay = finishAfterDelay(continuation);
        return objFinishAfterDelay == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objFinishAfterDelay : Unit.INSTANCE;
    }

    public final Object updateHeight(int pageHeight, Continuation<? super Unit> continuation) {
        this.pageHeight = pageHeight;
        Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new C00652(pageHeight, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.display.impl.InAppMessageView$updateHeight$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppMessageView.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.display.impl.InAppMessageView$updateHeight$2", f = "InAppMessageView.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C00652 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $pageHeight;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00652(int i, Continuation<? super C00652> continuation) {
            super(2, continuation);
            this.$pageHeight = i;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return InAppMessageView.this.new C00652(this.$pageHeight, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    if (InAppMessageView.this.webView != null) {
                        WebView webView = InAppMessageView.this.webView;
                        Intrinsics.checkNotNull(webView);
                        ViewGroup.LayoutParams layoutParams = webView.getLayoutParams();
                        if (layoutParams == null) {
                            Logging.warn$default("WebView height update skipped because of null layoutParams, new height will be used once it is displayed.", null, 2, null);
                            return Unit.INSTANCE;
                        }
                        layoutParams.height = this.$pageHeight;
                        WebView webView2 = InAppMessageView.this.webView;
                        Intrinsics.checkNotNull(webView2);
                        webView2.setLayoutParams(layoutParams);
                        if (InAppMessageView.this.draggableRelativeLayout != null) {
                            DraggableRelativeLayout draggableRelativeLayout = InAppMessageView.this.draggableRelativeLayout;
                            Intrinsics.checkNotNull(draggableRelativeLayout);
                            draggableRelativeLayout.setParams(InAppMessageView.this.createDraggableLayoutParams(this.$pageHeight, InAppMessageView.this.getDisplayPosition(), InAppMessageView.this.disableDragDismiss));
                        }
                        return Unit.INSTANCE;
                    }
                    Logging.warn$default("WebView height update skipped, new height will be used once it is displayed.", null, 2, null);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final Object showInAppMessageView(Activity currentActivity, Continuation<? super Unit> continuation) {
        this.currentActivity = currentActivity;
        RelativeLayout.LayoutParams webViewLayoutParams = new RelativeLayout.LayoutParams(-1, this.pageHeight);
        webViewLayoutParams.addRule(13);
        RelativeLayout.LayoutParams relativeLayoutParams = this.hasBackground ? createParentRelativeLayoutParams() : null;
        Object objShowDraggableView = showDraggableView(this.displayPosition, webViewLayoutParams, relativeLayoutParams, createDraggableLayoutParams(this.pageHeight, this.displayPosition, this.disableDragDismiss), continuation);
        return objShowDraggableView == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objShowDraggableView : Unit.INSTANCE;
    }

    private final int getDisplayYSize() {
        ViewUtils viewUtils = ViewUtils.INSTANCE;
        Activity activity = this.currentActivity;
        Intrinsics.checkNotNull(activity);
        return viewUtils.getWindowHeight(activity);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final RelativeLayout.LayoutParams createParentRelativeLayoutParams() {
        RelativeLayout.LayoutParams relativeLayoutParams = new RelativeLayout.LayoutParams(this.pageWidth, -1);
        switch (WhenMappings.$EnumSwitchMapping$0[this.displayPosition.ordinal()]) {
            case 1:
                relativeLayoutParams.addRule(10);
                relativeLayoutParams.addRule(14);
                return relativeLayoutParams;
            case 2:
                relativeLayoutParams.addRule(12);
                relativeLayoutParams.addRule(14);
                return relativeLayoutParams;
            case 3:
            case 4:
                relativeLayoutParams.addRule(13);
                return relativeLayoutParams;
            default:
                return relativeLayoutParams;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final DraggableRelativeLayout.Params createDraggableLayoutParams(int pageHeight, WebViewManager.Position displayLocation, boolean disableDragging) {
        DraggableRelativeLayout.Params draggableParams = new DraggableRelativeLayout.Params();
        draggableParams.setMaxXPos(this.marginPxSizeRight);
        draggableParams.setMaxYPos(this.marginPxSizeTop);
        draggableParams.setDraggingDisabled(disableDragging);
        draggableParams.setMessageHeight(pageHeight);
        draggableParams.setHeight(getDisplayYSize());
        switch (WhenMappings.$EnumSwitchMapping$0[displayLocation.ordinal()]) {
            case 1:
                draggableParams.setDragThresholdY(this.marginPxSizeTop - DRAG_THRESHOLD_PX_SIZE);
                break;
            case 2:
                draggableParams.setPosY(getDisplayYSize() - pageHeight);
                draggableParams.setDragThresholdY(this.marginPxSizeBottom + DRAG_THRESHOLD_PX_SIZE);
                break;
            case 3:
                int y = (getDisplayYSize() / 2) - (pageHeight / 2);
                draggableParams.setDragThresholdY(DRAG_THRESHOLD_PX_SIZE + y);
                draggableParams.setMaxYPos(y);
                draggableParams.setPosY(y);
                break;
            case 4:
                InAppMessageView $this$createDraggableLayoutParams_u24lambda_u2d0 = this;
                int pageHeight2 = $this$createDraggableLayoutParams_u24lambda_u2d0.getDisplayYSize() - ($this$createDraggableLayoutParams_u24lambda_u2d0.marginPxSizeBottom + $this$createDraggableLayoutParams_u24lambda_u2d0.marginPxSizeTop);
                draggableParams.setMessageHeight(pageHeight2);
                int y2 = (getDisplayYSize() / 2) - (pageHeight2 / 2);
                draggableParams.setDragThresholdY(DRAG_THRESHOLD_PX_SIZE + y2);
                draggableParams.setMaxYPos(y2);
                draggableParams.setPosY(y2);
                break;
        }
        draggableParams.setDragDirection(displayLocation == WebViewManager.Position.TOP_BANNER ? 0 : 1);
        return draggableParams;
    }

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.display.impl.InAppMessageView$showDraggableView$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InAppMessageView.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.display.impl.InAppMessageView$showDraggableView$2", f = "InAppMessageView.kt", i = {}, l = {263}, m = "invokeSuspend", n = {}, s = {})
    static final class C00632 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ WebViewManager.Position $displayLocation;
        final /* synthetic */ RelativeLayout.LayoutParams $draggableRelativeLayoutParams;
        final /* synthetic */ RelativeLayout.LayoutParams $relativeLayoutParams;
        final /* synthetic */ DraggableRelativeLayout.Params $webViewLayoutParams;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00632(RelativeLayout.LayoutParams layoutParams, RelativeLayout.LayoutParams layoutParams2, DraggableRelativeLayout.Params params, WebViewManager.Position position, Continuation<? super C00632> continuation) {
            super(2, continuation);
            this.$relativeLayoutParams = layoutParams;
            this.$draggableRelativeLayoutParams = layoutParams2;
            this.$webViewLayoutParams = params;
            this.$displayLocation = position;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return InAppMessageView.this.new C00632(this.$relativeLayoutParams, this.$draggableRelativeLayoutParams, this.$webViewLayoutParams, this.$displayLocation, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
        public final Object invokeSuspend(Object $result) throws NoWhenBranchMatchedException {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    if (InAppMessageView.this.webView != null) {
                        WebView webView = InAppMessageView.this.webView;
                        Intrinsics.checkNotNull(webView);
                        webView.setLayoutParams(this.$relativeLayoutParams);
                        InAppMessageView inAppMessageView = InAppMessageView.this;
                        Activity activity = InAppMessageView.this.currentActivity;
                        Intrinsics.checkNotNull(activity);
                        inAppMessageView.setUpDraggableLayout(activity, this.$draggableRelativeLayoutParams, this.$webViewLayoutParams);
                        InAppMessageView inAppMessageView2 = InAppMessageView.this;
                        Activity activity2 = InAppMessageView.this.currentActivity;
                        Intrinsics.checkNotNull(activity2);
                        inAppMessageView2.setUpParentRelativeLayout(activity2);
                        InAppMessageView inAppMessageView3 = InAppMessageView.this;
                        RelativeLayout relativeLayout = InAppMessageView.this.parentRelativeLayout;
                        Intrinsics.checkNotNull(relativeLayout);
                        inAppMessageView3.createPopupWindow(relativeLayout);
                        if (InAppMessageView.this.messageController != null) {
                            InAppMessageView inAppMessageView4 = InAppMessageView.this;
                            WebViewManager.Position position = this.$displayLocation;
                            DraggableRelativeLayout draggableRelativeLayout = InAppMessageView.this.draggableRelativeLayout;
                            Intrinsics.checkNotNull(draggableRelativeLayout);
                            RelativeLayout relativeLayout2 = InAppMessageView.this.parentRelativeLayout;
                            Intrinsics.checkNotNull(relativeLayout2);
                            inAppMessageView4.animateInAppMessage(position, draggableRelativeLayout, relativeLayout2);
                        }
                        this.label = 1;
                        if (InAppMessageView.this.startDismissTimerIfNeeded((Continuation) this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        return Unit.INSTANCE;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object showDraggableView(WebViewManager.Position displayLocation, RelativeLayout.LayoutParams relativeLayoutParams, RelativeLayout.LayoutParams draggableRelativeLayoutParams, DraggableRelativeLayout.Params webViewLayoutParams, Continuation<? super Unit> continuation) {
        Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new C00632(relativeLayoutParams, draggableRelativeLayoutParams, webViewLayoutParams, displayLocation, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final void createPopupWindow(RelativeLayout parentRelativeLayout) throws NoWhenBranchMatchedException {
        this.popupWindow = new PopupWindow((View) parentRelativeLayout, this.hasBackground ? -1 : this.pageWidth, this.hasBackground ? -1 : -2, false);
        PopupWindow popupWindow = this.popupWindow;
        if (popupWindow != null) {
            popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        }
        PopupWindow popupWindow2 = this.popupWindow;
        int i = 1;
        if (popupWindow2 != null) {
            popupWindow2.setTouchable(true);
        }
        PopupWindow popupWindow3 = this.popupWindow;
        if (popupWindow3 != null) {
            popupWindow3.setFocusable(!this.displayPosition.isBanner());
        }
        PopupWindow popupWindow4 = this.popupWindow;
        if (popupWindow4 != null) {
            popupWindow4.setClippingEnabled(false);
        }
        int gravity = 0;
        if (!this.hasBackground) {
            switch (WhenMappings.$EnumSwitchMapping$0[this.displayPosition.ordinal()]) {
                case 1:
                    i = 49;
                    gravity = i;
                    break;
                case 2:
                    i = 81;
                    gravity = i;
                    break;
                case 3:
                case 4:
                    gravity = i;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
        int displayType = this.messageContent.isFullBleed() ? 1000 : 1003;
        PopupWindow popupWindow5 = this.popupWindow;
        Intrinsics.checkNotNull(popupWindow5);
        PopupWindowCompat.setWindowLayoutType(popupWindow5, displayType);
        PopupWindow popupWindow6 = this.popupWindow;
        if (popupWindow6 != null) {
            Activity activity = this.currentActivity;
            Intrinsics.checkNotNull(activity);
            popupWindow6.showAtLocation(activity.getWindow().getDecorView().getRootView(), gravity, 0, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setUpParentRelativeLayout(Context context) {
        this.parentRelativeLayout = new RelativeLayout(context);
        RelativeLayout relativeLayout = this.parentRelativeLayout;
        Intrinsics.checkNotNull(relativeLayout);
        relativeLayout.setBackgroundDrawable(new ColorDrawable(0));
        RelativeLayout relativeLayout2 = this.parentRelativeLayout;
        Intrinsics.checkNotNull(relativeLayout2);
        relativeLayout2.setClipChildren(false);
        RelativeLayout relativeLayout3 = this.parentRelativeLayout;
        Intrinsics.checkNotNull(relativeLayout3);
        relativeLayout3.setClipToPadding(false);
        RelativeLayout relativeLayout4 = this.parentRelativeLayout;
        Intrinsics.checkNotNull(relativeLayout4);
        relativeLayout4.addView(this.draggableRelativeLayout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setUpDraggableLayout(Context context, RelativeLayout.LayoutParams relativeLayoutParams, DraggableRelativeLayout.Params draggableParams) {
        this.draggableRelativeLayout = new DraggableRelativeLayout(context);
        if (relativeLayoutParams != null) {
            DraggableRelativeLayout draggableRelativeLayout = this.draggableRelativeLayout;
            Intrinsics.checkNotNull(draggableRelativeLayout);
            draggableRelativeLayout.setLayoutParams(relativeLayoutParams);
        }
        DraggableRelativeLayout draggableRelativeLayout2 = this.draggableRelativeLayout;
        Intrinsics.checkNotNull(draggableRelativeLayout2);
        draggableRelativeLayout2.setParams(draggableParams);
        DraggableRelativeLayout draggableRelativeLayout3 = this.draggableRelativeLayout;
        Intrinsics.checkNotNull(draggableRelativeLayout3);
        draggableRelativeLayout3.setListener(new DraggableRelativeLayout.DraggableListener() { // from class: com.onesignal.inAppMessages.internal.display.impl.InAppMessageView.setUpDraggableLayout.1
            @Override // com.onesignal.inAppMessages.internal.display.impl.DraggableRelativeLayout.DraggableListener
            public void onDismiss() {
                if (InAppMessageView.this.messageController != null) {
                    InAppMessageViewListener inAppMessageViewListener = InAppMessageView.this.messageController;
                    Intrinsics.checkNotNull(inAppMessageViewListener);
                    inAppMessageViewListener.onMessageWillDismiss();
                }
                ThreadUtilsKt.suspendifyOnThread$default(0, new InAppMessageView$setUpDraggableLayout$1$onDismiss$1(InAppMessageView.this, null), 1, null);
            }

            @Override // com.onesignal.inAppMessages.internal.display.impl.DraggableRelativeLayout.DraggableListener
            public void onDragStart() {
                InAppMessageView.this.isDragging = true;
            }

            @Override // com.onesignal.inAppMessages.internal.display.impl.DraggableRelativeLayout.DraggableListener
            public void onDragEnd() {
                InAppMessageView.this.isDragging = false;
            }
        });
        WebView webView = this.webView;
        Intrinsics.checkNotNull(webView);
        if (webView.getParent() != null) {
            WebView webView2 = this.webView;
            Intrinsics.checkNotNull(webView2);
            ViewParent parent = webView2.getParent();
            Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.ViewGroup");
            ((ViewGroup) parent).removeAllViews();
        }
        View viewCreateCardView = createCardView(context);
        viewCreateCardView.setTag(IN_APP_MESSAGE_CARD_VIEW_TAG);
        viewCreateCardView.addView(this.webView);
        DraggableRelativeLayout draggableRelativeLayout4 = this.draggableRelativeLayout;
        Intrinsics.checkNotNull(draggableRelativeLayout4);
        draggableRelativeLayout4.setPadding(this.marginPxSizeLeft, this.marginPxSizeTop, this.marginPxSizeRight, this.marginPxSizeBottom);
        DraggableRelativeLayout draggableRelativeLayout5 = this.draggableRelativeLayout;
        Intrinsics.checkNotNull(draggableRelativeLayout5);
        draggableRelativeLayout5.setClipChildren(false);
        DraggableRelativeLayout draggableRelativeLayout6 = this.draggableRelativeLayout;
        Intrinsics.checkNotNull(draggableRelativeLayout6);
        draggableRelativeLayout6.setClipToPadding(false);
        DraggableRelativeLayout draggableRelativeLayout7 = this.draggableRelativeLayout;
        Intrinsics.checkNotNull(draggableRelativeLayout7);
        draggableRelativeLayout7.addView(viewCreateCardView);
    }

    private final CardView createCardView(Context context) {
        CardView cardView = new CardView(context);
        int height = this.displayPosition == WebViewManager.Position.FULL_SCREEN ? -1 : -2;
        RelativeLayout.LayoutParams cardViewLayoutParams = new RelativeLayout.LayoutParams(-1, height);
        cardViewLayoutParams.addRule(13);
        cardView.setLayoutParams(cardViewLayoutParams);
        if (getHideDropShadow(context)) {
            cardView.setCardElevation(0.0f);
        } else {
            cardView.setCardElevation(ViewUtils.INSTANCE.dpToPx(5));
        }
        cardView.setRadius(ViewUtils.INSTANCE.dpToPx(8));
        cardView.setClipChildren(false);
        cardView.setClipToPadding(false);
        cardView.setPreventCornerOverlap(false);
        cardView.setCardBackgroundColor(0);
        return cardView;
    }

    private final boolean getHideDropShadow(Context context) {
        return AndroidUtils.INSTANCE.getManifestMetaBoolean(context, "com.onesignal.inAppMessageHideDropShadow");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object startDismissTimerIfNeeded(kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r9 = this;
            boolean r0 = r10 instanceof com.onesignal.inAppMessages.internal.display.impl.InAppMessageView.C00641
            if (r0 == 0) goto L14
            r0 = r10
            com.onesignal.inAppMessages.internal.display.impl.InAppMessageView$startDismissTimerIfNeeded$1 r0 = (com.onesignal.inAppMessages.internal.display.impl.InAppMessageView.C00641) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            com.onesignal.inAppMessages.internal.display.impl.InAppMessageView$startDismissTimerIfNeeded$1 r0 = new com.onesignal.inAppMessages.internal.display.impl.InAppMessageView$startDismissTimerIfNeeded$1
            r0.<init>(r10)
        L19:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            switch(r2) {
                case 0: goto L3e;
                case 1: goto L36;
                case 2: goto L2e;
                default: goto L26;
            }
        L26:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L2e:
            java.lang.Object r1 = r0.L$0
            com.onesignal.inAppMessages.internal.display.impl.InAppMessageView r1 = (com.onesignal.inAppMessages.internal.display.impl.InAppMessageView) r1
            kotlin.ResultKt.throwOnFailure(r10)
            goto L89
        L36:
            java.lang.Object r2 = r0.L$0
            com.onesignal.inAppMessages.internal.display.impl.InAppMessageView r2 = (com.onesignal.inAppMessages.internal.display.impl.InAppMessageView) r2
            kotlin.ResultKt.throwOnFailure(r10)
            goto L63
        L3e:
            kotlin.ResultKt.throwOnFailure(r10)
            r2 = r9
            double r5 = r2.displayDuration
            r7 = 0
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 <= 0) goto L91
            boolean r5 = r2.isDismissTimerSet
            if (r5 == 0) goto L4f
            goto L91
        L4f:
            r2.isDismissTimerSet = r4
            double r5 = r2.displayDuration
            long r5 = (long) r5
            r7 = 1000(0x3e8, float:1.401E-42)
            long r7 = (long) r7
            long r5 = r5 * r7
            r0.L$0 = r2
            r0.label = r4
            java.lang.Object r5 = kotlinx.coroutines.DelayKt.delay(r5, r0)
            if (r5 != r1) goto L63
            return r1
        L63:
            boolean r5 = r2.cancelDismissTimer
            if (r5 == 0) goto L6c
            r2.cancelDismissTimer = r3
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        L6c:
            com.onesignal.inAppMessages.internal.display.impl.InAppMessageView$InAppMessageViewListener r5 = r2.messageController
            if (r5 == 0) goto L78
            com.onesignal.inAppMessages.internal.display.impl.InAppMessageView$InAppMessageViewListener r5 = r2.messageController
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
            r5.onMessageWillDismiss()
        L78:
            android.app.Activity r5 = r2.currentActivity
            if (r5 == 0) goto L8c
            r0.L$0 = r2
            r4 = 2
            r0.label = r4
            java.lang.Object r4 = r2.dismissAndAwaitNextMessage(r0)
            if (r4 != r1) goto L88
            return r1
        L88:
            r1 = r2
        L89:
            r1.isDismissTimerSet = r3
            goto L8e
        L8c:
            r2.shouldDismissWhenActive = r4
        L8e:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        L91:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.inAppMessages.internal.display.impl.InAppMessageView.startDismissTimerIfNeeded(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:29:0x007b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object delayShowUntilAvailable(android.app.Activity r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.onesignal.inAppMessages.internal.display.impl.InAppMessageView.C00611
            if (r0 == 0) goto L14
            r0 = r7
            com.onesignal.inAppMessages.internal.display.impl.InAppMessageView$delayShowUntilAvailable$1 r0 = (com.onesignal.inAppMessages.internal.display.impl.InAppMessageView.C00611) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            com.onesignal.inAppMessages.internal.display.impl.InAppMessageView$delayShowUntilAvailable$1 r0 = new com.onesignal.inAppMessages.internal.display.impl.InAppMessageView$delayShowUntilAvailable$1
            r0.<init>(r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L40;
                case 1: goto L3c;
                case 2: goto L30;
                case 3: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2c:
            kotlin.ResultKt.throwOnFailure(r7)
            goto L7c
        L30:
            java.lang.Object r6 = r0.L$1
            android.app.Activity r6 = (android.app.Activity) r6
            java.lang.Object r2 = r0.L$0
            com.onesignal.inAppMessages.internal.display.impl.InAppMessageView r2 = (com.onesignal.inAppMessages.internal.display.impl.InAppMessageView) r2
            kotlin.ResultKt.throwOnFailure(r7)
            goto L6d
        L3c:
            kotlin.ResultKt.throwOnFailure(r7)
            goto L5a
        L40:
            kotlin.ResultKt.throwOnFailure(r7)
            r2 = r5
            com.onesignal.common.AndroidUtils r3 = com.onesignal.common.AndroidUtils.INSTANCE
            boolean r3 = r3.isActivityFullyReady(r6)
            if (r3 == 0) goto L5d
            android.widget.RelativeLayout r3 = r2.parentRelativeLayout
            if (r3 != 0) goto L5d
            r3 = 1
            r0.label = r3
            java.lang.Object r6 = r2.showInAppMessageView(r6, r0)
            if (r6 != r1) goto L5a
            return r1
        L5a:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L5d:
            r0.L$0 = r2
            r0.L$1 = r6
            r3 = 2
            r0.label = r3
            r3 = 200(0xc8, double:9.9E-322)
            java.lang.Object r3 = kotlinx.coroutines.DelayKt.delay(r3, r0)
            if (r3 != r1) goto L6d
            return r1
        L6d:
            r3 = 0
            r0.L$0 = r3
            r0.L$1 = r3
            r3 = 3
            r0.label = r3
            java.lang.Object r6 = r2.delayShowUntilAvailable(r6, r0)
            if (r6 != r1) goto L7c
            return r1
        L7c:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.inAppMessages.internal.display.impl.InAppMessageView.delayShowUntilAvailable(android.app.Activity, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object dismissAndAwaitNextMessage(Continuation<? super Unit> continuation) {
        if (this.draggableRelativeLayout == null) {
            Logging.error$default("No host presenter to trigger dismiss animation, counting as dismissed already", null, 2, null);
            dereferenceViews();
            return Unit.INSTANCE;
        }
        DraggableRelativeLayout draggableRelativeLayout = this.draggableRelativeLayout;
        Intrinsics.checkNotNull(draggableRelativeLayout);
        draggableRelativeLayout.dismiss();
        Object objFinishAfterDelay = finishAfterDelay(continuation);
        return objFinishAfterDelay == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objFinishAfterDelay : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.onesignal.inAppMessages.internal.display.impl.InAppMessageView$finishAfterDelay$2, reason: invalid class name */
    /* JADX INFO: compiled from: InAppMessageView.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.inAppMessages.internal.display.impl.InAppMessageView$finishAfterDelay$2", f = "InAppMessageView.kt", i = {}, l = {467, 469}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return InAppMessageView.this.new AnonymousClass2(continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            AnonymousClass2 anonymousClass2;
            AnonymousClass2 anonymousClass22;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    anonymousClass2 = this;
                    anonymousClass2.label = 1;
                    if (DelayKt.delay(600L, (Continuation) anonymousClass2) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    if (InAppMessageView.this.hasBackground || InAppMessageView.this.parentRelativeLayout == null) {
                        InAppMessageView.this.cleanupViewsAfterDismiss();
                        return Unit.INSTANCE;
                    }
                    InAppMessageView inAppMessageView = InAppMessageView.this;
                    RelativeLayout relativeLayout = InAppMessageView.this.parentRelativeLayout;
                    Intrinsics.checkNotNull(relativeLayout);
                    anonymousClass2.label = 2;
                    if (inAppMessageView.animateAndDismissLayout(relativeLayout, (Continuation) anonymousClass2) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    anonymousClass22 = anonymousClass2;
                    return Unit.INSTANCE;
                case 1:
                    anonymousClass2 = this;
                    ResultKt.throwOnFailure($result);
                    if (InAppMessageView.this.hasBackground) {
                        break;
                    }
                    InAppMessageView.this.cleanupViewsAfterDismiss();
                    return Unit.INSTANCE;
                case 2:
                    anonymousClass22 = this;
                    ResultKt.throwOnFailure($result);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object finishAfterDelay(Continuation<? super Unit> continuation) {
        Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass2(null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cleanupViewsAfterDismiss() {
        removeAllViews();
        InAppMessageViewListener inAppMessageViewListener = this.messageController;
        if (inAppMessageViewListener != null) {
            inAppMessageViewListener.onMessageWasDismissed();
        }
    }

    public final void removeAllViews() {
        Logging.debug$default("InAppMessageView.removeAllViews()", null, 2, null);
        if (this.isDismissTimerSet) {
            this.cancelDismissTimer = true;
        }
        if (this.draggableRelativeLayout != null) {
            DraggableRelativeLayout draggableRelativeLayout = this.draggableRelativeLayout;
            Intrinsics.checkNotNull(draggableRelativeLayout);
            draggableRelativeLayout.removeAllViews();
        }
        if (this.popupWindow != null) {
            PopupWindow popupWindow = this.popupWindow;
            Intrinsics.checkNotNull(popupWindow);
            popupWindow.dismiss();
        }
        dereferenceViews();
    }

    private final void dereferenceViews() {
        this.parentRelativeLayout = null;
        this.draggableRelativeLayout = null;
        this.webView = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void animateInAppMessage(WebViewManager.Position displayLocation, View messageView, View backgroundView) {
        Intrinsics.checkNotNull(messageView);
        CardView messageViewCardView = (CardView) messageView.findViewWithTag(IN_APP_MESSAGE_CARD_VIEW_TAG);
        Intrinsics.checkNotNullExpressionValue(messageViewCardView, "messageViewCardView");
        Animation.AnimationListener cardViewAnimCallback = createAnimationListener(messageViewCardView);
        switch (WhenMappings.$EnumSwitchMapping$0[displayLocation.ordinal()]) {
            case 1:
                WebView webView = this.webView;
                Intrinsics.checkNotNull(webView);
                animateTop((View) messageViewCardView, webView.getHeight(), cardViewAnimCallback);
                break;
            case 2:
                WebView webView2 = this.webView;
                Intrinsics.checkNotNull(webView2);
                animateBottom((View) messageViewCardView, webView2.getHeight(), cardViewAnimCallback);
                break;
            case 3:
            case 4:
                animateCenter(messageView, backgroundView, cardViewAnimCallback, null);
                break;
        }
    }

    private final Animation.AnimationListener createAnimationListener(final CardView messageViewCardView) {
        return new Animation.AnimationListener() { // from class: com.onesignal.inAppMessages.internal.display.impl.InAppMessageView.createAnimationListener.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                if (this.messageController != null) {
                    InAppMessageViewListener inAppMessageViewListener = this.messageController;
                    Intrinsics.checkNotNull(inAppMessageViewListener);
                    inAppMessageViewListener.onMessageWasDisplayed();
                }
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }
        };
    }

    private final void animateTop(View messageView, int height, Animation.AnimationListener cardViewAnimCallback) {
        OneSignalAnimate.INSTANCE.animateViewByTranslation(messageView, (-height) - this.marginPxSizeTop, 0.0f, 1000, new OneSignalBounceInterpolator(0.1d, 8.0d), cardViewAnimCallback).start();
    }

    private final void animateBottom(View messageView, int height, Animation.AnimationListener cardViewAnimCallback) {
        OneSignalAnimate.INSTANCE.animateViewByTranslation(messageView, this.marginPxSizeBottom + height, 0.0f, 1000, new OneSignalBounceInterpolator(0.1d, 8.0d), cardViewAnimCallback).start();
    }

    private final void animateCenter(View messageView, View backgroundView, Animation.AnimationListener cardViewAnimCallback, Animator.AnimatorListener backgroundAnimCallback) {
        Animation messageAnimation = OneSignalAnimate.INSTANCE.animateViewSmallToLarge(messageView, 1000, new OneSignalBounceInterpolator(0.1d, 8.0d), cardViewAnimCallback);
        ValueAnimator backgroundAnimation = animateBackgroundColor(backgroundView, IN_APP_BACKGROUND_ANIMATION_DURATION_MS, 0, getOverlayColor(), backgroundAnimCallback);
        messageAnimation.start();
        backgroundAnimation.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object animateAndDismissLayout(View backgroundView, Continuation<? super Unit> continuation) {
        final Waiter waiter = new Waiter();
        Animator.AnimatorListener animCallback = new AnimatorListenerAdapter() { // from class: com.onesignal.inAppMessages.internal.display.impl.InAppMessageView$animateAndDismissLayout$animCallback$1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) throws Exception {
                Intrinsics.checkNotNullParameter(animation, "animation");
                this.this$0.cleanupViewsAfterDismiss();
                waiter.wake();
            }
        };
        animateBackgroundColor(backgroundView, IN_APP_BACKGROUND_ANIMATION_DURATION_MS, getOverlayColor(), 0, animCallback).start();
        Object objWaitForWake = waiter.waitForWake(continuation);
        return objWaitForWake == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWaitForWake : Unit.INSTANCE;
    }

    private final ValueAnimator animateBackgroundColor(View backgroundView, int duration, int startColor, int endColor, Animator.AnimatorListener animCallback) {
        return OneSignalAnimate.INSTANCE.animateViewColor(backgroundView, duration, startColor, endColor, animCallback);
    }

    public String toString() {
        return "InAppMessageView{currentActivity=" + this.currentActivity + ", pageWidth=" + this.pageWidth + ", pageHeight=" + this.pageHeight + ", displayDuration=" + this.displayDuration + ", hasBackground=" + this.hasBackground + ", shouldDismissWhenActive=" + this.shouldDismissWhenActive + ", isDragging=" + this.isDragging + ", disableDragDismiss=" + this.disableDragDismiss + ", displayLocation=" + this.displayPosition + ", webView=" + this.webView + '}';
    }

    private final int getOverlayColor() {
        if (this.hideGrayOverlay) {
            return 0;
        }
        return ACTIVITY_BACKGROUND_COLOR_FULL;
    }

    /* JADX INFO: compiled from: InAppMessageView.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/onesignal/inAppMessages/internal/display/impl/InAppMessageView$Companion;", "", "()V", "ACTIVITY_BACKGROUND_COLOR_EMPTY", "", "ACTIVITY_BACKGROUND_COLOR_FULL", "ACTIVITY_FINISH_AFTER_DISMISS_DELAY_MS", "ACTIVITY_INIT_DELAY", "DRAG_THRESHOLD_PX_SIZE", "IN_APP_BACKGROUND_ANIMATION_DURATION_MS", "IN_APP_BANNER_ANIMATION_DURATION_MS", "IN_APP_CENTER_ANIMATION_DURATION_MS", InAppMessageView.IN_APP_MESSAGE_CARD_VIEW_TAG, "", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
