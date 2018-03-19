package com.kfit;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class FileUploadController {
	
	//访问路径为：http://127.0.0.1:8080/file
	@RequestMapping("/file")
	public String file(){
		return "/file";
	}
	
	/**
	 * 文件上传具体实现方法;
	 * @param file
	 * @return
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public String handleFileUpload(@RequestParam("file")MultipartFile file){
		
			if (!file.isEmpty()) {  
	            String saveFileName = file.getOriginalFilename();  
	            File saveFile = new File("D:\\student\\"+ saveFileName);  
	            if (!saveFile.getParentFile().exists()) {  
	                saveFile.getParentFile().mkdirs();  
	            }  
	            try {  
	                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));  
	                out.write(file.getBytes());  
	                out.flush();  
	                out.close();  
	                return " 上传成功";  
	            } catch (FileNotFoundException e) {  
	                e.printStackTrace();  
	                return "上传失败" ;  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	                return "上传失败" ;  
	            }  
	        } else {  
	            return "上传失败，因为文件为空";  
	        }
	}
		
	
	///////////////////多文件上传.///////////////////////////
	//访问：http://127.0.0.1:8080/mutifile
	@RequestMapping(value = "/mutifile", method = RequestMethod.GET)
	public String provideUploadInfo1() {
		return "/mutifile";
	}
	
	/**
	 * 多文件具体上传时间，主要是使用了MultipartHttpServletRequest和MultipartFile
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/batch/upload", method= RequestMethod.POST)  
    public @ResponseBody  
    String handleFileUpload1(HttpServletRequest request){  
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("file");  
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i =0; i< files.size(); ++i) {  
            file = files.get(i);  
            if (!file.isEmpty()) {  
                try {  
                    byte[] bytes = file.getBytes();  
                    stream =  
                            new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));  
                    stream.write(bytes);  
                    stream.close();  
                } catch (Exception e) {  
                	stream =  null;
                    return "You failed to upload " + i + " => " + e.getMessage();  
                }  
            } else {  
                return "You failed to upload " + i + " because the file was empty.";  
            }  
        }  
        return "upload successful";  
    }  
	
}
