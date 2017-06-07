package com.ttpsc.task4;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class JMXTest {
    public static void main(String[] args) throws Exception{
        ApplicationCache cache = new ApplicationCache();
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName objectName = new ObjectName("mbeans:type=ApplicationCacheMBean");
        mbs.registerMBean(cache, objectName);
        imitateActivity(cache);
    }

    private static void imitateActivity(ApplicationCache cache) {
        while(true) {
            try {
                cache.cacheObject(new Object());
                Thread.sleep(1000);
            }
            catch (InterruptedException ignored) {}
        }
    }
}