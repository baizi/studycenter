package com.study.util;

import com.google.common.collect.Lists;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.List;

/**
 * Created by zhangshuwen
 * Date is 2016/10/2.
 * Time is 21:18
 */
public class FileUtil {

    private static final FileUtil instance = new FileUtil();

    private FileUtil() {}

    public static FileUtil getInstance() {
        return instance;
    }

    public List<String> read(String path) {

        List<String> lines = Lists.newArrayList();

        try{

            File file = new File(path);
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line = null;
            while((line = reader.readLine()) != null) {
                lines.add(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lines;
    }
}
