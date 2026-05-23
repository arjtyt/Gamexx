package com.onesignal.inAppMessages.internal.state;

import com.onesignal.inAppMessages.BuildConfig;
import com.onesignal.inAppMessages.internal.prompt.impl.InAppMessagePrompt;
import kotlin.Metadata;

/* JADX INFO: compiled from: InAppStateService.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0015\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"Lcom/onesignal/inAppMessages/internal/state/InAppStateService;", "", "()V", "currentPrompt", "Lcom/onesignal/inAppMessages/internal/prompt/impl/InAppMessagePrompt;", "getCurrentPrompt", "()Lcom/onesignal/inAppMessages/internal/prompt/impl/InAppMessagePrompt;", "setCurrentPrompt", "(Lcom/onesignal/inAppMessages/internal/prompt/impl/InAppMessagePrompt;)V", "inAppMessageIdShowing", "", "getInAppMessageIdShowing", "()Ljava/lang/String;", "setInAppMessageIdShowing", "(Ljava/lang/String;)V", "lastTimeInAppDismissed", "", "getLastTimeInAppDismissed", "()Ljava/lang/Long;", "setLastTimeInAppDismissed", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "paused", "", "getPaused", "()Z", "setPaused", "(Z)V", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class InAppStateService {
    private InAppMessagePrompt currentPrompt;
    private String inAppMessageIdShowing;
    private Long lastTimeInAppDismissed;
    private boolean paused;

    public final boolean getPaused() {
        return this.paused;
    }

    public final void setPaused(boolean z) {
        this.paused = z;
    }

    public final String getInAppMessageIdShowing() {
        return this.inAppMessageIdShowing;
    }

    public final void setInAppMessageIdShowing(String str) {
        this.inAppMessageIdShowing = str;
    }

    public final Long getLastTimeInAppDismissed() {
        return this.lastTimeInAppDismissed;
    }

    public final void setLastTimeInAppDismissed(Long l) {
        this.lastTimeInAppDismissed = l;
    }

    public final InAppMessagePrompt getCurrentPrompt() {
        return this.currentPrompt;
    }

    public final void setCurrentPrompt(InAppMessagePrompt inAppMessagePrompt) {
        this.currentPrompt = inAppMessagePrompt;
    }
}
