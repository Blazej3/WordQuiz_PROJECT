package exercise.wordquiz.domain;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;
    private String categoryName;

    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<WordSet> WordSets;

    public Category() {
    }

    public Category(String categoryName) {
        super();
        this.categoryName = categoryName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<WordSet> getWordSets() {
        return WordSets;
    }

    public void setWordSets(List<WordSet> WordSets) {
        this.WordSets = WordSets;
    }

    @Override
    public String toString() {
        return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
    }

}

