package leongcheewah.invoicemanagement.service

import leongcheewah.invoicemanagement.entity.Invoice
import leongcheewah.invoicemanagement.exception.InvoiceException
import leongcheewah.invoicemanagement.model.InvoiceVO
import leongcheewah.invoicemanagement.repository.InvoiceRepository
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.apache.commons.csv.CSVRecord
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedReader
import java.io.InputStreamReader
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.util.stream.Collectors


@Service
class InvoiceService() : IInvoiceService {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    private val invoiceRepository: InvoiceRepository? = null

    override fun getInvoices(): List<Invoice> {
        logger.info("Service: {}.{} is started", this.javaClass, "getInvoices")

        var invoiceList: List<Invoice>
        invoiceList = invoiceRepository?.findAll() as List<Invoice>;

        logger.info("Service: {}.{} is ended", this.javaClass, "getInvoices")
        return invoiceList
    }

    override fun createInvoice(invoiceData: InvoiceVO) {
        logger.info("Service: {}.{} is started", this.javaClass, "createInvoice")

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
            logger.error("Error", ex)
            throw InvoiceException("Failed to create Invoice");
        }

        logger.info("Service: {}.{} is ended", this.javaClass, "createInvoice")
    }

    override fun uploadInvoices(file: MultipartFile) {
        logger.info("Service: {}.{} is started", this.javaClass, "uploadInvoices")

        try {

            var csvRecords = parseCsv(file)
            println(csvRecords?.size)

            if (csvRecords == null || csvRecords.isEmpty()) {
                throw InvoiceException("CSV File is empty");
            }
            val csvInvoiceNoSet: MutableSet<String?> = HashSet()
            val invoiceList: MutableList<Invoice> = ArrayList()

            for (csvRecord in csvRecords) {
                csvInvoiceNoSet.add(csvRecord["InvoiceNo"])

                val createInvoice = Invoice()
                createInvoice.invoiceNo = csvRecord["InvoiceNo"]
                createInvoice.invoiceDate = let { convertDateStrToDate(csvRecord["InvoiceDate"]) }
                createInvoice.customerId = csvRecord["CustomerID"]
                createInvoice.country = csvRecord["Country"]
                createInvoice.stockCode = csvRecord["StockCode"]
                createInvoice.description = csvRecord["Description"]
                createInvoice.quantity = csvRecord["Quantity"].toInt()
                createInvoice.unitPrice = csvRecord["UnitPrice"].toDouble()
                invoiceList.add(createInvoice)
            }
            invoiceRepository?.saveAll(invoiceList);

        } catch (ex: Exception) {
            logger.error("Error", ex)
            throw InvoiceException("Failed to upload Invoices");
        }

        logger.info("Service: {}.{} is ended", this.javaClass, "uploadInvoices")
    }

    fun convertDateStrToDate(invoiceDate: String): LocalDateTime {
        val formatter = DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("M/d/yyyy H:m"))
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