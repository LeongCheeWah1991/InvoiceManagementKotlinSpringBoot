package leongcheewah.invoicemanagement.util

import leongcheewah.invoicemanagement.entity.Invoice
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.ThreadLocalRandom

class InvoiceFixture {
    companion object {
        fun invoice(
            invoiceId: Int = ThreadLocalRandom.current().nextInt(1, 10 + 1),
            invoiceNo: String = "invoiceNo",
            invoiceDate: LocalDateTime = LocalDateTime.now(),
            customerId: String = "customerId",
            country: String = "country",
            stockCode: String = "stockCode",
            description: String = "description",
            quantity: Int = 1,
            unitPrice: Double = 10.0,
        ): Invoice {
            return Invoice(
                invoiceId = invoiceId,
                invoiceNo = invoiceNo,
                invoiceDate = invoiceDate,
                customerId = customerId,
                country = country,
                stockCode = stockCode,
                description = description,
                quantity = quantity,
                unitPrice = unitPrice)
        }
    }
}