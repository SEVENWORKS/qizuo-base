package org.qizuo.cm.utils;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author: fangl
 * @description: 文件操作工具
 * @date: 16:18 2019/1/11
 */
public class FileUtil {
    /**********************read***************************/
    /**
     * @author: fangl
     * @description: 通过流，读取文件内容
     * @date: 17:17 2019/2/26
     */
    public static String readFile(InputStream is) {
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();
        try {
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String readLine = null;
            while ((readLine = br.readLine()) != null) {
                sb.append(readLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * @author: fangl
     * @description: 读取text文件操作
     * @date: 17:24 2019/2/26
     */
    public static String readText(String filePath) {
        String lines = "";
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                lines += line + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    /**********************message***************************/
    /**
     * @author: fangl
     * @description: 返回文件的URL地址
     * @date: 17:18 2019/2/26
     */
    public static URL readGetURL(File file) throws MalformedURLException {
        String fileURL = "file:/" + file.getAbsolutePath();
        URL url = new URL(fileURL);
        return url;
    }

    /**
     * @author: fangl
     * @description: 从文件名得到文件绝对路径
     * @date: 17:18 2019/2/26
     */
    public static String readGetFilePath(String fileName) {
        File file = new File(fileName);
        return file.getAbsolutePath();
    }

    /**
     * @author: fangl
     * @description: 从文件路径得到文件名。
     * @date: 17:18 2019/2/26
     */
    public static String readGetFileName(String filePath) {
        File file = new File(filePath);
        return file.getName();
    }

    /**
     * @author: fangl
     * @description: 将DOS/Windows格式的路径转换为UNIX/Linux格式的路径。
     * @date: 17:18 2019/2/26
     */
    public static String toUNIXpath(String filePath) {
        return filePath.replace("", "/");
    }

    /**
     * @author: fangl
     * @description: 从文件名得到UNIX风格的文件绝对路径。
     * @date: 17:19 2019/2/26
     */
    public static String getUNIXfilePath(String fileName) {
        File file = new File(fileName);
        return toUNIXpath(file.getAbsolutePath());
    }

    /**
     * 得到文件后缀名
     */
    public static String getFileExt(String fileName) {
        int point = fileName.lastIndexOf('.');
        int length = fileName.length();
        if (point == -1 || point == length - 1) {
            return "";
        } else {
            return fileName.substring(point + 1, length);
        }
    }

    /**
     * 得到文件的名字部分。 实际上就是路径中的最后一个路径分隔符后的部分。
     */
    public static String getNamePart(String fileName) {
        int point = getPathLastIndex(fileName);
        int length = fileName.length();
        if (point == -1) {
            return fileName;
        } else if (point == length - 1) {
            int secondPoint = getPathLastIndex(fileName, point - 1);
            if (secondPoint == -1) {
                if (length == 1) {
                    return fileName;
                } else {
                    return fileName.substring(0, point);
                }
            } else {
                return fileName.substring(secondPoint + 1, point);
            }
        } else {
            return fileName.substring(point + 1);
        }
    }

    /**
     * 得到文件名中的父路径部分。 对两种路径分隔符都有效。 不存在时返回""。
     * 如果文件名是以路径分隔符结尾的则不考虑该分隔符，例如"/path/"返回""。
     */
    public static String getPathPart(String fileName) {
        int point = getPathLastIndex(fileName);
        int length = fileName.length();
        if (point == -1) {
            return "";
        } else if (point == length - 1) {
            int secondPoint = getPathLastIndex(fileName, point - 1);
            if (secondPoint == -1) {
                return "";
            } else {
                return fileName.substring(0, secondPoint);
            }
        } else {
            return fileName.substring(0, point);
        }
    }

    /**
     * 得到路径分隔符在文件路径中最后出现的位置。 对于DOS或者UNIX风格的分隔符都可以。
     */
    public static int getPathLastIndex(String fileName) {
        int point = fileName.lastIndexOf("/");
        if (point == -1) {
            point = fileName.lastIndexOf("");
        }
        return point;
    }

    /**
     * 得到路径分隔符在文件路径中指定位置前最后出现的位置。 对于DOS或者UNIX风格的分隔符都可以。
     */
    public static int getPathLastIndex(String fileName, int fromIndex) {
        int point = fileName.lastIndexOf("/", fromIndex);
        if (point == -1) {
            point = fileName.lastIndexOf("", fromIndex);
        }
        return point;
    }

    /**
     * 得到路径分隔符在文件路径中首次出现的位置。 对于DOS或者UNIX风格的分隔符都可以。
     */
    public static int getPathIndex(String fileName) {
        int point = fileName.indexOf("/");
        if (point == -1) {
            point = fileName.indexOf("");
        }
        return point;
    }

    /**
     * 得到路径分隔符在文件路径中指定位置后首次出现的位置。 对于DOS或者UNIX风格的分隔符都可以。
     */
    public static int getPathIndex(String fileName, int fromIndex) {
        int point = fileName.indexOf("/", fromIndex);
        if (point == -1) {
            point = fileName.indexOf("", fromIndex);
        }
        return point;
    }

    /**
     * 将文件名中的类型部分去掉。
     */
    public static String removeFileExt(String filename) {
        int index = filename.lastIndexOf(".");
        if (index != -1) {
            return filename.substring(0, index);
        } else {
            return filename;
        }
    }

    /**
     * 得到相对路径。 文件名不是目录名的子节点时返回文件名。
     */
    public static String getSubpath(String pathName, String fileName) {
        int index = fileName.indexOf(pathName);
        if (index != -1) {
            return fileName.substring(index + pathName.length() + 1);
        } else {
            return fileName;
        }
    }

    /**
     * 文件或者目录重命名
     */
    public static boolean renameTo(String oldFilePath, String newName) {
        try {
            //若文件存在
            File oldFile = new File(oldFilePath);
            //判断是全路径还是文件名
            if (oldFile.exists()) {
                //单文件名，判断是windows还是Linux系统
                if (newName.indexOf("/") < 0 && newName.indexOf("\\") < 0) {
                    String absolutePath = oldFile.getAbsolutePath();
                    //Linux系统
                    if (newName.indexOf("/") > 0) {
                        newName = absolutePath.substring(0, absolutePath.lastIndexOf("/") + 1) + newName;
                    } else {
                        newName = absolutePath.substring(0, absolutePath.lastIndexOf("\\") + 1) + newName;
                    }
                }
                //判断重命名后的文件是否存在
                File file = new File(newName);
                if (file.exists()) {
                    System.out.println("该文件已存在,不能重命名");
                } else { //不存在，重命名
                    return oldFile.renameTo(file);
                }
            } else {
                System.out.println("原该文件不存在,不能重命名");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**************************create********************************/
    /**
     * 如果目录不存在，就创建文件
     */
    public static String createFile(String dirPath) {
        try {
            File file = new File(dirPath);
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dirPath;
    }

    /**************************write********************************/
    /**
     * @author: fangl
     * @description: 写入Text文件操作
     * @date: 17:25 2019/2/26
     */
    public static void writeText(String filePath, String content, boolean isAppend) {
        FileOutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            outputStream = new FileOutputStream(filePath, isAppend);
            outputStreamWriter = new OutputStreamWriter(outputStream);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 复制文件
     */
    public static void writeCopy(File src, File dst) throws Exception {
        int BUFFER_SIZE = 4096;
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
            out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
            byte[] buffer = new byte[BUFFER_SIZE];
            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                out = null;
            }
        }
    }

    /**
     * 复制文件，支持把源文件内容追加到目标文件末尾
     */
    public static void writeCopy(File src, File dst, boolean append) throws Exception {
        int BUFFER_SIZE = 4096;
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
            out = new BufferedOutputStream(new FileOutputStream(dst, append), BUFFER_SIZE);
            byte[] buffer = new byte[BUFFER_SIZE];
            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                out = null;
            }
        }
    }

    /**********************delete***************************/
    /**
     * 删除一个文件。
     */
    public static void deleteFile(String filename) throws IOException {
        File file = new File(filename);
        if (file.isDirectory()) {
            throw new IOException("IOException -> BadInputException: not a file.");
        }
        if (!file.exists()) {
            throw new IOException("IOException -> BadInputException: file is not exist.");
        }
        if (!file.delete()) {
            throw new IOException("Cannot delete file. filename = " + filename);
        }
    }

    /**
     * 删除文件夹及其下面的子文件夹
     */
    public static void deleteDir(File dir) throws IOException {
        if (dir.isFile()) throw new IOException("IOException -> BadInputException: not a directory.");
        File[] files = dir.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                if (file.isFile()) {
                    file.delete();
                } else {
                    deleteDir(file);
                }
            }
        }
        dir.delete();
    }

    /**************************is********************************/
    /**
     * @author: fangl
     * @description: 判断文件是否存在
     * @date: 17:17 2019/2/26
     */
    public static boolean isFileExist(String fileName) {
        return new File(fileName).isFile();
    }

    /**************************dir********************************/
    /**
     * 通过上一层目录和目录名得到最后的目录层次
     */
    public static String dirGetSaveDir(String previousDir, String dirName) {
        if (StringUtils.isNotBlank(previousDir)) {
            dirName = previousDir + "/" + dirName + "/";
        } else {
            dirName = dirName + "/";
        }
        return dirName;
    }

    /**
     * @author: fangl
     * @description: 判断是否是文件夹
     * @date: 17:23 2019/2/26
     */
    public static boolean dirIsDir(String path) {
        File file = new File(path);
        if (file.exists()) {
            return file.isDirectory();
        } else {
            return false;
        }
    }

    /**
     * @author: fangl
     * @description: 创建指定的目录。 如果指定的目录的父目录不存在则创建其目录书上所有需要的父目录(注意：可能会在返回false的时候创建部分父目录。)
     * @date: 17:18 2019/2/26
     */
    public static boolean dirMakeDirectory(File file) {
        File parent = file.getParentFile();
        if (parent != null) {
            return parent.mkdirs();
        }
        return false;
    }


    /** **********************qizuo********************** */
    /**
     * @author: fangl
     * @description: MultipartFile和file进行互相转换
     * @date: 15:23 2019/2/27
     */
    public static File multipartFileToFile(MultipartFile multipartFile) {
        //流转换
        CommonsMultipartFile cFile = (CommonsMultipartFile) multipartFile;
        DiskFileItem fileItem = (DiskFileItem) cFile.getFileItem();
        //InputStream inputStream = fileItem.getInputStream();
        //会生成一个新文件
        return fileItem.getStoreLocation();
    }
}
