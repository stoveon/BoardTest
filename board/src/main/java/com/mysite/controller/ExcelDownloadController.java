package com.mysite.controller;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.service.MemberServiceImple;
import com.mysite.vo.MemberVo;

@Controller
public class ExcelDownloadController {
	@Autowired
	private MemberServiceImple memberService;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);	
	private static final String filePath = "C://MemberFile";
	private static int excelNum = 1;
	
	@RequestMapping(value="/downDoc")
	public void excelDownload(@RequestParam(required=false) String searchWord, HttpServletResponse response) throws Exception {
		logger.info("파일 다운로드 요청");
		List<MemberVo> memberList;
		if(searchWord != null) {
			memberList = memberService.searchMember(searchWord);
		}else {
			memberList = memberService.memberListAll();
		}
		for(MemberVo m : memberList) {
			System.out.println(m.toString());
		}
		
		Date todayTime = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		String str = sdf.format(todayTime);
       	String fileName = "result_" + str + ".xls";
       	
		//Workbook workbook = new HSSFWorkbook();	//Excel 97 ~ 2003 버전인 경우
		//XSSFWorkbook workbook = new XSSFWorkbook();	//Excel 2007 이상인 경우
		 SXSSFWorkbook workbook = new SXSSFWorkbook();		//Excel 2007 이상, 대용량 Excel 처리에 적합하며 쓰기 전용.
	         
		 SXSSFSheet sheet = workbook.createSheet("MEMBERLIST");
	         
	        Iterator<MemberVo> iterator = memberList.iterator();
	        
	        //테이블 헤더용 스타일
	        CellStyle headStyle = workbook.createCellStyle();
	        //데이터 가운데 정렬
	        headStyle.setAlignment(HorizontalAlignment.CENTER);
	        // 가는 경계선
	        headStyle.setBorderTop(BorderStyle.THIN);
	        headStyle.setBorderBottom(BorderStyle.THIN);
	        headStyle.setBorderLeft(BorderStyle.THIN);
	        headStyle.setBorderRight(BorderStyle.THIN);
	        //배경색
	        headStyle.setFillForegroundColor(HSSFColorPredefined.GREY_25_PERCENT.getIndex());
	        headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        
	        CellStyle bodyStyle = workbook.createCellStyle();
	        bodyStyle.setAlignment(HorizontalAlignment.CENTER);
	        bodyStyle.setBorderTop(BorderStyle.THIN);
	        bodyStyle.setBorderBottom(BorderStyle.THIN);
	        bodyStyle.setBorderLeft(BorderStyle.THIN);
	        bodyStyle.setBorderRight(BorderStyle.THIN);
	         
	        int rowIndex = 0;
	        int excelname=0; // 처음에는 ID 학번등 고정값을 넣기 위해 사용한 변수
	        do{
	        	MemberVo member = iterator.next();
	        	SXSSFRow row = sheet.createRow(rowIndex++);
	           
	            
	            if(excelname==0){ // 처음에 고정값 
	                 
	            	SXSSFCell cell0 = row.createCell(0);
	            	cell0.setCellStyle(headStyle);
	                cell0.setCellValue("No");
	                SXSSFCell cell1 = row.createCell(1);
	            	cell1.setCellStyle(headStyle);
	                cell1.setCellValue("직원번호");
	                SXSSFCell cell2 = row.createCell(2);
	            	cell2.setCellStyle(headStyle);
	                cell2.setCellValue("이름");
	                SXSSFCell cell3 = row.createCell(3);
	            	cell3.setCellStyle(headStyle);
	                cell3.setCellValue("전화번호");
	                SXSSFCell cell4 = row.createCell(4);
	            	cell4.setCellStyle(headStyle);
	                cell4.setCellValue("직급");
	                SXSSFCell cell5 = row.createCell(5);
	            	cell5.setCellStyle(headStyle);
	                cell5.setCellValue("이메일");
	                excelname++;
	                
	            }else{  // 다음부터는 순차적으로 값이 들어감 
	                 
	            	SXSSFCell cell0 = row.createCell(0);
	            	cell0.setCellStyle(bodyStyle);
	                cell0.setCellValue(excelNum++);
	                SXSSFCell cell1 = row.createCell(1);
	            	cell1.setCellStyle(bodyStyle);
	                cell1.setCellValue(member.getNum());
	                SXSSFCell cell2 = row.createCell(2);
	            	cell2.setCellStyle(bodyStyle);
	                cell2.setCellValue(member.getName());
	                SXSSFCell cell3 = row.createCell(3);
	            	cell3.setCellStyle(bodyStyle);
	                cell3.setCellValue(member.getPhone());
	                SXSSFCell cell4 = row.createCell(4);
	            	cell4.setCellStyle(bodyStyle);
	                cell4.setCellValue(member.getMemberRank());
	                SXSSFCell cell5 = row.createCell(5);
	            	cell5.setCellStyle(bodyStyle);
	                cell5.setCellValue(member.getEmail());
	                
	            }
	            
	            
	        }while(iterator.hasNext());

	        //lets write the excel data to file now
	        FileOutputStream fos = new FileOutputStream(fileName);
	        
	        response.setContentType("ms-vnd/excel");
	        response.setHeader("Content-Disposition", "attachment;filename="+fileName);
	        
	        workbook.write(fos);
	        fos.close();
	        workbook.close();		//HSSFWorkbook, XSSFWorkbook 사용시 사용
	        //workbook.dispose();		//SXSSFWorkbook 사용시 사용
	}
}
