package leongcheewah.invoicemanagement.controller

import leongcheewah.invoicemanagement.model.InvoiceVO
import leongcheewah.invoicemanagement.service.InvoiceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/invoice")
class InvoiceController {

    @Autowired
    private val invoiceSvc: InvoiceService? = null

    @GetMapping("/")
    fun getInvoice(): ResponseEntity<Any> {
        println("start ----- getInvoice")
        var invoiceList = invoiceSvc?.getInvoices()
        println("end ----- getInvoice")
        return ResponseEntity.status(HttpStatus.OK).body(invoiceList);
    }

    @PostMapping("/create", produces = ["application/json"])
    fun createInvoice(@RequestBody invoiceData: InvoiceVO): ResponseEntity<Any> {
        println("start ----- createInvoice")
        invoiceSvc?.createInvoice(invoiceData)
        val returnMsgObj: MutableMap<String, Any> = HashMap()
        returnMsgObj["message"] = "Success"
        println("end ----- createInvoice")
        return ResponseEntity.status(HttpStatus.OK).body(returnMsgObj);

    }
}