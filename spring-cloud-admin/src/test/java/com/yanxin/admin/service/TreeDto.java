package com.yanxin.admin.service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2021-12-01 16:03
 */
public class TreeDto {

    private String name;

    private String path;

    private String parentName;

    private String parentPath;

    private Boolean directory;

    private List<TreeDto> children = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    public List<TreeDto> getChildren() {
        return children;
    }

    public void setChildren(List<TreeDto> children) {
        this.children = children;
    }

    public Boolean getDirectory() {
        return directory;
    }

    public void setDirectory(Boolean directory) {
        this.directory = directory;
    }

    @Override
    public String toString() {
        return "TreeDto{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", parentName='" + parentName + '\'' +
                ", parentPath='" + parentPath + '\'' +
                ", directory=" + directory +
                ", children=" + children +
                '}';
    }
}
