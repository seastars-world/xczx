package com.xczx.media;

import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MinioTest {

    MinioClient minioClient =
            MinioClient.builder()
                    .endpoint("http://xczx:9000")
                    .credentials("minioadmin", "minioadmin")
                    .build();

    @Test
    public void test() {
        try {
            UploadObjectArgs args = UploadObjectArgs.builder()
                    .bucket("testbucket")
                    //.object("test001.mp4")
                    .object("001/test001.yaml")//添加子目录
                    .filename("D:\\BaiduNetdiskDownload\\javadownload\\5.Java项目\\学成在线\\day05 媒资管理 Nacos Gateway MinIO\\资料\\nacos配置\\nacos_config_export\\xczx-common\\feign-dev.yaml")
                    .contentType("text/yaml")//默认根据扩展名确定文件内容类型，也可以指定
                    .build();
            minioClient.uploadObject(args);
            System.out.println("上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("上传失败");
        }


    }
}
