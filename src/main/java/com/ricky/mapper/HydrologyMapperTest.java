package com.ricky.mapper;

import com.ricky.entity.HydrologyEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by PVer on 2018/6/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HydrologyMapperTest {
    @Autowired
    private HydrologyMapper HM;
    @Test
    public void testQueryById() throws Exception {
           long userid=0;
           HydrologyEntity he=HM.queryById(userid);
           System.out.print(he.toString());
    }

    @Test
    public void testAddHydrologyEntity() throws Exception {
        long userID=5;
        String address="武昌";
        String nowtime="12时";
        String waterlevel="1111";
        String flow="222";
        HydrologyEntity she = new HydrologyEntity();
        she.setId(userID);
        she.setNowtime(nowtime);
        she.setWaterLevel(waterlevel);
        she.setAddress(address);
        she.setFlow(flow);
        boolean flag=HM.addHydrologyEntity(she);
        System.out.println(flag);
    }

}