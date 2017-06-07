package com.ttpsc.task4;

public interface ApplicationCacheMBean {

    int getMaxCacheSize();
    void setMaxCacheSize(int value);
    int getCachedObjects();
    void clearCache();
}
