package org.spring.springboot.controller;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.PictureType;
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
import org.spring.springboot.utils.ServiceCallByHessian;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
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
    private ServiceCallByHessian serviceCallByHessian;
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


  /*  @RequestMapping("/addTemplateUpLoad")
    @ResponseBody
    public Object addTemplate(@RequestParam(value = "file", required = false) MultipartFile file)
    {
        if(file==null){
            throw new BusinessException(ErrorCode.NO_IREPORT_FILE,"请选择模板在上传");
        }
        UploadFileResponseDTO message = new UploadFileResponseDTO();
        DocumentUpLoadDTO  documentUpLoadDTO = new  DocumentUpLoadDTO();

        try {
            String fileName = file.getOriginalFilename();
            String fileExt = getExtention(fileName);
            byte[] fileByte = file.getBytes();
            GozapServiceResult result = serviceCallByHessian.sendByHessian(fileExt,fileByte);
            message = getResult(result);
            String url = message.getUrl();
            documentUpLoadDTO.setDocumentName(fileName);
            documentUpLoadDTO.setUrlPath(url);
        } catch (IOException e) {
            message.setMsg("001");
            message.setSuccess(false);
            return new Result("001", e.getMessage());
        }
        return new Result("0", documentUpLoadDTO);
    }*/




    @RequestMapping(value = "/templateDownLoad", method = RequestMethod.GET)
    public void download(@PathParam("url") String url, HttpServletResponse httpResponse) {
        OutputStream outputStream = null;
        BufferedInputStream bis = null;
        HttpURLConnection httpUrl = null;
        URL destUrl = null;
        try {
            String decoderUrl = URLDecoder.decode(url, "utf-8");
            String imageUrl = imageProperties.getDownLoadHosts() + "/" + decoderUrl;
            String name = imageUrl.substring(imageUrl.lastIndexOf('/'), imageUrl.length());
            byte[] buf = new byte[1024];
            int size = 0;
            destUrl = new URL(imageUrl);
            httpUrl = (HttpURLConnection) destUrl.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            httpResponse.setContentType("octets/stream");
            httpResponse.addHeader("Content-Type", "text/html; charset=utf-8");
            String downLoadName = new String(name.getBytes("gbk"), "iso8859-1");
            httpResponse.setHeader("Content-Disposition", "attachment;fileName=" + downLoadName);
            outputStream = httpResponse.getOutputStream();
            while ((size = bis.read(buf)) != -1) {
                outputStream.write(buf, 0, size);
            }
        } catch (Exception e) {
        } finally {
            try {
                if (outputStream != null)
                {
                    outputStream.close();
                }
                if (bis != null){
                    bis.close();
                }
                if (httpUrl != null){
                    httpUrl.disconnect();
                }
            } catch (IOException e) {
            } catch (NullPointerException e) {
            }
        }

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
        try {
            path = config.getTbl_surf_glb_mul_file_path() + file.getOriginalFilename();
            file.transferTo(new File(path));
            htmlPath = word2007ToHtml(new File(path));
        } catch (IOException e) {
            return new Result("001", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        documentUpLoadDTO.setDocumentName(file.getOriginalFilename());
        documentUpLoadDTO.setUrlPath(path);
        documentUpLoadDTO.setHtmlPath(htmlPath);
        return new Result("0", documentUpLoadDTO);
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

        String targetFileName = config.getTbl_surf_glb_mul_file_path()+ Calendar.getInstance().getTimeInMillis()+".html";
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
        return targetFileName;
    }

    public static String getExtention(String fileName) {
        int pos = fileName.lastIndexOf(".");
        return fileName.substring(pos + 1);
    }







  /*  public static UploadFileResponseDTO getResult(GozapServiceResult result){
        UploadFileResponseDTO message = new UploadFileResponseDTO();
        if(result.equals(GozapServiceResult.OK)) {
            String fileID = (String) result.getObject();
            message.setUrl(fileID);
            message.setCode("000");
            message.setMsg("000");
            message.setSuccess(true);
        }else{
            message.setMsg("001");
            message.setSuccess(false);
        }
        return message;
    }*/

}
