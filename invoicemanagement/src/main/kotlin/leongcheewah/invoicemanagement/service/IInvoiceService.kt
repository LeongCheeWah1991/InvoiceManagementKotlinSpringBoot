package leongcheewah.invoicemanagement.service

import leongcheewah.invoicemanagement.entity.Invoice
import leongcheewah.invoicemanagement.model.InvoiceVO

interface IInvoiceService {
    fun getInvoices() : List<Invoice>;

    fun createInvoice(invoice: InvoiceVO)

}