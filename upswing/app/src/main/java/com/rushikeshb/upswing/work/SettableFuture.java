package com.rushikeshb.upswing.work;

import androidx.annotation.Nullable;

import com.google.common.util.concurrent.ListenableFuture;

/**
 * Copied from androidx.work.impl.utils.futures.SettableFuture.
 */
public final class SettableFuture<V> extends AbstractFuture<V> {
    /**
     * Creates a new {@code SettableFuture} that can be completed or cancelled by a later method
     * call.
     */
    public static <V> SettableFuture<V> create() {
        return new SettableFuture<V>();
    }

    @Override
    public boolean set(@Nullable V value) {
        return super.set(value);
    }

    @Override
    public boolean setException(Throwable throwable) {
        return super.setException(throwable);
    }

    @Override
    public boolean setFuture(ListenableFuture<? extends V> future) {
        return super.setFuture(future);
    }

    private SettableFuture() {
    }
}
