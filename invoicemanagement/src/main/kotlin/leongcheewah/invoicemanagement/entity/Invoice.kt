package leongcheewah.invoicemanagement.entity

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
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

}