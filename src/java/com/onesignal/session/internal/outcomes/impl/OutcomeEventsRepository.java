package com.onesignal.session.internal.outcomes.impl;

import android.content.ContentValues;
import com.onesignal.core.BuildConfig;
import com.onesignal.core.internal.database.ICursor;
import com.onesignal.core.internal.database.IDatabase;
import com.onesignal.core.internal.database.IDatabaseProvider;
import com.onesignal.core.internal.database.impl.OneSignalDbContract;
import com.onesignal.debug.internal.logging.Logging;
import com.onesignal.session.internal.influence.Influence;
import com.onesignal.session.internal.influence.InfluenceChannel;
import com.onesignal.session.internal.influence.InfluenceType;
import com.onesignal.session.internal.outcomes.migrations.RemoveZeroSessionTimeRecords;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: compiled from: OutcomeEventsRepository.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J(\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J \u0010\u000e\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002J\u0011\u0010\u0011\u001a\u00020\u0006H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0012J\u0019\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0015H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0016J\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00150\u0018H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0012J4\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u001aH\u0002J-\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u00182\u0006\u0010$\u001a\u00020 2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020#0\u0018H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010&J*\u0010'\u001a\u0004\u0018\u00010\u001a2\u0006\u0010(\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u00102\u0006\u0010)\u001a\u00020 H\u0002J\u0019\u0010*\u001a\u00020\u00062\u0006\u0010+\u001a\u00020\u0015H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0016J\u0019\u0010,\u001a\u00020\u00062\u0006\u0010+\u001a\u00020\u0015H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006-"}, d2 = {"Lcom/onesignal/session/internal/outcomes/impl/OutcomeEventsRepository;", "Lcom/onesignal/session/internal/outcomes/impl/IOutcomeEventsRepository;", "_databaseProvider", "Lcom/onesignal/core/internal/database/IDatabaseProvider;", "(Lcom/onesignal/core/internal/database/IDatabaseProvider;)V", "addIdToListFromChannel", "", "cachedUniqueOutcomes", "", "Lcom/onesignal/session/internal/outcomes/impl/CachedUniqueOutcome;", "channelIds", "Lorg/json/JSONArray;", "channel", "Lcom/onesignal/session/internal/influence/InfluenceChannel;", "addIdsToListFromSource", "sourceBody", "Lcom/onesignal/session/internal/outcomes/impl/OutcomeSourceBody;", "cleanCachedUniqueOutcomeEventNotifications", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteOldOutcomeEvent", "event", "Lcom/onesignal/session/internal/outcomes/impl/OutcomeEventParams;", "(Lcom/onesignal/session/internal/outcomes/impl/OutcomeEventParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllEventsToSend", "", "getIAMInfluenceSource", "Lcom/onesignal/session/internal/outcomes/impl/OutcomeSource;", "iamInfluenceType", "Lcom/onesignal/session/internal/influence/InfluenceType;", "directSourceBody", "indirectSourceBody", "iamIds", "", "source", "getNotCachedUniqueInfluencesForOutcome", "Lcom/onesignal/session/internal/influence/Influence;", "name", "influences", "(Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getNotificationInfluenceSource", "notificationInfluenceType", "notificationIds", "saveOutcomeEvent", "eventParams", "saveUniqueOutcomeEventParams", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class OutcomeEventsRepository implements IOutcomeEventsRepository {
    private final IDatabaseProvider _databaseProvider;

    /* JADX INFO: compiled from: OutcomeEventsRepository.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[InfluenceType.values().length];
            iArr[InfluenceType.DIRECT.ordinal()] = 1;
            iArr[InfluenceType.INDIRECT.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: com.onesignal.session.internal.outcomes.impl.OutcomeEventsRepository$getAllEventsToSend$1, reason: invalid class name */
    /* JADX INFO: compiled from: OutcomeEventsRepository.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.session.internal.outcomes.impl.OutcomeEventsRepository", f = "OutcomeEventsRepository.kt", i = {0}, l = {104}, m = "getAllEventsToSend", n = {"events"}, s = {"L$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return OutcomeEventsRepository.this.getAllEventsToSend((Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.onesignal.session.internal.outcomes.impl.OutcomeEventsRepository$getNotCachedUniqueInfluencesForOutcome$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: OutcomeEventsRepository.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.session.internal.outcomes.impl.OutcomeEventsRepository", f = "OutcomeEventsRepository.kt", i = {0}, l = {286}, m = "getNotCachedUniqueInfluencesForOutcome", n = {"uniqueInfluences"}, s = {"L$0"})
    static final class C01541 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C01541(Continuation<? super C01541> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return OutcomeEventsRepository.this.getNotCachedUniqueInfluencesForOutcome(null, null, (Continuation) this);
        }
    }

    public OutcomeEventsRepository(IDatabaseProvider _databaseProvider) {
        Intrinsics.checkNotNullParameter(_databaseProvider, "_databaseProvider");
        this._databaseProvider = _databaseProvider;
    }

    /* JADX INFO: renamed from: com.onesignal.session.internal.outcomes.impl.OutcomeEventsRepository$deleteOldOutcomeEvent$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: OutcomeEventsRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.session.internal.outcomes.impl.OutcomeEventsRepository$deleteOldOutcomeEvent$2", f = "OutcomeEventsRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C01522 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ OutcomeEventParams $event;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01522(OutcomeEventParams outcomeEventParams, Continuation<? super C01522> continuation) {
            super(2, continuation);
            this.$event = outcomeEventParams;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return OutcomeEventsRepository.this.new C01522(this.$event, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    OutcomeEventsRepository.this._databaseProvider.getOs().delete("outcome", "timestamp = ?", new String[]{String.valueOf(this.$event.getTimestamp())});
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    @Override // com.onesignal.session.internal.outcomes.impl.IOutcomeEventsRepository
    public Object deleteOldOutcomeEvent(OutcomeEventParams event, Continuation<? super Unit> continuation) {
        Object objWithContext = BuildersKt.withContext(Dispatchers.getIO(), new C01522(event, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.onesignal.session.internal.outcomes.impl.OutcomeEventsRepository$saveOutcomeEvent$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: OutcomeEventsRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Landroid/content/ContentValues;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.session.internal.outcomes.impl.OutcomeEventsRepository$saveOutcomeEvent$2", f = "OutcomeEventsRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C01562 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ContentValues>, Object> {
        final /* synthetic */ OutcomeEventParams $eventParams;
        int label;
        final /* synthetic */ OutcomeEventsRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01562(OutcomeEventParams outcomeEventParams, OutcomeEventsRepository outcomeEventsRepository, Continuation<? super C01562> continuation) {
            super(2, continuation);
            this.$eventParams = outcomeEventParams;
            this.this$0 = outcomeEventsRepository;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C01562(this.$eventParams, this.this$0, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ContentValues> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            OutcomeSourceBody indirectBody;
            OutcomeSourceBody directBody;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    Ref.ObjectRef notificationIds = new Ref.ObjectRef();
                    notificationIds.element = new JSONArray();
                    Ref.ObjectRef iamIds = new Ref.ObjectRef();
                    iamIds.element = new JSONArray();
                    Ref.ObjectRef notificationInfluenceType = new Ref.ObjectRef();
                    notificationInfluenceType.element = InfluenceType.UNATTRIBUTED;
                    Ref.ObjectRef iamInfluenceType = new Ref.ObjectRef();
                    iamInfluenceType.element = InfluenceType.UNATTRIBUTED;
                    OutcomeSource outcomeSource = this.$eventParams.getOutcomeSource();
                    if (outcomeSource != null && (directBody = outcomeSource.getDirectBody()) != null) {
                        JSONArray it = directBody.getNotificationIds();
                        if (it != null && it.length() > 0) {
                            notificationInfluenceType.element = InfluenceType.DIRECT;
                            notificationIds.element = it;
                        }
                        JSONArray it2 = directBody.getInAppMessagesIds();
                        if (it2 != null && it2.length() > 0) {
                            iamInfluenceType.element = InfluenceType.DIRECT;
                            iamIds.element = it2;
                        }
                    }
                    OutcomeSource outcomeSource2 = this.$eventParams.getOutcomeSource();
                    if (outcomeSource2 != null && (indirectBody = outcomeSource2.getIndirectBody()) != null) {
                        JSONArray it3 = indirectBody.getNotificationIds();
                        if (it3 != null && it3.length() > 0) {
                            notificationInfluenceType.element = InfluenceType.INDIRECT;
                            notificationIds.element = it3;
                        }
                        JSONArray it4 = indirectBody.getInAppMessagesIds();
                        if (it4 != null && it4.length() > 0) {
                            iamInfluenceType.element = InfluenceType.INDIRECT;
                            iamIds.element = it4;
                        }
                    }
                    ContentValues $this$invokeSuspend_u24lambda_u2d6 = new ContentValues();
                    OutcomeEventParams outcomeEventParams = this.$eventParams;
                    $this$invokeSuspend_u24lambda_u2d6.put("notification_ids", ((JSONArray) notificationIds.element).toString());
                    $this$invokeSuspend_u24lambda_u2d6.put(OutcomeEventsTable.COLUMN_NAME_IAM_IDS, ((JSONArray) iamIds.element).toString());
                    String string = ((InfluenceType) notificationInfluenceType.element).toString();
                    Locale locale = Locale.ROOT;
                    Intrinsics.checkNotNullExpressionValue(locale, "ROOT");
                    String lowerCase = string.toLowerCase(locale);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                    $this$invokeSuspend_u24lambda_u2d6.put(OutcomeEventsTable.COLUMN_NAME_NOTIFICATION_INFLUENCE_TYPE, lowerCase);
                    String string2 = ((InfluenceType) iamInfluenceType.element).toString();
                    Locale locale2 = Locale.ROOT;
                    Intrinsics.checkNotNullExpressionValue(locale2, "ROOT");
                    String lowerCase2 = string2.toLowerCase(locale2);
                    Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
                    $this$invokeSuspend_u24lambda_u2d6.put(OutcomeEventsTable.COLUMN_NAME_IAM_INFLUENCE_TYPE, lowerCase2);
                    $this$invokeSuspend_u24lambda_u2d6.put("name", outcomeEventParams.getOutcomeId());
                    $this$invokeSuspend_u24lambda_u2d6.put("weight", Boxing.boxFloat(outcomeEventParams.getWeight()));
                    $this$invokeSuspend_u24lambda_u2d6.put("timestamp", Boxing.boxLong(outcomeEventParams.getTimestamp()));
                    $this$invokeSuspend_u24lambda_u2d6.put("session_time", Boxing.boxLong(outcomeEventParams.getSessionTime()));
                    this.this$0._databaseProvider.getOs().insert("outcome", null, $this$invokeSuspend_u24lambda_u2d6);
                    return $this$invokeSuspend_u24lambda_u2d6;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    @Override // com.onesignal.session.internal.outcomes.impl.IOutcomeEventsRepository
    public Object saveOutcomeEvent(OutcomeEventParams eventParams, Continuation<? super Unit> continuation) {
        Object objWithContext = BuildersKt.withContext(Dispatchers.getIO(), new C01562(eventParams, this, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // com.onesignal.session.internal.outcomes.impl.IOutcomeEventsRepository
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object getAllEventsToSend(kotlin.coroutines.Continuation<? super java.util.List<com.onesignal.session.internal.outcomes.impl.OutcomeEventParams>> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.onesignal.session.internal.outcomes.impl.OutcomeEventsRepository.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r8
            com.onesignal.session.internal.outcomes.impl.OutcomeEventsRepository$getAllEventsToSend$1 r0 = (com.onesignal.session.internal.outcomes.impl.OutcomeEventsRepository.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            com.onesignal.session.internal.outcomes.impl.OutcomeEventsRepository$getAllEventsToSend$1 r0 = new com.onesignal.session.internal.outcomes.impl.OutcomeEventsRepository$getAllEventsToSend$1
            r0.<init>(r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L34;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L2c:
            java.lang.Object r1 = r0.L$0
            java.util.List r1 = (java.util.List) r1
            kotlin.ResultKt.throwOnFailure(r8)
            goto L5a
        L34:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r7
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.List r3 = (java.util.List) r3
            kotlinx.coroutines.CoroutineDispatcher r4 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r4 = (kotlin.coroutines.CoroutineContext) r4
            com.onesignal.session.internal.outcomes.impl.OutcomeEventsRepository$getAllEventsToSend$2 r5 = new com.onesignal.session.internal.outcomes.impl.OutcomeEventsRepository$getAllEventsToSend$2
            r6 = 0
            r5.<init>(r3, r6)
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r0.L$0 = r3
            r6 = 1
            r0.label = r6
            java.lang.Object r2 = kotlinx.coroutines.BuildersKt.withContext(r4, r5, r0)
            if (r2 != r1) goto L59
            return r1
        L59:
            r1 = r3
        L5a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.session.internal.outcomes.impl.OutcomeEventsRepository.getAllEventsToSend(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.onesignal.session.internal.outcomes.impl.OutcomeEventsRepository$getAllEventsToSend$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: OutcomeEventsRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.session.internal.outcomes.impl.OutcomeEventsRepository$getAllEventsToSend$2", f = "OutcomeEventsRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C01532 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<OutcomeEventParams> $events;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01532(List<OutcomeEventParams> list, Continuation<? super C01532> continuation) {
            super(2, continuation);
            this.$events = list;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return OutcomeEventsRepository.this.new C01532(this.$events, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    RemoveZeroSessionTimeRecords.INSTANCE.run(OutcomeEventsRepository.this._databaseProvider);
                    IDatabase os = OutcomeEventsRepository.this._databaseProvider.getOs();
                    final OutcomeEventsRepository outcomeEventsRepository = OutcomeEventsRepository.this;
                    final List<OutcomeEventParams> list = this.$events;
                    IDatabase.DefaultImpls.query$default(os, "outcome", null, null, null, null, null, null, null, new Function1<ICursor, Unit>() { // from class: com.onesignal.session.internal.outcomes.impl.OutcomeEventsRepository.getAllEventsToSend.2.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                            invoke((ICursor) p1);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(ICursor cursor) {
                            Intrinsics.checkNotNullParameter(cursor, "cursor");
                            if (cursor.moveToFirst()) {
                                do {
                                    String notificationInfluenceTypeString = cursor.getString(OutcomeEventsTable.COLUMN_NAME_NOTIFICATION_INFLUENCE_TYPE);
                                    InfluenceType notificationInfluenceType = InfluenceType.Companion.fromString(notificationInfluenceTypeString);
                                    String iamInfluenceTypeString = cursor.getString(OutcomeEventsTable.COLUMN_NAME_IAM_INFLUENCE_TYPE);
                                    InfluenceType iamInfluenceType = InfluenceType.Companion.fromString(iamInfluenceTypeString);
                                    String optString = cursor.getOptString("notification_ids");
                                    if (optString == null) {
                                        optString = "[]";
                                    }
                                    String notificationIds = optString;
                                    String optString2 = cursor.getOptString(OutcomeEventsTable.COLUMN_NAME_IAM_IDS);
                                    String iamIds = optString2 == null ? "[]" : optString2;
                                    String name = cursor.getString("name");
                                    float weight = cursor.getFloat("weight");
                                    long timestamp = cursor.getLong("timestamp");
                                    long sessionTime = cursor.getLong("session_time");
                                    try {
                                        OutcomeSourceBody directSourceBody = new OutcomeSourceBody(null, null, 3, null);
                                        OutcomeSourceBody indirectSourceBody = new OutcomeSourceBody(null, null, 3, null);
                                        OutcomeSource it = outcomeEventsRepository.getNotificationInfluenceSource(notificationInfluenceType, directSourceBody, indirectSourceBody, notificationIds);
                                        outcomeEventsRepository.getIAMInfluenceSource(iamInfluenceType, directSourceBody, indirectSourceBody, iamIds, it);
                                        OutcomeSource source = it == null ? new OutcomeSource(null, null) : it;
                                        list.add(new OutcomeEventParams(name, source, weight, sessionTime, timestamp));
                                    } catch (JSONException e) {
                                        Logging.error("Generating JSONArray from notifications ids outcome:JSON Failed.", e);
                                    }
                                } while (cursor.moveToNext());
                            }
                        }
                    }, 254, null);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OutcomeSource getNotificationInfluenceSource(InfluenceType notificationInfluenceType, OutcomeSourceBody directSourceBody, OutcomeSourceBody indirectSourceBody, String notificationIds) {
        switch (WhenMappings.$EnumSwitchMapping$0[notificationInfluenceType.ordinal()]) {
            case 1:
                directSourceBody.setNotificationIds(new JSONArray(notificationIds));
                return new OutcomeSource(directSourceBody, null);
            case 2:
                indirectSourceBody.setNotificationIds(new JSONArray(notificationIds));
                return new OutcomeSource(null, indirectSourceBody);
            default:
                return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OutcomeSource getIAMInfluenceSource(InfluenceType iamInfluenceType, OutcomeSourceBody directSourceBody, OutcomeSourceBody indirectSourceBody, String iamIds, OutcomeSource source) {
        OutcomeSource directBody;
        OutcomeSource indirectBody;
        switch (WhenMappings.$EnumSwitchMapping$0[iamInfluenceType.ordinal()]) {
            case 1:
                directSourceBody.setInAppMessagesIds(new JSONArray(iamIds));
                if (source != null && (directBody = source.setDirectBody(directSourceBody)) != null) {
                    return directBody;
                }
                return new OutcomeSource(directSourceBody, null);
            case 2:
                indirectSourceBody.setInAppMessagesIds(new JSONArray(iamIds));
                if (source != null && (indirectBody = source.setIndirectBody(indirectSourceBody)) != null) {
                    return indirectBody;
                }
                return new OutcomeSource(null, indirectSourceBody);
            default:
                return source;
        }
    }

    private final void addIdToListFromChannel(List<CachedUniqueOutcome> list, JSONArray channelIds, InfluenceChannel channel) {
        if (channelIds != null) {
            int length = channelIds.length();
            for (int i = 0; i < length; i++) {
                try {
                    String influenceId = channelIds.getString(i);
                    Intrinsics.checkNotNullExpressionValue(influenceId, "influenceId");
                    list.add(new CachedUniqueOutcome(influenceId, channel));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addIdsToListFromSource(List<CachedUniqueOutcome> list, OutcomeSourceBody sourceBody) {
        if (sourceBody != null) {
            JSONArray iamIds = sourceBody.getInAppMessagesIds();
            JSONArray notificationIds = sourceBody.getNotificationIds();
            addIdToListFromChannel(list, iamIds, InfluenceChannel.IAM);
            addIdToListFromChannel(list, notificationIds, InfluenceChannel.NOTIFICATION);
        }
    }

    @Override // com.onesignal.session.internal.outcomes.impl.IOutcomeEventsRepository
    public Object saveUniqueOutcomeEventParams(OutcomeEventParams eventParams, Continuation<? super Unit> continuation) {
        Logging.debug$default("OutcomeEventsCache.saveUniqueOutcomeEventParams(eventParams: " + eventParams + ')', null, 2, null);
        Object objWithContext = BuildersKt.withContext(Dispatchers.getIO(), new C01572(eventParams, this, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.onesignal.session.internal.outcomes.impl.OutcomeEventsRepository$saveUniqueOutcomeEventParams$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: OutcomeEventsRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.session.internal.outcomes.impl.OutcomeEventsRepository$saveUniqueOutcomeEventParams$2", f = "OutcomeEventsRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C01572 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ OutcomeEventParams $eventParams;
        int label;
        final /* synthetic */ OutcomeEventsRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01572(OutcomeEventParams outcomeEventParams, OutcomeEventsRepository outcomeEventsRepository, Continuation<? super C01572> continuation) {
            super(2, continuation);
            this.$eventParams = outcomeEventParams;
            this.this$0 = outcomeEventsRepository;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C01572(this.$eventParams, this.this$0, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    String outcomeName = this.$eventParams.getOutcomeId();
                    List<CachedUniqueOutcome> cachedUniqueOutcomes = new ArrayList();
                    OutcomeSource outcomeSource = this.$eventParams.getOutcomeSource();
                    OutcomeSourceBody directBody = outcomeSource != null ? outcomeSource.getDirectBody() : null;
                    OutcomeSource outcomeSource2 = this.$eventParams.getOutcomeSource();
                    OutcomeSourceBody indirectBody = outcomeSource2 != null ? outcomeSource2.getIndirectBody() : null;
                    this.this$0.addIdsToListFromSource(cachedUniqueOutcomes, directBody);
                    this.this$0.addIdsToListFromSource(cachedUniqueOutcomes, indirectBody);
                    for (CachedUniqueOutcome uniqueOutcome : cachedUniqueOutcomes) {
                        ContentValues values = new ContentValues();
                        values.put("channel_influence_id", uniqueOutcome.getInfluenceId());
                        values.put("channel_type", uniqueOutcome.getChannel().toString());
                        values.put("name", outcomeName);
                        this.this$0._databaseProvider.getOs().insert("cached_unique_outcome", null, values);
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // com.onesignal.session.internal.outcomes.impl.IOutcomeEventsRepository
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object getNotCachedUniqueInfluencesForOutcome(java.lang.String r9, java.util.List<com.onesignal.session.internal.influence.Influence> r10, kotlin.coroutines.Continuation<? super java.util.List<com.onesignal.session.internal.influence.Influence>> r11) {
        /*
            r8 = this;
            boolean r0 = r11 instanceof com.onesignal.session.internal.outcomes.impl.OutcomeEventsRepository.C01541
            if (r0 == 0) goto L14
            r0 = r11
            com.onesignal.session.internal.outcomes.impl.OutcomeEventsRepository$getNotCachedUniqueInfluencesForOutcome$1 r0 = (com.onesignal.session.internal.outcomes.impl.OutcomeEventsRepository.C01541) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            com.onesignal.session.internal.outcomes.impl.OutcomeEventsRepository$getNotCachedUniqueInfluencesForOutcome$1 r0 = new com.onesignal.session.internal.outcomes.impl.OutcomeEventsRepository$getNotCachedUniqueInfluencesForOutcome$1
            r0.<init>(r11)
        L19:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L34;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L2c:
            java.lang.Object r9 = r0.L$0
            java.util.List r9 = (java.util.List) r9
            kotlin.ResultKt.throwOnFailure(r11)
            goto L5d
        L34:
            kotlin.ResultKt.throwOnFailure(r11)
            r5 = r8
            r3 = r10
            r4 = r9
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            r6 = r9
            java.util.List r6 = (java.util.List) r6
            kotlinx.coroutines.CoroutineDispatcher r9 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r9 = (kotlin.coroutines.CoroutineContext) r9
            com.onesignal.session.internal.outcomes.impl.OutcomeEventsRepository$getNotCachedUniqueInfluencesForOutcome$2 r2 = new com.onesignal.session.internal.outcomes.impl.OutcomeEventsRepository$getNotCachedUniqueInfluencesForOutcome$2
            r7 = 0
            r2.<init>(r3, r4, r5, r6, r7)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.L$0 = r6
            r10 = 1
            r0.label = r10
            java.lang.Object r9 = kotlinx.coroutines.BuildersKt.withContext(r9, r2, r0)
            if (r9 != r1) goto L5c
            return r1
        L5c:
            r9 = r6
        L5d:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.session.internal.outcomes.impl.OutcomeEventsRepository.getNotCachedUniqueInfluencesForOutcome(java.lang.String, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.onesignal.session.internal.outcomes.impl.OutcomeEventsRepository$getNotCachedUniqueInfluencesForOutcome$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: OutcomeEventsRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.session.internal.outcomes.impl.OutcomeEventsRepository$getNotCachedUniqueInfluencesForOutcome$2", f = "OutcomeEventsRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C01552 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<Influence> $influences;
        final /* synthetic */ String $name;
        final /* synthetic */ List<Influence> $uniqueInfluences;
        int label;
        final /* synthetic */ OutcomeEventsRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01552(List<Influence> list, String str, OutcomeEventsRepository outcomeEventsRepository, List<Influence> list2, Continuation<? super C01552> continuation) {
            super(2, continuation);
            this.$influences = list;
            this.$name = str;
            this.this$0 = outcomeEventsRepository;
            this.$uniqueInfluences = list2;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C01552(this.$influences, this.$name, this.this$0, this.$uniqueInfluences, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    try {
                        for (Influence influence : this.$influences) {
                            final JSONArray availableInfluenceIds = new JSONArray();
                            JSONArray influenceIds = influence.getIds();
                            if (influenceIds != null) {
                                int length = influenceIds.length();
                                for (int i = 0; i < length; i++) {
                                    final String channelInfluenceId = influenceIds.getString(i);
                                    InfluenceChannel channel = influence.getInfluenceChannel();
                                    String[] columns = new String[0];
                                    String[] args = {channelInfluenceId, channel.toString(), this.$name};
                                    IDatabase.DefaultImpls.query$default(this.this$0._databaseProvider.getOs(), "cached_unique_outcome", columns, "channel_influence_id = ? AND channel_type = ? AND name = ?", args, null, null, null, "1", new Function1<ICursor, Unit>() { // from class: com.onesignal.session.internal.outcomes.impl.OutcomeEventsRepository.getNotCachedUniqueInfluencesForOutcome.2.1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(1);
                                        }

                                        public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                                            invoke((ICursor) p1);
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(ICursor it) {
                                            Intrinsics.checkNotNullParameter(it, "it");
                                            if (it.getCount() == 0) {
                                                availableInfluenceIds.put(channelInfluenceId);
                                            }
                                        }
                                    }, 112, null);
                                }
                                if (availableInfluenceIds.length() > 0) {
                                    Influence newInfluence = influence.copy();
                                    newInfluence.setIds(availableInfluenceIds);
                                    this.$uniqueInfluences.add(newInfluence);
                                }
                            }
                            break;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    @Override // com.onesignal.session.internal.outcomes.impl.IOutcomeEventsRepository
    public Object cleanCachedUniqueOutcomeEventNotifications(Continuation<? super Unit> continuation) {
        Object objWithContext = BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(OneSignalDbContract.NotificationTable.TABLE_NAME, "notification_id", this, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.onesignal.session.internal.outcomes.impl.OutcomeEventsRepository$cleanCachedUniqueOutcomeEventNotifications$2, reason: invalid class name */
    /* JADX INFO: compiled from: OutcomeEventsRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.onesignal.session.internal.outcomes.impl.OutcomeEventsRepository$cleanCachedUniqueOutcomeEventNotifications$2", f = "OutcomeEventsRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $notificationIdColumnName;
        final /* synthetic */ String $notificationTableName;
        int label;
        final /* synthetic */ OutcomeEventsRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, String str2, OutcomeEventsRepository outcomeEventsRepository, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$notificationTableName = str;
            this.$notificationIdColumnName = str2;
            this.this$0 = outcomeEventsRepository;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$notificationTableName, this.$notificationIdColumnName, this.this$0, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    StringBuilder sbAppend = new StringBuilder().append("NOT EXISTS(SELECT NULL FROM ").append(this.$notificationTableName).append(" n WHERE n.").append(this.$notificationIdColumnName).append(" = channel_influence_id AND channel_type = \"");
                    String string = InfluenceChannel.NOTIFICATION.toString();
                    Locale locale = Locale.ROOT;
                    Intrinsics.checkNotNullExpressionValue(locale, "ROOT");
                    String lowerCase = string.toLowerCase(locale);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                    String whereStr = sbAppend.append(lowerCase).append("\")").toString();
                    this.this$0._databaseProvider.getOs().delete("cached_unique_outcome", whereStr, null);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }
}
