package com.fidroid.centerscaledrecycelerview;

import android.animation.Animator;
import android.annotation.TargetApi;

/**
 * Created by jabin on 4/12/16.
 */
@TargetApi(20)
public class SimpleAnimatorListener implements Animator.AnimatorListener {
    private boolean mWasCanceled;

    public SimpleAnimatorListener() {
    }

    public void onAnimationCancel(Animator animator) {
        this.mWasCanceled = true;
    }

    public void onAnimationEnd(Animator animator) {
        if (!this.mWasCanceled) {
            this.onAnimationComplete(animator);
        }

    }

    public void onAnimationRepeat(Animator animator) {
    }

    public void onAnimationStart(Animator animator) {
        this.mWasCanceled = false;
    }

    public void onAnimationComplete(Animator animator) {
    }

    public boolean wasCanceled() {
        return this.mWasCanceled;
    }
}
