package com.evetime.cms.service;

import java.io.IOException;
import java.io.InputStream;

public interface ImageService {

	public String UploadImage(String filepath, String filename);
	
	public String UploadImage(InputStream content, String filename, String goodsId) throws IOException;
}
