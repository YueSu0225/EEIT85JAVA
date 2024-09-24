package tw.rc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "OrderDetails")
public class OrderDetails {

	@JsonIgnore//這指令:在傳遞json格式時,不會show出來
	@EmbeddedId//代表OrderDetailsKey是被嵌入來用的
	private OrderDetailsKey id;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "OrderID")//資料表的"OrderID"欄位是被join的
	@MapsId("orderId")//透過這個在複合鍵裡,去認識orderModel
	private Orders order;//該類別定義的屬性名稱為order,要被關聯的類別
	
	@JsonIgnore//這指令:在傳遞json格式時,不會show出來
	@ManyToOne
	@JoinColumn(name = "ProductID")
	@MapsId("productId")//透過這個在複合鍵裡,去認識productModel
	private Products product;
	
	@Column(name = "UnitPrice")
	private Double unitPrice;
	
	@Column(name = "Quantity")
	private Integer quantity;
	
	@Column(name = "Discount")
	private Double discount;
	
	@Transient //這指令:不要做jpa的序列化 去mapping資料庫欄位
	private String productName;//純粹java屬性
	

	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public OrderDetailsKey getId() {
		return id;
	}

	public Orders getOrder() {
		return order;
	}

	public Products getProduct() {
		return product;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setId(OrderDetailsKey id) {
		this.id = id;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	
	
	
}
