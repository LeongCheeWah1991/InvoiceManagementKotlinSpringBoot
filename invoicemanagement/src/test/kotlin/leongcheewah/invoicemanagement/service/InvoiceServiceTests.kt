package leongcheewah.invoicemanagement.service

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import leongcheewah.invoicemanagement.repository.InvoiceRepository
import leongcheewah.invoicemanagement.util.InvoiceFixture
import leongcheewah.invoicemanagement.util.InvoiceVOFixture
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class InvoiceServiceTests {

    @MockK
    lateinit var invoiceRepository: InvoiceRepository

    @InjectMockKs
    lateinit var invoiceService: InvoiceService

    @Test
    fun getInvoices() {
        every { invoiceRepository.findAll() } returns listOf(InvoiceFixture.invoice())
        invoiceService.getInvoices()
        assertAll(
            {verify(exactly = 1) { invoiceRepository.findAll()} }
        )
    }

    @Test
    fun createInvoice() {
        val invoice = InvoiceFixture.invoice()
        every { invoiceRepository.save(any()) } returns invoice
        invoiceService.createInvoice(InvoiceVOFixture.invoiceVO())
        assertAll(
            {verify(exactly = 1) { invoiceRepository?.save(invoice)} }
        )
    }
}