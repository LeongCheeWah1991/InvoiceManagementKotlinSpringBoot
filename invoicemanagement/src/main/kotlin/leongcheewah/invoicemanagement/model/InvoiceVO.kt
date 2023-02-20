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

}