package com.jian.service;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Jian Qi
 * @Date 2022/4/10 6:23 下午
 * @Description
 * @Version
 */
class DealData_ServiceTest {

    @org.junit.jupiter.api.Test
    void dealImgByDir() {
        DealData_Service.dealImgByDir("");
        DealData_Service.dealImgByDir("/Users/qi/Downloads/test02.jpeg");
    }

    @org.junit.jupiter.api.Test
    void dealImgByData() {
        DealData_Service.dealImgByData("");
        DealData_Service.dealImgByData(null);
    }
}