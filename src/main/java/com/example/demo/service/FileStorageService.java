package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface FileStorageService {

    File createNewFile(String destinationPath) throws IOException;

    void importStream(InputStream in, String destinationPath) throws IOException;

    File getFile(String path);

    void importByteArray(byte[] source, String destinationPath) throws IOException;

}
