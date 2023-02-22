package leongcheewah.invoicemanagement.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import leongcheewah.invoicemanagement.service.InvoiceService
import leongcheewah.invoicemanagement.util.InvoiceFixture
import leongcheewah.invoicemanagement.util.InvoiceVOFixture
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(InvoiceController::class)
class InvoiceControllerTests {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockkBean
    lateinit var invoiceService: InvoiceService

    private val objectMapper = jacksonObjectMapper()

    @Test
    fun getInvoices() {
        every { invoiceService.getInvoices() } returns listOf(InvoiceFixture.invoice(), InvoiceFixture.invoice())

        mockMvc.perform(
            MockMvcRequestBuilders.get("/invoice/")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].invoiceNo", CoreMatchers.`is`("invoiceNo")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].customerId", CoreMatchers.`is`("customerId")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].country", CoreMatchers.`is`("country")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].stockCode", CoreMatchers.`is`("stockCode")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].description", CoreMatchers.`is`("description")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].quantity", CoreMatchers.`is`(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].unitPrice", CoreMatchers.`is`(10.0)))

        assertAll(
            { verify(exactly = 1) { invoiceService.getInvoices() } }
        )
    }

    @Test
    fun createAccount() {
        val invoiceVO = InvoiceVOFixture.invoiceVO()
        val invoice = InvoiceFixture.invoice();
        every { invoiceService.createInvoice(invoiceVO) } just runs

        mockMvc.perform(MockMvcRequestBuilders.post("/invoice/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(invoiceVO)))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("message", CoreMatchers.`is`("success")))

        assertAll(
            { verify(exactly = 1) { invoiceService.createInvoice(invoiceVO) } }

        )
    }

}