package uz.pdp.appapiweek1task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appapiweek1task2.entity.Example;
import uz.pdp.appapiweek1task2.entity.ProgrammingLanguage;
import uz.pdp.appapiweek1task2.entity.Task;
import uz.pdp.appapiweek1task2.payload.ApiResponse;
import uz.pdp.appapiweek1task2.payload.ExampleDTO;
import uz.pdp.appapiweek1task2.repository.ExampleRepository;
import uz.pdp.appapiweek1task2.repository.ProgrammingLanguageRepository;
import uz.pdp.appapiweek1task2.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProgrammingLanguageService {
    @Autowired
    ProgrammingLanguageRepository languageRepository;


    public List<ProgrammingLanguage> getLanguages(){
        return languageRepository.findAll();
    }

    public ProgrammingLanguage getLanguage(Integer id){
        Optional<ProgrammingLanguage> opLanguage = languageRepository.findById(id);
        if (opLanguage.isEmpty())
            return null;
        return opLanguage.get();
    }

    public ApiResponse addLanguage(ProgrammingLanguage programmingLanguage){
        if (languageRepository.existsByName(programmingLanguage.getName()))
            return new ApiResponse("Bunday language allaqachon bor", false);

        ProgrammingLanguage language = new ProgrammingLanguage();
        language.setName(programmingLanguage.getName());
        languageRepository.save(language);
        return new ApiResponse("Successfully added", true);
    }

    public ApiResponse editLanguage(ProgrammingLanguage programmingLanguage, Integer id){
        if (languageRepository.existsByNameAndIdNot(programmingLanguage.getName(), id))
            return new ApiResponse("Bor", false);

        ProgrammingLanguage editingLanguage = languageRepository.getById(id);
        editingLanguage.setName(programmingLanguage.getName());
        languageRepository.save(editingLanguage);
        return new ApiResponse("Successfully edited",true);
    }

    public ApiResponse deleteLanguage(Integer id){
        if (!languageRepository.existsById(id))
            return new ApiResponse("Bunday answer mavjud amas", false);
        languageRepository.deleteById(id);
        return new ApiResponse("Successfully deleted", true );
    }
}
