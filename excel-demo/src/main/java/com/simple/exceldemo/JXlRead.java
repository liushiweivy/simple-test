package com.simple.exceldemo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

public  class JXlRead{

	/**
	 * 通过导入excel文件，读出每个单元格的内容。
	 * InputStream来自于文件上传时的MultipartFile对象
	 */
	public List<Shipment> readXls(InputStream is,Integer userId,Integer prodId) throws IOException,
		InvalidFormatException {
		List<Shipment> shipments=new ArrayList<Shipment>();
		HSSFWorkbook book = new HSSFWorkbook(is);
		HSSFSheet sheet = book.getSheetAt(0);

		/**
		 * 通常第一行都是标题，所以从第二行开始读取数据
		 */
		for(int i=1; i<sheet.getLastRowNum()+1; i++) {
			HSSFRow row = sheet.getRow(i);
			row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
			row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
			row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
			String uuid = row.getCell(0).getStringCellValue(); //名称
			String dealerName = row.getCell(1).getStringCellValue(); //url
			String prodName = row.getCell(2).getStringCellValue();
			Shipment shipment=new Shipment();
//			shipment.setUuid(uuid);
//			shipment.setDealerName(dealerName);
//			shipment.setProdName(prodName);
//			shipment.setUserId(userId);
//			shipment.setProdId(prodId);
//			shipment.setCreated(new Date());
//			shipment.setUpdated(new Date());
//
//
//			shipments.add(shipment);
		}

		for (Shipment shipment : shipments) {
			System.err.println(shipment);
		}

		return shipments;

	}
}