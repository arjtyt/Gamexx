package com.onesignal.inAppMessages.internal.display.impl;

import android.view.animation.Interpolator;
import com.onesignal.inAppMessages.BuildConfig;
import com.onesignal.session.internal.influence.impl.InfluenceConstants;
import kotlin.Metadata;

/* JADX INFO: compiled from: OneSignalBounceInterpolator.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/onesignal/inAppMessages/internal/display/impl/OneSignalBounceInterpolator;", "Landroid/view/animation/Interpolator;", "amplitude", "", "frequency", "(DD)V", "mAmplitude", "mFrequency", "getInterpolation", "", InfluenceConstants.TIME, BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class OneSignalBounceInterpolator implements Interpolator {
    private double mAmplitude;
    private double mFrequency;

    public OneSignalBounceInterpolator(double amplitude, double frequency) {
        this.mAmplitude = 1.0d;
        this.mFrequency = 10.0d;
        this.mAmplitude = amplitude;
        this.mFrequency = frequency;
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float time) {
        return (float) ((((double) (-1)) * Math.pow(2.718281828459045d, ((double) (-time)) / this.mAmplitude) * Math.cos(this.mFrequency * ((double) time))) + ((double) 1));
    }
}
