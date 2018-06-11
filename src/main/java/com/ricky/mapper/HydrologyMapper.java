package com.ricky.mapper;

import com.ricky.entity.HydrologyEntity;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by PVer on 2018/5/12.
 */
public interface HydrologyMapper {
    @Select("SELECT * FROM Hydrology WHERE id = #{id}")
    HydrologyEntity queryById(Long id);
    @Select("SELECT * FROM Hydrology WHERE address = #{address}")
    HydrologyEntity queryByAddress(String address);

    /**
     * 用户数据新增
     */
    @Insert("INSERT INTO hydrology(id,address,nowtime,waterlevel,flow) values (#{id},#{address},#{nowtime},#{waterLevel},#{flow})")
    Boolean addHydrologyEntity(HydrologyEntity hydrologyEntity);
   /* @Insert("INSERT INTO `websocket`.`hydrology` (`id`, `address`, `nowtime`, `waterlevel`, `flow`) VALUES ('5', '武昌', '5月12日21时', '12.78', '4060立方米/秒');")*/
    /*int insert()*/
/*   void addHydrology(@RequestParam("address") String address, @RequestParam("nowtime") String nowtime,@RequestParam("waterlevel") String waterlevel,@RequestParam("flow") String flow){*/
     /*@Insert("INSERT INTO `websocket`.`hydrology` ( `address`, `nowtime`, `waterlevel`, `flow`) VALUES (#{address}, #{nowtime}, #{waterlevel}, #{flow})")
     void addHydrology(String address,String nowtime,String waterlevel,String flow);*/
}
