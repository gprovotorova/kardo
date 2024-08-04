package com.kardoaward.kardo.file;

import com.kardoaward.kardo.client.BaseClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Service
public class FileClient extends BaseClient {
    private static final String API_PREFIX = "/media/storage";

    @Autowired
    public FileClient(@Value("${kardo-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public ResponseEntity<Object> upload(MultipartFile attachment) {
        return post("/", attachment);
    }

    public ResponseEntity<Object> download(Long id) {
        return get("/" + id);
    }

    public ResponseEntity<Object> delete(Long id) {
        return delete("/" + id);
    }
}
