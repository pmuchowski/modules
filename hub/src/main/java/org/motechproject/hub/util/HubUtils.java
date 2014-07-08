package org.motechproject.hub.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Date;
import java.util.Enumeration;

import org.apache.log4j.Logger;
import org.motechproject.commons.date.util.DateUtil;

public final class HubUtils {

    private static final Logger LOGGER = Logger.getLogger(HubUtils.class);

    private HubUtils() {

    }

    public static String getNetworkHostName() {
        String hostName = null;

        try {
            hostName = InetAddress.getLocalHost().getHostName();

            if ("localhost".equalsIgnoreCase(hostName)) {
                NetworkInterface networkInterface = NetworkInterface
                        .getByName("eth0");

                Enumeration<InetAddress> a = networkInterface
                        .getInetAddresses();

                for (; a.hasMoreElements();) {
                    InetAddress addr = a.nextElement();
                    hostName = addr.getCanonicalHostName();
                    // Check for ipv4 only
                    if (!hostName.contains(":")) {
                        break;
                    }
                }
            }

        } catch (Exception e) {
            LOGGER.info("Failed to fetch network host name");
        }

        return hostName;
    }

    public static Date getCurrentDateTime() {
        return DateUtil.now().toDate();
    }
}
