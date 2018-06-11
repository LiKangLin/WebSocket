package com.ricky.entity;

import java.io.Serializable;

/**
 * Created by PVer on 2018/5/12.
 */
public class ProductEntity implements Serializable {
    private Long id;
    private String name;
    private String code;

    public ProductEntity() {
        super();
    }
    public ProductEntity(Long id, String name, String code) {
        super();
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
