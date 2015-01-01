package machete.db.model.dto;

public class Category {

  private long categoryId;
  private String categoryName;

  public Category() {

  }

  public Category(long catid, String name) {
    super();
    this.categoryId = catid;
    this.categoryName = name;
  }

  public long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(long catid) {
    this.categoryId = catid;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String name) {
    this.categoryName = name;
  }

}
