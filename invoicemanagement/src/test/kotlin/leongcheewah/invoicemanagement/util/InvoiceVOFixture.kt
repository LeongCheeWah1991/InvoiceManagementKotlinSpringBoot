package leongcheewah.invoicemanagement.util

import leongcheewah.invoicemanagement.model.InvoiceVO
import java.util.concurrent.ThreadLocalRandom

class InvoiceVOFixture {
    companion object {
        fun invoiceVO(
            invoiceId: Int = ThreadLocalRandom.current().nextInt(1, 10 + 1),
            invoiceNo: String = "invoiceNo",
            invoiceDate: String = "12/1/2010 8:26",
            customerId: String = "customerId",
            country: String = "country",
            stockCode: String = "stockCode",
            description: String = "description",
            quantity: Int = 1,
            unitPrice: Double = 10.0,
        ): InvoiceVO {
            return InvoiceVO(
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