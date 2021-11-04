package uz.pdp.appapiweek1task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appapiweek1task2.entity.*;
import uz.pdp.appapiweek1task2.payload.AnswerDTO;
import uz.pdp.appapiweek1task2.payload.ApiResponse;
import uz.pdp.appapiweek1task2.payload.CategoryDTO;
import uz.pdp.appapiweek1task2.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProgrammingLanguageRepository languageRepository;


    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public Category getCategory(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty())
            return null;
        return optionalCategory.get();
    }

    public ApiResponse addCategory(CategoryDTO categoryDTO){
        List<ProgrammingLanguage> languages = languageRepository.findAllById(categoryDTO.getIds());
        if ((long) languages.size() == 0)
            return new ApiResponse("Bunday languages yuq", false);

        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setLanguages(languages);
        categoryRepository.save(category);
        return new ApiResponse("Successfully added", true);
    }

    public ApiResponse editCategory(CategoryDTO categoryDTO, Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        List<ProgrammingLanguage> languages = languageRepository.findAllById(categoryDTO.getIds());
        if ((long) languages.size() == 0 )
            return new ApiResponse("Bunday languages yuq", false );

        if (optionalCategory.isEmpty())
            return new ApiResponse("Bunday category mavjud emas", false);

        Category category = optionalCategory.get();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setLanguages(languages);
        categoryRepository.save(category);
        return new ApiResponse("Successfully edited",true);
    }

    public ApiResponse deleteAnswer(Integer id){
        if (!categoryRepository.existsById(id))
            return new ApiResponse("Bunday answer mavjud amas", false);
        categoryRepository.deleteById(id);
        return new ApiResponse("Successfully deleted", true );
    }
}
