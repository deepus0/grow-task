package au.com.deep.grow.utils;

import javax.servlet.http.HttpServletRequest;

public final class WebUtils {

    private WebUtils() {

    }

    public static String getAuthorization(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    public static String getTenantId(HttpServletRequest request) {
        return request.getHeader("tenant-id");
    }
}
