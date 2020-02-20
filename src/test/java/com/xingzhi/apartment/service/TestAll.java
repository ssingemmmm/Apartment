package com.xingzhi.apartment.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApartmentServiceTest.class,
        PropertyInfoServiceTest.class,
        RoomInfoServiceTest.class,
        MessageServiceTest.class,
        MessageServiceMockTest.class,
        FileServiceTest.class,
        FileServiceMockTest.class
})

public class TestAll {
}