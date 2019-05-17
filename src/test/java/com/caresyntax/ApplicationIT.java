package com.caresyntax;

import org.apache.commons.io.IOUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public abstract class ApplicationIT {

    @Autowired
    protected MockMvc mockMvc;

    protected String getJson() throws IOException {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        String fileName = getClass().getSimpleName() + "." + methodName + ".json";
        return getJson(fileName);
    }

    protected String getJson(String fileName) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream(fileName);
        return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
    }
}