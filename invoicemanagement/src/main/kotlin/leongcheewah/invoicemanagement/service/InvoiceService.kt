package leongcheewah.invoicemanagement.service

import leongcheewah.invoicemanagement.entity.Invoice
import leongcheewah.invoicemanagement.model.InvoiceVO
import leongcheewah.invoicemanagement.repository.InvoiceRepository
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.apache.commons.csv.CSVRecord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedReader
import java.io.InputStreamReader
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder


@Service
class InvoiceService() : IInvoiceService {

    @Autowired
    private val invoiceRepository: InvoiceRepository? = null

    override fun getInvoices(): List<Invoice> {
        var invoiceList : List<Invoice>
        invoiceList = invoiceRepository?.findAll() as List<Invoice>;
        return invoiceList
    }

    override fun createInvoice(invoiceData: InvoiceVO) {
        println("start InvoiceService:createInvoice")
        try {
            var invoice = Invoice();
            invoice.invoiceNo = invoiceData.invoiceNo;
            invoice.invoiceDate = invoiceData.invoiceDate?.let { convertDateStrToDate(it) }
            invoice.customerId = invoiceData.customerId;
            invoice.country = invoiceData.country;
            invoice.stockCode = invoiceData.stockCode;
            invoice.description = invoiceData.description;
            invoice.quantity = invoiceData.quantity;
            invoice.unitPrice = invoiceData.unitPrice;

            invoiceRepository?.save(invoice);
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        println("end InvoiceService:createInvoice")

    }

    override fun uploadInvoices(file: MultipartFile) {
        val csvRecords = parseCsv(file)
        println(csvRecords?.size)
    }

    fun convertDateStrToDate(invoiceDate: String): LocalDateTime {
        val formatter = DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("MM/d/yyyy H:m"))
            .appendOptional(DateTimeFormatter.ofPattern("d-MM-yy H:m"))
            .toFormatter()

        var formattedDateTime = LocalDateTime.parse(invoiceDate, formatter)

        return formattedDateTime;
    }

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
            return null
        }
        return csvRecords
    }
}