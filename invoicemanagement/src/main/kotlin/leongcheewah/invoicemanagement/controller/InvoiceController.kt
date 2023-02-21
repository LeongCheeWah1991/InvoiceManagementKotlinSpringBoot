package leongcheewah.invoicemanagement.controller

import leongcheewah.invoicemanagement.model.InvoiceVO
import leongcheewah.invoicemanagement.service.InvoiceService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/invoice")
@CrossOrigin(origins = arrayOf("http://localhost:3000"), allowCredentials = "true")
class InvoiceController : BaseController() {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    private val invoiceSvc: InvoiceService? = null

    @GetMapping("/")
    fun getInvoice(): ResponseEntity<Any> {
        logger.info("Controller: {}.{} is started", this.javaClass, "getInvoice")

        var invoiceList = invoiceSvc?.getInvoices()

        logger.info("Controller: {}.{} is ended", this.javaClass, "getInvoice")
        return ResponseEntity.status(HttpStatus.OK).body(invoiceList);
    }

    @PostMapping("/create", produces = ["application/json"])
    fun createInvoice(@RequestBody invoiceData: InvoiceVO): ResponseEntity<Any> {
        logger.info("Controller: {}.{} is started", this.javaClass, "createInvoice")

        invoiceSvc?.createInvoice(invoiceData)
        val returnMsgObj: MutableMap<String, Any> = HashMap()
        returnMsgObj["message"] = "Success"

        logger.info("Controller: {}.{} is ended", this.javaClass, "createInvoice")
        return ResponseEntity.status(HttpStatus.OK).body(returnMsgObj);
    }

    @PostMapping("/upload", consumes = ["multipart/form-data"], produces = ["application/json"])
    fun uploadInvoices(@RequestParam("file") file: MultipartFile): ResponseEntity<Any> {
        logger.info("Controller: {}.{} is started", this.javaClass, "uploadInvoices")

        val returnMsgObj: MutableMap<String, Any> = HashMap()
        invoiceSvc?.uploadInvoices(file)
        returnMsgObj["message"] = "Success"

        logger.info("Controller: {}.{} is ended", this.javaClass, "uploadInvoices")
        return ResponseEntity.status(HttpStatus.OK).body(returnMsgObj)
    }
}