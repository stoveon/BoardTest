package com.mysite.controller;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
       	String fileName = "result_" + str + ".xlsx";
       	
		//Workbook workbook = new HSSFWorkbook();	//Excel 97 ~ 2003 버전인 경우
		//Workbook workbook = new XSSFWorkbook();	//Excel 2007 이상인 경우
		 Workbook workbook = new SXSSFWorkbook();		//Excel 2007 이상, 대용량 Excel 처리에 적합하며 쓰기 전용.
	         
		 Sheet sheet = workbook.createSheet("MEMBERLIST");
	         
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
	        
	        Row row = null;
	        Cell cell = null;
        	row = sheet.createRow(rowIndex++);	        
        	cell = row.createCell(0);
        	cell.setCellStyle(headStyle);
        	cell.setCellValue("No");
        	cell = row.createCell(1);
        	cell.setCellStyle(headStyle);
        	cell.setCellValue("직원번호");
        	cell = row.createCell(2);
        	cell.setCellStyle(headStyle);
        	cell.setCellValue("이름");
        	cell = row.createCell(3);
        	cell.setCellStyle(headStyle);
        	cell.setCellValue("전화번호");
        	cell = row.createCell(4);
        	cell.setCellStyle(headStyle);
        	cell.setCellValue("직급");
        	cell = row.createCell(5);
        	cell.setCellStyle(headStyle);
        	cell.setCellValue("이메일");
        	
        	while(iterator.hasNext()){
	        	MemberVo member = iterator.next();
	        	row = sheet.createRow(rowIndex++);
	           
	            	cell = row.createCell(0);
	            	cell.setCellStyle(bodyStyle);
	            	cell.setCellValue(excelNum++);
	            	cell = row.createCell(1);
	            	cell.setCellStyle(bodyStyle);
	            	cell.setCellValue(member.getNum());
	            	cell = row.createCell(2);
	            	cell.setCellStyle(bodyStyle);
	            	cell.setCellValue(member.getName());
	            	cell = row.createCell(3);
	            	cell.setCellStyle(bodyStyle);
	            	cell.setCellValue(member.getPhone());
	            	cell = row.createCell(4);
	            	cell.setCellStyle(bodyStyle);
	            	cell.setCellValue(member.getMemberRank());
	            	cell = row.createCell(5);
	            	cell.setCellStyle(bodyStyle);
	            	cell.setCellValue(member.getEmail());
	        }
        	
        	sheet.autoSizeColumn(0);
        	sheet.autoSizeColumn(1);
        	sheet.autoSizeColumn(2);
        	sheet.autoSizeColumn(3);
        	sheet.autoSizeColumn(4);
        	sheet.autoSizeColumn(5);
        	
	        response.setContentType("Application/Msexcel");
	        response.setHeader("Content-Disposition", "attachment;filename="+fileName);
	        
	        workbook.write(response.getOutputStream());
	        workbook.close();
	        
	        response.getOutputStream().flush();
	        response.getOutputStream().close();
	}
}
