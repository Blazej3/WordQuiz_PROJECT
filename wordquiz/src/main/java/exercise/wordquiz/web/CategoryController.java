package exercise.wordquiz.web;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import exercise.wordquiz.domain.CategoryRepository;
import exercise.wordquiz.domain.WordSetRepository;
import exercise.wordquiz.domain.WordSet;
import exercise.wordquiz.domain.Category;


@Controller
public class CategoryController {

    @Autowired
    private WordSetRepository w_repository;

    @Autowired
    private CategoryRepository c_repository;


    @GetMapping(value = "/addcategory")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "addcategory.html";
    }



    @GetMapping(value = "/editcategory/{id}")
    public String editWordSetForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("category", c_repository.findById(id));
        return "editcategory.html";
    }

    @GetMapping(value = "/deletecategory/{id}")
    public String deleteCategoryAndWordSets(@PathVariable("id") Long id, Model model) {
        Category category = c_repository.findById(id).orElse(null);

            List<WordSet> wordSets = category.getWordSets();
            for (WordSet wordSet : wordSets) {
                w_repository.deleteById(wordSet.getId());
            }
            c_repository.deleteById(id);
            return "redirect:/home";

    }
    
    


    @PostMapping(value = "/savecategory")
    public String save(Category category) {
        c_repository.save(category);
        return "redirect:/home";
    }
// add delete category which deletes all the sub words within it 

// add edit category name

}
