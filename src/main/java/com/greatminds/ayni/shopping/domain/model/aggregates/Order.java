package com.greatminds.ayni.shopping.domain.model.aggregates;

import com.greatminds.ayni.shopping.domain.model.entities.Sale;
import com.greatminds.ayni.shopping.domain.model.valueobjects.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

/**
 * Represents the order aggregate root.
 * The structure of the "orders" table in the database is mapped.
 */
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    private Sale sale;

    @Getter
    private Long orderedBy;

    @Getter
    private Long acceptedBy;

    @Getter
    private String description;

    @Getter
    private Long quantity;

    @Getter
    private OrderStatus status;

    @Getter
    private Date orderedDate;

    @Getter
    private Double totalPrice;

    @Getter
    private String paymentMethod;

    public Order(String description, Double totalPrice, Long quantity, String paymentMethod, Sale sale, Long orderedBy, Long acceptedBy, Date orderedDate) {
        this.description = description;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
        this.status = OrderStatus.PENDING;
        this.orderedBy = orderedBy;
        this.acceptedBy = acceptedBy;
        this.sale = sale;
    }

    public Order() {
    }

    /**
     * Update the order date
     * @param currentDate
     */
    public void updateDate(Date currentDate) {
        this.orderedDate = currentDate;
    }

    public void update(Order request) {
        if (request.description != null) {
            this.description = request.description;
        }
        if (request.quantity != null) {
            this.quantity = request.quantity;
        }
        if (request.totalPrice != null) {
            this.totalPrice = request.totalPrice;
        }
        if (request.paymentMethod != null) {
            this.paymentMethod = request.paymentMethod;
        }
    }

    /**
     * End the order
     */
    public void end() {
        this.status = OrderStatus.FINALIZED;
    }

    /**
     * Qualify the order
     */
    public void qualify() {
        this.status = OrderStatus.QUALIFIED;
    }

    /**
     * Verify if the order is pending
     */
    public boolean isPending() { return this.status == OrderStatus.PENDING; }

    /**
     * Verify if the order is qualified
     */
    public boolean isQualified() { return this.status == OrderStatus.QUALIFIED; }

    /**
     * Verify if the order is finalized
     */
    public boolean isFinalized() { return this.status == OrderStatus.FINALIZED; }

    /**
     * Get the status of the order
     */
    public String getStatus() {
        return this.status.name().toLowerCase();
    }

}
