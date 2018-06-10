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
                File file = new File(path1);
                if (file.exists()){
                    file.delete();

                }else{
                    file.createNewFile();
                }
                FileWriter fileWriter = new FileWriter(path1);
                fileWriter.write(str.toString());
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
}
