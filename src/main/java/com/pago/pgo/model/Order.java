package com.pago.pgo.model;

import java.math.BigDecimal;
import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pago.pgo.enums.EstatusOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "purchase_order", schema = "bdprueba")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long orderId;

	@Column(name = "date_order")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "GMT")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateOrder;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private EstatusOrder status;

	@Column(name = "total")
	private BigDecimal total;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Date getDateOrder() {
		return dateOrder;
	}

	public void setDateOrder(Date dateOrder) {
		this.dateOrder = dateOrder;
	}

	public EstatusOrder getStatus() {
		return status;
	}

	public void setStatus(EstatusOrder status) {
		this.status = status;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}


	public Order(Long orderId, Date dateOrder, EstatusOrder status, BigDecimal total) {
		super();
		this.orderId = orderId;
		this.dateOrder = dateOrder;
		this.status = status;
		this.total = total;
	}

	public Order() {
		super();
	}

}
