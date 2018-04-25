package org.spring.springboot.controller;


import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.spring.springboot.common.Result;
import org.spring.springboot.config.ConfigBean;
import org.spring.springboot.config.image.ImageProperties;
import org.spring.springboot.domain.DocumentUpLoadDTO;
import org.spring.springboot.domain.ProjectDocument;
import org.spring.springboot.domain.image.HttpResponseBean;
import org.spring.springboot.service.ProjectDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

import java.io.*;

import java.util.Calendar;
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
        List<ProjectDocument> list =  projectDocumentService.findAllDocumentAndProject(paramDTO);
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
        projectDocumentService.deleteDocumentAndProject(ids);
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
            path = config.getTbl_surf_glb_mul_file_path() + file.getOriginalFilename();
            file.transferTo(new File(path));
            htmlPath = word2007ToHtml(new File(path));
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



    @RequestMapping("/addTemplateUpLoadHeadImage")
    @ResponseBody
    public Object addTemplateUpLoadHeadImage(@RequestParam(value = "file", required = false) MultipartFile file)
    {
        if (file.isEmpty()) {
            return new Result("001", "");
        }
        //上传
        String path ="";
        try {
            path = config.getHeadImage() + file.getOriginalFilename();
            file.transferTo(new File(path));
        } catch (IOException e) {
            return new Result("001", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result("0", path);
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
        return config.getTbl_surf_html()+htmlName;
    }

    public static String getExtention(String fileName) {
        int pos = fileName.lastIndexOf(".");
        return fileName.substring(pos + 1);
    }






}
