package com.pago.pgo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pago.pgo.service.PagoService;
import com.pago.pgo.util.ResponseFormat;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/")
public class PagoController {
	
	@Autowired
	private PagoService pagoService;
	
	@PostMapping("payOrder/{orderId}")
	@Operation(summary = "Pay Order")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Pay Order: ", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", description = "Your payment could not be made") })
	public ResponseEntity<Map<String, Object>> getProducts(
			@RequestHeader(name = "Authorization") String Authorization, @PathVariable Long orderId) {
		try {
			return ResponseFormat.successEntity(pagoService.genetarePayOrder(orderId));
		} catch (Exception e) {
			return ResponseFormat.errorEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
