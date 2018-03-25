package com.emazdoor.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.os.Environment;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class JExcelClass {

	public JExcelClass() {
	};

	public void createNewWorkbook(String path) {
		File sdCard = Environment.getExternalStorageDirectory();
		File dir = new File(sdCard.getAbsolutePath() + "/data/test");
		dir.mkdir();
		try {
			FileOutputStream f = new FileOutputStream(new File(dir, path));
			WritableWorkbook work = Workbook.createWorkbook(f);
			WritableSheet sheet = work.createSheet("First sheet", 0);
			Label label = new Label(0, 0, "A label record");
			sheet.addCell(label);
			work.write();
			work.close();
		} catch (RowsExceededException e) {
		} catch (WriteException e) {
		} catch (IOException e) {
		}
	}

	public void writeToExisting(String path, int row, int column, String apl) {
		try {
			File sdCard = Environment.getExternalStorageDirectory();
			File dir = new File(sdCard.getAbsolutePath() + "/data/test");
			dir.mkdir();
			Workbook workbook = Workbook
					.getWorkbook(new File(dir + "/" + path));
			WritableWorkbook copy = Workbook.createWorkbook(new File(dir + "/"
					+ path), workbook);
			WritableSheet sheet = copy.getSheet(0);
			Label label = new Label(column, row, apl);
			sheet.addCell(label);
			copy.write();
			copy.close();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		}
	}

	public List<String> readExcelSheet(String path, int type) {
		List<String> a1 = new ArrayList<String>();
		try {
			File sdCard = Environment.getExternalStorageDirectory();
			File dir = new File(sdCard.getAbsolutePath() + "/data/test");
			Workbook workbook = Workbook
					.getWorkbook(new File(dir + "/" + path));
			Sheet sheet = workbook.getSheet(0);
			int rows = sheet.getRows() - 1;
			if (type == 0) {
				for (int i = 1; i <= rows; i++) {
					if (!sheet.getCell(1, i).getContents().equals("")) {
						a1.add(sheet.getCell(1, i).getContents());
					}
				}
			} else {
				for (int i = 1; i <= rows; i++) {
					if (!sheet.getCell(0, i).getContents().equals("")) {
						a1.add(sheet.getCell(0, i).getContents());
					}
				}
			}
			return a1;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
		}
		return a1;
	}

	public int findEmptyRow(int row, String path) {
		int emptyCell = 0;
		try {
			File sdCard = Environment.getExternalStorageDirectory();
			File dir = new File(sdCard.getAbsolutePath() + "/data/test");
			Workbook workbook = Workbook
					.getWorkbook(new File(dir + "/" + path));
			Sheet sheet = workbook.getSheet(0);
			Cell cells[] = sheet.getRow(row);
			for (int i = 0; i <= cells.length - 1; i++) {
				// String cellContent = cells[i].getContents();
				// if (cellContent == null) {
				// System.out.println("cell is null at " + i);
				// } else if (cellContent.length() == 0) {
				// System.out.println("this is cell empty at " + i);
				// break;
				// } else {
				// System.out.println("this is cell is not empty at " + i
				// + " and content is " + cellContent);
				// }
				emptyCell = i;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		}
		return emptyCell + 1;
	}

	public int findEmptyColumn(int column, String path) {
		int emptyCell = 0;
		try {
			File sdCard = Environment.getExternalStorageDirectory();
			File dir = new File(sdCard.getAbsolutePath() + "/data/test");
			Workbook workbook = Workbook
					.getWorkbook(new File(dir + "/" + path));
			Sheet sheet = workbook.getSheet(0);
			Cell cells[] = sheet.getColumn(0);
			for (int i = 0; i <= cells.length - 1; i++) {
				// String cellContent = cells[i].getContents();
				// if (cellContent == null) {
				// System.out.println("cell is null at " + i + " "
				// + cellContent);
				// } else if (cellContent.length() == 0) {
				// System.out.println("this is cell empty at " + i + " "
				// + cellContent);
				// emptyCell = i;
				// } else {
				// System.out.println("this is cell is not empty at " + i
				// + " " + cellContent);
				// }
				emptyCell = i;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
		}
		return emptyCell + 1;
	}
}
