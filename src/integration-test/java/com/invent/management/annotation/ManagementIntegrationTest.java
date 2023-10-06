package com.invent.management.annotation;

import com.invent.management.ManagementApplication;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = {ManagementApplication.class})
@Inherited
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
public @interface ManagementIntegrationTest {
}
