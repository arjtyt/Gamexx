package com.onesignal.notifications.internal.data;

import com.onesignal.core.internal.database.impl.OneSignalDbContract;
import com.onesignal.notifications.BuildConfig;
import com.onesignal.session.internal.outcomes.impl.OutcomeConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: INotificationRepository.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\r\b`\u0018\u00002\u00020\u0001:\u00012J!\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0007Ji\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\n2\b\u0010\u0012\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\nH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0016J!\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\nH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J\u0011\u0010\u0019\u001a\u00020\u0003H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u001aJ\u001b\u0010\u001b\u001a\u00020\u000e2\b\u0010\t\u001a\u0004\u0018\u00010\nH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u001cJ#\u0010\u001d\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\u000eH¦@ø\u0001\u0000¢\u0006\u0002\u0010 J\u001b\u0010!\u001a\u0004\u0018\u00010\u00052\u0006\u0010\f\u001a\u00020\nH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u001cJ\u001b\u0010\"\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0010\u001a\u00020\u0005H¦@ø\u0001\u0000¢\u0006\u0002\u0010#J\u001f\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%2\u0006\u0010\u001e\u001a\u00020\nH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u001cJ)\u0010'\u001a\b\u0012\u0004\u0012\u00020&0%2\u0010\b\u0002\u0010(\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010%H¦@ø\u0001\u0000¢\u0006\u0002\u0010)J7\u0010*\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010+\u001a\u00020\u000e2\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010-\u001a\u00020\u000eH¦@ø\u0001\u0000¢\u0006\u0002\u0010.J\u0019\u0010/\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0005H¦@ø\u0001\u0000¢\u0006\u0002\u0010#J\u0019\u00100\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\nH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u001cJ\u0011\u00101\u001a\u00020\u0003H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u001a\u0082\u0002\u0004\n\u0002\b\u0019¨\u00063"}, d2 = {"Lcom/onesignal/notifications/internal/data/INotificationRepository;", "", "clearOldestOverLimitFallback", "", "notificationsToMakeRoomFor", "", "maxNumberOfNotificationsInt", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createNotification", OutcomeConstants.OUTCOME_ID, "", "groupId", "collapseKey", "shouldDismissIdenticals", "", "isOpened", "androidId", OneSignalDbContract.NotificationTable.COLUMN_NAME_TITLE, "body", "expireTime", "", "jsonPayload", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZILjava/lang/String;Ljava/lang/String;JLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createSummaryNotification", "(ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteExpiredNotifications", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doesNotificationExist", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAndroidIdForGroup", "group", "getSummaryNotification", "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAndroidIdFromCollapseKey", "getGroupId", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "listNotificationsForGroup", "", "Lcom/onesignal/notifications/internal/data/INotificationRepository$NotificationData;", "listNotificationsForOutstanding", "excludeAndroidIds", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markAsConsumed", OneSignalDbContract.NotificationTable.COLUMN_NAME_DISMISSED, "summaryGroup", "clearGroupOnSummaryClick", "(IZLjava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markAsDismissed", "markAsDismissedForGroup", "markAsDismissedForOutstanding", "NotificationData", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface INotificationRepository {
    Object clearOldestOverLimitFallback(int i, int i2, Continuation<? super Unit> continuation);

    Object createNotification(String str, String str2, String str3, boolean z, boolean z2, int i, String str4, String str5, long j, String str6, Continuation<? super Unit> continuation);

    Object createSummaryNotification(int i, String str, Continuation<? super Unit> continuation);

    Object deleteExpiredNotifications(Continuation<? super Unit> continuation);

    Object doesNotificationExist(String str, Continuation<? super Boolean> continuation);

    Object getAndroidIdForGroup(String str, boolean z, Continuation<? super Integer> continuation);

    Object getAndroidIdFromCollapseKey(String str, Continuation<? super Integer> continuation);

    Object getGroupId(int i, Continuation<? super String> continuation);

    Object listNotificationsForGroup(String str, Continuation<? super List<NotificationData>> continuation);

    Object listNotificationsForOutstanding(List<Integer> list, Continuation<? super List<NotificationData>> continuation);

    Object markAsConsumed(int i, boolean z, String str, boolean z2, Continuation<? super Unit> continuation);

    Object markAsDismissed(int i, Continuation<? super Boolean> continuation);

    Object markAsDismissedForGroup(String str, Continuation<? super Unit> continuation);

    Object markAsDismissedForOutstanding(Continuation<? super Unit> continuation);

    /* JADX INFO: compiled from: INotificationRepository.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public static final class DefaultImpls {
        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Object listNotificationsForOutstanding$default(INotificationRepository iNotificationRepository, List list, Continuation continuation, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: listNotificationsForOutstanding");
            }
            if ((i & 1) != 0) {
                list = null;
            }
            return iNotificationRepository.listNotificationsForOutstanding(list, continuation);
        }

        public static /* synthetic */ Object markAsConsumed$default(INotificationRepository iNotificationRepository, int i, boolean z, String str, boolean z2, Continuation continuation, int i2, Object obj) {
            String str2;
            boolean z3;
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: markAsConsumed");
            }
            if ((i2 & 4) == 0) {
                str2 = str;
            } else {
                str2 = null;
            }
            if ((i2 & 8) == 0) {
                z3 = z2;
            } else {
                z3 = true;
            }
            return iNotificationRepository.markAsConsumed(i, z, str2, z3, continuation);
        }
    }

    /* JADX INFO: compiled from: INotificationRepository.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\r\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011¨\u0006\u0015"}, d2 = {"Lcom/onesignal/notifications/internal/data/INotificationRepository$NotificationData;", "", "androidId", "", OutcomeConstants.OUTCOME_ID, "", "fullData", "createdAt", "", OneSignalDbContract.NotificationTable.COLUMN_NAME_TITLE, OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, "(ILjava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;)V", "getAndroidId", "()I", "getCreatedAt", "()J", "getFullData", "()Ljava/lang/String;", "getId", "getMessage", "getTitle", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class NotificationData {
        private final int androidId;
        private final long createdAt;
        private final String fullData;
        private final String id;
        private final String message;
        private final String title;

        public NotificationData(int androidId, String id, String fullData, long createdAt, String title, String message) {
            Intrinsics.checkNotNullParameter(id, OutcomeConstants.OUTCOME_ID);
            Intrinsics.checkNotNullParameter(fullData, "fullData");
            this.androidId = androidId;
            this.id = id;
            this.fullData = fullData;
            this.createdAt = createdAt;
            this.title = title;
            this.message = message;
        }

        public final int getAndroidId() {
            return this.androidId;
        }

        public final String getId() {
            return this.id;
        }

        public final String getFullData() {
            return this.fullData;
        }

        public final long getCreatedAt() {
            return this.createdAt;
        }

        public final String getTitle() {
            return this.title;
        }

        public final String getMessage() {
            return this.message;
        }
    }
}
