package machete.db.model.dto;

public class Product {
  private long productId;
  private long subcategoryId;
  private String productName;
  private String productDescription;
  private double productPrice;
  private String productBrand;
  private String productModel;
  private String productPhoto;

  public Product() {

  }

  public Product(long productId, long subcategoryId, String productName, String productDescription,
      double productPrice, String productBrand, String productModel, String productPhoto) {
    super();
    this.productId = productId;
    this.subcategoryId = subcategoryId;
    this.productName = productName;
    this.productDescription = productDescription;
    this.productPrice = productPrice;
    this.productBrand = productBrand;
    this.productModel = productModel;
    this.productPhoto = productPhoto;
  }

  public long getProductId() {
    return productId;
  }

  public void setProductId(long productId) {
    this.productId = productId;
  }

  public long getSubcategoryId() {
    return subcategoryId;
  }

  public void setSubcategoryId(long subcategoryId) {
    this.subcategoryId = subcategoryId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getProductDescription() {
    return productDescription;
  }

  public void setProductDescription(String productDescription) {
    this.productDescription = productDescription;
  }

  public double getProductPrice() {
    return productPrice;
  }

  public void setProductPrice(double productPrice) {
    this.productPrice = productPrice;
  }

  public String getProductBrand() {
    return productBrand;
  }

  public void setProductBrand(String productBrand) {
    this.productBrand = productBrand;
  }

  public String getProductModel() {
    return productModel;
  }

  public void setProductModel(String productModel) {
    this.productModel = productModel;
  }

  public String getProductPhoto() {
    return productPhoto;
  }

  public void setProductPhoto(String productPhoto) {
    this.productPhoto = productPhoto;
  }

  public String toString() {
    return "productId: " + productId + "\nsubcategoryId: " + subcategoryId + "\nproductName: "
        + productName + "\nproductDescription: " + productDescription + "\nproductPrice: "
        + productPrice + "\nproductBrand: " + productBrand + "\nproductModel: " + productModel
        + "\nproductPhoto: " + productPhoto;
  }

}
