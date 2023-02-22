package leongcheewah.invoicemanagement.service

import leongcheewah.invoicemanagement.entity.Invoice
import leongcheewah.invoicemanagement.model.InvoiceVO
import org.springframework.web.multipart.MultipartFile

interface IInvoiceService {
    fun getInvoices() : List<Invoice>?

    fun createInvoice(invoice: InvoiceVO)

    fun uploadInvoices(file: MultipartFile)

}