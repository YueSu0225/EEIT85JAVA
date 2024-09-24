package tw.rc.model;

import jakarta.persistence.Embeddable;

@Embeddable //代表這個model是要可以被嵌入的
public class OrderDetailsKey {//複合主鍵
	
	private Long orderId;
	private Long productId;
	
	public Long getOrderId() {
		return orderId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
}
