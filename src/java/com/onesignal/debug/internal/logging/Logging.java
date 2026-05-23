package com.onesignal.debug.internal.logging;

import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import com.onesignal.common.threading.ThreadUtilsKt;
import com.onesignal.core.internal.application.IApplicationService;
import com.onesignal.core.internal.database.impl.OneSignalDbContract;
import com.onesignal.debug.LogLevel;
import com.primexop.webview.BuildConfig;
import java.io.PrintWriter;
import java.io.StringWriter;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Logging.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\fH\u0007J\u001c\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00042\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0007J\u001c\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00042\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0007J\u001c\u0010\u001f\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00042\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0007J\u001c\u0010 \u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00042\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0007J\u0018\u0010!\u001a\u00020\u001a2\u0006\u0010\u0018\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u0004H\u0007J\"\u0010!\u001a\u00020\u001a2\u0006\u0010\u0018\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u00042\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0007J\u001c\u0010\"\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00042\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0007J\u001c\u0010#\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00042\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR$\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\r\u0010\u0002\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0012\u001a\u00020\f8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0013\u0010\u0002\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0011¨\u0006$"}, d2 = {"Lcom/onesignal/debug/internal/logging/Logging;", "", "()V", "TAG", "", "applicationService", "Lcom/onesignal/core/internal/application/IApplicationService;", "getApplicationService", "()Lcom/onesignal/core/internal/application/IApplicationService;", "setApplicationService", "(Lcom/onesignal/core/internal/application/IApplicationService;)V", "logLevel", "Lcom/onesignal/debug/LogLevel;", "getLogLevel$annotations", "getLogLevel", "()Lcom/onesignal/debug/LogLevel;", "setLogLevel", "(Lcom/onesignal/debug/LogLevel;)V", "visualLogLevel", "getVisualLogLevel$annotations", "getVisualLogLevel", "setVisualLogLevel", "atLogLevel", "", "level", BuildConfig.BUILD_TYPE, "", OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, "throwable", "", "error", "fatal", "info", "log", "verbose", "warn", com.onesignal.core.BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class Logging {
    private static final String TAG = "OneSignal";
    private static IApplicationService applicationService;
    public static final Logging INSTANCE = new Logging();
    private static LogLevel logLevel = LogLevel.WARN;
    private static LogLevel visualLogLevel = LogLevel.NONE;

    /* JADX INFO: compiled from: Logging.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LogLevel.values().length];
            iArr[LogLevel.VERBOSE.ordinal()] = 1;
            iArr[LogLevel.DEBUG.ordinal()] = 2;
            iArr[LogLevel.INFO.ordinal()] = 3;
            iArr[LogLevel.WARN.ordinal()] = 4;
            iArr[LogLevel.ERROR.ordinal()] = 5;
            iArr[LogLevel.FATAL.ordinal()] = 6;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @JvmStatic
    public static /* synthetic */ void getLogLevel$annotations() {
    }

    @JvmStatic
    public static /* synthetic */ void getVisualLogLevel$annotations() {
    }

    private Logging() {
    }

    public final IApplicationService getApplicationService() {
        return applicationService;
    }

    public final void setApplicationService(IApplicationService iApplicationService) {
        applicationService = iApplicationService;
    }

    public static final LogLevel getLogLevel() {
        return logLevel;
    }

    public static final void setLogLevel(LogLevel logLevel2) {
        Intrinsics.checkNotNullParameter(logLevel2, "<set-?>");
        logLevel = logLevel2;
    }

    public static final LogLevel getVisualLogLevel() {
        return visualLogLevel;
    }

    public static final void setVisualLogLevel(LogLevel logLevel2) {
        Intrinsics.checkNotNullParameter(logLevel2, "<set-?>");
        visualLogLevel = logLevel2;
    }

    @JvmStatic
    public static final boolean atLogLevel(LogLevel level) {
        Intrinsics.checkNotNullParameter(level, "level");
        return level.compareTo(visualLogLevel) < 1 || level.compareTo(logLevel) < 1;
    }

    public static /* synthetic */ void verbose$default(String str, Throwable th, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        verbose(str, th);
    }

    @JvmStatic
    public static final void verbose(String message, Throwable throwable) {
        Intrinsics.checkNotNullParameter(message, OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE);
        log(LogLevel.VERBOSE, message, throwable);
    }

    public static /* synthetic */ void debug$default(String str, Throwable th, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        debug(str, th);
    }

    @JvmStatic
    public static final void debug(String message, Throwable throwable) {
        Intrinsics.checkNotNullParameter(message, OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE);
        log(LogLevel.DEBUG, message, throwable);
    }

    public static /* synthetic */ void info$default(String str, Throwable th, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        info(str, th);
    }

    @JvmStatic
    public static final void info(String message, Throwable throwable) {
        Intrinsics.checkNotNullParameter(message, OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE);
        log(LogLevel.INFO, message, throwable);
    }

    public static /* synthetic */ void warn$default(String str, Throwable th, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        warn(str, th);
    }

    @JvmStatic
    public static final void warn(String message, Throwable throwable) {
        Intrinsics.checkNotNullParameter(message, OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE);
        log(LogLevel.WARN, message, throwable);
    }

    public static /* synthetic */ void error$default(String str, Throwable th, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        error(str, th);
    }

    @JvmStatic
    public static final void error(String message, Throwable throwable) {
        Intrinsics.checkNotNullParameter(message, OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE);
        log(LogLevel.ERROR, message, throwable);
    }

    public static /* synthetic */ void fatal$default(String str, Throwable th, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        fatal(str, th);
    }

    @JvmStatic
    public static final void fatal(String message, Throwable throwable) {
        Intrinsics.checkNotNullParameter(message, OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE);
        log(LogLevel.FATAL, message, throwable);
    }

    @JvmStatic
    public static final void log(LogLevel level, String message) {
        Intrinsics.checkNotNullParameter(level, "level");
        Intrinsics.checkNotNullParameter(message, OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE);
        log(level, message, null);
    }

    @JvmStatic
    public static final void log(LogLevel level, String message, Throwable throwable) {
        Intrinsics.checkNotNullParameter(level, "level");
        Intrinsics.checkNotNullParameter(message, OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE);
        String fullMessage = '[' + Thread.currentThread().getName() + "] " + message;
        if (level.compareTo(logLevel) < 1) {
            switch (WhenMappings.$EnumSwitchMapping$0[level.ordinal()]) {
                case 1:
                    Log.v("OneSignal", fullMessage, throwable);
                    break;
                case 2:
                    Log.d("OneSignal", fullMessage, throwable);
                    break;
                case 3:
                    Log.i("OneSignal", fullMessage, throwable);
                    break;
                case 4:
                    Log.w("OneSignal", fullMessage, throwable);
                    break;
                case 5:
                case 6:
                    Log.e("OneSignal", message, throwable);
                    break;
            }
        }
        if (level.compareTo(visualLogLevel) < 1) {
            IApplicationService iApplicationService = applicationService;
            if ((iApplicationService != null ? iApplicationService.getCurrent() : null) != null) {
                try {
                    String fullMessage2 = StringsKt.trimIndent(message + '\n');
                    if (throwable != null) {
                        String fullMessage3 = fullMessage2 + throwable.getMessage();
                        StringWriter sw = new StringWriter();
                        PrintWriter pw = new PrintWriter(sw);
                        throwable.printStackTrace(pw);
                        fullMessage2 = fullMessage3 + sw;
                    }
                    ThreadUtilsKt.suspendifyOnMain(new AnonymousClass1(level, fullMessage2, null));
                } catch (Throwable t) {
                    Log.e("OneSignal", "Error showing logging message.", t);
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.onesignal.debug.internal.logging.Logging$log$1, reason: invalid class name */
    /* JADX INFO: compiled from: Logging.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.debug.internal.logging.Logging$log$1", f = "Logging.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ String $finalFullMessage;
        final /* synthetic */ LogLevel $level;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(LogLevel logLevel, String str, Continuation<? super AnonymousClass1> continuation) {
            super(1, continuation);
            this.$level = logLevel;
            this.$finalFullMessage = str;
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass1(this.$level, this.$finalFullMessage, continuation);
        }

        public final Object invoke(Continuation<? super Unit> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    IApplicationService applicationService = Logging.INSTANCE.getApplicationService();
                    Activity currentActivity = applicationService != null ? applicationService.getCurrent() : null;
                    if (currentActivity != null) {
                        new AlertDialog.Builder(currentActivity).setTitle(this.$level.toString()).setMessage(this.$finalFullMessage).show();
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }
}
