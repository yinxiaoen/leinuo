package org.spring.springboot.controller;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageSz;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.spring.springboot.common.Result;
import org.spring.springboot.config.ConfigBean;
import org.spring.springboot.config.image.ImageProperties;
import org.spring.springboot.domain.AppEdition;
import org.spring.springboot.domain.DocumentUpLoadDTO;
import org.spring.springboot.domain.ProjectDocument;
import org.spring.springboot.domain.image.HttpResponseBean;
import org.spring.springboot.domain.image.ImageDTO;
import org.spring.springboot.service.ProjectDocumentService;
import org.spring.springboot.utils.CommonUtils;
import org.spring.springboot.utils.HtmlUtil;
import org.spring.springboot.utils.Md5tools;
import org.spring.springboot.utils.ToolsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
/**
 * Created by yinxiaoen on 2018/3/22.
 */
@RestController
@CrossOrigin
@RequestMapping("/leinuo")
public class ProjectDocumentController {
    @Autowired
    private ProjectDocumentService projectDocumentService;
    @Autowired
    private ConfigBean config;
    @Autowired
    private ImageProperties imageProperties;
    @RequestMapping(value = "/document/queryProjectAndDocument", method = RequestMethod.POST)
    public Object findOneCity(@RequestBody ProjectDocument paramDTO) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat sdfTime = new SimpleDateFormat("yyyyMMdd");
        try {
        if(CommonUtils.isBlank(paramDTO.getStartDate())){

            String start = "";
            start = sdfTime.format(format.parse(paramDTO.getStartDate()));
            paramDTO.setStartDate(start);
        }
        if(CommonUtils.isBlank(paramDTO.getEndDate())){
            String end = sdfTime.format(format.parse(paramDTO.getEndDate()));
            paramDTO.setEndDate(end);
        }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<ProjectDocument> list =  projectDocumentService.findAllDocumentAndProject(paramDTO);
        list.forEach(e->{
            try {
                String time = format.format(sdfTime.parse(String.valueOf(e.getFinishDate())));
                e.setFormatFinishDate(time);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        });
        return new Result("0", list);
    }


    @RequestMapping(value = "/document/insertDocument", method = RequestMethod.POST)
    public Object insertDocument(@RequestBody ProjectDocument paramDTO) {
        projectDocumentService.insertDocumentAndProject(paramDTO);
        return new Result("0", "");
    }


    @RequestMapping(value = "/document/updateDocument", method = RequestMethod.POST)
    public Object updateDocument(@RequestBody ProjectDocument paramDTO) {
        projectDocumentService.updateDocumentAndProject(paramDTO);
        return new Result("0", "");
    }


    @RequestMapping(value = "/document/deleteDocument", method = RequestMethod.GET)
    public Object updateDocument(String ids) {
        String[] idArray = ids.split(",");

        for(String id :idArray){
            ProjectDocument  doc = projectDocumentService.queryDocmentByID(Integer.valueOf(id));
            String htmlPath = "";
            if(CommonUtils.isBlank(doc.getHtmlPath())  && doc.getHtmlPath().indexOf(".html")!=-1){
                try {
                    htmlPath = word2007ToHtmlTest(new File(doc.getDocumentPath()),2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            projectDocumentService.deleteDocumentAndProject(id,htmlPath);

        }

        return new Result("0", "");
    }



    @RequestMapping(value = "/templateDownLoad", method = RequestMethod.GET)
    public HttpServletResponse download(String path, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }



    @RequestMapping(value = "/appDownLoad", method = RequestMethod.GET)
    public HttpServletResponse download(HttpServletResponse response) {
        try {
            String appPath = config.getAppPath();
            // path是指欲下载的文件的路径。
            File file = new File(appPath);
            // 取得文件名。
            String filename = file.getName();
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(appPath));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }


    @RequestMapping(value = "/appNeedUpLoad", method = RequestMethod.GET)
    public Object appNeedUpLoad() {
        String appPath = config.getAppPath();
        String appEdition = "1.0.4";
        String reason = "更改了一些bug";
        AppEdition edition = new AppEdition();
        edition.setAppDownLoadPath(appPath);
        edition.setEdition(appEdition);
        edition.setReason(reason);
        return new Result("0", edition);
    }



    @RequestMapping("/addTemplateUpLoadV2")
    @ResponseBody
    public Object addTemplateUpLoadV2(@RequestParam(value = "file", required = false) MultipartFile file)
    {
        DocumentUpLoadDTO  documentUpLoadDTO = new  DocumentUpLoadDTO();
        if (file.isEmpty()) {
            return new Result("001", "");
        }
        //上传
        String path ="";
        String htmlPath = "";
        String documentRealName = "";
        try {
            String fileName = Md5tools.MD5(file.getOriginalFilename());
            path = config.getTbl_surf_glb_mul_file_path() +fileName+".docx";
            file.transferTo(new File(path));
            htmlPath = word2007ToHtmlTest(new File(path),1);
            documentRealName = file.getOriginalFilename();
        } catch (IOException e) {
            return new Result("001", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        documentUpLoadDTO.setDocumentName(file.getOriginalFilename());
        documentUpLoadDTO.setUrlPath(path);
        documentUpLoadDTO.setHtmlPath(htmlPath);
        documentUpLoadDTO.setDocumentRealName(documentRealName);
        return new Result("0", documentUpLoadDTO);
    }

    @RequestMapping(value = "/document/getProjectDocumentByID", method = RequestMethod.GET)
    public Object getProjectDocumentByID(Integer id) {
        ProjectDocument paramDTO = new ProjectDocument();
        paramDTO.setId(id);
        ProjectDocument document =  projectDocumentService.queryDocmentByID(paramDTO.getId());
        List<ProjectDocument> doclist = new ArrayList();
        if(document!=null){
            doclist.add(document);
        }
        return new Result("0", doclist);
    }



    @RequestMapping("/addTemplateUpLoadHeadImage")
    @ResponseBody
    public Object addTemplateUpLoadHeadImage(@RequestParam(value = "file", required = false) MultipartFile file)
    {
        if (file.isEmpty()) {
            return new Result("001", "");
        }
        //上传
        String path ="";
        String realPath = "";
        try {
            path = config.getHeadImage() + file.getOriginalFilename();
            file.transferTo(new File(path));
            realPath = config.getTbl_surf_html()+config.getRealImage()+ file.getOriginalFilename();
        } catch (IOException e) {
            return new Result("001", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result("0", realPath);
    }



    @RequestMapping("/document/upLoadHeadImageByBase64")
    @ResponseBody
    public Object addTemplateUpLoadHeadImage(@RequestBody ImageDTO baseCode)
    {
        String imageName = ToolsUtils.getRandomString(4);
        String path = config.getHeadImage() + imageName+".jpg";
        generateImage(baseCode.getBaseCode(),path);
        String realPath = config.getTbl_surf_html()+config.getRealImage() + imageName+".jpg";
        return new Result("0", realPath);
    }



    /**
     * 上传多个文件
     *
     * @param files
     * @return
     */
    @RequestMapping("/multfileMoreFile")
    @ResponseBody
    public Object uploadMultTextFiles(MultipartFile[] files) {
        HttpResponseBean httpResponseBean = new HttpResponseBean();
        if (files == null || files.length == 0) {
            httpResponseBean.setCode("200");
            httpResponseBean.setMsg("文件为空");
            httpResponseBean.setSuccess("false");
            return new Result("01", httpResponseBean);
        }
        //上传
        try {
            for (MultipartFile file : files) {
                String path = config.getTbl_surf_glb_mul_file_path() + file.getName();
                file.transferTo(new File(path));
            }
        } catch (IOException e) {
            httpResponseBean.setCode("200");
            httpResponseBean.setMsg("上传文件失败");
            httpResponseBean.setSuccess("false");
            return httpResponseBean;
        }
        httpResponseBean.setCode("200");
        httpResponseBean.setMsg("上传文件成功");
        httpResponseBean.setSuccess("true");
        return new Result("0", httpResponseBean);

    }

    public String word2007ToHtml(File file) throws Exception {
        String htmlName = + Calendar.getInstance().getTimeInMillis()+".html";
        String targetFileName = config.getTbl_surf_glb_mul_file_path()+ htmlName;
        String imagePathStr = config.getTbl_surf_glb_mul_file_path()+"/image/";
        OutputStreamWriter outputStreamWriter = null;
        try {
            XWPFDocument document = new XWPFDocument(new FileInputStream(file));
            XHTMLOptions options = XHTMLOptions.create();
            // 存放图片的文件夹
            options.setExtractor(new FileImageExtractor(new File(imagePathStr)));
            // html中图片的路径
            options.URIResolver(new BasicURIResolver("image"));
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(targetFileName), "utf-8");
            XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
            xhtmlConverter.convert(document, outputStreamWriter, options);
        } finally {
            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }
        }
        return config.getTbl_surf_html()+"/"+htmlName;
    }


    public String word2007ToHtmlTest(File file,Integer type) throws Exception {
        String htmlName = + Calendar.getInstance().getTimeInMillis()+".html";
        String targetFileName = config.getTbl_surf_glb_mul_file_path()+ htmlName;
        String imagePathStr = config.getTbl_surf_glb_mul_file_path()+"/image/";
        OutputStreamWriter outputStreamWriter = null;
        try {
            XWPFDocument document = new XWPFDocument(new FileInputStream(file));
            XHTMLOptions options = XHTMLOptions.create();
            // 存放图片的文件夹
            options.setExtractor(new FileImageExtractor(new File(imagePathStr)));
            // html中图片的路径
            options.URIResolver(new BasicURIResolver("image"));
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(targetFileName), "utf-8");
            XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
            xhtmlConverter.convert(document, outputStreamWriter, options);
        } catch(Exception e){
           throw new Exception(e);
        } finally{
            try {
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
            }catch(Exception e){
                System.out.println("---2-------->"+e.getMessage());
            }

        }
        String htmlName2 = "";
        if(type == 1){
            htmlName2= + Calendar.getInstance().getTimeInMillis()+".html";
            HtmlUtil.replaceHtml(targetFileName,config.getTbl_surf_glb_mul_file_path()+"/"+htmlName2,HtmlUtil.returnJS());
        }else{
            htmlName2= + Calendar.getInstance().getTimeInMillis()+".html";
            HtmlUtil.replaceHtml(targetFileName,config.getTbl_surf_glb_mul_file_path()+"/"+htmlName2,HtmlUtil.returnDOCDownJS());
        }
        return config.getTbl_surf_html()+"/"+htmlName2;
    }

    /**
     * 设置页面大小及纸张方向 landscape横向
     * @param document
     * https://www.cnblogs.com/unruly/p/7552998.html
     * STPageOrientation.Enum stValue
     */
    public void setDocumentSize(XWPFDocument document) {
        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
        CTPageSz pgsz = sectPr.isSetPgSz() ? sectPr.getPgSz() : sectPr.addNewPgSz();
        pgsz.setW(new BigInteger("100%"));
        //pgsz.setOrient(stValue);
    }

    public static String getExtention(String fileName) {
        int pos = fileName.lastIndexOf(".");
        return fileName.substring(pos + 1);
    }

    //1代表编码   2代表 项目名称  3 代表挂牌下个   4代表 截至日期  5 代表 项目状态
    @RequestMapping(value = "/document/getDocumentTitle", method = RequestMethod.POST)
    public Object queryTableList(@RequestBody ProjectDocument paramDTO) {
        List<String> list = new LinkedList();
        list.add("2");
        list.add("3");
        list.add("4");
        return new Result("0", list);
    }



    /**
     * base64字符串转化成图片
     * @param imgStr
     * @param path
     * @return
     */
    public static boolean generateImage(String imgStr,String path)
    {   //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try
        {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            //生成jpeg图片
            String imgFilePath = path;//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public static void main(String[] args){
        String str = "我是一个中国共产党员";
        System.out.println(str.substring(0,8));
    }




    public String word2007ToHtmlTest2(File file,Integer type) throws Exception {
        String htmlName = + Calendar.getInstance().getTimeInMillis()+".html";
        String targetFileName = config.getTbl_surf_glb_mul_file_path()+ htmlName;
        String imagePathStr = config.getTbl_surf_glb_mul_file_path()+"/image/";
        OutputStreamWriter outputStreamWriter = null;
        try {

            if(file.getName().contains("docx")){
                docx(file.getPath(),"",targetFileName);
            }else{
                doc(file.getPath(),"",targetFileName);
            }
          /*  XWPFDocument document = new XWPFDocument(new FileInputStream(file));
            XHTMLOptions options = XHTMLOptions.create();
            // 存放图片的文件夹
            options.setExtractor(new FileImageExtractor(new File(imagePathStr)));
            // html中图片的路径
            options.URIResolver(new BasicURIResolver("image"));
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(targetFileName), "utf-8");
            XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
            xhtmlConverter.convert(document, outputStreamWriter, options);*/
        } catch(Exception e){
            System.out.println("-------1--------->"+e.getMessage());
        } finally{
            try {
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
            }catch(Exception e){
                System.out.println("---2-------->"+e.getMessage());
            }

        }
        String htmlName2 = "";
        if(type == 1){
            htmlName2= + Calendar.getInstance().getTimeInMillis()+".html";
            HtmlUtil.replaceHtml(targetFileName,config.getTbl_surf_glb_mul_file_path()+"/"+htmlName2,HtmlUtil.returnJS());
        }else{
            htmlName2= + Calendar.getInstance().getTimeInMillis()+".html";
            HtmlUtil.replaceHtml(targetFileName,config.getTbl_surf_glb_mul_file_path()+"/"+htmlName2,HtmlUtil.returnDOCDownJS());
        }
        return config.getTbl_surf_html()+"/"+htmlName2;
    }



    public static void docx(String filePath ,String fileName,String htmlName) throws Exception{
        final String file = filePath + fileName;
        File f = new File(file);
        // ) 加载word文档生成 XWPFDocument对象
        InputStream in = new FileInputStream(f);
        XWPFDocument document = new XWPFDocument(in);
        // ) 解析 XHTML配置 (这里设置IURIResolver来设置图片存放的目录)
        File imageFolderFile = new File(filePath);
        XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(imageFolderFile));
        options.setExtractor(new FileImageExtractor(imageFolderFile));
        options.setIgnoreStylesIfUnused(false);
        options.setFragment(true);
        // ) 将 XWPFDocument转换成XHTML
        OutputStream out = new FileOutputStream(new File(htmlName));
        XHTMLConverter.getInstance().convert(document, out, options);
    }
    /**
     * 转换doc
     * @param filePath
     * @param fileName
     * @param htmlName
     * @throws Exception
     */
    public static void doc(String filePath ,String fileName,String htmlName) throws Exception{
        final String file = filePath + fileName;
        InputStream input = new FileInputStream(new File(file));
        HWPFDocument wordDocument = new HWPFDocument(input);
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
        //解析word文档
        wordToHtmlConverter.processDocument(wordDocument);
        Document htmlDocument = wordToHtmlConverter.getDocument();

        File htmlFile = new File(htmlName);
        OutputStream outStream = new FileOutputStream(htmlFile);

        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(outStream);

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer serializer = factory.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");

        serializer.transform(domSource, streamResult);
        outStream.close();
    }


}
