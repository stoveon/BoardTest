package com.mysite.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.dao.MemberDaoImple;
import com.mysite.vo.MemberUpdateCommand;
import com.mysite.vo.MemberVo;

@Service("memberService")
public class MemberServiceImple implements IMemberService{
	@Autowired
	private MemberDaoImple memberDao;
	
	private static final String filePath = "C:\\download";
	private static int excelNum = 1;
	
	@Override
	public void memberRegist(MemberVo member) throws Exception {
		member.setMemberRank(member.getMemberRank().replace(" ", ""));
		member.setName(member.getName().replace(" ", ""));
		member.setEmail(member.getEmail().replace(" ", ""));
		member.setPhone(member.getPhone().replace(" ", ""));
		memberDao.memberRegist(member);
	}

	@Override
	public List<MemberVo> memberListAll() throws Exception {
		return memberDao.memberListAll();
	}

	@Override
	public MemberVo memberExist(int num) throws Exception {
		return memberDao.memberExist(num);
	}

	@Override
	public void memberDelete(int num) throws Exception {
		memberDao.memberDelete(num);
	}

	@Override
	public void memberUpdate(MemberUpdateCommand memUpCmd) throws Exception {
		memberDao.memberUpdate(memUpCmd);		
	}

	@Override
	public List<MemberVo> searchMember(String searchWord) throws Exception {
		searchWord = searchWord.replace(" ", "");
		return memberDao.searchMember(searchWord);
	}

	@Override
	public void WriteListToExcelFile(String fileName, List<MemberVo> noticeList) throws Exception {
		 HSSFWorkbook workbook = null;
         
		 	//새 엑셀 생성
	        if(fileName.endsWith("xlsx")){
	            workbook = new HSSFWorkbook();
	        }else if(fileName.endsWith("xls")){
	            workbook = new HSSFWorkbook();
	        }else{
	            throw new Exception("invalid file name, should be xls or xlsx");
	        }
	         
	        HSSFSheet sheet = workbook.createSheet("MEMBERLIST");
	         
	        Iterator<MemberVo> iterator = noticeList.iterator();
	         
	        int rowIndex = 0;
	        int excelname=0; // 처음에는 ID 학번등 고정값을 넣기 위해 사용한 변수
	        do{
	        	MemberVo member = iterator.next();
	        	HSSFRow row = sheet.createRow(rowIndex++);
	           
	            
	            if(excelname==0){ // 처음에 고정값 
	                 
	            	HSSFCell cell0 = row.createCell(0);
	                 cell0.setCellValue("No");
	                HSSFCell cell1 = row.createCell(1);
	                 cell1.setCellValue("직원번호");
	                HSSFCell cell2 = row.createCell(2);
	                 cell2.setCellValue("이름");
	                HSSFCell cell3 = row.createCell(3);
	                 cell3.setCellValue("전화번호");
	                HSSFCell cell4 = row.createCell(4);
	                 cell4.setCellValue("직급");
	                HSSFCell cell5 = row.createCell(5);
	                 cell5.setCellValue("이메일");
	                 excelname++;
	                
	            }else{  // 다음부터는 순차적으로 값이 들어감 
	                 
	            	HSSFCell cell0 = row.createCell(0);
	                 cell0.setCellValue(excelNum++);
	                HSSFCell cell1 = row.createCell(1);
	                 cell1.setCellValue(member.getNum());
	                HSSFCell cell2 = row.createCell(2);
	                 cell2.setCellValue(member.getName());
	                HSSFCell cell3 = row.createCell(3);
	                 cell3.setCellValue(member.getPhone());
	                HSSFCell cell4 = row.createCell(4);
	                 cell4.setCellValue(member.getMemberRank());
	                HSSFCell cell5 = row.createCell(5);
	                 cell5.setCellValue(member.getEmail());
	                
	            }
	            
	            
	        }while(iterator.hasNext());

	        File folderPath = new File(filePath);
	        if(!folderPath.exists()) {
	        	folderPath.mkdirs();
	        }
	        
	        //lets write the excel data to file now
	        FileOutputStream fos = new FileOutputStream(filePath+"\\"+fileName);
	        workbook.write(fos);
	        fos.close();
	}

}
