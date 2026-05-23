package com.onesignal.core.internal.operations;

import com.onesignal.core.BuildConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: IOperationRepo.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0019\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u0004H\u0086\b¨\u0006\u0005"}, d2 = {"containsInstanceOf", "", "T", "Lcom/onesignal/core/internal/operations/Operation;", "Lcom/onesignal/core/internal/operations/IOperationRepo;", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class IOperationRepoKt {
    public static final /* synthetic */ <T extends Operation> boolean containsInstanceOf(IOperationRepo $this$containsInstanceOf) {
        Intrinsics.checkNotNullParameter($this$containsInstanceOf, "<this>");
        Intrinsics.reifiedOperationMarker(4, "T");
        return $this$containsInstanceOf.containsInstanceOf(Reflection.getOrCreateKotlinClass(Operation.class));
    }
}
