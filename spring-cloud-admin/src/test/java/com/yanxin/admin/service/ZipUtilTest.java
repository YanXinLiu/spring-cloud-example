package com.yanxin.admin.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2021-05-24 11:44
 */
public class ZipUtilTest {

    @Test
    public void copyFileTest() {

        /*File unZipFile = ZipUtil.unzip(FileUtil.newFile("/Users/sa/Downloads/QQFile/rke.zip"));
        FileUtil.copy(unZipFile, FileUtil.file("/Users/sa/Downloads"), true);*/

        /*List<File> files = FileUtil.loopFiles("/Users/sa/Downloads/ansible");
        File[] ls = FileUtil.ls("/Users/sa/Downloads/ansible");*/
        System.out.println(JSON.toJSONString(readRoot("/Users/sa/Downloads/ansible")));


    }

    @Test
    public void downloadZipTest() {

        String url = "http://192.168.3.183:9900/dpa/yan/script/DDD.zip?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=admin%2F20211201%2F%2Fs3%2Faws4_request&X-Amz-Date=20211201T094228Z&X-Amz-Expires=432000&X-Amz-SignedHeaders=host&X-Amz-Signature=0c5bdd52d34feb092d2a3fcecffd63ed5796b1fc7cdc5793dc73555babaae89d";
        HttpUtil.downloadFile(url, "/tmp");
        File unzip = ZipUtil.unzip("/tmp/DDD.zip");

    }

    @Test
    public void readFileByPath() {

        String path = "/tmp/DDD/DDD/spring.yml";
        String content = FileUtil.readUtf8String(FileUtil.newFile(path));
        System.out.println(content);
    }

    @Test
    public void replaceFileContent() {

        String name = "${active}";
        String path = "/tmp/DDD/DDD/spring.yml";
        File originFile = FileUtil.newFile(path);
        String content = FileUtil.readUtf8String(originFile);
        if (StrUtil.contains(content, name)) {
            final String newContent = StrUtil.replace(content, name, "prod");
            FileUtil.writeUtf8String(newContent, originFile);
        }
        System.out.println(FileUtil.readUtf8String(originFile));
        // 打包所有文件
    }

    @Test
    public void zipAllFile() {
        /*String url = "http://192.168.3.183:9900/dpa/yan/script/DDD.zip?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=admin%2F20211201%2F%2Fs3%2Faws4_request&X-Amz-Date=20211201T094228Z&X-Amz-Expires=432000&X-Amz-SignedHeaders=host&X-Amz-Signature=0c5bdd52d34feb092d2a3fcecffd63ed5796b1fc7cdc5793dc73555babaae89d";
        HttpUtil.downloadFile(url, "/tmp");*/
        File unzip = ZipUtil.unzip("/tmp/3a9139188a.zip");
        File file = ZipUtil.zip(unzip);
        FileUtil.del(unzip);
        File zipUp = ZipUtil.unzip(file);
        System.out.println(file.getName() + file.getPath() + file.getParent());
        System.out.println(zipUp.getName() + zipUp.getPath() + zipUp.getParent());
    }

    @Test
    public void iteratorZipFile() {

        String url = "http://192.168.3.183:9900/dpa/yan/script/DDD.zip?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=admin%2F20211201%2F%2Fs3%2Faws4_request&X-Amz-Date=20211201T094228Z&X-Amz-Expires=432000&X-Amz-SignedHeaders=host&X-Amz-Signature=0c5bdd52d34feb092d2a3fcecffd63ed5796b1fc7cdc5793dc73555babaae89d";
        HttpUtil.downloadFile(url, "/tmp");
        File unzip = ZipUtil.unzip("/tmp/DDD.zip");
        final String placeHolder = "prod";
        final String value = "uat";
        FileUtil.loopFiles(unzip).forEach(it -> {
            if (FileUtil.isFile(it)) {
                String content = FileUtil.readUtf8String(it);
                if (StrUtil.contains(content, placeHolder)) {
                    String newContent = StrUtil.replace(content, placeHolder, value);
                    // 这里会把原文件覆盖,所以只需要write
                    FileUtil.writeUtf8String(newContent, it);
                }
            }
        });
        // 替换完所有文件之后,打包变量替换后的文件
        ZipUtil.zip(unzip);
        // 最后解压确认是否替换完成
    }

    public static TreeDto readRoot(String rootPath) {

        TreeDto root = new TreeDto();
        List<TreeDto> children = Lists.newArrayList();
        root.setPath("/");
        File[] files = FileUtil.ls(rootPath);
        for (int i = 1; i < files.length; i++) {
            File file = files[i];
            TreeDto tree = new TreeDto();
            tree.setName(file.getName());
            tree.setPath(file.getPath());
            tree.setParentName("webapp");
            if (file.isDirectory()) {
                tree.setDirectory(true);
                readChildren(file.getPath(), tree);
            } else {
                tree.setDirectory(true);
            }
            root.getChildren().add(tree);
        }

        return root;

    }

    private static void readChildren(String path, TreeDto tree) {

        File[] files = FileUtil.ls(path);

        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            TreeDto tr = new TreeDto();
            tr.setName(file.getName());
            tr.setPath(file.getPath());
            tr.setDirectory(false);
            tree.setParentName(tree.getName());
            tree.setParentPath(tree.getPath());
            tree.getChildren().add(tr);
            if (file.isDirectory()) {
                tr.setDirectory(true);
                readChildren(file.getPath(), tr);
            }

        }
    }

}
