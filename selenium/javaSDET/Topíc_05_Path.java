package javaSDET;

import java.io.File;

public class Topíc_05_Path {
    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);

        String osName = System.getProperty("os.name");
        System.out.println(osName);

        String separator = File.separator;
        System.out.println(separator);

        String uploadFolderPath = System.getProperty("user.dir") + File.separator + "uploadFiles" + File.separator;

        String haNoi = "ha noi.jpg";
        String daNang = "da nang.jpg";
        String haGiang = "ha giang.jpg";

        String haNoiPath = uploadFolderPath + haNoi;
        String daNangPath = uploadFolderPath + daNang;
        String haGiangPath = uploadFolderPath + haGiang;

        System.out.println(haGiangPath + "\n" + daNangPath + "\n" + haGiangPath);
    }
}
