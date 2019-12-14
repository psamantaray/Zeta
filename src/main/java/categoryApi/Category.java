package categoryApi;

public class Category {
    public CategoryDetails getCategories() {
        return categories;
    }

    public void setCategories(CategoryDetails categories) {
        this.categories = categories;
    }

    private CategoryDetails categories;
}
