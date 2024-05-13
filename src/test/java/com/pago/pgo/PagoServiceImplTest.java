package com.pago.pgo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pago.pgo.enums.EstatusOrder;
import com.pago.pgo.model.Order;
import com.pago.pgo.repository.OrderRepository;
import com.pago.pgo.serviceImpl.PagoServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PagoServiceImplTest {
	
	@Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private PagoServiceImpl pagoService;

    private Order existingOrder;
    
    @BeforeEach
    public void setup() {
        existingOrder = new Order();
        existingOrder.setOrderId(1L);
        existingOrder.setStatus(EstatusOrder.PEN); 
    }

    @Test
    public void testGeneratePayOrderSuccess() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(existingOrder));
        String response = pagoService.genetarePayOrder(1L);
        verify(orderRepository).save(existingOrder);
        assertTrue(response.contains("Your payment was made successfully, authorization number: AUTH0000000001"));
    }


}
