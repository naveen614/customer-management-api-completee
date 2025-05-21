
package com.example.customermanagement;

import com.example.customermanagement.dto.CustomerRequest;
import com.example.customermanagement.dto.CustomerResponse;
import com.example.customermanagement.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CustomerServiceTests {

    @Autowired
    private CustomerService service;

    @Test
    public void testCreateAndRetrieveCustomer() {
        CustomerRequest request = new CustomerRequest("Jane Doe", "jane@example.com", 1200.0, LocalDateTime.now());
        CustomerResponse created = service.createCustomer(request);

        CustomerResponse retrieved = service.getCustomerById(created.getId());
        assertEquals("Jane Doe", retrieved.getName());
        assertEquals("Gold", retrieved.getTier());
    }

    @Test
    public void testUpdateCustomerTier() {
        CustomerRequest request = new CustomerRequest("Mark Smith", "mark@example.com", 500.0, LocalDateTime.now());
        CustomerResponse created = service.createCustomer(request);

        CustomerRequest update = new CustomerRequest("Mark Smith", "mark@example.com", 12000.0, LocalDateTime.now().minusMonths(2));
        CustomerResponse updated = service.updateCustomer(created.getId(), update);

        assertEquals("Platinum", updated.getTier());
    }

    @Test
    public void testDeleteCustomer() {
        CustomerRequest request = new CustomerRequest("Tom Joe", "tom@example.com", 800.0, null);
        CustomerResponse created = service.createCustomer(request);

        UUID id = created.getId();
        service.deleteCustomer(id);

        assertThrows(RuntimeException.class, () -> service.getCustomerById(id));
    }
}
