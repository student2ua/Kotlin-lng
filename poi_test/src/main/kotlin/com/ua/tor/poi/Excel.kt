package com.ua.tor.poi

import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

/**
 * User: tor
 * Date: 028, 28.04.2021
 * Time: 16:43
 * To change this template use File | Settings | File Templates.
 */
fun writeToExelFile(filepath: String) {
    val xLwb = XSSFWorkbook()
    val xlws = xLwb.createSheet()
    val rowNumber = 0
    val columnNumber = 0
    xlws.createRow(rowNumber).createCell(columnNumber).setCellValue("Test")
    val outputStream = FileOutputStream(filepath)
    xLwb.write(outputStream)
    xLwb.close()
}

fun writeStyledToExelFile(filepath: String) {
    val COLUMNs = arrayOf("Id", "Name", "Address", "Age")

    val xLwb = XSSFWorkbook()
    val createHelper = xLwb.creationHelper
    val xlws = xLwb.createSheet()
    val headerFont = xLwb.createFont().apply {
        bold = true
        setColor(IndexedColors.BLUE.index)
    }
//    headerFont.bold = true
//    headerFont.setColor(IndexedColors.BLUE.index)
    val headerCellStyle = xLwb.createCellStyle()
    headerCellStyle.setFont(headerFont)

    val headerRow = xlws.createRow(0)
    for (col in COLUMNs.indices) {
        val cell = headerRow.createCell(col)
        cell.setCellValue(COLUMNs[col])
        cell.cellStyle = headerCellStyle
    }
    val ageCellStyle=xLwb.createCellStyle()
    ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"))

    val outputStream = FileOutputStream(filepath)
    xLwb.write(outputStream)
    xLwb.close()
}

fun readFromexelFile(filepath: String) {
    val inputStream = FileInputStream(filepath)
    val xlwb = WorkbookFactory.create(inputStream)
    val rowNumber = 0
    val columnNumber = 0
    val xlws = xlwb.getSheetAt(0)
    println(xlws.getRow(rowNumber).getCell(columnNumber))
}

@Throws(IOException::class)
fun main(args: Array<String>) {
    val filepath = "./test.xlsx"
    writeToExelFile(filepath)
    readFromexelFile(filepath)
}