package machete.db.model.dto;

public class SubcategoryOption {
  private long subcategoryId;
  private long subcategoryOptionId;
  private String optionName;

  public SubcategoryOption() {

  }

  public SubcategoryOption(long subcategoryId, String optionName) {
    super();
    this.subcategoryId = subcategoryId;
    this.optionName = optionName;
  }

  public long getSubcategoryOptionId() {
    return subcategoryOptionId;
  }

  public void setSubcategoryOptionId(long subcategoryOptionId) {
    this.subcategoryOptionId = subcategoryOptionId;
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

}
