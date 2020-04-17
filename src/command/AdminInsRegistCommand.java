package command;

import java.io.File;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.lec.beans.AdminClassDAO;

public class AdminInsRegistCommand implements Command {

	String ins_name, ins_tel1, ins_tel2, ins_tel3, ins_tel, ins_img;
	String ins_add1, ins_add2, ins_location, ins_branch;
	Double ins_x, ins_y;
	int ins_zip;

    
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		AdminClassDAO dao = new AdminClassDAO();
		
		int result_cnt = 0;
		
		response.setContentType("text/html; charset=UTF-8");

		try {	
			ServletContext context = request.getServletContext();
			String contextRootPath = context.getRealPath("admin/ins/img"); 
			System.out.println(contextRootPath);
			 //1. 메모리나 파일로 업로드 파일 보관하는 FileItem의 Factory 설정
            DiskFileItemFactory diskFactory = new DiskFileItemFactory(); //디스크 파일 아이템 공장
            diskFactory.setSizeThreshold(4096); //업로드시 사용할 임시 메모리
            diskFactory.setRepository(new File(contextRootPath + "/WEB-INF/temp")); //임시저장폴더

            //2. 업로드 요청을 처리하는 ServletFileUpload생성
            ServletFileUpload upload = new ServletFileUpload(diskFactory);
            upload.setSizeMax(3 * 1024 * 1024); //3MB : 전체 최대 업로드 파일 크기
            
            //3. 업로드 요청파싱해서 FileItem 목록구함
            List<FileItem> items = upload.parseRequest(request); 
            
            Iterator<FileItem> iter = items.iterator(); //반복자(Iterator)로 받기​            
            while(iter.hasNext()) { //반목문으로 처리​    
                FileItem item = (FileItem) iter.next(); //아이템 얻기
             //4. FileItem이 폼 입력 항목인지 여부에 따라 알맞은 처리
            if(item.isFormField()){ //파일이 아닌경우
                processFormField(item);
                
            } else { //파일인 경우
            	this.ins_img = processUploadFile(item, contextRootPath);
            }
            }
            
            ins_tel = ins_tel1+"-"+ins_tel2+"-"+ins_tel3;
            ins_img = "/Project_itmoa/admin/ins/img/" + ins_img;
            
            result_cnt = dao.insertIns(ins_name, ins_zip, ins_add1, ins_add2, ins_tel, ins_img, ins_branch, ins_location, ins_x, ins_y);
            
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("result", result_cnt);

	}

	
	//업로드한 정보가 파일인경우 처리
    private String processUploadFile(FileItem item, String contextRootPath) 
            throws Exception {
//        String name = item.getFieldName(); //파일의 필드 이름 얻기
        String fileName = item.getName(); //파일명 얻기
//        String contentType = item.getContentType();//컨텐츠 타입 얻기
//       long fileSize = item.getSize(); //파일의 크기 얻기
        
        //업로드 파일명을 현재시간으로 변경후 저장
        String fileExt = fileName.substring(fileName.lastIndexOf("."));
        String uploadedFileName = System.currentTimeMillis() + fileExt; 
        System.out.println(fileExt);
        
        //저장할 절대 경로로 파일 객체 생성
        File uploadedFile = new File(contextRootPath+"/"+uploadedFileName);
        item.write(uploadedFile); //파일 저장
        
        System.out.println("실제저장경로 : "+uploadedFile.getPath());
        System.out.println("파일명 :" + uploadedFileName);
        return uploadedFileName;
        
    }
    
    
    private void processFormField(FileItem item)throws Exception{
    	
        String name = item.getFieldName(); //필드명 얻기
        String value = item.getString("UTF-8");
   
        if(name.equals("ins_name")) {
        	this.ins_name = value;
        }else if(name.equals("ins_tel1")) {
        	this.ins_tel1 = value;
        }else if(name.equals("ins_tel2")) {
        	this.ins_tel2 = value;
        }else if(name.equals("ins_tel3")) {
        	this.ins_tel3 = value;
        }else if(name.equals("ins_zip")) {
        	this.ins_zip = Integer.parseInt(value);
        }else if(name.equals("ins_add1")) {
        	this.ins_add1 = value;
        }else if(name.equals("ins_add2")) {
        	this.ins_add2 = value;
        }else if(name.equals("ins_location")) {
        	this.ins_location = value;
        }else if(name.equals("ins_branch")) {
        	this.ins_branch = value;
        }else if(name.equals("ins_x")) {
        	this.ins_x = Double.parseDouble(value); 
        }else if(name.equals("ins_y")) {
        	this.ins_y = Double.parseDouble(value); 
        }

    }
    


}
