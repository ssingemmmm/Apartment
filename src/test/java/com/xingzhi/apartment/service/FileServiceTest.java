package com.xingzhi.apartment.service;

import com.amazonaws.services.s3.AmazonS3;
import com.xingzhi.apartment.init.AppInitializer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


@RunWith(SpringRunner.class)
@SpringBootTest(classes= AppInitializer.class)
public class FileServiceTest {
    @Autowired
    private Logger logger;
    @Autowired
    private FileService fileService;
    @Autowired
    private AmazonS3 amazonS3;
    private String bucketName = "apartment.xingzhi.com";
    private String fileName = "test.txt";
    private MultipartFile multipartFile;
    private String path;

    @Before
    public void setUp() throws IOException {
        logger.info(">>>>>>>>>> Start testing...");

        File file = new File("/Users/apple/IdeaProjects/test/README.md");
        FileInputStream input = new FileInputStream(file);
        multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", input);
        path = System.getProperty("user.dir") + File.separator + "temp";
    }

    @After
    public void tearDown() {
        logger.info(">>>>>>>>>> End test");
    }

    @Test
    public void uploadFile() throws IOException{
        String fileUrl = fileService.uploadFile(bucketName, multipartFile);
        Assert.assertNotNull(fileUrl);
    }

    @Test
    public void saveFile() throws IOException, FileNotFoundException {
        boolean isSuccess = fileService.saveFile(multipartFile, path);
        Assert.assertTrue(isSuccess);
    }
}
