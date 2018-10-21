package org.spring.springboot.utils;

import java.io.*;
import java.util.StringJoiner;

/**
 * Created by Administrator on 2018/5/31.
 */
public class HtmlUtil {
    public static String returnJS() {
        StringJoiner join = new StringJoiner("");
        join.add("<script type=\"text/javascript\">");
        join.add("(function() {");
        join.add("document.getElementsByTagName(\"table\")[0].style.width = \"100%\";");
        join.add("document.getElementsByTagName(\"div\")[0].style.width = \"100%\";");
        join.add("document.getElementsByTagName(\"div\")[0].style.margin=\"0\";");
        join.add("})()");
        join.add("</script>");
        join.add("</html>");
        return join.toString();
    }


    public static String returnHeadJS() {
        StringJoiner join = new StringJoiner("");
        join.add("<html><head><meta charset=utf-8>");
        return join.toString();
    }


    public static String returnDOCDownJS() {
        StringJoiner join = new StringJoiner("");
        join.add("<img src=\"http://39.107.247.32:8081/headerImage/yixiajia.png\" alt=\"头像\" width=\"250\" height=\"100\" class=\"preview\" title=\"xiajia\"/>");
        join.add("<style type=\"text/css\"> ");
        join.add(".preview {position:relative; left:50%; top:-100%; width:300px; height:200px; margin-left:-150px; margin-top:-90%; background-color:#f00} ");
        join.add("</style>");
        join.add("<script type=\"text/javascript\">");
        join.add("(function() {");
        join.add("document.getElementsByTagName(\"table\")[0].style.width = \"100%\";");
        join.add("document.getElementsByTagName(\"div\")[0].style.width = \"100%\";");
        join.add("document.getElementsByTagName(\"div\")[0].style.margin=\"0\";");
        join.add("})()");
        join.add("</script>");
        join.add("</html>");
        return join.toString();
    }


    public static void replaceHtml(String path, String path1, String newContent) {
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String node = null;
            StringBuffer buffer = new StringBuffer();
            while ((node = br.readLine()) != null) {
                buffer.append(node);
            }
            int startIndex = buffer.indexOf("</body>");
            int endIndex = buffer.lastIndexOf("</body>");
            System.out.println(startIndex+"/"+endIndex);
            if (startIndex != -1 && endIndex != -1) {

                StringBuffer str = buffer.replace(startIndex, startIndex+7,
                        newContent);
                String contect = str.toString();
                contect = contect.replace("<html><head>","<html><head><meta charset=utf-8>");
                File file = new File(path1);
                if (file.exists()){
                    file.delete();

                }else{
                    file.createNewFile();
                }
                FileWriter fileWriter = new FileWriter(path1);
                fileWriter.write(contect);
                fileWriter.flush();
                fileWriter.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 /*   public static void main(String[] args){
        replaceHtmlTest("C:\\Users\\Administrator\\Desktop\\123\\1536126137595.html");
    }*/

    public static void replaceHtmlTest(String path) {
        try {
            path = path.replace("http://39.107.247.32:8081","/leinuo/resource");
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String node = null;
            StringBuffer buffer = new StringBuffer();
            while ((node = br.readLine()) != null) {
                buffer.append(node);
            }

            String contect = buffer.toString();
            contect = contect.replace("<html><head>","<html><head><meta charset=utf-8>");
            File file = new File(path);
            if (file.exists()){
                file.delete();
            }else{
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(contect);
            fileWriter.flush();
            fileWriter.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
