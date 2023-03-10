package leongcheewah.invoicemanagement.entity

import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "INVOICE")
class Invoice : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    var invoiceId: Int? = null

    @Column
    var invoiceNo: String? = null

    @Column
    var invoiceDate: LocalDateTime? = null

    @Column
    var customerId: String? = null

    @Column
    var country: String? = null

    @Column
    var stockCode: String? = null

    @Column
    var description: String? = null

    @Column
    var quantity: Int? = null

    @Column
    var unitPrice: Double? = null

    constructor()

    constructor(
        invoiceId: Int?,
        invoiceNo: String?,
        invoiceDate: LocalDateTime?,
        customerId: String?,
        country: String?,
        stockCode: String?,
        description: String?,
        quantity: Int?,
        unitPrice: Double?
    ) {
        this.invoiceId = invoiceId
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