package com.example.demo.service.service_impl;

import com.example.demo.config.StorageProperties;
import com.example.demo.service.FileStorageService;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final File cacheDir;

    public FileStorageServiceImpl(StorageProperties storageProperties) {
        this.cacheDir = new File(storageProperties.location());
        if (!this.cacheDir.exists()) {
            if (!this.cacheDir.mkdir()) {
                throw new RuntimeException("Cache directory can not be created: " + storageProperties.location());
            }
        } else if (!this.cacheDir.isDirectory()) {
            throw new RuntimeException("Cache path is not directory: " + storageProperties.location());
        }
    }

    @Override
    public File createNewFile(String destinationPath) throws IOException {
        File file = new File(this.cacheDir, destinationPath);
        if (file.getName().isEmpty()) {
            throw new IllegalArgumentException("File path is not valid: " + file);
        } else {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            return file;
        }
    }

    @Override
    public void importStream(InputStream in, String destinationPath) throws IOException {
        File file = this.createNewFile(destinationPath);
        OutputStream out = new FileOutputStream(file);
        byte[] buf = new byte[4096];

        int len;
        while((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }

        in.close();
        out.close();
    }

    @Override
    public File getFile(String path) {
        File file = new File(this.cacheDir, path);
        return !file.exists() ? null : file;
    }


    @Override
    public void importByteArray(byte[] source, String destinationPath) throws IOException {
        File file = this.createNewFile(destinationPath);
        OutputStream out = new FileOutputStream(file);
        out.write(source);
        out.flush();
        out.close();
    }


}
