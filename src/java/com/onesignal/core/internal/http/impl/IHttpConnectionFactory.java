package com.onesignal.core.internal.http.impl;

import com.onesignal.core.BuildConfig;
import java.io.IOException;
import java.net.HttpURLConnection;
import kotlin.Metadata;

/* JADX INFO: compiled from: IHttpConnectionFactory.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b`\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/onesignal/core/internal/http/impl/IHttpConnectionFactory;", "", "newHttpURLConnection", "Ljava/net/HttpURLConnection;", "url", "", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface IHttpConnectionFactory {
    HttpURLConnection newHttpURLConnection(String str) throws IOException;
}
