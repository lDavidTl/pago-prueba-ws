package com.pago.pgo;

import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pago.pgo.enums.EstatusOrder;
import com.pago.pgo.exception.BadRequestException;
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

    @Test
    public void testGeneratePayOrderNotFound() {
        when(orderRepository.findById(1L)).thenThrow(new BadRequestException("Error to find order"));
        Exception exception = assertThrows(BadRequestException.class, () -> {
            pagoService.genetarePayOrder(1L);
        });
        assertEquals("Error to find order", exception.getMessage());
    }

}
