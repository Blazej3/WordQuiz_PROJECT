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

    @PostMapping(value = "/savecategory")
    public String save(Category category) {
        c_repository.save(category);
        return "redirect:/home";
    }

}
