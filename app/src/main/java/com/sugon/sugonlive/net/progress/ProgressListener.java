package com.sugon.sugonlive.net.progress;

public interface ProgressListener {
    void update(long bytesRead, long contentLength, boolean done);
}
