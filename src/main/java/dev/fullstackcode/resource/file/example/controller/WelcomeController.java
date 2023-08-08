package dev.fullstackcode.resource.file.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

@RestController
public class WelcomeController {

    @Autowired
    private ResourceLoader resourceLoader;

    @Value("classpath:/greetings.txt")
    private Resource resource;

    @GetMapping("/readTextFile")
    public String readTextFile() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:greetings.txt");
        String content = resource.getContentAsString(StandardCharsets.UTF_8);
        return content.formatted("sayHello");
    }

    @GetMapping("/readTextFile2")
    public String readTextFile2() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("greetings.txt");
        String content = classPathResource.getContentAsString(StandardCharsets.UTF_8);
        return content.formatted("sayHello");
    }

    @GetMapping("/readTextFile3")
    public String readTextFile3() throws IOException {
        String content = resource.getContentAsString(StandardCharsets.UTF_8);
        return content.formatted("sayHello");
    }

    @GetMapping("/readTextFile4")
    public String readTextFile4() throws IOException, URISyntaxException {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("greetings.txt");
        assert stream != null;
        String content = new String(stream.readAllBytes());
        return content.formatted("sayHello");
    }

    @GetMapping("/readTextFile5")
    public String readTextFile5() throws IOException {
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("greetings.txt");
        assert stream != null;
        String content = new String(stream.readAllBytes());
        return content.formatted("sayHello");
    }

    @GetMapping("/readTextFile6")
    public String readTextFile6() throws IOException, URISyntaxException {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("test/greetings.txt");
        assert stream != null;
        String content = new String(stream.readAllBytes());
        return content.formatted("sayHello");
    }

    @GetMapping("/readTextFile7")
    public String readTextFile7() throws IOException {
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("/test/greetings.txt");
        assert stream != null;
        String content = new String(stream.readAllBytes());
        return content.formatted("sayHello");
    }

    @GetMapping(value = "/readImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] readImage() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:spring.jpg");
        return resource.getContentAsByteArray();
    }

    @GetMapping(value = "/readImage2", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] readImage2() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("spring.jpg");
        return classPathResource.getContentAsByteArray();
    }

    @GetMapping(value = "/readPdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] readPDF() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:sample.pdf");
        return resource.getContentAsByteArray();
    }

    @GetMapping("/readTextFile8")
    public String readTextFile8() throws IOException {
        InputStream stream = getClass().getResourceAsStream("/greetings.txt");
        assert stream != null;
        String content = new String(stream.readAllBytes());
        return content.formatted("sayHello");
    }


}
