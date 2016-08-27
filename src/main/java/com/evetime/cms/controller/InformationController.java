package com.evetime.cms.controller;

import com.evetime.cms.entity.BusinessFile;
import com.evetime.cms.entity.BusinessInfo;
import com.evetime.cms.entity.ResultData;
import com.evetime.cms.service.BusinessInfoService;
import com.evetime.cms.util.UUIDGenerator;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Rains on 2016-07-25.
 */
@Controller
@RequestMapping("/information")
public class InformationController {

    @Resource(name = "BusinessInfoService")
    private BusinessInfoService businessInfoService;

    public BusinessInfoService getBusinessInfoService() {
        return businessInfoService;
    }

    public void setBusinessInfoService(BusinessInfoService businessInfoService) {
        this.businessInfoService = businessInfoService;
    }

    /**
     * 添加商户备份信息
     * @param request
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public ResultData addInformation(HttpServletRequest request ){

        ResultData result = new ResultData();

        //创建一个硬盘文件工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();

        //创建一个文件上传解析器
        ServletFileUpload upload = new ServletFileUpload(factory);

        //解决上传文件名中文乱码问题
        upload.setHeaderEncoding("UTF-8");

        //判断是否为复杂表单数据
        if(!ServletFileUpload.isMultipartContent(request)){
            //如果不是,按普通方式处理
        }

        List<BusinessFile> businessFileList = new ArrayList<BusinessFile>();
        BusinessInfo businessInfo = new BusinessInfo();
        String busiessInfoId = UUIDGenerator.getUUID();
        businessInfo.setId(busiessInfoId);

        try {
            List<FileItem> fileItems = upload.parseRequest(request);

            for(FileItem item:fileItems){
                if(item.isFormField()){//处理普通的表单数据

                    String fieldName = item.getFieldName();
                    String value = item.getString("UTF-8");
                    Field f = BusinessInfo.class.getDeclaredField(fieldName);
                    f.setAccessible(true);
                    f.set(businessInfo , value);


                }else{//处理上传的文件

                    //获取上传文件名(兼容性考虑,有的浏览器带文件路径)
                    String fileName  = item.getName();
                    fileName = fileName.substring(fileName.lastIndexOf("\\") + 1 );

                    //获取表单字段名
                    String fieldName = item.getFieldName();

                    if(fileName == null || "".equals(fileName)){
                        continue;
                    }

                    BusinessFile businessFile = new BusinessFile();

                    String fileId = UUIDGenerator.getUUID();
                    businessFile.setId(fileId);
                    businessFile.setFileName(fileName);
                    businessFile.setFieldName(fieldName);
                    businessFile.setContent(item.getInputStream());
                    businessFile.setFileSize(item.getSize());
                    businessFile.setBusinessInfoId(busiessInfoId);
                    businessFileList.add(businessFile);

                    Field f = BusinessInfo.class.getDeclaredField(fieldName);
                    f.setAccessible(true);
                    f.set(businessInfo , fileId );

                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
            return new ResultData(false , "文件上传失败");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new ResultData(false , "不支持的字符集");
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultData(false , "io异常");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return new ResultData(false ,"安全权限异常" );
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return new ResultData(false , "没有相应字段");
        }

        return businessInfoService.addInformation(businessInfo , businessFileList);
    }

    @RequestMapping("/sdownload")
    public void fileDownLoad(String id , HttpServletResponse response){

        businessInfoService.fileDownLoad(id , response);
    }


    /**
     * 使用springmvc进行文件下载
     * @param id
     * @return
     * @throws IOException
     */
    @RequestMapping("/download")
    public ResponseEntity download(String id) throws IOException {

        return businessInfoService.fileDownLoad(id);
    }


    @RequestMapping("/loadAll")
    @ResponseBody
    public ResultData loadAllInformations(){

        return businessInfoService.findAllInformations();
    }


    /**
     * 修改信息入口
     * @param request
     * @param businessInfo
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public ResultData editInformations(HttpServletRequest request , BusinessInfo businessInfo){



        ResultData result = new ResultData( );

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");


        if(!ServletFileUpload.isMultipartContent(request)){

            return businessInfoService.updateInformation(businessInfo);
        }

        List<BusinessFile> businessFileList = new ArrayList<BusinessFile>();

        try {
            List<FileItem> fileItemList = upload.parseRequest(request);

            for(FileItem item : fileItemList){

                if(item.isFormField()){ //处理表单字段
                    String fieldName = item.getFieldName();
                    String value = item.getString("UTF-8");
                    Field field = BusinessInfo.class.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    field.set(businessInfo , value);

                }else { //处理文件

                    BusinessFile businessFile = new BusinessFile();

                    //获取文件名
                    String fileName = item.getName();
                    String fieldName = item.getFieldName();
                    fileName = fileName.substring(fileName.lastIndexOf("\\") + 1 );

                    if(fileName == null || "".equals(fileName)){
                        continue;
                    }

                    businessFile.setFileName(fileName);
                    businessFile.setFieldName(fieldName);
                    businessFile.setFileSize(item.getSize());
                    businessFile.setContent(item.getInputStream());
                    businessFile.setBusinessInfoId(businessInfo.getId());
                    businessFileList.add(businessFile);

                }

            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * 如果列表为空则说明没有修改的文件. 则只需要更新普通form数据.
         */
        if(businessFileList.size() == 0){
            return businessInfoService.updateInformation(businessInfo);
        }

        return businessInfoService.updateInformation(businessInfo , businessFileList);
    }

    /**
     * 图片预览
     * @param request
     * @return
     */
    @RequestMapping("/preview")
    @ResponseBody
    public ResultData preview(HttpServletRequest request){

        ResultData result = new ResultData();

        String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/" + UUIDGenerator.getUUID() ;

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        InputStream in = null ;
        FileOutputStream out = null ;
        try {
            List<FileItem> fileItemList = upload.parseRequest(request);

            for(FileItem item : fileItemList){
                if(!item.isFormField()){

                    String fileName = item.getName();

                    if(fileName == null || "".equals(fileName)){
                        continue;
                    }
                    String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
                    filePath += fileSuffix ;
                    File saveDir = new File(filePath);

                    if (!saveDir.getParentFile().exists())
                        saveDir.getParentFile().mkdirs();
                    in =  item.getInputStream();
                    out = new FileOutputStream(saveDir);
                    byte[] buff = new byte[1024];
                    int d ;
                    while( (d = in.read(buff)) != -1){
                        out.write(buff , 0 , d);
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        result.setSuccess(true);
        result.setMessage("保存文件成功");
        result.setData(filePath);

        return result;
    }

    /**
     * 加载附件信息
     * @param businessInfoId
     * @return
     */
    @RequestMapping("loadAppendFile")
    @ResponseBody
    public ResultData loadAppendFile(String businessInfoId){

        if(businessInfoId == null || "".equals(businessInfoId)){
            return new ResultData(false , "参数错误");
        }

        return businessInfoService.loadAppendFile(businessInfoId);
    }

    /**
     * 删除备份信息(修改标识)
     * @param id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultData deleteInfo(String id){

        return businessInfoService.deleteInfo(id);
    }

    /**
     * 修改合约状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("updateContractStatus")
    @ResponseBody
    public ResultData updateContractStatus(String id , @RequestParam(value = "status")int status){

        return businessInfoService.updateContractStatus(id , status);
    }


}
