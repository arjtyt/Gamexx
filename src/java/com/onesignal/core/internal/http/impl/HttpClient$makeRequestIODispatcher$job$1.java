package com.onesignal.core.internal.http.impl;

import com.onesignal.core.internal.http.HttpResponse;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import org.json.JSONObject;

/* JADX INFO: compiled from: HttpClient.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
@DebugMetadata(c = "com.onesignal.core.internal.http.impl.HttpClient$makeRequestIODispatcher$job$1", f = "HttpClient.kt", i = {0, 0}, l = {151}, m = "invokeSuspend", n = {"con", "httpResponse"}, s = {"L$0", "I$0"})
final class HttpClient$makeRequestIODispatcher$job$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ OptionalHeaders $headers;
    final /* synthetic */ JSONObject $jsonBody;
    final /* synthetic */ String $method;
    final /* synthetic */ Ref.ObjectRef<HttpResponse> $retVal;
    final /* synthetic */ int $timeout;
    final /* synthetic */ String $url;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ HttpClient this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HttpClient$makeRequestIODispatcher$job$1(HttpClient httpClient, String str, int i, JSONObject jSONObject, String str2, OptionalHeaders optionalHeaders, Ref.ObjectRef<HttpResponse> objectRef, Continuation<? super HttpClient$makeRequestIODispatcher$job$1> continuation) {
        super(2, continuation);
        this.this$0 = httpClient;
        this.$url = str;
        this.$timeout = i;
        this.$jsonBody = jSONObject;
        this.$method = str2;
        this.$headers = optionalHeaders;
        this.$retVal = objectRef;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HttpClient$makeRequestIODispatcher$job$1(this.this$0, this.$url, this.$timeout, this.$jsonBody, this.$method, this.$headers, this.$retVal, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:121:0x0461 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:134:0x047f A[Catch: all -> 0x04dc, TryCatch #4 {all -> 0x04dc, blocks: (B:132:0x047a, B:134:0x047f, B:137:0x0484, B:139:0x04c3, B:138:0x04a3), top: B:156:0x047a }] */
    /* JADX WARN: Removed duplicated region for block: B:138:0x04a3 A[Catch: all -> 0x04dc, TryCatch #4 {all -> 0x04dc, blocks: (B:132:0x047a, B:134:0x047f, B:137:0x0484, B:139:0x04c3, B:138:0x04a3), top: B:156:0x047a }] */
    /* JADX WARN: Removed duplicated region for block: B:141:0x04d5  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00ff A[Catch: all -> 0x046a, TryCatch #3 {all -> 0x046a, blocks: (B:33:0x00f2, B:35:0x00ff, B:36:0x0102, B:38:0x0106, B:39:0x0115, B:42:0x0138, B:43:0x015e, B:45:0x0162, B:50:0x016c, B:52:0x0198, B:53:0x01b3, B:55:0x01b7, B:58:0x01bf, B:59:0x01ce, B:61:0x01d2, B:64:0x01da, B:65:0x01e9, B:67:0x01ed, B:70:0x01f5, B:71:0x0204), top: B:154:0x00f2 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0106 A[Catch: all -> 0x046a, TryCatch #3 {all -> 0x046a, blocks: (B:33:0x00f2, B:35:0x00ff, B:36:0x0102, B:38:0x0106, B:39:0x0115, B:42:0x0138, B:43:0x015e, B:45:0x0162, B:50:0x016c, B:52:0x0198, B:53:0x01b3, B:55:0x01b7, B:58:0x01bf, B:59:0x01ce, B:61:0x01d2, B:64:0x01da, B:65:0x01e9, B:67:0x01ed, B:70:0x01f5, B:71:0x0204), top: B:154:0x00f2 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0138 A[Catch: all -> 0x046a, TRY_ENTER, TryCatch #3 {all -> 0x046a, blocks: (B:33:0x00f2, B:35:0x00ff, B:36:0x0102, B:38:0x0106, B:39:0x0115, B:42:0x0138, B:43:0x015e, B:45:0x0162, B:50:0x016c, B:52:0x0198, B:53:0x01b3, B:55:0x01b7, B:58:0x01bf, B:59:0x01ce, B:61:0x01d2, B:64:0x01da, B:65:0x01e9, B:67:0x01ed, B:70:0x01f5, B:71:0x0204), top: B:154:0x00f2 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0162 A[Catch: all -> 0x046a, TRY_LEAVE, TryCatch #3 {all -> 0x046a, blocks: (B:33:0x00f2, B:35:0x00ff, B:36:0x0102, B:38:0x0106, B:39:0x0115, B:42:0x0138, B:43:0x015e, B:45:0x0162, B:50:0x016c, B:52:0x0198, B:53:0x01b3, B:55:0x01b7, B:58:0x01bf, B:59:0x01ce, B:61:0x01d2, B:64:0x01da, B:65:0x01e9, B:67:0x01ed, B:70:0x01f5, B:71:0x0204), top: B:154:0x00f2 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x01b7 A[Catch: all -> 0x046a, TryCatch #3 {all -> 0x046a, blocks: (B:33:0x00f2, B:35:0x00ff, B:36:0x0102, B:38:0x0106, B:39:0x0115, B:42:0x0138, B:43:0x015e, B:45:0x0162, B:50:0x016c, B:52:0x0198, B:53:0x01b3, B:55:0x01b7, B:58:0x01bf, B:59:0x01ce, B:61:0x01d2, B:64:0x01da, B:65:0x01e9, B:67:0x01ed, B:70:0x01f5, B:71:0x0204), top: B:154:0x00f2 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01bf A[Catch: all -> 0x046a, TryCatch #3 {all -> 0x046a, blocks: (B:33:0x00f2, B:35:0x00ff, B:36:0x0102, B:38:0x0106, B:39:0x0115, B:42:0x0138, B:43:0x015e, B:45:0x0162, B:50:0x016c, B:52:0x0198, B:53:0x01b3, B:55:0x01b7, B:58:0x01bf, B:59:0x01ce, B:61:0x01d2, B:64:0x01da, B:65:0x01e9, B:67:0x01ed, B:70:0x01f5, B:71:0x0204), top: B:154:0x00f2 }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01d2 A[Catch: all -> 0x046a, TryCatch #3 {all -> 0x046a, blocks: (B:33:0x00f2, B:35:0x00ff, B:36:0x0102, B:38:0x0106, B:39:0x0115, B:42:0x0138, B:43:0x015e, B:45:0x0162, B:50:0x016c, B:52:0x0198, B:53:0x01b3, B:55:0x01b7, B:58:0x01bf, B:59:0x01ce, B:61:0x01d2, B:64:0x01da, B:65:0x01e9, B:67:0x01ed, B:70:0x01f5, B:71:0x0204), top: B:154:0x00f2 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01da A[Catch: all -> 0x046a, TryCatch #3 {all -> 0x046a, blocks: (B:33:0x00f2, B:35:0x00ff, B:36:0x0102, B:38:0x0106, B:39:0x0115, B:42:0x0138, B:43:0x015e, B:45:0x0162, B:50:0x016c, B:52:0x0198, B:53:0x01b3, B:55:0x01b7, B:58:0x01bf, B:59:0x01ce, B:61:0x01d2, B:64:0x01da, B:65:0x01e9, B:67:0x01ed, B:70:0x01f5, B:71:0x0204), top: B:154:0x00f2 }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01ed A[Catch: all -> 0x046a, TryCatch #3 {all -> 0x046a, blocks: (B:33:0x00f2, B:35:0x00ff, B:36:0x0102, B:38:0x0106, B:39:0x0115, B:42:0x0138, B:43:0x015e, B:45:0x0162, B:50:0x016c, B:52:0x0198, B:53:0x01b3, B:55:0x01b7, B:58:0x01bf, B:59:0x01ce, B:61:0x01d2, B:64:0x01da, B:65:0x01e9, B:67:0x01ed, B:70:0x01f5, B:71:0x0204), top: B:154:0x00f2 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01f5 A[Catch: all -> 0x046a, TryCatch #3 {all -> 0x046a, blocks: (B:33:0x00f2, B:35:0x00ff, B:36:0x0102, B:38:0x0106, B:39:0x0115, B:42:0x0138, B:43:0x015e, B:45:0x0162, B:50:0x016c, B:52:0x0198, B:53:0x01b3, B:55:0x01b7, B:58:0x01bf, B:59:0x01ce, B:61:0x01d2, B:64:0x01da, B:65:0x01e9, B:67:0x01ed, B:70:0x01f5, B:71:0x0204), top: B:154:0x00f2 }] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0221 A[Catch: all -> 0x0466, TryCatch #2 {all -> 0x0466, blocks: (B:73:0x0209, B:75:0x0221, B:76:0x0225, B:78:0x0233, B:81:0x024b, B:105:0x03a1, B:108:0x03ae, B:110:0x03d9, B:111:0x03dd, B:113:0x03e7, B:115:0x03f6, B:116:0x03fa, B:118:0x044f, B:117:0x0428, B:82:0x024f, B:84:0x0264, B:86:0x026a, B:89:0x028c, B:90:0x02c1, B:92:0x02d8, B:93:0x02dc, B:96:0x02ef, B:98:0x031e, B:101:0x0326, B:103:0x032e, B:104:0x0390), top: B:153:0x0209 }] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0233 A[Catch: all -> 0x0466, TRY_LEAVE, TryCatch #2 {all -> 0x0466, blocks: (B:73:0x0209, B:75:0x0221, B:76:0x0225, B:78:0x0233, B:81:0x024b, B:105:0x03a1, B:108:0x03ae, B:110:0x03d9, B:111:0x03dd, B:113:0x03e7, B:115:0x03f6, B:116:0x03fa, B:118:0x044f, B:117:0x0428, B:82:0x024f, B:84:0x0264, B:86:0x026a, B:89:0x028c, B:90:0x02c1, B:92:0x02d8, B:93:0x02dc, B:96:0x02ef, B:98:0x031e, B:101:0x0326, B:103:0x032e, B:104:0x0390), top: B:153:0x0209 }] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x024b A[Catch: all -> 0x0466, TRY_ENTER, TryCatch #2 {all -> 0x0466, blocks: (B:73:0x0209, B:75:0x0221, B:76:0x0225, B:78:0x0233, B:81:0x024b, B:105:0x03a1, B:108:0x03ae, B:110:0x03d9, B:111:0x03dd, B:113:0x03e7, B:115:0x03f6, B:116:0x03fa, B:118:0x044f, B:117:0x0428, B:82:0x024f, B:84:0x0264, B:86:0x026a, B:89:0x028c, B:90:0x02c1, B:92:0x02d8, B:93:0x02dc, B:96:0x02ef, B:98:0x031e, B:101:0x0326, B:103:0x032e, B:104:0x0390), top: B:153:0x0209 }] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x024f A[Catch: all -> 0x0466, TryCatch #2 {all -> 0x0466, blocks: (B:73:0x0209, B:75:0x0221, B:76:0x0225, B:78:0x0233, B:81:0x024b, B:105:0x03a1, B:108:0x03ae, B:110:0x03d9, B:111:0x03dd, B:113:0x03e7, B:115:0x03f6, B:116:0x03fa, B:118:0x044f, B:117:0x0428, B:82:0x024f, B:84:0x0264, B:86:0x026a, B:89:0x028c, B:90:0x02c1, B:92:0x02d8, B:93:0x02dc, B:96:0x02ef, B:98:0x031e, B:101:0x0326, B:103:0x032e, B:104:0x0390), top: B:153:0x0209 }] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x02c1 A[Catch: all -> 0x0466, TryCatch #2 {all -> 0x0466, blocks: (B:73:0x0209, B:75:0x0221, B:76:0x0225, B:78:0x0233, B:81:0x024b, B:105:0x03a1, B:108:0x03ae, B:110:0x03d9, B:111:0x03dd, B:113:0x03e7, B:115:0x03f6, B:116:0x03fa, B:118:0x044f, B:117:0x0428, B:82:0x024f, B:84:0x0264, B:86:0x026a, B:89:0x028c, B:90:0x02c1, B:92:0x02d8, B:93:0x02dc, B:96:0x02ef, B:98:0x031e, B:101:0x0326, B:103:0x032e, B:104:0x0390), top: B:153:0x0209 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r28) {
        /*
            Method dump skipped, instruction units count: 1278
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.core.internal.http.impl.HttpClient$makeRequestIODispatcher$job$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
