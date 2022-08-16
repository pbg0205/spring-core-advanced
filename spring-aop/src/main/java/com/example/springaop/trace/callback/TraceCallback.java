package com.example.springaop.trace.callback;

public interface TraceCallback<T> {
    T call();
}
