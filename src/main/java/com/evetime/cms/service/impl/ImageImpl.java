package com.evetime.cms.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.annotation.Resource;


import com.evetime.cms.service.ImageService;
import com.evetime.cms.util.OSSManageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

@Service("imageImpl")
public class ImageImpl implements ImageService {

	@Resource(name="client")
	private OSSClient client;
	
	public OSSClient getClient() {
		return client;
	}

	public void setClient(OSSClient client) {
		this.client = client;
	}

	
	private static final String bucketName="bj-evetime";
	private static final String filePath = "shopimage/";

	@Override
	@Transactional
	public String UploadImage(String filepath,String filename) {
		//String bucketName = "bj-evetime";
		String resultjson=null;
		File file = new File(filepath);
		InputStream content;
		try {
			content = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("上传文件打开出错！");
			content=null;
		}
		if(content!=null){
			// 创建上传Object的Metadata
			ObjectMetadata meta = new ObjectMetadata();
			// 必须设置ContentLength
			meta.setContentLength(file.length());//该Object大小
			meta.setLastModified(new Date());//最近修改时间
			meta.setContentDisposition(filename);//指定该Object被下载时的名称
			meta.addUserMetadata("x-oss-server-side-encryption", "AES256");//表示服务器段对上传得文件进行加密
			//meta.setContentType("application/pdf");// 该Object文件类型 
			// 该Object文件类型 
			meta.setContentType(OSSManageUtil.contentType(file.getName().substring(file.getName().lastIndexOf("."))));
			
		    // 上传Object.
		    PutObjectResult result = client.putObject(bucketName,filename/*上传的文件名称*/, content, meta);
		    result.getETag();
		    //resultjson=JsonUtil.StringParamToJson("result", "success");
		    
		}else{
			//resultjson=JsonUtil.StringParamToJson("result", "faile");
		}
		return resultjson;
	}

	@Override
	@Transactional
	public String UploadImage(InputStream content, String filename,
			String id) throws IOException {
		//String bucketName = "bj-evetime";
		String resultjson=null;
	//	File file = new File(filepath);
	  
//		InputStream content;
//		try {
//			content = new FileInputStream(file);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//			System.out.println("上传文件打开出错！");
//			content=null;
//		}
		if(content!=null){
			// 创建上传Object的Metadata

			ObjectMetadata meta = new ObjectMetadata();
			// 必须设置ContentLength
		
			meta.setLastModified(new Date());//最近修改时间
			meta.setContentDisposition(filename);//指定该Object被下载时的名称
			meta.addUserMetadata("x-oss-server-side-encryption", "AES256");//表示服务器段对上传得文件进行加密
			//meta.setContentType("application/pdf");// 该Object文件类型 
			// 该Object文件类型 
			meta.setContentType(OSSManageUtil.contentType("jpeg"));

		    // 上传Object
		    PutObjectResult result = client.putObject(bucketName,filePath+filename/*上传的文件名称*/, content, meta);
		
           result.getETag();
		    //resultjson=JsonUtil.StringParamToJson("result", "success");
	       String alUrl="http://bj-evetime.oss-cn-shenzhen.aliyuncs.com/"+filePath+filename;
	      
			//imageDao.insertPicture(id, alUrl);
			resultjson = alUrl;

	       
		}else{
			resultjson="";
		}
		return resultjson;
	
	}

}
