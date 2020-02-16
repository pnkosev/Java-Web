package javache.http.impl;

import javache.http.HttpSession;

import java.util.HashMap;
import java.util.UUID;

public class HttpSessionImpl implements HttpSession {
    private String id;
    private boolean isValid;
    private HashMap<String, Object> attributes;

    public HttpSessionImpl() {
        this.id = UUID.randomUUID().toString();
        this.isValid = true;
        this.attributes = new HashMap<>();
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public boolean isValid() {
        return this.isValid;
    }

    @Override
    public HashMap<String, Object> getAttributes() {
        return this.attributes;
    }

    @Override
    public void addAttribute(String name, Object attribute) {
        this.attributes.put(name, attribute);
    }

    @Override
    public void invalidate() {
        this.isValid = false;
        this.attributes = null;
    }
}
