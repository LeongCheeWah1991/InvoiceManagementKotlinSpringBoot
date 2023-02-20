package leongcheewah.invoicemanagement.repository

import leongcheewah.invoicemanagement.model.Invoice
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface InvoiceRepository : CrudRepository<Invoice, String>