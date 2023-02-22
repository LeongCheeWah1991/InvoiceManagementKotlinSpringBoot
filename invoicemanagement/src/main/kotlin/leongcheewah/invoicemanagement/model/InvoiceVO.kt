package leongcheewah.invoicemanagement.model

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import java.io.Serializable


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
class InvoiceVO : Serializable {

    var invoiceNo: String? = null

    var invoiceDate: String? = null

    var customerId: String? = null

    var country: String? = null

    var stockCode: String? = null

    var description: String? = null

    var quantity: Int? = null

    var unitPrice: Double? = null

    constructor(
        invoiceNo: String?,
        invoiceDate: String?,
        customerId: String?,
        country: String?,
        stockCode: String?,
        description: String?,
        quantity: Int?,
        unitPrice: Double?
    ) {
        this.invoiceNo = invoiceNo
        this.invoiceDate = invoiceDate
        this.customerId = customerId
        this.country = country
        this.stockCode = stockCode
        this.description = description
        this.quantity = quantity
        this.unitPrice = unitPrice
    }
}