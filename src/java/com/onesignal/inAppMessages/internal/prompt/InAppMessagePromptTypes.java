package com.onesignal.inAppMessages.internal.prompt;

import com.onesignal.inAppMessages.BuildConfig;
import kotlin.Metadata;

/* JADX INFO: compiled from: IInAppMessagePromptFactory.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/onesignal/inAppMessages/internal/prompt/InAppMessagePromptTypes;", "", "()V", "LOCATION_PROMPT_KEY", "", "PUSH_PROMPT_KEY", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class InAppMessagePromptTypes {
    public static final InAppMessagePromptTypes INSTANCE = new InAppMessagePromptTypes();
    public static final String LOCATION_PROMPT_KEY = "location";
    public static final String PUSH_PROMPT_KEY = "push";

    private InAppMessagePromptTypes() {
    }
}
