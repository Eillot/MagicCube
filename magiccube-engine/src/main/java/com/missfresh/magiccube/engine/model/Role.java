package com.simon.magiccube.engine.model;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019-12-13  18:41
 * @File : Role
 * @Software: IntelliJ IDEA 2018.3
 */
public enum Role {

    ADMIN, MEMBER;

    public String authority() {
        return "ROLE_" + this.name();
    }

    @Override
    public String toString() {
        return this.name();
    }
}

