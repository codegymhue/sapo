class App {
    static DOMAIN_API = "http://localhost:8080";
    static BASE_URL_OrderItem = this.DOMAIN_API + "/api/orderItems";
}
class OrderItem {
    constructor(id,orderId,itemId,quantity,productId,price,tax) {
        this.id = id;
        this.orderId = orderId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.productId = productId;
        this.price = price;
        this.tax = tax
    }
}