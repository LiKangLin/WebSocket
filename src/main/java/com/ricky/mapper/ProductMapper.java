package com.ricky.mapper;

import com.ricky.entity.ProductEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by PVer on 2018/5/12.
 */
public interface ProductMapper {
    @Select("SELECT * FROM products")
    List<ProductEntity> getAll();

    @Select("SELECT * FROM Products WHERE id = #{id}")
    ProductEntity getOne(Long id);

    @Insert("INSERT INTO products(name,code) VALUES(#{name}, #{code})")
    void insert(ProductEntity product);
}
