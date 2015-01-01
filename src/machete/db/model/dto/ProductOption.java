package machete.db.model.dto;

public class ProductOption {
  private long productId;
  private long subcategoryId;
  private String optionName;
  private String optionValue;

  public ProductOption() {

  }

  public ProductOption(long productId, long subcategoryId, String optionName, String optionValue) {
    super();
    this.productId = productId;
    this.subcategoryId = subcategoryId;
    this.optionName = optionName;
    this.optionValue = optionValue;
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

  public String getOptionName() {
    return optionName;
  }

  public void setOptionName(String optionName) {
    this.optionName = optionName;
  }

  public String getOptionValue() {
    return optionValue;
  }

  public void setOptionValue(String optionValue) {
    this.optionValue = optionValue;
  }

}
