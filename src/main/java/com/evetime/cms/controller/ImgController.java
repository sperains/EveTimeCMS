package com.evetime.cms.controller;

import com.evetime.cms.entity.ResultData;
import com.evetime.cms.service.ImageService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

/**
 * Created by Rains on 2016-04-18.
 */
@Controller("ImgContoller")
@RequestMapping("/img")
public class ImgController {

    @Resource(name = "imageImpl")
    private ImageService imageServiceImpl;


    @RequestMapping("/upload")
    @ResponseBody
    public ResultData uploadImg(HttpServletRequest request ) throws IOException, FileUploadException {

        ResultData result = new ResultData();

        /*String savePath = request.getServletContext().getRealPath("/WEB-INF/upload");
        File file = new File(savePath);
        //判断上传文件的保存目录是否存在
        if (!file.exists() && !file.isDirectory()) {
            System.out.println(savePath+"目录不存在，需要创建");
            //创建目录
            file.mkdir();
        }*/
        //消息提示
        //String message = "";
        try{
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            //解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8");

            //3、判断提交上来的数据是否包含文件数据
            if(!ServletFileUpload.isMultipartContent(request)){
                //普通表单,按标准方式获取数据
                return result ;
            }
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(request);
            String brandName = "";
            for(FileItem item : list){
            //如果fileitem中封装的是普通输入项的数据
                if(item.isFormField()){
                    String name = item.getFieldName();
                    //解决普通输入项的数据的中文乱码问题
                    String value = item.getString("UTF-8");
                    //value = new String(value.getBytes("iso8859-1"),"UTF-8");
                    brandName = value;
                }else{//如果fileitem中封装的是上传文件
                    //得到上传的文件名称，
                    String filename = item.getName();
                    if(filename==null || filename.trim().equals("")){
                        result.setSuccess(false);
                        result.setMessage("请选择图片");
                        continue;
                    }
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如： c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    filename = filename.substring(filename.lastIndexOf("\\")+1);
                    //获取item中的上传文件的输入流
                    InputStream in = item.getInputStream();



                    String url = imageServiceImpl.UploadImage(in, filename, brandName);
                    item.delete();
                    in.close();
                    //关闭输出流
                    result.setSuccess(true);
                    result.setMessage("文件上传成功");
                    result.setData(url);
                }
            }
        }catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("文件上传失败！");
        }
        return result;

    }

}
