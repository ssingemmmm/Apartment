package com.xingzhi.apartment.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApartmentServiceTest.class,
        AuthServiceTest.class,
        FileServiceMockTest.class,
        PropertyInfoServiceTest.class,
        RoomInfoServiceTest.class,
        MessageServiceMockTest.class
})

public class TestAll {
}