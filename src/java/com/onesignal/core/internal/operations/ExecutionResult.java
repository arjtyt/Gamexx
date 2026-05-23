package com.onesignal.core.internal.operations;

import com.onesignal.core.BuildConfig;
import kotlin.Metadata;

/* JADX INFO: compiled from: IOperationExecutor.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/onesignal/core/internal/operations/ExecutionResult;", "", "(Ljava/lang/String;I)V", "SUCCESS", "SUCCESS_STARTING_ONLY", "FAIL_RETRY", "FAIL_NORETRY", "FAIL_UNAUTHORIZED", "FAIL_CONFLICT", "FAIL_PAUSE_OPREPO", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public enum ExecutionResult {
    SUCCESS,
    SUCCESS_STARTING_ONLY,
    FAIL_RETRY,
    FAIL_NORETRY,
    FAIL_UNAUTHORIZED,
    FAIL_CONFLICT,
    FAIL_PAUSE_OPREPO
}
