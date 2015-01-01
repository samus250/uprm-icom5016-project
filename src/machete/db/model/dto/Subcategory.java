package machete.db.model.dto;

public class Subcategory {

  private long subcategoryId;
  private long categoryId;
  private String subcategoryName;

  public Subcategory() {

  }

  public Subcategory(long subcategoryId, long categoryId, String subcategoryName) {
    super();
    this.subcategoryId = subcategoryId;
    this.categoryId = categoryId;
    this.subcategoryName = subcategoryName;
  }

  public long getSubcategoryId() {
    return subcategoryId;
  }

  public void setSubcategoryId(long subcategoryId) {
    this.subcategoryId = subcategoryId;
  }

  public long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(long categoryId) {
    this.categoryId = categoryId;
  }

  public String getSubcategoryName() {
    return subcategoryName;
  }

  public void setSubcategoryName(String subcategoryName) {
    this.subcategoryName = subcategoryName;
  }

}
