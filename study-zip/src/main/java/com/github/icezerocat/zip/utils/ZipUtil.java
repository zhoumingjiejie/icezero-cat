package com.github.icezerocat.zip.utils;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: zip工具类
 * CreateDate:  2020/12/18 9:36
 *
 * @author zero
 * @version 1.0
 */
public class ZipUtil {

    public static void addFile() {
        Map<String, String> env = new HashMap<>();
        env.put("create", "true");
        //zip路径
        Path path = Paths.get("D:\\test.zip");
        URI uri = URI.create("jar:" + path.toUri());
        try {
            FileSystem fs = FileSystems.newFileSystem(uri, env);
            //指定在zip中test_1目录下创建manualType
            Path nf = fs.getPath("test_1" + File.separator + "manualType.properties");
            Writer writer = Files.newBufferedWriter(nf, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
            writer.write("rev.type=3");
            writer.close();
            fs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        addFile();
    }
}
