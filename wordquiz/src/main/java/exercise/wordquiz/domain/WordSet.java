package exercise.wordquiz.domain;

import jakarta.persistence.*;





@Entity
public class WordSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String engWord;
    private String finWord;
    private String exampleSentance;
    private String difficultyLevel;
    
   
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

public WordSet(){

}

public WordSet(String engWord, String finWord, String exampleSentance, String diffiicultyLevel, Category category){
    this.engWord = engWord;
    this.finWord = finWord;
    this.exampleSentance = exampleSentance;
    this.difficultyLevel = diffiicultyLevel;
    this.category = category;
}

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getEngWord() {
    return engWord;
}

public void setEngWord(String engWord) {
    this.engWord = engWord;
}

public String getFinWord() {
    return finWord;
}

public void setFinWord(String finWord) {
    this.finWord = finWord;
}

public String getExampleSentance() {
    return exampleSentance;
}

public void setExampleSentance(String exampleSentance) {
    this.exampleSentance = exampleSentance;
}

public String getDifficultyLevel() {
    return difficultyLevel;
}

public void setDifficultyLevel(String difficultyLevel) {
    this.difficultyLevel = difficultyLevel;
}

public Category getCategory() {
    return category;
}

public void setCategory(Category category) {
    this.category = category;
}

@Override
public String toString() {
    return "WordSet [id=" + id + ", engWord=" + engWord + ", finWord=" + finWord + ", exampleSentance="
            + exampleSentance + ", difficultyLevel=" + difficultyLevel + ", category=" + category + "]";
}


}
