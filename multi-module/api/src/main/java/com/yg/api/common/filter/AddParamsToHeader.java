package com.yg.api.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class AddParamsToHeader extends HttpServletRequestWrapper {

    private String key;
    private String val;
    public AddParamsToHeader(HttpServletRequest request, String key, String val) {
        super(request);
        this.key = key;
        this.val = val;

    }

    public String getHeader(String name) {
        String headerVal = super.getHeader(name);
        return (headerVal != null) ? headerVal : this.val;
    }

    public Enumeration getHeaderNames() {
        List<String> names = Collections.list(super.getHeaderNames());
        names.add(this.key);
        return Collections.enumeration(names);
    }
}
