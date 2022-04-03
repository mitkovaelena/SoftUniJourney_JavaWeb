package org.softuni.summer.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Model {
    private Map<String, Object> attributes;

    private Map<String, Object> partials;

    public Model(){
        this.attributes = new HashMap<>();
        this.partials = new HashMap<>();
    }

    public Map<String, Object> getAttributes() {
        return Collections.unmodifiableMap(this.attributes);
    }

    public void addAttribute(String key, Object attribute) {
        this.attributes.putIfAbsent(key, attribute);
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> getPartials() {
        return partials;
    }

    public void setPartials(Map<String, Object> partials) {
        this.partials = partials;
    }

    public void addPartial(String key, Object partial) {
        this.partials.putIfAbsent(key, partial);
    }
}
