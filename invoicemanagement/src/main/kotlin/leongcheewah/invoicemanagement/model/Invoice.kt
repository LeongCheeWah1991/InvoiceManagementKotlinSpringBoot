package leongcheewah.invoicemanagement.model

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import java.io.Serializable
import java.util.Date;
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table


@Entity
@Table(name = "INVOICE")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
class Invoice : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private var invoiceId: Int? = null

    @Column
    private var invoiceNo: String? = null

    @Column
    private var invoiceDate: Date? = null

    @Column
    private var customerId: String? = null

    @Column
    private var country: String? = null

    @Column
    var stockCode: String? = null

    @Column
    var description: String? = null

    @Column
    var quantity: Int? = null

    @Column
    var unitPrice: Double? = null

}