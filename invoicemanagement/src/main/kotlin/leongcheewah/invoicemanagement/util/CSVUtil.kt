package leongcheewah.invoicemanagement.util

import leongcheewah.invoicemanagement.exception.InvoiceException
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.apache.commons.csv.CSVRecord
import org.slf4j.LoggerFactory
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

object CSVUtil {

    private val logger = LoggerFactory.getLogger(javaClass)

    private const val CSV_FILE_CONTENTYPE = "text/csv"
    private val CSV_FILE_HEADERS: List<String> = ArrayList(
        listOf("InvoiceNo","StockCode","Description","Quantity","InvoiceDate","UnitPrice","CustomerID","Country")
    )

    @JvmStatic
    fun validateCSVFileFormat(file: MultipartFile): Boolean {
        return CSV_FILE_CONTENTYPE == file.contentType
    }

    @JvmStatic
    fun validateCsvHeaders(file: MultipartFile): Boolean {
        try {
            val fileReader = BufferedReader(InputStreamReader(file.inputStream, "UTF-8"))
            val csvParser = CSVParser(
                fileReader,
                CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim()
            )
            val csvHeaders = csvParser.headerNames
            csvParser.close()
            if (CSV_FILE_HEADERS.size != csvHeaders.size) {
                return false
            }
            for (expectedHeader in CSV_FILE_HEADERS) {
                if (!csvHeaders.contains(expectedHeader)) {
                    return false
                }
            }
        } catch (ex: Exception) {
            return false
        }
        return true
    }

    @JvmStatic
    fun parseCsv(file: MultipartFile): List<CSVRecord>? {
        var csvRecords: List<CSVRecord>? = null
        try {
            val fileReader = BufferedReader(InputStreamReader(file.inputStream, "UTF-8"))
            val csvParser = CSVParser(
                fileReader,
                CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim()
            )
            csvRecords = csvParser.records
            csvParser.close()
        } catch (ex: Exception) {
            ex.printStackTrace()
            logger.error("Error", ex)
            throw InvoiceException("Failed to create Invoice");
        }
        return csvRecords
    }
}