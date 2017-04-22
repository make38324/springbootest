package com.czm.bean;

/**
 * Created by mac on 17/4/18.
 */
public class TestHash {
    private String name;

    public TestHash(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.equals(name);
    }
}
